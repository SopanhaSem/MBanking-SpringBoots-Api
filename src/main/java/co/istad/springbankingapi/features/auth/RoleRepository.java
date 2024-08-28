package co.istad.springbankingapi.features.auth;

import co.istad.springbankingapi.domain.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role,Integer> {
}
