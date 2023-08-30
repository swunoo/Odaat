package dev.java.odaat.Controller;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import dev.java.odaat.Entity.Note;
import dev.java.odaat.Service.NoteService;

@Controller
@RequestMapping("/note")
public class NoteController {
    @Autowired
    NoteService noteService;

    @GetMapping
    public String getAllNotes(Model model){

        // TMP: mock data
        List<Note> mockNotes = Arrays.asList(
            new Note(LocalDate.now(), LocalDate.now(), "Lorem Ipsum", "Lorem, ipsum dolor sit amet consectetur adipisicing elit. Enim, cupiditate!"),
            new Note(LocalDate.now(), LocalDate.now(), "Lorem Ipsum", "Lorem, ipsum dolor sit amet consectetur adipisicing elit. Enim, cupiditate!"),
            new Note(LocalDate.now(), LocalDate.now(), "Lorem Ipsum", "Lorem, ipsum dolor sit amet consectetur adipisicing elit. Enim, cupiditate!")
        );
        for(Note note : mockNotes){
            noteService.add(note);
        }

        List<Note> notes = noteService.getAll();
        model.addAttribute("notes", notes);
        return "index";
    }
}
