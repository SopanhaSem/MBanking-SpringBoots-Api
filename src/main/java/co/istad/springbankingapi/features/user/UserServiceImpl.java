package co.istad.springbankingapi.features.user;

import co.istad.springbankingapi.domain.EmailVerification;
import co.istad.springbankingapi.domain.Role;
import co.istad.springbankingapi.domain.User;
import co.istad.springbankingapi.features.auth.RoleRepository;
import co.istad.springbankingapi.features.user.dto.CreateUserRequest;
import co.istad.springbankingapi.features.user.dto.UserResponse;
import co.istad.springbankingapi.features.user.dto.VerifyRequest;
import co.istad.springbankingapi.mapper.UserMapper;
import co.istad.springbankingapi.util.RandomUtil;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final RoleRepository roleRepository;
    private final JavaMailSender javaMailSender;
    private final EmailVerificationRepository emailVerificationRepository;

    @Value("${spring.mail.username}")
    private String adminMail;
    @Override
    public void createUser(CreateUserRequest createUserRequest) throws MessagingException {
        User user = userMapper.fromCreateUserMapper(createUserRequest);

        if(userRepository.existsByEmail(createUserRequest.email())){
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT,
                    "Email already existed"
            );
        }
        if (userRepository.existsByPin(createUserRequest.pin())){
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT,
                    "Pin is already existed"
            );
        }
        if(userRepository.existsByNationalCardId(createUserRequest.nationalCardId())){
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT,
                    "National Card ID is already existed"
            );
        }
        if(userRepository.existsByPhoneNumber(createUserRequest.phoneNumber())){
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT,
                    "Phone number is already existed"
            );
        }
        if(userRepository.existsByStudentCardId(createUserRequest.studentCardId())){
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT,
                    "Student Card ID  is already existed"
            );
        }

        user.setUuid(UUID.randomUUID().toString());
        user.setIsBlocked(false);
        user.setIsDeleted(false);
        user.setIsVerified(false);
        user.setIsAccountNonExpired(false);
        user.setIsCredentialExpired(false);
        user.setIsAccountNonUnlocked(false);
        List<Role> roles = new ArrayList<>();
        roles.add(roleRepository.findById(1).orElseThrow());
        roles.add(roleRepository.findById(2).orElseThrow());
        user.setRoles(roles);
        userRepository.save(user);

        EmailVerification emailVerification = new EmailVerification();
        emailVerification.setVerificationCode(RandomUtil.random6Digits());
        emailVerification.setExpiryTime(LocalTime.now().plusMinutes(1));
        emailVerification.setUser(user);
        emailVerificationRepository.save(emailVerification);

        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage);
        helper.setSubject("Email Verification - MBanking");
        helper.setFrom(adminMail);
        helper.setText(emailVerification.getVerificationCode());
        helper.setTo(createUserRequest.email());
        String htmlContent = "<!DOCTYPE html>" +
                "<html lang=\"en\">" +
                "<head>" +
                "    <meta charset=\"UTF-8\">" +
                "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">" +
                "    <title>Email Verification - MBanking</title>" +
                "    <style>" +
                "        body { font-family: Arial, sans-serif; background-color: #f4f4f4; margin: 0; padding: 0; }" +
                "        .email-container { width: 100%; max-width: 600px; margin: 0 auto; background-color: #ffffff; padding: 20px; border-radius: 8px; box-shadow: 0 0 10px rgba(0, 0, 0, 0.1); }" +
                "        .header { text-align: center; padding-bottom: 20px; }" +
                "        .header img { max-width: 150px; }" +
                "        .content { text-align: center; padding: 20px; }" +
                "        .otp-code { font-size: 24px; font-weight: bold; color: #333333; padding: 10px; background-color: #eaf0f6; border-radius: 5px; }" +
                "        .instructions { font-size: 16px; color: #666666; margin-top: 20px; }" +
                "        .footer { text-align: center; font-size: 14px; color: #999999; padding-top: 20px; }" +
                "        .footer a { color: #007bff; text-decoration: none; }" +
                "    </style>" +
                "</head>" +
                "<body>" +
                "    <div class=\"email-container\">" +
                "        <div class=\"header\">" +
                "            <img src=\"https://i.pinimg.com/736x/e2/33/f5/e233f5b0c5a358449398f202b03f063a.jpg\" alt=\"MBanking Logo\">" +
                "        </div>" +
                "        <div class=\"content\">" +
                "            <h2>Email Verification</h2>" +
                "            <p>Dear User,</p>" +
                "            <p>Thank you for registering with MBanking. To complete your registration, please use the following OTP code:</p>" +
                "            <div class=\"otp-code\">" + emailVerification.getVerificationCode() + "</div>" +
                "            <p class=\"instructions\">" +
                "                This code will expire in 1 minute. If you did not request this code, please ignore this email." +
                "            </p>" +
                "        </div>" +
                "        <div class=\"footer\">" +
                "            <p>MBanking Team</p>" +
                "            <p><a href=\"https://example.com\">Visit our website</a></p>" +
                "        </div>" +
                "    </div>" +
                "</body>" +
                "</html>";

        helper.setText(htmlContent, true);
        javaMailSender.send(mimeMessage);

    }

    @Override
    public List<UserResponse> findAll() {
        List<User> user = userRepository.findAll();
        return userMapper.toUserResponseMapper(user);
    }

    @Override
    public UserResponse findByPhoneNumber(String phoneNumber) {
        User user1 = userRepository.findByPhoneNumber(phoneNumber)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "This account phone number doesn't existed"
                ));
        return userMapper.toUserResponse(user1);
    }

    @Override
    public UserResponse findByUuid(String uuid) {
        User user = userRepository.findByUuid(uuid)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "This user uuid doesn't existed"
                ));
        return userMapper.toUserResponse(user);
    }

    @Override
    public void verify(VerifyRequest verifyRequest) {
        User user = userRepository
                .findByEmail(verifyRequest.email())
                .orElseThrow(
                        ()->new ResponseStatusException(
                                HttpStatus.NOT_FOUND,
                                "Email doesn't existed"
                        )
                );
        EmailVerification emailVerification = emailVerificationRepository
                .findByUser(user)
                .orElseThrow(
                        ()-> new ResponseStatusException(
                                HttpStatus.UNAUTHORIZED,
                                "Verification failed"
                        )
                );
        if(!emailVerification.getVerificationCode().equals(verifyRequest.verificationCode())){
            throw new ResponseStatusException(
                    HttpStatus.UNAUTHORIZED,
                    "Verification code is invalid"
            );
        }
        if(LocalTime.now().isAfter(emailVerification.getExpiryTime())){
            throw new ResponseStatusException(
                    HttpStatus.UNAUTHORIZED,
                    "Verification code is expired"
            );
        }
        user.setIsVerified(true);
        user.setIsDeleted(false);
        userRepository.save(user);
    }
}
