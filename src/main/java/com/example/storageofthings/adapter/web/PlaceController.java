package com.example.storageofthings.adapter.web;

import com.example.storageofthings.app.place.GetAllPlacesService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/place")
public class PlaceController {
    private final GetAllPlacesService getAllPlacesService;

    @GetMapping("/existed")
    public String existed(Model model) {
        model.addAttribute("places", getAllPlacesService.get());
        return "places";
    }
}
