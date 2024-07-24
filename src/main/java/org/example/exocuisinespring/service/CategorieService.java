package org.example.exocuisinespring.service;

import lombok.Getter;
import org.example.exocuisinespring.model.Categorie;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


@Service
public class CategorieService {
    private List<Categorie> categories;

    public CategorieService() {
        categories = new ArrayList<Categorie>();
        Categorie c1 = Categorie
                .builder()
                .id(UUID.randomUUID())
                .nom("entrées")
                .description("des entrées")
                .build();

        Categorie c2 = Categorie
                .builder()
                .id(UUID.randomUUID())
                .nom("plats")
                .description("des plats")
                .build();

        Categorie c3 = Categorie
                .builder()
                .id(UUID.randomUUID())
                .nom("desserts")
                .description("des desserts")
                .build();

        categories.add(c1);
        categories.add(c2);
        categories.add(c3);
    }

    public List<Categorie> getCategories() {
        return categories;
    }

    public Categorie getCategorieById(UUID id) {
        return (categories.stream().filter(c -> c.getId().equals(id)).findFirst().orElse(null));
    }

    public Categorie addCategorie(Categorie categorie) {
        categorie.setId(UUID.randomUUID());
        categories.add(categorie);
        return categorie;
    }

    public void deleteCategorie(UUID categorie) {
        categories.removeIf(c -> c.getId().equals(categorie));
    }

    public Categorie updateCategorie(Categorie categorie) {
        Categorie categorieToUpdate = getCategorieById(categorie.getId());
        categorieToUpdate.setDescription(categorie.getDescription());
        categorieToUpdate.setNom(categorie.getNom());
        return categorieToUpdate;
    }
}
