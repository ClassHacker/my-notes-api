package com.example.demo;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.amazonaws.services.dynamodbv2.document.Item;
import com.amazonaws.services.dynamodbv2.document.ItemCollection;
import com.amazonaws.services.dynamodbv2.document.PutItemOutcome;
import com.amazonaws.services.dynamodbv2.document.QueryOutcome;
import com.amazonaws.services.dynamodbv2.document.Table;
import com.amazonaws.services.dynamodbv2.document.spec.QuerySpec;
import com.amazonaws.services.dynamodbv2.document.utils.ValueMap;
import com.example.demo.domain.api.Note;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class NotesService {

	Logger logger = LoggerFactory.getLogger(NotesService.class);


	@Autowired
	DynamoDB dynamoDB;
	
	@Autowired
	ObjectMapper om;
	
	public List<Note> getNote(String id) {
		Table table = dynamoDB.getTable("Notes");

		Map<String, Object> valueMap = new ValueMap();
		valueMap.put(":id", id);
		
		ItemCollection<QueryOutcome> items = table.query(new QuerySpec()
				.withKeyConditionExpression("id = :id")
				.withValueMap(valueMap));
		
		List<Note> notes = new ArrayList<>();
		
		for (Item item : items) {
			Note note = getObjectFromJson(item.toJSON(), Note.class);
			notes.add(note);
		}
		return notes;
	}

	public List<Note> getNoteByTitle(String title) {
		Table table = dynamoDB.getTable("Notes");

		Map<String, Object> valueMap = new ValueMap();
		valueMap.put(":title", title);
		
		ItemCollection<QueryOutcome> items = table.query(new QuerySpec()
				.withKeyConditionExpression("title = :title")
				.withValueMap(valueMap));
		
		List<Note> notes = new ArrayList<>();
		
		for (Item item : items) {
			Note note = getObjectFromJson(item.toJSON(), Note.class);
			notes.add(note);
		}
		return notes;
	}
	
	public Note addNote(Note note) {
		Table table = dynamoDB.getTable("Notes");
		
		Item item = new Item();
		
		logger.info("Request body: \n" + note);
		// Building item from note object
		item.with("id", note.getId());
		item.with("title", note.getTitle());
		item.with("subTitle", note.getSubTitle());
		item.with("content", note.getContent());
		
		PutItemOutcome outcome = table.putItem(item);
		
		logger.info("PutItemOutcom:\n" + outcome);
		
//		return getObjectFromJson(outcome.getItem().toJSON(), Note.class);
		return note;
	}
	
	private <T> T getObjectFromJson(String json, Class<T> clazz) {
		
		try {
			return om.readValue(json, clazz);
		} catch (Exception ex) {
			logger.error("Error reading json", ex);
			return null;
		}
	}
	
}
