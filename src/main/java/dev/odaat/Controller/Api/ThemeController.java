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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import dev.odaat.Entity.Project;
import dev.odaat.Entity.Routine;
import dev.odaat.Entity.Theme;
import dev.odaat.Entity.Enums.ThemeType;
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
    public ResponseEntity<Object> getById(@PathVariable int id){
        Optional<Theme> theme = themeService.getById(id);
        if(theme.isPresent()){
            return ResponseEntity.ok(theme.get());
        } else {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body("No theme found with id: " + id);
        }
    }

    // Insert Project
    @PostMapping("/project")
    public ResponseEntity<String> createProject(
        @Validated @RequestBody Project project, BindingResult bindingResult){

        if(bindingResult.hasErrors()){
            System.out.println("Binding errors:");
            System.out.println(bindingResult.getAllErrors().toString());

            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body("Request body must be a Project object.");
        }

        return createTheme(project);
    }

    // Insert Routine
    @PostMapping("/routine")
    public ResponseEntity<String> createRoutine(
        @Validated @RequestBody Routine routine, BindingResult bindingResult){

        if(bindingResult.hasErrors()){
            System.out.println("Binding errors:");
            System.out.println(bindingResult.getAllErrors().toString());

            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body("Request body must be a Routine object.");
                    
        } else return createTheme(routine);
    }

    // Internal method to create themes.
    private ResponseEntity<String> createTheme(Theme theme){

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
    @PutMapping("/project")
    public ResponseEntity<String> updateProject(
        @Validated @RequestBody Project project, BindingResult bindingResult){

        if(bindingResult.hasErrors()){
            System.out.println("Binding errors:");
            System.out.println(bindingResult.getAllErrors().toString());

            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body("Request body must be a Project object.");
                    
        } else return update(project);
    }

    @PutMapping("/routine")
    public ResponseEntity<String> updateRoutine(
        @Validated @RequestBody Routine routine, BindingResult bindingResult){

            System.out.println(routine.getRepeatedOn());

        if(bindingResult.hasErrors()){
            System.out.println("Binding errors:");
            System.out.println(bindingResult.getAllErrors().toString());

            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body("Request body must be a Routine object.");
                    
        } else return update(routine);
    }

    private ResponseEntity<String> update(Theme theme){

        System.out.println("==========");
        System.out.println("Received internally:");
        System.out.println(theme);
        System.out.println("==========");

        if(theme.getId()  == 0){
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body("Theme ID is invalid.");
        }

        Optional<Theme> updatedTheme = themeService.update(theme.getId(), theme);
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
    public ResponseEntity<String> delete(@PathVariable int id){

        System.out.println("--------------------");
        System.out.println("DELETE");
        System.out.println(id);
        System.out.println("--------------------");
        
        try {
            themeService.delete(id);
            return ResponseEntity
                .status(HttpStatus.OK)
                .body("Successfully deleted.");

        } catch (Exception e) {
            System.out.println(e.getMessage());
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Unable to delete.");
        }
}
}
