package co.istad.springbankingapi.features.account;

import co.istad.springbankingapi.domain.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccountRepository extends JpaRepository<Account,Integer> {
        Boolean existsByAccNo(String accNo);
        Optional<Account> findByAccNo(String accNo);
}
