package com.company.controller.impl;

import com.company.service.UserService;
import com.company.service.dto.UserDto;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
    private static final Logger log = LogManager.getLogger(UserController.class);
    private final UserService userService;

    @PostMapping("/user_update_form")
    public String ToUpdatePageUser(@RequestParam Long id, Model model) {
        model.addAttribute("user", userService.findById(id));
        return "updateUser";
    }

    @PostMapping("/user_update/{id}")
    public String editUser(@ModelAttribute("user") UserDto userDto) {
        userService.update(userDto);
        return "redirect:/users/find_user_by_id/" + userDto.getId();
    }

    @PostMapping("/create")
    public String createUser(@ModelAttribute UserDto userDto) {
        userService.create(userDto);
        return "redirect:/users/users_find";
    }

    @GetMapping("/find_user_by_id/{id}")
    public String findUser(@PathVariable Long id, Model model) {
        log.info("Start findUser {}", model);
        model.addAttribute("user", userService.findById(id));
        return "user";
    }

    @GetMapping("/users_find")
    public String findUsers(Model model) {
        log.info("Start UsersFindAll {}", model);
        model.addAttribute("user_count", userService.countAll());
        model.addAttribute("users", userService.findAll());
        return "users";
    }

    @PostMapping("/user_activate")
    public String activeUser(@RequestParam Long id, Model model) {
        log.info("Start activeUser {}", id);
        userService.active(id, true);
        model.addAttribute("user_count", userService.countAll());
        model.addAttribute("users", userService.findAll());
        return "users";
    }

    @PostMapping("/user_deactivate")
    public String deactivateUser(@RequestParam Long id, Model model) {
        log.info("Start deactivateUser {}", id);
        userService.active(id, false);
        model.addAttribute("user_count", userService.countAll());
        model.addAttribute("users", userService.findAll());
        return "users";
    }
}
