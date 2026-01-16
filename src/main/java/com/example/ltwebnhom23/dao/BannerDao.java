package com.example.ltwebnhom23.dao;

import com.example.ltwebnhom23.model.Banner;

import java.util.List;

public class BannerDao extends BaseDao{

    public List<Banner> getAllBaner(){
        return getJdbi().withHandle(handle ->
            handle.createQuery("SELECT * FROM banners")
                    .mapToBean(Banner.class)
                    .list()
        );
    }
    public List<Banner> getBannerActive(){
        return getJdbi().withHandle(handle ->
            handle.createQuery("SELECT * FROM banners WHERE start_date <= NOW() AND end_date >= NOW()")
                    .mapToBean(Banner.class)
                    .list()
        );
    }

}
