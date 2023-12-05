package ru.kata.spring.boot_security.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.RoleService;
import ru.kata.spring.boot_security.demo.service.UserService;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/admin")
public class AdminController {
    private final UserService userService;
    private final RoleService roleService;

    public AdminController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }
    @GetMapping(value="/getall")
    public String getUsers(ModelMap modelMap){
        List<User> users = userService.getUsers();
        System.out.printf("Controller getall users" + users.size());
        List<Role> roles = roleService.getRoles();
        System.out.printf("Controller getall roles" + roles.size());
        modelMap.addAttribute("users", users);
        modelMap.addAttribute("roles", roles);
        modelMap.addAttribute("listRoles", List.of("User", "Admin"));
        System.out.println("LLL. admin getall");
        return "index";
    }

    @PostMapping(value = "/create")
    public String createUser(@ModelAttribute("User") User user, ModelMap modelMap){
        userService.addUser(user);
        return "welcome";
    }

    @GetMapping(value="/")
    public String adminHome(){
        System.out.println("LLL. admin home");
        return "forward:/admin/getall";
    }

    @GetMapping(value="")
    public String adminHome_(){
        System.out.println("LLL. admin home");
        return "forward:/admin/getall";
    }

}
