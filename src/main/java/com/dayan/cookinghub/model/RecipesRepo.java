package com.dayan.cookinghub.model;

import org.springframework.data.jpa.repository.JpaRepository;

public interface RecipesRepo  extends JpaRepository<Recipe, String>{

}
