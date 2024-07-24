package org.example.exocuisinespring.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Recette {
    private UUID id;

    @NotBlank (message = "Il faut un nom !")
    private String nom;
    private List<String> ingredients;

    @NotBlank (message = "Il faut des instruction !")
    private String instructions;
    @NotNull (message = "Il faut une catégorie !")
    private Categorie categorie;
    private UUID idCategorie;

}
