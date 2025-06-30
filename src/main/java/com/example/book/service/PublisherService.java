package com.example.book.service;

import com.example.book.entity.Publisher;

import java.util.List;

public interface PublisherService {
    Publisher createPublisher(Publisher publisher);
    List<Publisher> getAllPublishers();
}
