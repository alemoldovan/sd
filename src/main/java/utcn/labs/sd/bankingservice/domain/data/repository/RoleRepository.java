package utcn.labs.sd.bankingservice.domain.data.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import utcn.labs.sd.bankingservice.domain.data.entity.Client;
import utcn.labs.sd.bankingservice.domain.data.entity.Role;

public interface RoleRepository extends JpaRepository<Role, Integer> {

}
