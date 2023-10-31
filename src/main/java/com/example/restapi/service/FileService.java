package com.example.restapi.service;

import com.example.restapi.model.File;
import com.example.restapi.repository.apiRepository.FileRepositoryImpl;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class FileService {

    private final FileRepositoryImpl fileRepository;
    public List<File> getAllFiles() {
        return fileRepository.getAll();
    }
    public boolean deleteFileById(Integer id) {
        fileRepository.deleteById(id);
        return true;
    }
    public File getByIdFiles(Integer id) {
        return fileRepository.getId(id);
    }
    public File updateFileById(File update, Integer id) {
        return fileRepository.update(update, id);
    }
    public File createNewFile(File create){
        return fileRepository.save(create);
    }
}
