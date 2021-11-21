package org.launchcode.outdoorEvents.controllers;

import org.launchcode.outdoorEvents.data.EventRepository;
import org.launchcode.outdoorEvents.data.LocationRepository;
import org.launchcode.outdoorEvents.data.UserRepository;
import org.launchcode.outdoorEvents.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Optional;

@Controller
public class HomeController{

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private LocationRepository locationRepository;

    @Autowired
    private AuthenticationController authenticationController;

    @GetMapping("/")
    public String displayDashboard(Model model, HttpServletRequest request){
        User currentUser = authenticationController.getUserFromSession(request.getSession());
        model.addAttribute("hello", "Hello, "+ currentUser.getFirstName() +" "+ currentUser.getLastName());
        model.addAttribute("userEvents", currentUser.getEvent());
        model.addAttribute("events", eventRepository.findAll());
        model.addAttribute("locations", locationRepository.findAll());
        model.addAttribute("title", "Logger");
        return "index";
    }
}
