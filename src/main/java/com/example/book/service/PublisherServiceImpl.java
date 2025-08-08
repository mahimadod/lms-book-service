package com.example.book.service;

import com.example.book.entity.Publisher;
import com.example.book.repository.PublisherRepository;
import com.example.exception_handler.LMSServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PublisherServiceImpl implements PublisherService {

    @Autowired
    private PublisherRepository publisherRepository;

    @Override
    public Publisher createPublisher(Publisher publisher) {
        if (publisherRepository.existsByName(publisher.getName())) {
            throw new LMSServiceException(HttpStatus.CONFLICT,"Publisher with this name already exists");
        }
        return publisherRepository.save(publisher);
    }

    @Override
    public List<Publisher> getAllPublishers() {
        return publisherRepository.findAll();
    }
}
