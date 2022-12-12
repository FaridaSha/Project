package com.example.springsecurityapplication.controllers;

import com.example.springsecurityapplication.models.Person;
import com.example.springsecurityapplication.repositories.ProductRepository;
import com.example.springsecurityapplication.services.OrderService;
import com.example.springsecurityapplication.services.PersonService;
import com.example.springsecurityapplication.services.ProductService;
import com.example.springsecurityapplication.util.PersonValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

// http:localhost:8080/auth/login
@Controller
@RequestMapping("/auth")
public class AuthController {
    private final PersonValidator personValidator;
    private final ProductService productService;
    private final PersonService personService;
    private final OrderService orderService;

    @Autowired
    public AuthController(PersonValidator personValidator, PersonService personService, ProductService productService, ProductRepository productRepository, OrderService orderService) {
        this.personValidator = personValidator;
        this.personService = personService;
        this.productService = productService;
        this.orderService = orderService;
    }

    @GetMapping("/login")
    public String login(){
        return "login";
    }
    @GetMapping("/registration")
    public String registration(Model model){
        model.addAttribute("person", new Person());
        return "registration";
    }
    @PostMapping("/registration")
    public String resultRegistration(@ModelAttribute("person") @Valid Person person, BindingResult bindingResult){
        personValidator.validate(person, bindingResult);
        if(bindingResult.hasErrors()){
            return "registration";
        }
        personService.register(person);
        return "redirect:/index";
    }
    //смена пароля//
    @GetMapping("/updatePassword")
    @PreAuthorize("hasRole('VIP_ROLE')")
    public String changeUserPassword(Model model) {
        model.addAttribute("person", new Person());
        return "updatePassword";
    }
    @PostMapping("/updatePassword")
    @PreAuthorize("hasRole('VIP_ROLE')")
    public String updatePassword(@ModelAttribute("person")  Person person, BindingResult bindingResult){
        Person person_db = personService.getPersonFindByLogin(person);
        int id = person_db.getId();
        String password= person.getPassword();

        personService.updatePassword(id,password);
        return "redirect:/index";
    }
     public ProductService getProductService() {
     return productService;
 }
}
