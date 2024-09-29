package com.example.storageofthings.adapter.web.security;

import com.example.storageofthings.adapter.persistence.RoleJpaRepo;
import com.example.storageofthings.adapter.persistence.ThingJpaRepo;
import com.example.storageofthings.adapter.persistence.UserJpaRepo;
import com.example.storageofthings.app.user.SaveUserService;
import com.example.storageofthings.domain.security.ThingUser;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("/admin")
@Slf4j
public class AdminController {
    private final RoleJpaRepo roleJpaRepo;
    private final UserJpaRepo userJpaRepo;
    private final ThingJpaRepo thingJpaRepo;
    private final SaveUserService saveUserService;

    @GetMapping("/users")
    public String users(Model model) {
        var users = userJpaRepo.findAll();
        model.addAttribute("users", users);
        model.addAttribute("new_user", new ThingUser());
        model.addAttribute("selected_role", "");
        model.addAttribute("roles", roleJpaRepo.findAll());
        model.addAttribute("things", thingJpaRepo.findAll());
        log.info("Get users from db: " + users);
        return "users";
    }

    @PostMapping("/user/add")
    public String addUser(@ModelAttribute("new_user") @Valid ThingUser thingUser) {
        saveUserService.saveUser(thingUser);
        return "redirect:/admin/users";
    }
//    @GetMapping("/user/delete/{id}")
//    public String userDelete(@PathVariable Long id, Model model) {
//        var user = userJpaRepo.findById();
//        model.addAttribute("users", users);
//        log.info("Get users from db: " + users);
//        return "users";
//    }
}

