package org.academiadecodigo.javabank.REST;

import org.academiadecodigo.javabank.command.AccountDto;
import org.academiadecodigo.javabank.command.CustomerDto;
import org.academiadecodigo.javabank.converters.AccountToAccountDto;
import org.academiadecodigo.javabank.converters.CustomerDtoToCustomer;
import org.academiadecodigo.javabank.converters.CustomerToCustomerDto;
import org.academiadecodigo.javabank.converters.RecipientToRecipientDto;
import org.academiadecodigo.javabank.persistence.model.Customer;
import org.academiadecodigo.javabank.persistence.model.account.Account;
import org.academiadecodigo.javabank.services.AccountService;
import org.academiadecodigo.javabank.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@org.springframework.web.bind.annotation.RestController
public class RestController {

    private CustomerService customerService;
    private AccountService accountService;
    private CustomerToCustomerDto customerToCustomerDto;
    private CustomerDtoToCustomer customerDtoToCustomer;
    private AccountToAccountDto accountToAccountDto;
    private RecipientToRecipientDto recipientToRecipientDto;

    @RequestMapping(method = RequestMethod.GET, value = "/api", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<CustomerDto>> listCustomer() {
        return new ResponseEntity<>(customerToCustomerDto.convert(customerService.list()), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/api/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CustomerDto> singleCustomer(@PathVariable Integer id) {
        return new ResponseEntity<>(customerToCustomerDto.convert(customerService.get(id)), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, value = "api/{id}/accounts", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<AccountDto>> listAccount(@PathVariable Integer id) {
        return new ResponseEntity<>(accountToAccountDto.convert(customerService.get(id).getAccounts()), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, value = "api/{id}/accounts/{aid}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AccountDto> singleAccount(@PathVariable Integer id, @PathVariable Integer aid) {
        return new ResponseEntity<>(accountToAccountDto.convert(customerService.get(id).getAccounts().get(aid)), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST, value = "api/customer", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Customer> addCustomer(@Valid @RequestBody CustomerDto customerDto, BindingResult bindingResult) {
        Customer c1 = customerDtoToCustomer.convert(customerDto);
        if (bindingResult.hasErrors()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        customerService.save(c1);
        return new ResponseEntity<>(HttpStatus.OK);
    }


    @Autowired
    public void setCustomerService(CustomerService customerService) {
        this.customerService = customerService;
    }
    @Autowired
    public void setAccountService(AccountService accountService) {
        this.accountService = accountService;
    }
    @Autowired
    public void setCustomerToCustomerDto(CustomerToCustomerDto customerToCustomerDto) {
        this.customerToCustomerDto = customerToCustomerDto;
    }
    @Autowired
    public void setCustomerDtoToCustomer(CustomerDtoToCustomer customerDtoToCustomer) {
        this.customerDtoToCustomer = customerDtoToCustomer;
    }
    @Autowired
    public void setAccountToAccountDto(AccountToAccountDto accountToAccountDto) {
        this.accountToAccountDto = accountToAccountDto;
    }
    @Autowired
    public void setRecipientToRecipientDto(RecipientToRecipientDto recipientToRecipientDto) {
        this.recipientToRecipientDto = recipientToRecipientDto;
    }
}



