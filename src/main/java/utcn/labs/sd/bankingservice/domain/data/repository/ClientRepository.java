package utcn.labs.sd.bankingservice.domain.data.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import utcn.labs.sd.bankingservice.domain.data.entity.Account;
import utcn.labs.sd.bankingservice.domain.data.entity.Bill;
import utcn.labs.sd.bankingservice.domain.data.entity.Client;

public interface ClientRepository extends JpaRepository<Client, String> {
	
	
}
