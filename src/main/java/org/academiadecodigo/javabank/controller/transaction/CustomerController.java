package org.academiadecodigo.javabank.controller.transaction;

import org.academiadecodigo.javabank.persistence.dao.jpa.JpaCustomerDao;
import org.academiadecodigo.javabank.persistence.model.Customer;
import org.academiadecodigo.javabank.services.CustomerServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
public class CustomerController {

    JpaCustomerDao jpaCustomerDao;

    @Autowired
    public void setJpaCustomerDao(JpaCustomerDao jpaCustomerDao) {
        this.jpaCustomerDao = jpaCustomerDao;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/customer")

    public String list(Model model) {

        model.addAttribute("customers", jpaCustomerDao.findAll());

        ;/* CustomerServiceImpl customerService = new CustomerServiceImpl();
        model.addAttribute(customerService.getList(jpaCustomerDao.getCustomerIds()));*/
        return "javabank";
    }

}
