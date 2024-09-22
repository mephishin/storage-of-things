package com.example.storageofthings.adapter.web;

import com.example.storageofthings.adapter.persistence.ThingJpaRepo;
import com.example.storageofthings.app.thing.ThingDeleteService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/thing")
public class ThingController {

    private final ThingJpaRepo thingJpaRepo;
    private final ThingDeleteService thingDeleteService;

    @GetMapping()
    public String thing(Model model) {
        var things = thingJpaRepo.findAll();
        model.addAttribute("things", things);
        log.info("Get things from db: " + things);
        return "thing";
    }

    @PostMapping("/delete/{id}")
    public String deleteThing(@PathVariable Long id) {
        thingDeleteService.deleteThing(id);
        return "redirect:/thing";
    }
}
