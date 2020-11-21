package com.api.service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.model.Topic;
import com.api.model.TopicStatus;
import com.api.repository.TopicRepository;

@Service
public class TopicService {
	
	@Autowired 
	private TopicRepository repo;
	
	public List<Topic> getTopics() {
		List<Topic> topics = StreamSupport.stream(repo.findAllOrderedByBump().spliterator(), false)
			    .collect(Collectors.toList());
		return topics;
	}
	
	public Topic getById(long id) {
		return repo.findById(id);
	}
	
	public Topic insert(Topic topic) throws Exception {
		topic.creationDate = new Date();
		topic.status = TopicStatus.OPEN;
		return repo.save(topic);
	}
	
	public Topic upate(Topic oldData, Topic newData, long id) throws Exception {
		if(oldData.status == TopicStatus.CLOSED)
			throw new Exception("Tópico indisponível");
		
		oldData.subject = newData.subject;
		oldData.content = newData.content;
		oldData.updateDate = new Date();
		return repo.save(oldData);
	}
	
	public void delete(Topic t) throws Exception {
		if(t.status == TopicStatus.CLOSED)
			throw new Exception("Tópico indisponível");
		
		t.status = TopicStatus.CLOSED;
		repo.save(t);
	}
}
