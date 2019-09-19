package com.example.chatty.controller;

import com.example.chatty.model.Presence;
import com.example.chatty.model.User;
import com.example.chatty.service.PresenceManager;
import com.example.chatty.service.SecurityService;
import com.example.chatty.service.UserService;
import com.example.chatty.validator.UserValidator;
import org.apache.el.stream.Stream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Controller
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private SecurityService securityService;

    @Autowired
    private UserValidator userValidator;

    @Autowired
    private PresenceManager presenceManager;

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
        return "redirect:/welcome";
    }

    @GetMapping("/jsp/login")
    public String login(Model model, String error, String logout){
        if(error !=null){
            model.addAttribute("error","UserName or Password is Invalid");
        }
        if(logout != null){
            model.addAttribute("message", "You have been logged out successfully.");
        }
        return "jsp/login";
    }
    @GetMapping({"/","/welcome"})
    public String welcome(Model model){
        return "welcome";
    }

    @GetMapping("/chat")
    public String chat(Model model){
        return "chat";
    }

//    @GetMapping("/user/presence")
//    public List<Presence> getPresenseInfomation() {
//        List presenceList = new ArrayList();
//        List<User> userList = userService.getAllusers();
//        presenceList = StreamSupport.stream(userList.spliterator(), false)
//                .filter((user) -> user.getUsername().equals("test"))
//                .map(user ->
//                        presenceManager.getHeartbeat(user.getUsername()))
//                                .collect(Collectors.toList());
//        return presenceList;
//    }
}
