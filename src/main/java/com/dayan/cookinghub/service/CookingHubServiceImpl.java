package com.dayan.cookinghub.service;

import com.dayan.cookinghub.model.Recipe;
import com.dayan.cookinghub.model.RecipesRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


@Service
public class CookingHubServiceImpl implements CookingHubService {

    @Autowired
    private RecipesRepo recipesRepo;


    @Override
    public Page<Recipe> getAllRecipes(Pageable pageable) {
        return recipesRepo.findAll(pageable);
    }

    @Override
    public Recipe findByTitle(String title) {
        return recipesRepo.findById(title).orElse(null);
    }

    @Override
    public Recipe addRecipe(Recipe recipe) {
        return recipesRepo.saveAndFlush(recipe);
    }

    @Override
    public void deleteRecipes() {
        recipesRepo.deleteAll();
    }

}
