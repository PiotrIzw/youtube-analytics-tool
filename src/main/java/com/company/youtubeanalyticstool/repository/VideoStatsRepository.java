package com.company.youtubeanalyticstool.repository;

import com.company.youtubeanalyticstool.model.UserDAO;
import com.company.youtubeanalyticstool.model.VideoStats;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VideoStatsRepository extends JpaRepository<VideoStats, Long> {
    public VideoStats getVideoStatsByIdAndUserDAO(long id, UserDAO userDAO);
    public void deleteVideoStatsByIdAndUserDAO(long id, UserDAO userDAO);
    List<VideoStats> findAllByUserDAO(UserDAO userDAO);
}
