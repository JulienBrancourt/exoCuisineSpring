package org.example.exocuisinespring.controller;

import jakarta.validation.constraints.NotBlank;
import org.example.exocuisinespring.model.Categorie;
import org.example.exocuisinespring.model.Recette;
import org.example.exocuisinespring.service.CategorieService;
import org.example.exocuisinespring.service.RecetteService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.UUID;

@Controller
public class RecetteController {
    private final RecetteService recetteService;
    private final CategorieService categorieService;

    public RecetteController(RecetteService recetteService, CategorieService categorieService) {
        this.recetteService = recetteService;
        this.categorieService = categorieService;
    }

    @GetMapping("/")
    public String home() {
        return "home";
    }

    @GetMapping("/recettes")
    public String showAllRecettes(Model model) {
        model.addAttribute("recettes", recetteService.getRecettes());
        return "recettes";
    }

    @GetMapping("/formulaireRecette")
    public String addRecette(Model model) {
        List<Categorie> categories = categorieService.getCategories();
        model.addAttribute("recette", new Recette());
        model.addAttribute("categories", categories);
        return "formRecette";
    }

    @PostMapping("/formulaireRecette")
    public String addRecette(@NotBlank @ModelAttribute("recette") Recette recette, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "formRecette";
        } else {
            if (recette.getId() == null) {
                recetteService.addRecette(recette);
            } else {
                recetteService.updateRecette(recette);
            }
            return "redirect:/recettes";
        }
    }

    @GetMapping("/deleteRecette")
    public String deleteRecette(@RequestParam("id") UUID id) {
        recetteService.deleteRecette(id);
        return "redirect:/recettes";
    }

    @GetMapping("/update")
    public String updateRecette(@RequestParam("id") UUID id, Model model) {
        List<Categorie> categories = categorieService.getCategories();
        Recette recette = recetteService.getRecetteById(id);
        model.addAttribute("recette", recette);
        model.addAttribute("categories", categories);
        return "formRecette";
    }
}
