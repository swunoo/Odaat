package dev.java.odaat.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dev.java.odaat.Entity.Note;
import dev.java.odaat.Mapper.NoteMapper;

@Service
public class NoteService {
    @Autowired
    private NoteMapper noteMapper;

    public void add(Note note){
        noteMapper.insert(note);
    }

    public List<Note> getAll(){
        return noteMapper.selectAll();
    }

    public Note getOne(int id){
        return noteMapper.selectById(id);
    }
}