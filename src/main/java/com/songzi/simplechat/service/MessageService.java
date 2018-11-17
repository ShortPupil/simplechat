package com.songzi.simplechat.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.songzi.simplechat.repository.MessageRepository;

@Service
public class MessageService {
    @Autowired
    MessageRepository mr;

   // public Message1
}
