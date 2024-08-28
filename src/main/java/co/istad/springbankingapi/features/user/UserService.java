package co.istad.springbankingapi.features.user;

import co.istad.springbankingapi.domain.User;
import co.istad.springbankingapi.features.user.dto.CreateUserRequest;
import co.istad.springbankingapi.features.user.dto.UserResponse;
import co.istad.springbankingapi.features.user.dto.VerifyRequest;
import jakarta.mail.MessagingException;

import java.util.List;

public interface UserService {
    void createUser(CreateUserRequest createUserRequest) throws MessagingException;
    List<UserResponse> findAll();
    UserResponse findByPhoneNumber(String phoneNumber);

    UserResponse findByUuid(String uuid);
    void verify(VerifyRequest verifyRequest);
}
