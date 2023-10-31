package com.example.restapi.controller;

import com.example.restapi.model.File;
import com.example.restapi.service.FileService;
import com.google.gson.Gson;
import lombok.RequiredArgsConstructor;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@RequiredArgsConstructor
@WebServlet("/api/v1/files")
public class FileController extends HttpServlet {

    private final FileService fileService;
    private Gson gson;
    public void init() {
        gson = new Gson();
    }
    @Override
    public void doPut(HttpServletRequest request, HttpServletResponse response) throws IOException {
        File file = gson.fromJson(request.getReader(), File.class);
        fileService.updateFileById(file, file.getId());
        response.setContentType("application/json");
        PrintWriter out = response.getWriter();
        out.flush();
    }
    @Override
    public void doDelete(HttpServletRequest request, HttpServletResponse response) throws IOException {
        File file = gson.fromJson(request.getReader(), File.class);
        fileService.deleteFileById(file.getId());
        response.setContentType("application/json");
        PrintWriter out = response.getWriter();
        out.flush();
    }
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        File file = gson.fromJson(request.getReader(), File.class);
        fileService.createNewFile(file);
        response.setContentType("application/json");
        PrintWriter out = response.getWriter();
        out.flush();
    }
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        File file = gson.fromJson(request.getReader(), File.class);
        Integer id = file.getId();
        File fileId = fileService.getByIdFiles(id);
        response.setContentType("application/json");
        PrintWriter out = response.getWriter();
        out.print(fileId);
        out.flush();
    }

}
