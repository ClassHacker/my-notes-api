package com.example.demo.repository;

import com.example.demo.domain.api.Note;
import org.springframework.context.annotation.Profile;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

@Profile("mongo-db")
public interface NotesRepository extends MongoRepository<Note, String> {
    // Custom query methods (if needed)
    List<Note> findByTitle(String title);
}