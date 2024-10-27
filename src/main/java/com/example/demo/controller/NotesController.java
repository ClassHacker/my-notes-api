package com.example.demo.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.NotesService;
import com.example.demo.domain.api.Note;

@RestController()
public class NotesController {
	
	Logger logger = LoggerFactory.getLogger(NotesController.class);
	
	@Autowired
	NotesService notesService;
	
	@GetMapping("/notes/id/{id}")
	public List<Note> getNoteById(@PathVariable String id) {
		logger.info("Retriving note having id: " + id);
		return notesService.getNote(id);
	}
	
	@GetMapping("/notes/titles/{title}")
	public List<Note> getNotesByTitle(@PathVariable String title) {
		return notesService.getNoteByTitle(title);
	}

	
	@PostMapping("/notes/add")
	public Note addNote(@RequestBody Note note) {
		return notesService.addNote(note);
	}

	@GetMapping("/healthcheck")
	public String getHealth() {
		return "OK";
	}
}
