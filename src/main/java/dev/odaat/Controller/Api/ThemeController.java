package dev.odaat.Controller.Api;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import dev.odaat.Entity.Theme;
import dev.odaat.Service.ThemeService;

@RestController
@RequestMapping("/api/v1/theme")
public class ThemeController {
    
    @Autowired private ThemeService themeService;

    // GetAll
    @GetMapping("/all")
    public List<Theme> getAll(){
        return themeService.getAll();
    }

    // GetById
    @GetMapping("/{id}")
    public ResponseEntity<Object> getById(@RequestParam int id){
        Optional<Theme> theme = themeService.getById(id);
        if(theme.isPresent()){
            return ResponseEntity.ok(theme.get());
        } else {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body("No theme found with id: " + id);
        }
    }

    // Insert
    @PostMapping
    public ResponseEntity<String> create(
        @Validated @RequestBody Theme theme, BindingResult bindingResult){

        if(bindingResult.hasErrors()){
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body("Request body must be a Theme object.");
        }

        Optional<Theme> insertedTheme = themeService.insert(theme);
        if(insertedTheme.isPresent()){
            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body("Successfully created with ID: " + insertedTheme.get().getId());
        } else {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Unable to create.");
        }
    }

    // Update
    @PutMapping("/{id}")
    public ResponseEntity<String> update(
        @RequestParam int id, @Validated @RequestBody Theme theme, BindingResult bindingResult){
            
        if(bindingResult.hasErrors()){
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body("Request body must be a Theme object.");
        }

        Optional<Theme> updatedTheme = themeService.update(id, theme);
        if(updatedTheme.isPresent()){
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body("Successfully updated.");
        } else {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Unable to update.");
        }
    }

    // Delete
    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@RequestParam int id){
        
        boolean isDeleted = themeService.delete(id);
        if(isDeleted){
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body("Successfully deleted.");
        } else {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Unable to delete.");
        }
}
}
