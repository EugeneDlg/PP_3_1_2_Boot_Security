package ru.kata.spring.boot_security.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.RoleService;
import ru.kata.spring.boot_security.demo.service.UserService;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/admin")
public class AdminController {
    private final UserService userService;
    private final RoleService roleService;

    public AdminController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping(value = "/all")
    public String getUsers(@ModelAttribute("user") User user, ModelMap modelMap) {
        List<User> users = userService.getUsers();
        List<Role> roleList = roleService.getRoles();
        modelMap.addAttribute("users", users);
        modelMap.addAttribute("roleList", roleList);
        return "index";
    }

    @GetMapping(value = "/userdetails")
    public String getUser(@RequestParam("id") Long id, ModelMap modelMap) {
        Optional<User> user = userService.getByIdForUpdate(id);
        if (id == null  || user.isEmpty()) {
            return "notfound";
        }
        List<Role> roleList = roleService.getRoles();
        modelMap.addAttribute("user", user.get());
        modelMap.addAttribute("roleList", roleList);
        return "userdetails";
    }

    @PostMapping(value = "/create")
    public String createUser(@ModelAttribute("user") User user) {
        userService.addUser(user);
        return "complete";
    }

    @GetMapping(value = "")
    public String adminHome() {
        return "forward:/admin/all";
    }

    @PostMapping(value = "/update")
    public String updateUser(@ModelAttribute("user") User user, @RequestParam("id") Long id) {
        if (id == null  || userService.getById(id).isEmpty()) {
            return "notfound";
        }
        userService.updateUser(id, user);
        return "complete";
    }

    @PostMapping(value = "/delete")
    public String deleteUser(@RequestParam("id") Long id) {
        if (id == null  || userService.getById(id).isEmpty()) {
            return "notfound";
        }
        userService.deleteUser(id);
        return "complete";
    }

}
