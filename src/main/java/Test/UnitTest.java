package Test;

import java.util.Collections;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import utcn.labs.sd.bankingservice.domain.data.converter.AccountConverter;
import utcn.labs.sd.bankingservice.domain.data.converter.ActivityConverter;
import utcn.labs.sd.bankingservice.domain.data.converter.BillConverter;
import utcn.labs.sd.bankingservice.domain.data.converter.ClientConverter;
import utcn.labs.sd.bankingservice.domain.data.converter.UserConverter;
import utcn.labs.sd.bankingservice.domain.data.entity.Account;
import utcn.labs.sd.bankingservice.domain.data.entity.Activity;
import utcn.labs.sd.bankingservice.domain.data.entity.Bill;
import utcn.labs.sd.bankingservice.domain.data.entity.Client;
import utcn.labs.sd.bankingservice.domain.data.entity.Role;
import utcn.labs.sd.bankingservice.domain.data.entity.User;
import utcn.labs.sd.bankingservice.domain.data.entity.enums.AccountType;
import utcn.labs.sd.bankingservice.domain.data.entity.enums.ActivityType;
import utcn.labs.sd.bankingservice.domain.data.entity.enums.BillStatusType;
import utcn.labs.sd.bankingservice.domain.data.entity.enums.RoleType;
import utcn.labs.sd.bankingservice.domain.data.repository.AccountRepository;
import utcn.labs.sd.bankingservice.domain.data.repository.ActivityRespository;
import utcn.labs.sd.bankingservice.domain.data.repository.BillRepository;
import utcn.labs.sd.bankingservice.domain.data.repository.ClientRepository;
import utcn.labs.sd.bankingservice.domain.data.repository.RoleRepository;
import utcn.labs.sd.bankingservice.domain.data.repository.UserRepository;
import utcn.labs.sd.bankingservice.domain.dto.AccountDTO;
import utcn.labs.sd.bankingservice.domain.dto.ActivityDTO;
import utcn.labs.sd.bankingservice.domain.dto.BillDTO;
import utcn.labs.sd.bankingservice.domain.dto.ClientDTO;
import utcn.labs.sd.bankingservice.domain.dto.UserDTO;
import utcn.labs.sd.bankingservice.domain.service.AccountService;
import utcn.labs.sd.bankingservice.domain.service.ActivityService;
import utcn.labs.sd.bankingservice.domain.service.BillService;
import utcn.labs.sd.bankingservice.domain.service.ClientService;
import utcn.labs.sd.bankingservice.domain.service.UserService;

import java.util.Optional;

@RunWith(MockitoJUnitRunner.Silent.class)

@WebMvcTest
public class UnitTest {
	
	@InjectMocks 
	private BillService billService;
	
	@Mock
	private BillRepository billRepository;
	
	@Mock
	private ClientRepository clientRepository;
	
	@Mock
	private UserRepository userRepository;
	
	@Mock
	private ActivityRespository activityRepository;
	
	@Mock
	private RoleRepository roleRepository;
	
	@Mock
	private AccountRepository accountRepository;
	
	@Mock
	private AccountService accountService;
	
	@InjectMocks
	private AccountService accountService1;
	
	@InjectMocks
	private ClientService clientService;
	
	@InjectMocks
	private UserService userService;
	
