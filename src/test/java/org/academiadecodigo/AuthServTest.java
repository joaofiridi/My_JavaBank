package org.academiadecodigo;

import com.sun.security.ntlm.Client;
import org.academiadecodigo.javabank.model.Customer;
import org.academiadecodigo.javabank.services.AuthServiceImpl;
import org.academiadecodigo.javabank.services.CustomerService;
import org.academiadecodigo.javabank.services.CustomerServiceImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;


public class AuthServTest {

    CustomerServiceImpl customerService = Mockito.mock(CustomerServiceImpl.class);
    Customer customer = Mockito.mock(Customer.class);
    AuthServiceImpl authService;
    int id;

    @Before
    public void setup(){
        authService = new AuthServiceImpl();
        authService.setCustomerService(customerService);

    }

    @Test
    public void checkIfNullReturnFalse(){

        Mockito.when(customerService.get(customer.getId())).thenReturn(null);
        boolean result = authService.authenticate(customer.getId());
        Assert.assertFalse(result);

    }

    @Test
    public void checkIfNotNullReturnTrue(){

        Mockito.when(customerService.get(customer.getId())).thenReturn(customer);
        boolean result = authService.authenticate(customer.getId());
        Assert.assertTrue(result);

    }
    @Test
    public void checkIfCustServGetIdWorks(){

        //Mockito.when(customerService.get());

    }

}
