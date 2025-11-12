package com.ai.SpringAIProject.service;

import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class RecipeService {
    private final ChatModel chatModel;

    public RecipeService(ChatModel chatModel) {
        this.chatModel = chatModel;
    }

    public String createRecipe(String ingredients, String cuisine, String dietaryRestrictions, String goal) {
        var template = """
                You are a professional fitness nutritionist and chef.
                Create a healthy recipe suitable for the goal: {goal}.
                Use the following ingredients: {ingredients}.
                Cuisine type: {cuisine}.
                Dietary restrictions: {dietaryRestrictions}.
                
                Please include the following fields:
                Title, Calories, Protein, Carbs, Fats, Ingredients, Instructions.
                
                The recipe should be optimized for the fitness goal,
                and use only healthy cooking methods (boiling, grilling, air-frying, etc.).
                """;
        PromptTemplate promptTemplate = new PromptTemplate(template);
        Map<String, Object> params = Map.of(
                "ingredients", ingredients,
                "cuisine", cuisine,
                "dietaryRestrictions",dietaryRestrictions,
                "goal", goal
        );

        Prompt prompt = promptTemplate.create(params);
        return chatModel.call(prompt).getResult().getOutput().getText();
    }
}
