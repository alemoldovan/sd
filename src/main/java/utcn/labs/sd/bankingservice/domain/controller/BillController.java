package utcn.labs.sd.bankingservice.domain.controller;

import java.util.List;

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
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import javassist.NotFoundException;
import utcn.labs.sd.bankingservice.core.configuration.SwaggerTags;
import utcn.labs.sd.bankingservice.domain.dto.AccountDTO;
import utcn.labs.sd.bankingservice.domain.dto.BillDTO;
import utcn.labs.sd.bankingservice.domain.service.BillService;

@Api(tags = {SwaggerTags.BANKING_SERVICE_TAG})
@RestController
@RequestMapping("/bank/employee/bill")
@CrossOrigin
public class BillController {
	
	@Autowired
	private BillService billService;
	
	
	@ApiOperation(value = "getAllBills", tags = SwaggerTags.BILL_TAG)
    @RequestMapping(method = RequestMethod.GET)
    public List<BillDTO> getAllBills() {

        return billService.getAllBills();
    }

    @ApiOperation(value = "findBillById", tags = SwaggerTags.BILL_TAG)
    @GetMapping(value = "/id/{billId}")
    public ResponseEntity<?> findBillById(@PathVariable("billId") Integer billId) {
        try {
            BillDTO billDto = billService.getBillById(billId);
            return new ResponseEntity<>(billDto, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    

    
    
    @ApiOperation(value = "insertBill", tags = SwaggerTags.BILL_TAG)
    @PostMapping(value = "/insert")
    public ResponseEntity<?> insertBill(@RequestBody BillDTO billDto) {
        BillDTO billDtoToBeInserted;
        try {
        	 System.out.println("3");
            billDtoToBeInserted = billService.createBill(billDto);
        } catch (Exception e) {
        	 System.out.println("4");
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(billDtoToBeInserted, HttpStatus.CREATED);
    }

    @ApiOperation(value = "updateBill", tags = SwaggerTags.BILL_TAG)
    @PutMapping(value = "/{billId}")
    public ResponseEntity<?> updateAccount(@PathVariable("billId") Integer billId, @RequestBody BillDTO billDto) {
        try {
            billService.updateBill(billId, billDto);
            return new ResponseEntity<>(billDto, HttpStatus.OK);
        } catch (NotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        } catch (Exception ex) {
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @ApiOperation(value = "deleteBill", tags = SwaggerTags.BILL_TAG)
    @DeleteMapping(value = "/delete/{billId}")
    public ResponseEntity<?> deleteBill(@PathVariable("billId") Integer billId) {
        try {
            billService.deleteBill(billId);
            return new ResponseEntity<Integer>(HttpStatus.OK);
        } catch (NotFoundException ne) {
            return new ResponseEntity<>(ne.getMessage(), HttpStatus.NOT_FOUND);
        } catch (Exception ex) {
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
	

}
