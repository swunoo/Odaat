package dev.java.odaat.Controller;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import dev.java.odaat.Entity.Note;
import dev.java.odaat.Service.NoteService;

@RestController
@RequestMapping("/api/v1/note")
public class NoteController {
    @Autowired
    NoteService noteService;

    @GetMapping(path = "/getAll")
    public List<Note> getAllNotes(){
        return noteService.getAll();
    }

    @GetMapping(path = "/get/{id}")
    public Note getNote(@PathVariable("id") Integer id){
        return noteService.getOne(id);
    }

}
