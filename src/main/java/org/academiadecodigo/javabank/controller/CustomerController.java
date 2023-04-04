package org.academiadecodigo.javabank.controller;

import org.academiadecodigo.javabank.dtos.DTOConverter;
import org.academiadecodigo.javabank.persistence.model.Customer;
import org.academiadecodigo.javabank.dtos.CustomerDTO;
import org.academiadecodigo.javabank.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * Controller responsible for rendering {@link Customer} related views
 */
@Controller
@RequestMapping("/customer")
public class CustomerController {

    private CustomerService customerService;

    /**
     * Sets the customer service
     *
     * @param customerService the customer service to set
     */
    @Autowired
    public void setCustomerService(CustomerService customerService) {
        this.customerService = customerService;
    }

    /**
     * Renders a view with a list of customers
     *
     * @param model the model object
     * @return the view to render
     */
    @RequestMapping(method = RequestMethod.GET, path = {"/list", "/", ""})
    public String listCustomers(Model model) {
        model.addAttribute("customers", customerService.list());
        return "customer/list";
    }

    /**
     * Renders a view with customer details
     *
     * @param id    the customer id
     * @param model the model object
     * @return the view to render
     */
    @RequestMapping(method = RequestMethod.GET, path = "/{id}")
    public String showCustomer(@PathVariable Integer id, Model model) {
        model.addAttribute("customer", customerService.get(id));
        model.addAttribute("recipients", customerService.listRecipients(id));
        return "customer/show";
    }

    /**
     * Deletes a customer
     *
     * @param id the customer id
     * @return the view to render
     */
    @RequestMapping(method = RequestMethod.GET, path = "{id}/delete")
    public String deleteCustomer(@PathVariable Integer id) {
        customerService.delete(id);
        return "redirect:/customer/list";
    }

    /**
     * Deletes a recipient from a customer
     *
     * @param cid the customer id
     * @param rid the recipient id
     * @return the view to render
     */
    @RequestMapping(method = RequestMethod.GET, path = "/{cid}/recipient/{rid}/delete/")
    public String deleteRecipient(@PathVariable Integer cid, @PathVariable Integer rid) {
        customerService.removeRecipient(cid, rid);
        return "redirect:/customer/" + cid;
    }

    @RequestMapping(method = RequestMethod.GET, path = "/edit/{id}")
    public String editCustomer(@PathVariable Integer id, Model model) {

        Customer customerToEdit = customerService.get(id);
        DTOConverter dtoConverter = new DTOConverter();
        CustomerDTO customerDTO = dtoConverter.convertCustomer(customerToEdit);
        model.addAttribute("customer", customerDTO);

        return "customer/edit";

    }

    @RequestMapping(method = RequestMethod.POST, path = "/edit/Submit")
    public String edditing(@ModelAttribute CustomerDTO customerDTO, RedirectAttributes redirectAttributes, Integer id) {

        Customer customer = new Customer();
        customer.setFirstName(customerDTO.getFirstName());
        customer.setLastName(customerDTO.getLastName());
        customer.setEmail(customerDTO.getEmail());
        customer.setPhone(customer.getPhone());
        customerService.save(customer);

        redirectAttributes.addFlashAttribute("lastAction", "Added customer successfully!");
        return "redirect:/customer/list";

    }

    @RequestMapping(method = RequestMethod.GET, path = "/add")
    public String addCustomer(Model model) {
        CustomerDTO customerDTO = new CustomerDTO();
        DTOConverter dtoConverter = new DTOConverter();
        dtoConverter.convertCustomerDTO(customerDTO);
        Customer customer = new Customer();
        customerDTO.setFirstName(customer.getFirstName());
        customerDTO.setLastName(customer.getLastName());
        customerDTO.setEmail(customer.getEmail());
        customerDTO.setPhone(customer.getPhone());

        model.addAttribute("customer", customerDTO);

        return "customer/edit";

    }
}
