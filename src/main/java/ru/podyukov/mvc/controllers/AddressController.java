package ru.podyukov.mvc.controllers;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.podyukov.mvc.dao.AddressDAO;
import ru.podyukov.mvc.models.Address;

@Controller
@RequestMapping("/addresses")
public class AddressController {

    @Autowired
    private AddressDAO addressDAO;

    @GetMapping("")
    public String index(Model model) {
        model.addAttribute("addresses", addressDAO.index());
        return "addresses/index";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model) {
        model.addAttribute("address", addressDAO.show(id));
        return "addresses/show";
    }

    @GetMapping("/new")
    public String newAddress(Model model) {
        model.addAttribute("address", new Address());
        return "addresses/new";
    }

    @PostMapping()
    public String create(@ModelAttribute("address") @Valid Address address, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) return "addresses/new";
        addressDAO.save(address);
        return "redirect:/addresses";
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") int id) {
        model.addAttribute("address", addressDAO.show(id));
        return "addresses/edit";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("address") @Valid Address address, BindingResult bindingResult, @PathVariable("id") int id) {
        if (bindingResult.hasErrors()) return "addresses/edit";
        addressDAO.update(id, address);
        return "redirect:/addresses";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        addressDAO.delete(id);
        return "redirect:/addresses";
    }

}
