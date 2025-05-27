package com.example.demo.service;

import com.example.demo.domain.api.Note;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface NotesService {
    Note getNote(String id);

    List<Note> getNotesByTitle(String title);

    Note addNote(Note note);
}
