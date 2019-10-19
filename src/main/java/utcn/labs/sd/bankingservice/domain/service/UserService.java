
package utcn.labs.sd.bankingservice.domain.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javassist.NotFoundException;
import utcn.labs.sd.bankingservice.domain.data.converter.UserConverter;
import utcn.labs.sd.bankingservice.domain.data.entity.Role;
import utcn.labs.sd.bankingservice.domain.data.entity.User;
import utcn.labs.sd.bankingservice.domain.data.repository.RoleRepository;
import utcn.labs.sd.bankingservice.domain.data.repository.UserRepository;
import utcn.labs.sd.bankingservice.domain.dto.UserDTO;


@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private RoleRepository roleRepository;

	public List<UserDTO> getAllUsers(){
		return UserConverter.toDto(userRepository.findAll());
	}
	
	public UserDTO getUserById(Integer userId) throws Exception{
		User user = userRepository.findById(userId).orElse(null);
		if(user == null) throw new NotFoundException("No user found with the specified id");
		
		return UserConverter.toDto(user);
	}
	
	public User getUserByUsername(String username) throws Exception{
		User user = userRepository.findByUsername(username);
		if(user == null) throw new NotFoundException("No user found with the specified username");
		
		return user;
	}
	
	public UserDTO createUser(UserDTO userDto, Integer idRole) throws Exception{
		User user = new User(userDto.getUserId(),userDto.getUsername(),userDto.getPassword(), userDto.getName(), userDto.getEmail());
		Role role = roleRepository.findById(idRole).orElse(null);
		
		user.setRole(role);
		User possibleAlreadyExistingUser = userRepository.findById(userDto.getUserId()).orElse(null);
		User possibleAlreadyExistingUsername = userRepository.findByUsername(userDto.getUsername());
		if(possibleAlreadyExistingUser == null && possibleAlreadyExistingUsername==null) {
			User newUser = userRepository.save(user);
			return UserConverter.toDto(newUser);
		}else {
			throw new Exception("User already exists!");
		}
	}
	
	public UserDTO updateUser(Integer userId, UserDTO userDto) throws Exception{
		User user = userRepository.findById(userId).orElse(null);
		if(user == null) {
			throw new NotFoundException("User does not exist");
		}
		user.setUsername(userDto.getUsername());
		user.setPassword(userDto.getPassword());
		user.setRole(userDto.getRole());
		return UserConverter.toDto(userRepository.save(user));
	}
	
	 public void deleteUser(Integer userId) throws Exception {
		 User user = userRepository.findById(userId).orElse(null);
	        if (user == null) {
	            throw new NotFoundException("No user with that id");
	        }
	       
	        userRepository.delete(user);
	    }
	
	
}



/*package utcn.labs.sd.bankingservice.domain.service;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javassist.NotFoundException;
import utcn.labs.sd.bankingservice.domain.data.converter.AccountConverter;
import utcn.labs.sd.bankingservice.domain.data.converter.UserConverter;
import utcn.labs.sd.bankingservice.domain.data.entity.Account;
import utcn.labs.sd.bankingservice.domain.data.entity.Role;
import utcn.labs.sd.bankingservice.domain.data.entity.User;
import utcn.labs.sd.bankingservice.domain.data.repository.RoleRepository;
import utcn.labs.sd.bankingservice.domain.data.repository.UserRepository;
import utcn.labs.sd.bankingservice.domain.dto.AccountDTO;
import utcn.labs.sd.bankingservice.domain.dto.EmployeeDTO;
import utcn.labs.sd.bankingservice.domain.dto.UserDTO;

public class EmployeeService {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private RoleRepository roleRepository;
	
	public List<UserDTO> getAllUsers(){
		return UserConverter.toDto(userRepository.findAll());
	}

	public UserDTO getUserById(Integer employeeId) throws Exception {
		User user = userRepository.findById(employeeId).orElse(null);
		if(user == null) throw new NotFoundException("No employee found with that employeeId");
        return UserConverter.toDto(user);
	}
	
	public UserDTO createUser(UserDTO employeeDto, Integer roleId) throws Exception {
       
		User user = new User(employeeDto.getUserId(), employeeDto.getUsername(), employeeDto.getPassword(), employeeDto.getName(), employeeDto.getEmail());
		Role role  = roleRepository.findById(roleId).orElse(null);
		user.setRole(role);
		User newUser = userRepository.save(user);
        return UserConverter.toDto(newUser);
    }
	
	public UserDTO updateUser(Integer employeeId, UserDTO userDto) throws Exception {
        User user = userRepository.findById(employeeId).orElse(null);
        if (user == null) {
            throw new NotFoundException("No user found with that id");
        }
        user.setName(userDto.getName());
        user.setUsername(userDto.getUsername());
        user.setPassword(userDto.getPassword());
        user.setEmail(userDto.getEmail());
        return UserConverter.toDto(userRepository.save(user));
    }


    public void deleteUser(Integer userId) throws Exception {
        User user = userRepository.findById(userId).orElse(null);
        if (user == null) {
            throw new NotFoundException("No employee that userId");
        }
        userRepository.delete(user);
    }
}
*/