	@InjectMocks
	private ActivityService activityService;

	
	@Test
	public void findBillByIdTest() throws Exception{	
	
		Bill bill = new Bill(50, "EON Gaz", 0,2, /*BillStatusType.*/"UNPAID");
		
		when(billRepository.findById(anyInt())).thenReturn(Optional.of(bill));
		
		try {
		BillDTO billDTO = billService.getBillById(1);
		
		verify(billRepository, times(1)).findById(anyInt());
		
		Assert.assertNotNull(billDTO);
		Assert.assertEquals(billDTO.getAmount(), bill.getAmount(), 0);
		}catch (NotFoundException e) {
            e.printStackTrace();
		}
		
				
	}
	
	
	@Test
	public void findClientByIdTest() throws Exception{
		
		Client client = new Client("1234567891012", "Mircea", "Bravo", "12345", "adresa", "mircea@yahoo.com", null);
		
		when(clientRepository.findById("1234567891012")).thenReturn(Optional.of(client));
		
		try {
		ClientDTO clientDto = clientService.getClientById(client.getSsn());
		verify(clientRepository, times(1)).findById(client.getSsn());
		Assert.assertNotNull(clientDto);
		Assert.assertEquals(clientDto.getFirstname(),client.getFirstname());
		Assert.assertEquals(clientDto.getLastname(),client.getLastname());
		Assert.assertEquals(clientDto.getIdentityCardNumber(),client.getIdentityCardNumber());
		Assert.assertEquals(clientDto.getSsn(),client.getSsn());
		Assert.assertEquals(clientDto.getAddress(), client.getAddress());
		Assert.assertEquals(clientDto.getEmail(), client.getEmail());
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	
	
	@Test
	public void findUserByIdTest() throws Exception{
		
		User user = new User(10, "username", "password", "name", "email@yahoo.com");
		
		when(userRepository.findById(anyInt())).thenReturn(Optional.of(user));
		
		try {
		UserDTO userDto = userService.getUserById(user.getUserId());
		verify(userRepository, times(1)).findById(user.getUserId());
		Assert.assertNotNull(userDto);
		Assert.assertEquals(userDto.getUsername(),user.getUsername());
		Assert.assertEquals(userDto.getPassword(),user.getPassword());
		Assert.assertEquals(userDto.getName(),user.getName());
		Assert.assertEquals(userDto.getEmail(),user.getEmail());
		
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	
	
	@Test
	public void findAccountByIdTest() throws Exception{
		
		Account account = new Account(10, AccountType.CREDIT, "2019-31-03", 28000);
		
		when(accountRepository.findById(anyInt())).thenReturn(Optional.of(account));
		
		try {
		AccountDTO accountDto = accountService1.getAccountById(account.getAccountId());
		verify(accountRepository, times(1)).findById(account.getAccountId());
		Assert.assertNotNull(accountDto);
		Assert.assertEquals(accountDto.getAccountType(),account.getAccountType());
		Assert.assertEquals(accountDto.getCreationDate(),account.getCreationDate());
		Assert.assertEquals(accountDto.getBalance(),account.getBalance(),0);
		Assert.assertEquals(accountDto.getAccountId(),account.getAccountId());
		
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	
	
	@Test
	public void findActivityByIdTest() throws Exception{
		
		Activity activity = new Activity(10, "username", ActivityType.PAYMENT, "2019-03-31", "description");
		
		
		when(activityRepository.findById(anyInt())).thenReturn(Optional.of(activity));
		
		try {
		ActivityDTO activityDto = activityService.getActivityById(activity.getIdActivity());
		verify(activityRepository, times(1)).findById(activity.getIdActivity());
		Assert.assertNotNull(activityDto);
		Assert.assertEquals(activityDto.getIdActivity(),activity.getIdActivity());
		Assert.assertEquals(activityDto.getUsername(),activity.getUsername());
		Assert.assertEquals(activityDto.getActivityName(),activity.getActivityName());
		Assert.assertEquals(activityDto.getDate(),activity.getDate());
		
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	
	
	@Test
	public void addClientTest() throws Exception {
		
		Client client = new Client("1234567891012", "Mircea", "Bravo", "12345", "adresa", "mircea@yahoo.com", null);

        ClientDTO clientDTO = ClientConverter.toDto(client);

        when(clientRepository.save(any())).thenReturn(client);
        when(clientRepository.findById("1234567891012")).thenReturn(Optional.empty());

        ClientDTO addedClientDto = clientService.createClient(clientDTO);

        verify(clientRepository, times(1)).save(any());

        Assert.assertNotNull(addedClientDto);
        Assert.assertEquals(clientDTO.getSsn(), addedClientDto.getSsn());
        Assert.assertEquals(clientDTO.getFirstname(), addedClientDto.getFirstname());
        Assert.assertEquals(clientDTO.getLastname(), addedClientDto.getLastname());
    }
	
	
	@Test
	public void addAccountTest() throws Exception {
		
		Account account = new Account(10, AccountType.CREDIT, "2019-31-03", 28000);

        AccountDTO accountDto = AccountConverter.toDto(account);

        when(accountRepository.save(any())).thenReturn(account);
       // when(accountRepository.findById(10)).thenReturn(Optional.empty());

        AccountDTO addedAccountDto = accountService1.createAccount(accountDto);

        verify(accountRepository, times(1)).save(any());

        Assert.assertNotNull(addedAccountDto);
        Assert.assertEquals(accountDto.getAccountType(),addedAccountDto.getAccountType());
		Assert.assertEquals(accountDto.getCreationDate(),addedAccountDto.getCreationDate());
		Assert.assertEquals(accountDto.getBalance(),addedAccountDto.getBalance(),0);
		Assert.assertEquals(accountDto.getAccountId(),addedAccountDto.getAccountId());
        
        
    }
	
	
	@Test
	public void addUserTest() throws Exception {
		
		User user = new User(10, "username", "password", "name", "email@yahoo.com");
		Role role = new Role();
		role.setRole(RoleType.ADMIN);
		System.out.println("role din test" + role);
		//user.setRole(role);

        UserDTO userDto = UserConverter.toDto(user);
        System.out.println(user);
        when(userRepository.save(any())).thenReturn(user);
      
        when(userRepository.findById(10)).thenReturn(Optional.empty());

        UserDTO addedUserDto = userService.createUser(userDto,0);

        verify(userRepository, times(1)).save(any());

        Assert.assertNotNull(addedUserDto);
        Assert.assertEquals(userDto.getUsername(),addedUserDto.getUsername());
		Assert.assertEquals(userDto.getPassword(),addedUserDto.getPassword());
		Assert.assertEquals(userDto.getName(),addedUserDto.getName());
		Assert.assertEquals(userDto.getEmail(),addedUserDto.getEmail());
		Assert.assertEquals(userDto.getRole(),addedUserDto.getRole());
        
        
    }
	
	
	@Test
	public void addBillTest() throws Exception {
		
		Bill bill = new Bill(50, "EON Gaz", 50, 2, /*BillStatusType.*/"UNPAID");
	
        BillDTO billDto = BillConverter.toDto(bill);
      
        when(billRepository.save(any())).thenReturn(bill);
      
        when(billRepository.findById(10)).thenReturn(Optional.empty());

        BillDTO addedBillDto = billService.createBill(billDto);

        verify(billRepository, times(1)).save(any());

        Assert.assertNotNull(addedBillDto);
        Assert.assertEquals(billDto.getAmount(),addedBillDto.getAmount(),0);
		Assert.assertEquals(billDto.getDescription(),addedBillDto.getDescription());
		Assert.assertEquals(billDto.getBillId(),addedBillDto.getBillId());
	
        
        
    }
	
	@Test
	public void addActivityTest() throws Exception {
		
		Activity activity = new Activity(10, "username", ActivityType.PAYMENT, "2019-03-31", "description");
	
        ActivityDTO activityDto = ActivityConverter.toDto(activity);
      
        when(activityRepository.save(any())).thenReturn(activity);
      
        when(activityRepository.findById(10)).thenReturn(Optional.empty());

        ActivityDTO addedActivityDto = activityService.createActivity(activityDto);

        verify(activityRepository, times(1)).save(any());

        Assert.assertNotNull(addedActivityDto);
        Assert.assertEquals(activityDto.getIdActivity(),addedActivityDto.getIdActivity());
		Assert.assertEquals(activityDto.getUsername(),addedActivityDto.getUsername());
		Assert.assertEquals(activityDto.getActivityName(),addedActivityDto.getActivityName());
		Assert.assertEquals(activityDto.getDate(),addedActivityDto.getDate());
	
        
        
    }
	
	@Test
    public void deleteClientTest() throws Exception {
		Client client = new Client("1234567891012", "Mircea", "Bravo", "12345", "adresa", "mircea@yahoo.com", null);

        when(clientRepository.findById(any())).thenReturn(Optional.of(client));
        doNothing().when(clientRepository).delete(any());

        clientService.deleteClient(client.getSsn());

        verify(clientRepository, times(1)).findById(client.getSsn());
        verify(clientRepository, times(1)).delete(any());
    }
	
	
	@Test
    public void deleteUserTest() throws Exception {
		User user = new User(10, "username", "password", "name", "email@yahoo.com");

        when(userRepository.findById(any())).thenReturn(Optional.of(user));
        doNothing().when(userRepository).delete(any());

        userService.deleteUser(user.getUserId());

        verify(userRepository, times(1)).findById(user.getUserId());
        verify(userRepository, times(1)).delete(any());
    }
	
	
	@Test
    public void deleteAccountTest() throws Exception {
		Account account = new Account(10, AccountType.CREDIT, "2019-31-03", 28000);

        when(accountRepository.findById(any())).thenReturn(Optional.of(account));
        doNothing().when(accountRepository).delete(any());

        accountService1.deleteAccount(account.getAccountId());

        verify(accountRepository, times(1)).findById(account.getAccountId());
        verify(accountRepository, times(1)).delete(any());
    }
	
	@Test
    public void deleteBillTest() throws Exception {
		Bill bill = new Bill(50, "EON Gaz", 0,2,/*BillStatusType.*/"PAID");
		
        when(billRepository.findById(any())).thenReturn(Optional.of(bill));
        doNothing().when(billRepository).delete(any());

        billService.deleteBill(bill.getBillId());

        verify(billRepository, times(1)).findById(bill.getBillId());
        verify(billRepository, times(1)).delete(any());
    }
	
	@Test
    public void deleteActivityTest() throws Exception {
		Activity activity = new Activity(10, "username", ActivityType.PAYMENT, "2019-03-31", "description");

		
        when(activityRepository.findById(any())).thenReturn(Optional.of(activity));
        doNothing().when(activityRepository).delete(any());

        activityService.deleteActivity(activity.getIdActivity());

        verify(activityRepository, times(1)).findById(activity.getIdActivity());
        verify(activityRepository, times(1)).delete(any());
    }
}
