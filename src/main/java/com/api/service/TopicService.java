package com.api.service;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.model.Topic;
import com.api.repository.TopicRepository;

@Service
public class TopicService {
	
	@Autowired 
	private TopicRepository repo;
	
	public List<Topic> getTopics() {
		List<Topic> topics = StreamSupport.stream(repo.findAll().spliterator(), false)
			    .collect(Collectors.toList());
		return topics;
	}
	
	public Topic getById(long id) {
		return repo.findById(id);
	}
	
	public Topic insert(Topic topic) throws Exception {
		topic.creationDate = new Date();
		return repo.save(topic);
	}
}
