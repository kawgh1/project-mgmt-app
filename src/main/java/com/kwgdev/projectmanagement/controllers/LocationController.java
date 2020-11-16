package com.kwgdev.projectmanagement.controllers;

import com.kwgdev.projectmanagement.dao.LocationRepository;
import com.kwgdev.projectmanagement.entities.Location;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/locations")
public class LocationController {

    @Autowired
    LocationRepository locationRepo;

    @GetMapping
    public String displayLocations(Model model) {
        List<Location> locations = locationRepo.findAll();
        model.addAttribute("locations", locations);
        return "locations/list-locations";

    }

    @GetMapping("/new")
    public String displayLocationForm(Model model) { // Model is used to bind the Java object (Location) to the HTML Form


        Location aLocation = new Location();
        // bind an empty Project object to the HTML form
        model.addAttribute("location", aLocation);

        return "locations/new-location";
    }

    @PostMapping("/save")
    public String createLocation(Location location, Model model) {
        // this will handle saving the new Location to the database
        locationRepo.save(location);

        // use a redirect to new to prevent duplicate submissions
        // always use redirect after saving data
        return "redirect:/locations";
    }


}
