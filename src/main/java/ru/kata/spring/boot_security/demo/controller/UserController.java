package ru.kata.spring.boot_security.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.RoleService;
import ru.kata.spring.boot_security.demo.service.UserService;

import java.util.List;

@Controller
public class UserController {
    private final UserService userService;
    private final RoleService roleService;

    public UserController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

//    @GetMapping("/")
    public String welcome(ModelMap modelMap){
        System.out.println("LLL. WELCOME");
        return "welcome";
    }
    @GetMapping("/index")
    public String getUsers(ModelMap modelMap){
        List<User> userList = userService.getUsers();
        modelMap.addAttribute("userList", userList);
        System.out.println("LLL. admin page");
        return "index";
    }

    @GetMapping("/user")
    public String getUsers0(ModelMap modelMap){
        List<User> userList = userService.getUsers();
        modelMap.addAttribute("userList", userList);
        System.out.println("LLL. user page");
        return "user";
    }
}
