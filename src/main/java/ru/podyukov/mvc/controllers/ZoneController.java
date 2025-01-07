package ru.podyukov.mvc.controllers;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.podyukov.mvc.dao.ZoneDAO;
import ru.podyukov.mvc.models.Zone;

@Controller
@RequestMapping("/zones")
public class ZoneController {

    @Autowired
    private ZoneDAO zoneDAO;

    @GetMapping("")
    public String index(Model model) {
        model.addAttribute("zones", zoneDAO.index());
        return "zones/index";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model) {
        model.addAttribute("zone", zoneDAO.show(id));
        return "zones/show";
    }

    @GetMapping("/new")
    public String newSwitch(Model model) {
        model.addAttribute("zone", new Zone());
        return "zones/new";
    }

    @PostMapping()
    public String create(@ModelAttribute("zone") @Valid Zone zone, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) return "zones/new";
        try {
            zoneDAO.save(zone);
        } catch (RuntimeException e) {
            model.addAttribute("error", e.getMessage());
            return "zones/new";
        }
        return "redirect:/zones";
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") int id) {
        model.addAttribute("zone", zoneDAO.show(id));
        return "zones/edit";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("zone") @Valid Zone zone, BindingResult bindingResult, @PathVariable("id") int id, Model model) {
        if (bindingResult.hasErrors()) return "zones/edit";
        try {
            zoneDAO.update(id, zone);
        } catch (RuntimeException e) {
            model.addAttribute("error", e.getMessage());
            return "zones/edit";
        }
        return "redirect:/zones";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        zoneDAO.delete(id);
        return "redirect:/zones";
    }
}
