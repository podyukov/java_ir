package ru.podyukov.mvc.controllers;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.podyukov.mvc.dao.SwitchDAO;
import ru.podyukov.mvc.models.Switch;

@Controller
@RequestMapping("/switches")
public class SwitchConrtoller {

    @Autowired
    private SwitchDAO switchDAO;

    @GetMapping("")
    public String index(Model model) {
        model.addAttribute("switches", switchDAO.index());
        return "switches/index";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model) {
        model.addAttribute("switch1", switchDAO.show(id));
        return "switches/show";
    }

    @GetMapping("/new")
    public String newSwitch(Model model) {
        model.addAttribute("switch1", new Switch());
        return "switches/new";
    }

    @PostMapping()
    public String create(@ModelAttribute("switch1") @Valid Switch switch1, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) return "switches/new";
        switchDAO.save(switch1);
        return "redirect:/switches";
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") int id) {
        model.addAttribute("switch1", switchDAO.show(id));
        return "switches/edit";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("switch1") @Valid Switch switch1, BindingResult bindingResult, @PathVariable("id") int id) {
        if (bindingResult.hasErrors()) return "switches/edit";
        switchDAO.update(id, switch1);
        return "redirect:/switches";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        switchDAO.delete(id);
        return "redirect:/switches";
    }
}
