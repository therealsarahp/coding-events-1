package org.launchcode.codingevents.controllers;

import org.launchcode.codingevents.data.EventData;
import org.launchcode.codingevents.models.Event;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Chris Bay
 */
@Controller
@RequestMapping("events")
public class EventController {



    @GetMapping
    public String displayAllEvents(Model model) {
        model.addAttribute("title", "All Events");
        model.addAttribute("events", EventData.getAll());
        return "events/index";
    }

    @GetMapping("create")
    public String displayCreateEventForm(Model model) {
        model.addAttribute("title", "Create Event");
        return "events/create";
    }

    @PostMapping("create")
    public String processCreateEventForm(@ModelAttribute Event newEvent) {
        EventData.add(newEvent);
        return "redirect:";
    }

    @GetMapping("delete")
    public String displayDeleteEventForm(Model model){
        model.addAttribute("title", "Delete Events");
        model.addAttribute("events", EventData.getAll());
        return "events/delete";
    }

    @PostMapping("delete")
    public String processDeleteEventForm(@RequestParam(required = false) int[] eventIds){
        if(eventIds != null) {
            for (int id : eventIds) {
                EventData.remove(id);
            }
        }
        return "redirect:";
    }

    @GetMapping("edit/{id}")
    public String displayEditForm(Model model, @PathVariable int id){
        model.addAttribute("event", EventData.getById(id));
        model.addAttribute("title", "Edit Event " + EventData.getById(id).getName() + " ID: " + EventData.getById(id).getId());
        model.addAttribute("name", EventData.getById(id).getName());
        model.addAttribute("description", EventData.getById(id).getDescription());

        return "events/edit";
    }

    @PostMapping("edit")
    public String processEditForm(@RequestParam int eventId, @RequestParam String aName, @RequestParam String aDescription){
        Event event = EventData.getById(eventId);
        event.setDescription(aDescription);
        event.setName(aName);

        return "redirect:";
    }

}
