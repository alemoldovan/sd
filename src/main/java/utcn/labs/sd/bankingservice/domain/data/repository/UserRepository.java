package utcn.labs.sd.bankingservice.domain.data.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import utcn.labs.sd.bankingservice.domain.data.entity.Client;
import utcn.labs.sd.bankingservice.domain.data.entity.User;

public interface UserRepository extends JpaRepository<User, Integer>{
	
	User findByUsername(String username);

}
