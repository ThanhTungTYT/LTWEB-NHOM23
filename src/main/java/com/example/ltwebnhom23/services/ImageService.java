package com.example.ltwebnhom23.services;

import com.example.ltwebnhom23.dao.ImageDao;
import com.example.ltwebnhom23.model.ProductImage;

import java.util.List;

public class ImageService {

    private ImageDao i = new ImageDao();

    public List<ProductImage> getAllImageById(int pid){
        return i.getAllImageById(pid);
    }

}
