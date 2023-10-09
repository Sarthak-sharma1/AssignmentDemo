package com.assignment.backend.AssignmentDemo.controller;

import com.assignment.backend.AssignmentDemo.model.Items;
import com.assignment.backend.AssignmentDemo.repository.ItemsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.Optional;

@RestController
/*@RestController is a Spring Framework annotation used to indicate that a
Java class defines a RESTful web service endpoint.
*/
public class ItemController {

    @Autowired
    /*@Autowired annotation is used in Spring to automatically
    inject dependencies or beans into a class, field, or method.
    */
    ItemsRepository itemsRepository;

    /*
     * This method adds a new item to the collection.
     * @param items The item to be added, provided as a request body (JSON or XML).
     * @return A ResponseEntity containing the newly added item with HTTP status CREATED (201).
     */
    @PostMapping("/addNewItems")
    /*@PostMapping is a Spring annotation used to map HTTP POST requests onto
    specific handler methods in a controller class.
    */
    public ResponseEntity<Items> addNewItems(@RequestBody Items items){
        // Save the new item to the repository, creating a new entry.
        Items savedItems = itemsRepository.save(items);
        // Return the newly added item with HTTP status CREATED (201).
        return new ResponseEntity<>(savedItems,HttpStatus.CREATED);
    }

    /*
     * This method retrieves an item by its unique ID.
     * @param id The ID of the item to retrieve, provided as a path variable.
     * @return A ResponseEntity containing either the found item with HTTP status OK (200)
     * or an HTTP status NOT_FOUND (404) if the item does not exist.
     */
    @GetMapping("/getItemsById/{id}")
    /*
    * @GetMapping is a Spring annotation used to map HTTP GET requests onto
    * specific handler methods in a controller class.
    * */
    public ResponseEntity<Items> getItemsById(@PathVariable int id) {
        // Attempt to find an item in the repository by its ID.
        Optional<Items> itemsOptional = itemsRepository.findById(id);

        if (itemsOptional.isPresent()) {
            // If the item is found, retrieve it from the Optional.
            Items items = itemsOptional.get();
            // Return the item with HTTP status OK (200).
            return new ResponseEntity<>(items, HttpStatus.OK);
        } else {
            // If the item is not found, return an HTTP status NOT_FOUND (404).
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
