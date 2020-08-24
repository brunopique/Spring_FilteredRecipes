package com.brunopique.assignment8.service;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.springframework.stereotype.Service;

import com.brunopique.assignment8.domain.Recipe;

@Service
public class RecipeService {

	public List<Recipe> getRecipes() {
		
		List<Recipe> recipes = new ArrayList<>();
		Iterable<CSVRecord> records = null;
		
		// try with resources uses a 'Finally' block behind the scenes to close the reader
		try (Reader fileReader = new FileReader("recipes.txt")){
			
			records = CSVFormat.DEFAULT.withIgnoreSurroundingSpaces().withHeader().parse(fileReader);
			
			for (CSVRecord record : records) {
				Integer cookingMinutes = Integer.parseInt(record.get("Cooking Minutes"));
				Boolean dairyFree = Boolean.parseBoolean(record.get("Dairy Free"));
				Boolean glutenFree = Boolean.parseBoolean(record.get("Gluten Free"));
				String instructions = record.get("Instructions");
				Double preparationMinutes = Double.valueOf(record.get("Preparation Minutes"));
				Double pricePerServing = Double.valueOf(record.get("Price Per Serving"));
				Integer readyInMinutes = Integer.parseInt(record.get("Ready In Minutes"));
				Integer servings = Integer.parseInt(record.get("Servings"));
				Double spoonacularScore = Double.valueOf(record.get("Spoonacular Score"));
				String title = record.get("Title");
				Boolean vegan = Boolean.parseBoolean(record.get("Vegan"));
				Boolean vegetarian = Boolean.parseBoolean(record.get("Vegetarian"));
				
				recipes.add(new Recipe(cookingMinutes, dairyFree, glutenFree, instructions, 
						preparationMinutes, pricePerServing, readyInMinutes, servings, spoonacularScore, title, vegan, vegetarian));
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		return recipes;
	}	
}