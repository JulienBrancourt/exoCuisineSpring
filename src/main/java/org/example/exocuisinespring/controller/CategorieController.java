package org.example.exocuisinespring.controller;

import jakarta.validation.Valid;
import org.example.exocuisinespring.model.Categorie;
import org.example.exocuisinespring.service.CategorieService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.UUID;

@Controller
public class CategorieController {
    private CategorieService categorieService;

    public CategorieController(CategorieService categorieService) {
        this.categorieService = categorieService;
    }

    @GetMapping("/categories")
    public String categories(Model model) {
        model.addAttribute("categories", categorieService.getCategories());
        return "categories";
    }

    @GetMapping("/formulaireCategorie")
    public String addCategorie(Model model) {
        model.addAttribute("categorie", new Categorie());
    return "formCategorie";
    }

    @PostMapping("/formulaireCategorie")
    public String addCategorie(@Valid @ModelAttribute("categorie") Categorie categorie, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "formCategorie";
        } else {
        if (categorie.getId() == null) {
            categorieService.addCategorie(categorie);
        } else {
            categorieService.updateCategorie(categorie);
        }
        return "redirect:/categories";
        }
    }

    @GetMapping("/deleteCategorie")
    public String deleteRecette(@RequestParam("id") UUID id) {
        categorieService.deleteCategorie(id);
        return "redirect:/categories";
    }

    @GetMapping("/updateCategorie")
    public String updateCategorie(@RequestParam("id") UUID id, Model model) {
        Categorie categorie = categorieService.getCategorieById(id);
        model.addAttribute("categorie", categorie);
        return "formCategorie";
    }
}
