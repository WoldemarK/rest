package com.example.restapi.service;

import com.example.restapi.repository.apiRepository.EventRepositoryImpl;
import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
public class EventService {

    private final EventRepositoryImpl eventRepository;
}
