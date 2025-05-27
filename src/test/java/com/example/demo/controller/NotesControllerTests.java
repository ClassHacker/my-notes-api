package com.example.demo.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import com.example.demo.domain.api.Note;
import com.example.demo.service.NotesDynamoDbService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class NotesControllerTests {

    @Mock
    private NotesDynamoDbService notesDynamoDbService;

    @InjectMocks
    private NotesController notesController;

    private Note note;
    private List<Note> notes;

    @BeforeEach
    public void setup() {
        note = new Note();
        note.setId("1");
        note.setTitle("Test Title");
        note.setSubTitle("Test Subtitle");
        note.setContent(Arrays.asList("Content1", "Content2"));

        notes = Arrays.asList(note);
    }

    @Test
    public void testGetNoteById() {
        when(notesDynamoDbService.getNote("1")).thenReturn(note);

        Note result = notesController.getNoteById("1").getBody();

        assertEquals(note, result);
        verify(notesDynamoDbService).getNote("1");
    }

    @Test
    public void testGetNotesByTitle() {
        when(notesDynamoDbService.getNotesByTitle("Test Title")).thenReturn(notes);

        List<Note> result = notesController.getNotesByTitle("Test Title").getBody();

        assertEquals(notes, result);
        verify(notesDynamoDbService).getNotesByTitle("Test Title");
    }

    @Test
    public void testAddNote() {
        when(notesDynamoDbService.addNote(note)).thenReturn(note);

        Note result = notesController.addNote(note).getBody();

        assertEquals(note, result);
        verify(notesDynamoDbService).addNote(note);
    }

    @Test
    public void testGetHealth() {
        String result = notesController.getHealth().name();

        assertEquals("OK", result);
    }
}