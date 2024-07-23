package org.example.exocuisinespring.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Categorie {
    private UUID id;
    private String nom;
    private String description;

    @Override
    public String toString() {
        return "Categorie{" +
                "nom='" + nom + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
