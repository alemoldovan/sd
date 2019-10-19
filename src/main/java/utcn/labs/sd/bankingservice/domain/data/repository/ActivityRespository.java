package utcn.labs.sd.bankingservice.domain.data.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import utcn.labs.sd.bankingservice.domain.data.entity.Activity;

public interface ActivityRespository extends JpaRepository<Activity, Integer>{
	
	Activity findByDate(String date);
}
