package com.example.demo.service;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;


import org.springframework.stereotype.Service;

import com.example.demo.model.Data;
import com.example.demo.model.Item;

//TODO: SpringBoot:Practical 5 (Group) - Search function restful services

//Discuss in a group 
//How to modify the existing Spring Boot to search input words
//Discuss why use DO POST or DO GET or vice versa for the case below?

//What you need to do

//Add a new method to restful controller then invoke the ItemServiceAnalysis 
//Create a new service call ItemServiceAnalysis
//Complete the method getAllItemsWithDemo()
//return only the value with "demo" wording
//Create a Unit Testing to test the method getAllItemsWithDemo()

//Hint: Refer to the ItemService.java, Use the getAllItems with passing parameter of "demo" and return objects with "demo" only
@Service
public class ItemServiceAnalysis {
	

	// TODO: Complete the code below
	public List<Item> getAllItemsWithDemo() {
		List<Item> i = Data.getDataStore().entrySet().stream()
        .map(entry -> new Item(entry.getKey(), entry.getValue()))
		//.filter(item -> item.value() != null && item.value().toLowerCase().contains("demo"))
		.collect(Collectors.toList()); 
		
		return Data.getDataStore().entrySet().stream()
	            .map(entry -> new Item(entry.getKey(), entry.getValue()))
				.filter(item -> item.value() != null && item.value().toLowerCase().contains("demo"))
				.collect(Collectors.toList());
	}

}
