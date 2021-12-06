package org.launchcode.outdoorEvents.controllers;

import org.launchcode.outdoorEvents.data.EventCategoryRepository;
import org.launchcode.outdoorEvents.data.EventRepository;
import org.launchcode.outdoorEvents.data.UserRepository;
import org.launchcode.outdoorEvents.models.Event;
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

import java.lang.StackWalker.Option;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
public class EventController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private EventCategoryRepository eventCategoryRepository;

    @Autowired
    private AuthenticationController authenticationController;

    @GetMapping("/events")
    public String displayAllEvents(Model model, HttpServletRequest request) {
            User currentUser = authenticationController.getUserFromSession(request.getSession());
            model.addAttribute("title", "All Events");
            model.addAttribute("events", eventRepository.findAll());
            model.addAttribute("users", userRepository.findAll());
            model.addAttribute("userEvents", eventRepository.findAllByUser_Id(currentUser.getId()));
            model.addAttribute("eventCategories", eventCategoryRepository.findAll());
            return "events/index";
    }

    @GetMapping("/events/create")
    public String displayCreateEventForm(Model model, HttpServletRequest request) {
            User currentUser = authenticationController.getUserFromSession(request.getSession());
            model.addAttribute("title", "Create Event");
            model.addAttribute("eventCategories", eventCategoryRepository.findAll());
            model.addAttribute(new Event());

            return "events/create";
    }

    @PostMapping("/events/create")
    public String processCreateEventForm(@ModelAttribute @Valid Event newEvent, 
                                            Errors errors, Model model, HttpServletRequest request) {
            if(errors.hasErrors()) {
              model.addAttribute("title", "Create Event");
             return "events/create";
          }
            User currentUser = authenticationController.getUserFromSession(request.getSession());

            newEvent.setUser(currentUser);
            eventRepository.save(newEvent);
            return "redirect:/events";
    }

    @GetMapping("/events/delete")
    public String displayDeleteEventForm(Model model, HttpServletRequest request) {
        User currentUser = authenticationController.getUserFromSession(request.getSession());
        model.addAttribute("title", "Delete Event");
        model.addAttribute("userEvents", eventRepository.findAllByUser_Id(currentUser.getId()));
        return "events/delete";
    }

    @PostMapping("/events/delete")
    public String processDeleteEventsForm(@RequestParam(required = false) int[] eventIds) {
        if (eventIds != null) {
            for (int id : eventIds) {
                eventRepository.deleteById(id);
            }
        }         return "redirect:/events";
    }

    @GetMapping("/events/edit")
    public String selectEditEventForm(Model model, HttpServletRequest request) {
        User currentUser = authenticationController.getUserFromSession(request.getSession());
        model.addAttribute("title", "Edit Events");
        model.addAttribute("userEvents", eventRepository.findAllByUser_Id(currentUser.getId()));
        model.addAttribute("eventCategories", eventCategoryRepository.findAll());
        return "events/edit";
    }

    @PostMapping("/events/edit")
    public String processSelectEditEventForm(@ModelAttribute @Valid Event event, Integer eventId,
                                                Errors errors, Model model, HttpServletRequest request) {
        User currentUser = authenticationController.getUserFromSession(request.getSession());
        model.addAttribute("event", eventRepository.findById(eventId));
        model.addAttribute("eventCategories", eventCategoryRepository.findAll());
        event.setUser(currentUser);
        eventRepository.save(event);
        return "/events/editSelect";    
    }

    // @GetMapping("/events/editSelect")
    // public String displayEditSelectionEventForm(Model model, HttpServletRequest request, @ModelAttribute @Valid Event event) {
    //     model.addAttribute("eventName", event.getName());
    //     model.addAttribute("eventDescription", event.getDescription());
    //     model.addAttribute("eventCategory", event.getEventCategory());
    //     model.addAttribute("eventCategories", eventCategoryRepository.findAll());
    //     return "events/editSelect";
    // }

    // @PostMapping("/events/editSelect")
    // public String processEditSelectionEventForm(@ModelAttribute @Valid Event event,
    //                                             Errors errors, Model model, HttpServletRequest request) {
        
        
    //     eventRepository.save(event);
    //     return "redirect:/events";
    // }
}