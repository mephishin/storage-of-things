package com.example.storageofthings.adapter.web;

import com.example.storageofthings.app.place.GetAllEmptyPlaces;
import com.example.storageofthings.app.thing.*;
import com.example.storageofthings.app.user.GetAllUsersExceptAuthenticated;
import com.example.storageofthings.domain.Thing;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/thing")
public class ThingController {
    private final FindThingsByThingUserService findThingsByThingUserService;
    private final GetAllUsersExceptAuthenticated getAllUsersExceptAuthenticated;
    private final ThingDeleteService thingDeleteService;
    private final TransferThingToUserService transferThingToUserService;
    private final GetAllEmptyPlaces getAllEmptyPlaces;
    private final AddThingService addThingService;

    @GetMapping("/my")
    public String thing(Model model, Authentication authentication) {
        model.addAttribute("new_thing", new Thing());

        var places = getAllEmptyPlaces.get();
        model.addAttribute("places", places);

        var things = findThingsByThingUserService.find(authentication.getName());
        model.addAttribute("things", things);

        var users = getAllUsersExceptAuthenticated.get(authentication.getName());
        model.addAttribute("users", users);

        log.info("Get things from db: " + things);
        return "thing";
    }

    @PostMapping("/delete/{id}")
    public String deleteThing(@PathVariable Long id, Authentication authentication) {
        thingDeleteService.deleteThing(id, authentication.getName());
        return "redirect:/thing/my";
    }

    @PostMapping("/transfer/{id}")
    public String transferThing(
            @PathVariable Long id,
            Authentication authentication,
            @RequestParam("receiver") Long receiver) {
        transferThingToUserService.transfer(receiver, id, authentication.getName());
        return "redirect:/thing/my";
    }

    @PostMapping("/add")
    public String addThing(@ModelAttribute("new_thing") Thing thing, Authentication authentication) {
        addThingService.add(thing, authentication.getName());
        return "redirect:/thing/my";
    }
}
