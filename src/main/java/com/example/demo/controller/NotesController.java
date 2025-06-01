package com.example.demo.controller;

import java.util.List;

import com.example.demo.service.NotesService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.domain.api.Note;

@RestController("/notes")
public class NotesController {
	
	Logger logger = LoggerFactory.getLogger(NotesController.class);

	// Use the appropriate service based on the active profile
	private NotesService notesService;

    public NotesController(NotesService notesService) {
        this.notesService = notesService;
    }

    @GetMapping("/id/{id}")
	public ResponseEntity<Note> getNoteById(@PathVariable String id) {
		logger.info("GET endpoint is called to get note having id: {}", id);
		Note note = notesService.getNote(id);
		logger.info("Response Body: {}", note);
		return new ResponseEntity<>(note, HttpStatus.OK);
	}
	
	@GetMapping("/titles/{title}")
	public ResponseEntity<List<Note>> getNotesByTitle(@PathVariable String title) {
		logger.info("GET endpoint is called to get all notes having title: {}", title);
		List<Note> notes = notesService.getNotesByTitle(title);
		logger.info("Response Body: {}", notes);
		return new ResponseEntity<>(notes, HttpStatus.OK);
	}

	
	@PostMapping("/add")
	public ResponseEntity<Note> addNote(@RequestBody Note note) {
		logger.info("POST endpoint is called to add note");
		logger.info("Request Body: {}", note);
		Note noteInDB = notesService.addNote(note);
		logger.info("Response Body: {}", noteInDB);
		return new ResponseEntity<>(noteInDB, HttpStatus.CREATED);
	}

	@GetMapping("/healthcheck")
	public HttpStatus getHealth() {
		return HttpStatus.OK;
	}
}
