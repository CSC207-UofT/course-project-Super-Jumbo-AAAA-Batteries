package com.example.webapp_connection.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import controller.ListManager;
import driver.LogInSystem;
import gateway.LoadingManager;

import java.text.SimpleDateFormat;
import java.time.LocalDate;

@RestController
public class WebController {
    String currentUser;
    ListManager listManager;

    public WebController() {
        currentUser = LogInSystem.logInSys(); // this call initiates the login system
        listManager = new ListManager(LoadingManager.InventoryListLoader(currentUser), LoadingManager.ShoppingListLoader(currentUser));
    }

    @PostMapping("/add")
    public String addItemToShoppingList(@RequestParam String name, @RequestParam int quantity) {
        listManager.addShoppingListItem(name, quantity);
        return "Successfully added the item to your shopping list.";
    }

    @PostMapping("/remove")
    public String RemoveItemFromShoppingList(@RequestParam int index) {
        listManager.removeShoppingListItem(index);
        return "Successfully removed the item from your shopping list.";
    }

    @GetMapping("/list")
    public String getShoppingListItems() {
        return listManager.printShoppingList();
    }

    @PostMapping("/add_inventory")
    public String addItemToInventoryList(@RequestParam String name, @RequestParam int quantity, @RequestParam String expiryDate) {
        listManager.addInventoryItem(name, quantity, expiryDate);
        return "Successfully added the item to your inventory list.";
    }

    @PostMapping("/remove_inventory")
    public String RemoveItemFromInventoryList(@RequestParam int index) {
        listManager.removeInventoryItem(index);
        return "Successfully removed the item from your inventory list.";
    }

    @GetMapping("/list_inventory")
    public String getInventoryListItems() {
        return listManager.printInventory();
    }


}