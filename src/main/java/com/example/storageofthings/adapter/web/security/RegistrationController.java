package com.example.storageofthings.adapter.web.security;

import com.example.storageofthings.app.user.SaveUserService;
import com.example.storageofthings.domain.security.ThingUser;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/registration")
@RequiredArgsConstructor
public class RegistrationController {
    private final SaveUserService saveUserService;

    @GetMapping()
    public String registration(Model model) {
        model.addAttribute("userForm", new ThingUser());

        return "registration";
    }

    @PostMapping()
    public String addUser(@ModelAttribute("userForm") @Valid ThingUser userForm, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "registration";
        }
        try {
            saveUserService.saveUser(userForm);
        } catch (IllegalArgumentException e) {
            model.addAttribute("usernameError", "Пользователь с таким именем уже существует");
            return "registration";
        }
        return "redirect:/login";
    }
}
