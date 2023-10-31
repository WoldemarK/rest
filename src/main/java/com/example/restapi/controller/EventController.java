package com.example.restapi.controller;

import com.example.restapi.model.Event;
import com.example.restapi.repository.apiRepository.EventRepositoryImpl;
import com.google.gson.Gson;
import lombok.RequiredArgsConstructor;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;


@RequiredArgsConstructor
@WebServlet("/api/v1/events")
public class EventController {

    private final EventRepositoryImpl service;
    private Gson gson;

    public void init(){
        gson = new Gson();
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws  IOException {
        Event event = gson.fromJson(request.getReader(), Event.class);
        Integer id = event.getId();
        Event eventId = service.getId(id);
            response.setContentType("application/json");
            PrintWriter out = response.getWriter();
            out.print(eventId);
            out.flush();
        }


    public void doPut(HttpServletRequest request, HttpServletResponse response) throws  IOException {
        Event event = gson.fromJson(request.getReader(), Event.class);
        service.update(event, Integer.valueOf(request.getHeader("id")));
        response.setContentType("application/json");
        PrintWriter out = response.getWriter();

        out.flush();
    }


    public void doDelete(HttpServletRequest request, HttpServletResponse response) throws  IOException {
        Event event = gson.fromJson(request.getReader(), Event.class);
        service.deleteById(event.getId());
        response.setContentType("application/json");
        PrintWriter out = response.getWriter();
        out.flush();
    }
}
