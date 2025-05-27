package com.example.demo.service;

import com.example.demo.domain.api.Note;
import com.example.demo.repository.NotesRepository;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.List;

@Profile("mongo-db")
@Service()
public class NotesMongoDbService implements NotesService {

    private NotesRepository mongoRepository;

    NotesMongoDbService(NotesRepository mongoRepository) {
        this.mongoRepository = mongoRepository;
    }

    public Note getNote(String id) {
        return mongoRepository.findById(id).orElse(null);
    }

    public List<Note> getNotesByTitle(String title) {
        return mongoRepository.findByTitle(title);
    }

    public Note addNote(Note note) {
        return mongoRepository.save(note);
    }
}
