package com.example.demo.repository;

import com.example.demo.domain.api.Note;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface NotesRepository extends MongoRepository<Note, String> {
    // Custom query methods (if needed)
    List<Note> findByTitle(String title);
}