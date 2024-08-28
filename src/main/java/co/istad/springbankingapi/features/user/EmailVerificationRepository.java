package co.istad.springbankingapi.features.user;

import co.istad.springbankingapi.domain.EmailVerification;
import co.istad.springbankingapi.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EmailVerificationRepository extends JpaRepository<EmailVerification,Integer> {

    Optional<EmailVerification> findByUser(User user);
}
