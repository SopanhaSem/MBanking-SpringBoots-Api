package co.istad.springbankingapi.features.user;

import co.istad.springbankingapi.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Integer> {
    Optional<User> findByPhoneNumber(String phoneNumber);
    Optional<User> findByName(String name);
    Optional<User> findByUuid(String uuid);

    Optional<User> findByEmail(String email);
    Boolean existsByEmail(String email);
    Boolean existsByPin(String pin);
    Boolean existsByNationalCardId( String nationalCardId);
    Boolean existsByPhoneNumber(String phoneNumber);
    Boolean existsByStudentCardId(String studentCardId);

}
