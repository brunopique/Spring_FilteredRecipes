package com.brunopique.assignment8.web;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.brunopique.assignment8.domain.Recipe;
import com.brunopique.assignment8.service.RecipeService;

@RestController
public class RecipeController {
	
	@Autowired
	private RecipeService recipeService;
	
	@GetMapping("/gluten-free")
	public String getGlutenFreeRecipes() {
		Map<Integer, List<Recipe>> recipesGlutenFree = recipeService.getRecipes().stream()
																	// check Boolean against var, no the other way around, to avoid null pointer
																   .filter(recipe -> Boolean.TRUE.equals(recipe.getGlutenFree()))
																   .collect(Collectors.groupingBy(Recipe::getCookingMinutes));
		return recipesGlutenFree.toString();
	}

	@GetMapping("/vegan")
	public String getVeganRecipes() {
		Map<Integer, List<Recipe>> recipesVegan = recipeService.getRecipes().stream()
															   .filter(recipe -> Boolean.TRUE.equals(recipe.getVegan()))
															   .collect(Collectors.groupingBy(Recipe::getCookingMinutes));
		return recipesVegan.toString();
	}
	

	@GetMapping("/vegan-and-gluten-free")
	public String getGlutenFreeAndVeganRecipes() {
		Map<Integer, List<Recipe>> recipesGlutenFreeVegan = recipeService.getRecipes().stream()
																	.filter(recipe -> Boolean.TRUE.equals(recipe.getGlutenFree()) && Boolean.TRUE.equals(recipe.getVegan()))
																	.collect(Collectors.groupingBy(Recipe::getCookingMinutes));
		return recipesGlutenFreeVegan.toString();
	}

	@GetMapping("/vegetarian")
	public String getVegetarianRecipes() {
		Map<Integer, List<Recipe>> recipesVegetarian = recipeService.getRecipes().stream()
																	.filter(recipe -> Boolean.TRUE.equals(recipe.getVegetarian()))
																	.collect(Collectors.groupingBy(Recipe::getCookingMinutes));
		return recipesVegetarian.toString();
	}

	@GetMapping("/all-recipes")
	public String getRecipes() {
		return recipeService.getRecipes().toString();
	}
}