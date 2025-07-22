package com.example.demo.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Item;
import com.example.demo.service.ItemService;
import com.example.demo.util.ResponseEntityUtil;
import com.example.demo.validation.ItemValidation;
import com.example.demo.service.ItemServiceAnalysis;

@RestController
@RequestMapping("/demo/v1") // Base path for all endpoints in this controller

// TODO: SpringBoot:Practical 1
// Create a new Spring Boot Project as per guide
// Create a new RestController below and update your own GitHub local repo
// Add DOGET, DOPOST, DODELETE and DOPUT function as below 
// Ensure it's working with Postman
// Upload your code your Git Hub

// Hint: Create a HelloController as per sample and test ensure your springboot is working accordingly
// use browser to hit http://localhost:8080/

//TODO: SpringBoot:Practical 2
// Loose coupling design - Related slide (Microservices)

// Discuss in a group to identify 2-3 items below are duplicated and how to improve
// Brainstorm it together and exchange idea
// Create a new class to decouple the logic below

public class CRUDController {

	private final ItemService itemService;
	private final ItemServiceAnalysis itemServiceAnalysis;

    public CRUDController(ItemService itemService, ItemServiceAnalysis itemServiceAnalysis) {
        this.itemService = itemService;
		this.itemServiceAnalysis = itemServiceAnalysis;
    }

    // --- CREATE (Auto-generated ID) ---
    @PostMapping
    public ResponseEntity<String> createItem(@RequestBody String newItemName) {
        ItemValidation.validateItemName(newItemName);
        Item item = itemService.createItem(newItemName);
        return ResponseEntityUtil.buildResponse("Item created successfully with ID: " + item.id() + " and data: " + item.value(), HttpStatus.CREATED);
    }

    // --- READ (All Items) ---
    @GetMapping
    public ResponseEntity<List<Item>> getAllItems() {
        return ResponseEntity.ok(itemService.getAllItems());
    }

    // --- READ (By ID) ---
    @GetMapping("/{id}")
    public ResponseEntity<String> getItemById(@PathVariable Long id) {
        return itemService.getItemById(id)
                .map(item -> ResponseEntityUtil.buildResponse("Found item with ID: " + item.id() + " and data: " + item.value(), HttpStatus.OK))
                .orElseGet(() -> ResponseEntityUtil.buildResponse("Item with ID: " + id + " not found.", HttpStatus.NOT_FOUND));
    }
    
// // --- READ (By ID) ---
//    @GetMapping("/exception/{id}")
//    public ResponseEntity<String> getItemByIdEx(@PathVariable String id) {
//    	ItemValidation.parseAndValidateLongId(id);
//    	
//        return itemService.getItemById(Long.valueOf(id))
//                .map(item -> ResponseEntityUtil.buildResponse("Found item with ID: " + item.id() + " and data: " + item.value(), HttpStatus.OK))
//                .orElseGet(() -> ResponseEntityUtil.buildResponse("Item with ID: " + id + " not found.", HttpStatus.NOT_FOUND));
//    }

    // --- UPDATE ---
    @PutMapping("/{id}")
    public ResponseEntity<String> updateItem(@PathVariable Long id, @RequestBody String updatedName) {
        ItemValidation.validateItemName(updatedName);
        return itemService.updateItem(id, updatedName)
                .map(item -> ResponseEntityUtil.buildResponse("Item with ID: " + item.id() + " updated successfully to: " + item.value(), HttpStatus.OK))
                .orElseGet(() -> ResponseEntityUtil.buildResponse("Item with ID: " + id + " not found for update.", HttpStatus.NOT_FOUND));
    }

    // --- DELETE ---
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteItem(@PathVariable Long id) {
        return itemService.deleteItem(id)
                ? ResponseEntityUtil.buildResponse("Item with ID: " + id + " deleted successfully.", HttpStatus.NO_CONTENT)
                : ResponseEntityUtil.buildResponse("Item with ID: " + id + " not found for deletion.", HttpStatus.NOT_FOUND);
    }

	
	// --- GET ID WITH PASSING PARAMETER OF "demo" ---
	@GetMapping("/demoOnly")
	public ResponseEntity<List<Item>> getItemsWithDemo() {
		return ResponseEntity.ok(itemServiceAnalysis.getAllItemsWithDemo());
    }
	
}
