package com.example.ltwebnhom23.services;

import com.example.ltwebnhom23.dao.BannerDao;
import com.example.ltwebnhom23.model.Banner;

import java.util.List;

public class BannerService {

    private BannerDao banner = new BannerDao();

    public List<Banner> getAllBanner(){
        return banner.getAllBaner();
    }
    public List<Banner> getBannerActive(){
        return banner.getBannerActive();
    }
    public boolean addBanner(Banner b){
        return banner.addBanner(b);
    }

}
