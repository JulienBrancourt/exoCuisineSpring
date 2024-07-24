package org.example.exocuisinespring.model;

import jakarta.validation.constraints.NotBlank;
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

    @NotBlank(message = "Il faut un nom !")
    private String nom;

    @NotBlank (message = "Il faut un nom !")
    private String description;

    @Override
    public String toString() {
        return "{" +
                "nom='" + nom + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
