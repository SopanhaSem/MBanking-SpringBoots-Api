package co.istad.springbankingapi.init;

import co.istad.springbankingapi.domain.AccountType;
import co.istad.springbankingapi.domain.CardType;
import co.istad.springbankingapi.domain.Role;
import co.istad.springbankingapi.domain.User;
import co.istad.springbankingapi.features.account.AccountTypeRepository;
import co.istad.springbankingapi.features.auth.RoleRepository;
import co.istad.springbankingapi.features.card.CardTypeRepository;
import co.istad.springbankingapi.features.user.UserRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class DataInit {

    private final CardTypeRepository cardTypeRepository;
    private final AccountTypeRepository accountTypeRepository;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;


    @PostConstruct
    void init(){
        initAccountTypeData();
        initTypeCardData();
        initRoleData();
        initUserData();
    }
    private void initRoleData() {
        // ADMIN, MANAGER, STAFF, CUSTOMER, USER
        List<Role> roles = new ArrayList<>();
        roles.add(Role.builder().name("USER").build());
        roles.add(Role.builder().name("CUSTOMER").build());
        roles.add(Role.builder().name("STAFF").build());
        roles.add(Role.builder().name("MANAGER").build());
        roles.add(Role.builder().name("ADMIN").build());
        roleRepository.saveAll(roles);
    }
    private void initUserData(){
        User user = new User();
        user.setUuid(UUID.randomUUID().toString());
        user.setEmail("nha@gmail.com");
        user.setNationalCardId("0326596456");
        user.setName("Alber");
        user.setDob(LocalDate.now());
        user.setGender("Male");
        user.setPhoneNumber("021558655");
        user.setProfileImage("profileImage.png");
        user.setStudentCardId("0258933578745");
        user.setPosition("Developer");
        user.setCompanyName("CSTAD");
        user.setEmployeeType("Employee");
        user.setCityOrProvince("city");
        user.setKhanOrDistrict("Sen sok");
        user.setSongKatOrCommune("Toul Kork");
        user.setStreet("st.26545");
        user.setVillage("KORKOKA");
        user.setMonthlyIncomeRange("1000-1500$");
        user.setMainSrcIncome("1000$");
        user.setPin("4566");
        user.setPassword(passwordEncoder.encode("qwer"));
        user.setIsDeleted(false);
        user.setIsBlocked(false);
        user.setIsVerified(false);
        user.setIsAccountNonExpired(true);
        user.setIsCredentialExpired(true);
        user.setIsAccountNonUnlocked(true);
        List<Role> roles = new ArrayList<>();
        roles.add(roleRepository.findById(1).orElseThrow());
        roles.add(roleRepository.findById(4).orElseThrow());
        roles.add(roleRepository.findById(5).orElseThrow());
        user.setRoles(roles);
        userRepository.saveAll(List.of(user));
    }

    private void initAccountTypeData(){
        AccountType saving = new AccountType();
        saving.setName("Saving Account");
        saving.setAlias("saving-account");
        saving.setDescription("Saving Account");
        saving.setIsDeleted(false);

        AccountType payroll = new AccountType();
        payroll.setName("Payroll Account");
        payroll.setAlias("payroll-account");
        payroll.setDescription("Payroll Account");
        payroll.setIsDeleted(false);

        AccountType current = new AccountType();
        current.setName("Current Account");
        current.setAlias("current-account");
        current.setDescription("Current Account");
        current.setIsDeleted(true);

        accountTypeRepository.saveAll(List.of(saving,payroll,current));
    }

    private void initTypeCardData(){
        CardType visa = new CardType();
        visa.setName("Visa");
        visa.setAlias("visa");
        visa.setIsDeleted(false);
        CardType mastercard = new CardType();
        mastercard.setName("Mastercard");
        mastercard.setAlias("mastercard");
        mastercard.setIsDeleted(false);
        cardTypeRepository.saveAll(List.of(visa,mastercard));
    }

}
