package com.dayan.cookinghub.service;

import com.dayan.cookinghub.model.Recipe;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CookingHubService{

    Page<Recipe> getAllRecipes(Pageable pageable);

    Recipe findByTitle(String title);

    Recipe addRecipe(Recipe recipe);
}
