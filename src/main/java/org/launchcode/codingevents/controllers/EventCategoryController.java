package org.launchcode.codingevents.controllers;


import org.launchcode.codingevents.data.EventCategoryRepository;
import org.launchcode.codingevents.models.Event;
import org.launchcode.codingevents.models.EventCategory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequestMapping("eventCategories")
public class EventCategoryController {

    @Autowired
    private EventCategoryRepository eventCategoryRepository;

    @GetMapping("index")
    public String displayAllEvents(Model model){
        model.addAttribute("title", "All Categories");
        model.addAttribute("Event Categories", eventCategoryRepository.findAll());
        return "eventCategories/index";

    }

    @GetMapping("create")
    public String renderCreateEventCategoryForm(Model model){
        model.addAttribute("title", "Create Cateogry");
        model.addAttribute(new EventCategory());
        return "eventCategories/create";
    }

    @PostMapping("create")
    public String processCreateEventCategoryForm(@ModelAttribute @Valid EventCategory newEventCategory,
                                                 Errors errors, Model model){
        model.addAttribute("title", "Create Category");

        if(errors.hasErrors()) {
            model.addAttribute("title", "Create Category");
            return "eventCategories/create";
        }

        eventCategoryRepository.save(newEventCategory);
        return "redirect:";
    }


}
