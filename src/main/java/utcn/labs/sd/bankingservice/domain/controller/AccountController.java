package utcn.labs.sd.bankingservice.domain.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import utcn.labs.sd.bankingservice.core.configuration.SwaggerTags;
import utcn.labs.sd.bankingservice.domain.data.entity.Bill;
import utcn.labs.sd.bankingservice.domain.data.entity.User;
import utcn.labs.sd.bankingservice.domain.data.entity.enums.ActivityType;
import utcn.labs.sd.bankingservice.domain.dto.AccountDTO;
import utcn.labs.sd.bankingservice.domain.dto.ActivityDTO;
import utcn.labs.sd.bankingservice.domain.dto.BillDTO;
import utcn.labs.sd.bankingservice.domain.dto.ClientDTO;
import utcn.labs.sd.bankingservice.domain.service.AccountService;
import utcn.labs.sd.bankingservice.domain.service.ActivityService;
import utcn.labs.sd.bankingservice.domain.service.BillService;
import utcn.labs.sd.bankingservice.domain.service.UserService;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Api(tags = {SwaggerTags.BANKING_SERVICE_TAG})
@RestController
@RequestMapping("/bank/employee/account")
@CrossOrigin
class AccountController {

    @Autowired
    private AccountService accountService;
    
    @Autowired
    private UserService userService;
    
    @Autowired
    private ActivityService activityService;
    
    @Autowired
    private BillService billService;

    @ApiOperation(value = "getAllAccounts", tags = SwaggerTags.ACCOUNT_TAG)
    @RequestMapping(method = RequestMethod.GET)
    public List<AccountDTO> getAllAccounts() {
    	System.out.println("one");
        return accountService.getAllAccounts();
    }

    @ApiOperation(value = "findAccountById", tags = SwaggerTags.ACCOUNT_TAG)
    @GetMapping(value = "/id/{accountId}")
    public ResponseEntity<?> findAccountById(@PathVariable("accountId") Integer accountId) {
        try {
            AccountDTO accountDto = accountService.getAccountById(accountId);
            return new ResponseEntity<>(accountDto, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
    
    @ApiOperation(value = "findBillsByAccount", tags = SwaggerTags.ACCOUNT_TAG)
    @RequestMapping(value = "/accountId/{accountId}", method = RequestMethod.GET)
    public List<BillDTO> findBillsByAccounts(@PathVariable("accountId") Integer accountId){
    	
    	try {
			return accountService.getBillsByAccounts(accountId);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return null;
    }

    @ApiOperation(value = "insertAccount", tags = SwaggerTags.ACCOUNT_TAG)
    @PostMapping(value = "/insert")
    public ResponseEntity<?> insertAccount(@RequestBody AccountDTO accountDto) {
        AccountDTO accountDtoToBeInserted;
        try {
        	 System.out.println("3");
            accountDtoToBeInserted = accountService.createAccount(accountDto);
        } catch (Exception e) {
        	 System.out.println("4");
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(accountDtoToBeInserted, HttpStatus.CREATED);
    }

    @ApiOperation(value = "updateAccount", tags = SwaggerTags.ACCOUNT_TAG)
    @PutMapping(value = "/{accountId}")
    public ResponseEntity<?> updateAccount(@PathVariable("accountId") Integer accountId, @RequestBody AccountDTO accountDto) {
        try {
            accountService.updateAccount(accountId, accountDto);
            return new ResponseEntity<>(accountDto, HttpStatus.OK);
        } catch (NotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        } catch (Exception ex) {
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @ApiOperation(value = "deleteAccount", tags = SwaggerTags.ACCOUNT_TAG)
    @DeleteMapping(value = "delete/{accountId}")
    public ResponseEntity<?> deleteAccount(@PathVariable("accountId") Integer accountId) {
        try {
            accountService.deleteAccount(accountId);
            return new ResponseEntity<Integer>(HttpStatus.OK);
        } catch (NotFoundException ne) {
            return new ResponseEntity<>(ne.getMessage(), HttpStatus.NOT_FOUND);
        } catch (Exception ex) {
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
    
    
    
    @ApiOperation(value = "transferMoney", tags = SwaggerTags.ACCOUNT_TAG)
    @PutMapping(value = "/{accountId1}/{accountId2}/{amount}")
    public ResponseEntity<?> transferMoney(@PathVariable("accountId1") Integer accountId1,@PathVariable("accountId2") Integer accountId2,
    		@PathVariable("amount") float amount) {
        try {
        	AccountDTO accountDto = accountService.transferMoney(accountId1, accountId2, amount);
        	Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        	
        	String username = authentication.getName();
        	
        	Date date = new Date();
            String dateString = new SimpleDateFormat("yyyy-MM-dd").format(date);
            
            String description = "Account " + accountId1 + " has transfered to account " + accountId2 + "an amount of " + amount;
        	
            ActivityDTO activityDto = new ActivityDTO(0,username, ActivityType.TRANSFER,  dateString,description);
            activityService.createActivity(activityDto);
            return new ResponseEntity<>(accountDto, HttpStatus.OK);
        } catch (NotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        } catch (Exception ex) {
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
    
    
    
    @ApiOperation(value = "addBillToAccount", tags = SwaggerTags.ACCOUNT_TAG)
    @PostMapping(value = "/bill/{accountId}")
    public ResponseEntity<?> addBillToAccount(@PathVariable("accountId") Integer accountId, @RequestBody BillDTO billDto) {
        try {
            accountService.addBillToAccount(accountId, billDto);
            AccountDTO accountDto = accountService.getAccountById(accountId);
            return new ResponseEntity<>(accountDto, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @ApiOperation(value = "deleteBillFromAccount", tags = SwaggerTags.ACCOUNT_TAG)
    @DeleteMapping(value = "/bill/{accountId}/{billId}")
    public ResponseEntity<?> deleteBillFromAccount(@PathVariable("accountId") Integer accountId, @PathVariable("billId") Integer billId) {
        try {
            accountService.deleteBillFromAccount(accountId, billId);
            return new ResponseEntity<>(accountService.getAccountById(accountId), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
    
    
    @ApiOperation(value = "payBill", tags = SwaggerTags.ACCOUNT_TAG)
    @PutMapping(value = "/{accountId}/{billId}")
    public ResponseEntity<?> payBill(@PathVariable("accountId") Integer accountId,@PathVariable("billId") Integer billId) {
        try {
        	System.out.println("ACCOUNTCONTROLLER - pay1");
        	AccountDTO accountDto = accountService.payBill(accountId, billId);
        	System.out.println("ACCOUNTCONTROLLER - pay11");
        	BillDTO billDto = billService.getBillById(billId);
        	System.out.println("ACCOUNTCONTROLLER - pay2");
        	Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        	System.out.println("ACCOUNTCONTROLLER - aut1");
            String username = authentication.getName();
            System.out.println(username);
            User user = userService.getUserByUsername(username);
            String description = "Account " + accountId + " has paid "+ billDto.getAmount() + " LEI for the bill " + billId;
            
            //Timestamp currentTimestamp = new Timestamp(Instant.now().toEpochMilli());
            Date date = new Date();
            String dateString = new SimpleDateFormat("yyyy-MM-dd").format(date);
            System.out.println("ACCOUNTCONTROLLER - activitydto");
            ActivityDTO activityDto = new ActivityDTO(0,user.getUsername(), ActivityType.PAYMENT, dateString, description);
            System.out.println(username);
            
            activityService.createActivity(activityDto);
        	return new ResponseEntity<>(accountDto, HttpStatus.OK);
        } catch (NotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        } catch (Exception ex) {
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
    
    
}
