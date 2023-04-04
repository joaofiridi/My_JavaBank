package org.academiadecodigo.javabank.dtos;

import org.academiadecodigo.javabank.persistence.model.Customer;

public class DTOConverter {


    public CustomerDTO convertCustomer(Customer customer){

        CustomerDTO customerDTO = new CustomerDTO();

        customerDTO.setFirstName(customer.getFirstName());
        customerDTO.setLastName(customer.getLastName());
        customerDTO.setEmail(customer.getEmail());
        customerDTO.setPhone(customer.getPhone());

        return customerDTO;

    }

    public Customer convertCustomerDTO (CustomerDTO customerDTO){
        Customer customer =  new Customer();
        customer.setFirstName(customerDTO.getFirstName());
        customer.setLastName(customerDTO.getLastName());
        customer.setEmail(customerDTO.getEmail());
        customer.setPhone(customerDTO.getPhone());

        return customer;
    }

}
