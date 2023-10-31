package com.example.restapi.controller;

import com.example.restapi.model.User;
import com.example.restapi.service.UserService;
import com.google.gson.Gson;
import lombok.RequiredArgsConstructor;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
@RequiredArgsConstructor
@WebServlet("/api/v1/users")
public class UserController extends HttpServlet {

    private final UserService userService;
    private Gson gson;
    public void init(){
        gson= new Gson();
    }
    @Override
    public void doPut(HttpServletRequest request, HttpServletResponse response) throws IOException {
        User user = gson.fromJson(request.getReader(), User.class);
        userService.updateUserById(user, user.getId());
        response.setContentType("application/json");
        PrintWriter out = response.getWriter();
        out.flush();
    }

    @Override
    public void doDelete(HttpServletRequest request, HttpServletResponse response) throws  IOException {
        User user = gson.fromJson(request.getReader(), User.class);
        userService.deleteUserById(user.getId());
        response.setContentType("application/json");
        PrintWriter out = response.getWriter();
        out.flush();
    }
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        User user = gson.fromJson(request.getReader(), User.class);
        userService.createNewUser(user);
        response.setContentType("application/json");
        PrintWriter out = response.getWriter();
        out.flush();
    }
   @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        User user = gson.fromJson(request.getReader(), User.class);
        Integer id = user.getId();
            User userId = userService.getByIdUsers(id);
            response.setContentType("application/json");
            PrintWriter out = response.getWriter();
            out.print(userId);
            out.flush();
        }
}
