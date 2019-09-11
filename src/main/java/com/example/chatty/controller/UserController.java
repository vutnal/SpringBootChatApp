package com.example.chatty.controller;

import com.example.chatty.model.User;
import com.example.chatty.service.SecurityService;
import com.example.chatty.service.UserService;
import com.example.chatty.validator.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private SecurityService securityService;

    @Autowired
    private UserValidator userValidator;

    @GetMapping("/registration")
    public String registration(Model model){
        model.addAttribute("userForm", new User());
        return "registration";
    }

    @PostMapping("/registration")
    public String registration(@ModelAttribute("userForm") User userForm, BindingResult bindingResult){
        userValidator.validate(userForm,bindingResult);
        if(bindingResult.hasErrors()){
            return "registration";
        }
        userService.save(userForm);
        securityService.autoLogin(userForm.getUsername(),userForm.getPasswordConfirm());
        return "redirect:/chat";
    }

    @GetMapping("/login")
    public String login(Model model, String error, String logout){
        if(error !=null){
            model.addAttribute("error","UserName or Password is Invalid");
        }
        if(logout != null){
            model.addAttribute("message", "You have been logged out successfully.");
        }
        return "login";
    }
    @GetMapping({"/" , "/chat"})
    public String chat(Model model){
        return "chat";
    }
}
