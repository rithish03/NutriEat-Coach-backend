package com.ai.SpringAIProject.controller;

import com.ai.SpringAIProject.service.ChatService;
import com.ai.SpringAIProject.service.RecipeService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GenAIController {
    private final ChatService chatService;
    private final RecipeService recipeService;

    public GenAIController(ChatService chatService, RecipeService recipeService) {
        this.chatService = chatService;
        this.recipeService = recipeService;
    }

    @GetMapping("/ask-ai")
    public String getResponse(@RequestParam String prompt) {
        return chatService.getResponse(prompt);
    }

    @GetMapping("ask-ai-options")
    public String getResponseOptions(@RequestParam String prompt) {
        return chatService.getResponseOptions(prompt);
    }

    @GetMapping("recipe-creator")
    public String recipeCreator(@RequestParam String ingredients, @RequestParam(defaultValue = "any") String cuisine, @RequestParam(defaultValue = "") String dietaryRestrictions, @RequestParam(defaultValue = "Weight Loss") String goal){
        return recipeService.createRecipe(ingredients, cuisine, dietaryRestrictions, goal);
    }
}
