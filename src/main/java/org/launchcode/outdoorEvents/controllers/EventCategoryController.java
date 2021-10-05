package org.launchcode.outdoorEvents.controllers;

import org.launchcode.outdoorEvents.data.EventCategoryRepository;
import org.launchcode.outdoorEvents.data.EventRepository;
import org.launchcode.outdoorEvents.data.UserRepository;
import org.launchcode.outdoorEvents.models.EventCategory;
import org.launchcode.outdoorEvents.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.Optional;

@Controller
public class EventCategoryController{

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private EventCategoryRepository eventCategoryRepository;

    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private AuthenticationController authenticationController;

    @GetMapping("eventCategories")
    public String displayAllCategories(Model model, HttpServletRequest request) {
        User currentUser = authenticationController.getUserFromSession(request.getSession());
        model.addAttribute("title", "All Categories");
        //model.addAttribute("eventCategories", eventCategoryRepository.findById(currentUser.getId()));
        model.addAttribute("eventCategories", eventCategoryRepository.findAll());
        model.addAttribute("events", eventRepository.findAll());
        return "eventCategories/index";
    }

    @GetMapping("eventCategories/add")
    public String renderCreateEventCategoryForm(Model model) {
        model.addAttribute("title", "Create Category");
        model.addAttribute(new EventCategory());
        return "eventCategories/add";
    }

    @PostMapping("eventCategories/add")
    public String processCreateEventCategoryForm(@Valid @ModelAttribute EventCategory eventCategory, Errors errors,
                                                 Model model) {

        if (errors.hasErrors()) {
            model.addAttribute("title", "Create Category");
            return "eventCategories/add";
        }

        eventCategoryRepository.save(eventCategory);
        return "redirect:/eventCategories/add";
    }

    @GetMapping("/eventCategories/delete")
    public String displayDeleteEventForm(Model model) {
        model.addAttribute("title", "All Categories");
        model.addAttribute("eventCategories", eventCategoryRepository.findAll());
        model.addAttribute("events", eventRepository.findAll());
        return "eventCategories/delete";
    }

    @PostMapping("eventCategories/delete")
    public String processDeleteEventsForm(@RequestParam(required = false) int[] eventIds) {
        if (eventIds != null) {
            for (int id : eventIds) {
                eventCategoryRepository.deleteById(id);
            }
        }         return "redirect:/eventCategories/delete";
    }

}

