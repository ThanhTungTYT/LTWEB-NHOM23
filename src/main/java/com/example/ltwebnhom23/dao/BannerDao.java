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

    public boolean addBanner(Banner banner){
        return getJdbi().withHandle(handle ->
            handle.createUpdate("INSERT INTO banners(banner_url, status, start_date, end_date) VALUES(:banner_url, :status, :start, :end)")
                    .bind("banner_url", banner.getBanner_url())
                    .bind("status", banner.getStatus())
                    .bind("start", banner.getStart_date())
                    .bind("end", banner.getEnd_date())
                    .execute() > 0
        );
    }

    public boolean updateBanner(int bid, Banner bannner){
        return getJdbi().withHandle(handle ->
            handle.createUpdate("UPDATE banners SET banner_url = :banner, status = :status, start_date = :start, end_date = :end WHERE id = :bid")
                    .bind("banner", bannner.getBanner_url())
                    .bind("status", bannner.getStatus())
                    .bind("start", bannner.getStart_date())
                    .bind("end", bannner.getEnd_date())
                    .bind("bid", bid)
                    .execute() > 0
        );
    }

}
