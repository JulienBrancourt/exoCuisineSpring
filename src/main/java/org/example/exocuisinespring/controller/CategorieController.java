package org.example.exocuisinespring.controller;

import org.example.exocuisinespring.service.CategorieService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CategorieController {
    private CategorieService categorieService;

    public CategorieController(CategorieService categorieService) {
        this.categorieService = categorieService;
    }

    @GetMapping("categories")
    public String categories(Model model) {
        model.addAttribute("categories", categorieService.getCategories());
        return "categories";
    }
}
