package org.example.exocuisinespring.service;

import org.example.exocuisinespring.model.Categorie;
import org.example.exocuisinespring.model.Recette;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Service
public class RecetteService {
    private final List<Recette> recettes;
    private final List<Categorie> categories;
    private final CategorieService categorieService;

    // injection de dépendance !!!!!!!!!!!!!!!
    public RecetteService(CategorieService categorieService) {
        this.categories = categorieService.getCategories();
        this.recettes = new ArrayList<>();

        Recette r1 = Recette
                .builder()
                .id(UUID.randomUUID())
                .nom("Recette 1")
                .ingredients(Arrays.asList("ingredient1", "ingredient2"))
                .instructions("Instructions de la recette 1")
                .categorie(categories.get(1))
                .build();

        Recette r2 = Recette
                .builder()
                .id(UUID.randomUUID())
                .nom("Recette 2")
                .ingredients(Arrays.asList("ingredient3", "ingredient4"))
                .instructions("Autres instructions de la recette 2")
                .categorie(categories.get(2))
                .build();

        recettes.add(r1);
        recettes.add(r2);
        this.categorieService = categorieService;
    }

    public List<Recette> getRecettes() {
        return recettes;
    }

    public Recette addRecette(Recette recette) {
        recette.setId(UUID.randomUUID());
        recette.setCategorie(categorieService.getCategorieById(recette.getIdCategorie()));
        recettes.add(recette);
        return recette;
    }

    public void deleteRecette(UUID recette) {
        recettes.removeIf(r -> r.getId().equals(recette));
    }

    public Recette getRecetteById(UUID id) {
        Recette recetteToFind =
                recettes
                        .stream()
                        .filter(r -> r.getId().equals(id))
                        .findFirst()
                        .orElse(null);
        return recetteToFind;
    }

    public Recette updateRecette(Recette recette) {
        Recette recetteToUpdate = getRecetteById(recette.getId());
        recetteToUpdate.setNom(recette.getNom());
        recetteToUpdate.setIngredients(recette.getIngredients());
        recetteToUpdate.setInstructions(recette.getInstructions());
        recetteToUpdate.setCategorie(categorieService.getCategorieById(recette.getIdCategorie()));
//        recetteToUpdate.setCategorie(recette.getCategorie());
        return recetteToUpdate;
    }
}
