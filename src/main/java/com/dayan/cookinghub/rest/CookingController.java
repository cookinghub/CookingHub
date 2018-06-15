package com.dayan.cookinghub.rest;


import com.dayan.cookinghub.model.Recipe;
import com.dayan.cookinghub.service.CookingHubService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/recipes")
public class CookingController {

    @Autowired
    private CookingHubService cookingHubService;

    @GetMapping("/{title}")
    public Recipe findByTitle(@PathVariable("title") String title) {
        return cookingHubService.findByTitle(title);
    }

    @GetMapping
    public Page<Recipe> findAll(Pageable pageable) {
        return cookingHubService.getAllRecipes(pageable);
    }
}
