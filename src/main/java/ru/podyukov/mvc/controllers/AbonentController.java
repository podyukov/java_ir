package ru.podyukov.mvc.controllers;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.podyukov.mvc.dao.AbonentDAO;
import ru.podyukov.mvc.models.Abonent;

@Controller
@RequestMapping("/abonents")
public class AbonentController {

    @Autowired
    private AbonentDAO abonentDAO;

    @GetMapping("")
    public String index(Model model) {
        model.addAttribute("abonents", abonentDAO.index());
        return "abonents/index";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model) {
        model.addAttribute("abonent", abonentDAO.show(id));
        return "abonents/show";
    }

    @GetMapping("/new")
    public String newAbonent(Model model) {
        model.addAttribute("abonent", new Abonent());
        return "abonents/new";
    }

    @PostMapping()
    public String create(@ModelAttribute("abonent") @Valid Abonent abonent, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) return "abonents/new";
        try {
            abonentDAO.save(abonent);
        } catch (RuntimeException e) {
            model.addAttribute("error", e.getMessage());
            return "abonents/new";
        }
        return "redirect:/abonents";
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") int id) {
        model.addAttribute("abonent", abonentDAO.show(id));
        return "abonents/edit";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("abonent") @Valid Abonent abonent, BindingResult bindingResult, @PathVariable("id") int id, Model model) {
        if (bindingResult.hasErrors()) return "abonents/edit";
        try {
            abonentDAO.update(id, abonent);
        } catch (RuntimeException e) {
            model.addAttribute("error", e.getMessage());
            return "abonents/edit";
        }
        return "redirect:/abonents";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        abonentDAO.delete(id);
        return "redirect:/abonents";
    }
}
