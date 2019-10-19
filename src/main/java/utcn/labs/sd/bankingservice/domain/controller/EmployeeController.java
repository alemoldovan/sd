package utcn.labs.sd.bankingservice.domain.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import javassist.NotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import utcn.labs.sd.bankingservice.core.configuration.SwaggerTags;

import utcn.labs.sd.bankingservice.domain.dto.EmployeeDTO;
import utcn.labs.sd.bankingservice.domain.dto.UserDTO;

import utcn.labs.sd.bankingservice.domain.service.UserService;

import java.util.Collections;
import java.util.List;

@Api(tags = {SwaggerTags.BANKING_SERVICE_TAG})
@RestController
@RequestMapping("/bank/admin/employee")
@CrossOrigin
class EmployeeController {
	
	@Autowired 
	private UserService employeeService;


    @ApiOperation(value = "getAllUsers", tags = SwaggerTags.EMPLOYEE_TAG)
    @GetMapping
    public List<UserDTO> getAllUsers() {
    	System.out.println("It's in");
    	return employeeService.getAllUsers();
        
    }

    @ApiOperation(value = "findEmployeeById", tags = SwaggerTags.EMPLOYEE_TAG)
    @GetMapping(value = "/id/{employeeId}")
    public ResponseEntity<?> findUserById(@PathVariable("employeeId") Integer employeeId) {
        try {
            UserDTO employeeDto = employeeService.getUserById(employeeId);
            return new ResponseEntity<>(employeeDto, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @ApiOperation(value = "insertEmployee", tags = SwaggerTags.EMPLOYEE_TAG)
    @PostMapping(value = "/insert/{roleId}")
    public ResponseEntity<?> insertUser(@PathVariable("roleId") Integer roleId,@RequestBody UserDTO employeeDto) {
        UserDTO employeeDtoToBeInserted;
        try {
        	 System.out.println("3");
            employeeDtoToBeInserted = employeeService.createUser(employeeDto,roleId);
        } catch (Exception e) {
        	 System.out.println("4");
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(employeeDtoToBeInserted, HttpStatus.CREATED);
    }

    @ApiOperation(value = "updateEmployee", tags = SwaggerTags.EMPLOYEE_TAG)
    @PutMapping(value = "/{employeeId}")
    public ResponseEntity<?> updateUser(@PathVariable("employeeId") Integer employeeId, @RequestBody UserDTO employeeDto) {
        try {
            employeeService.updateUser(employeeId, employeeDto);
            return new ResponseEntity<>(employeeDto, HttpStatus.OK);
        } catch (NotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        } catch (Exception ex) {
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @ApiOperation(value = "deleteEmployee", tags = SwaggerTags.EMPLOYEE_TAG)
    @DeleteMapping(value = "/delete/{employeeId}")
    public ResponseEntity<?> deleteUser(@PathVariable("employeeId") Integer employeeId) {
        try {
            employeeService.deleteUser(employeeId);
            return new ResponseEntity<Integer>(HttpStatus.OK);
        } catch (NotFoundException ne) {
            return new ResponseEntity<>(ne.getMessage(), HttpStatus.NOT_FOUND);
        } catch (Exception ex) {
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

}
