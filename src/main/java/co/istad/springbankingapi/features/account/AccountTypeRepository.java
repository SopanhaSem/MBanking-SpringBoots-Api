package co.istad.springbankingapi.features.account;

import co.istad.springbankingapi.domain.AccountType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AccountTypeRepository extends JpaRepository<AccountType,Integer> {

    Optional<AccountType> findByAlias(String alias);

}
