package com.company.youtubeanalyticstool.service;

import com.company.youtubeanalyticstool.exception.ResourceNotFoundException;
import com.company.youtubeanalyticstool.model.UserDAO;
import com.company.youtubeanalyticstool.model.VideoStats;
import com.company.youtubeanalyticstool.repository.UserRepository;
import com.company.youtubeanalyticstool.repository.VideoStatsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VideoStatsServiceImpl implements VideoStatsService{

    @Autowired
    VideoStatsRepository videoStatsRepository;
    @Autowired
    UserRepository userRepository;

    @Override
    public void save(VideoStats video) {
        videoStatsRepository.save(video);
    }

    @Override
    public void update(VideoStats updatedVideo) {
        videoStatsRepository.save(updatedVideo);

    }

    @Override
    public void delete(long id, String userId) {
        UserDAO userDAO = userRepository.findByUsername(userId);

        VideoStats videoStats = videoStatsRepository.getVideoStatsByIdAndUserDAO(id, userDAO);

        videoStatsRepository.delete(videoStats);
    }

    @Override
    public VideoStats get(long id, String userId) {
        UserDAO userDAO = userRepository.findByUsername(userId);

        return videoStatsRepository.getVideoStatsByIdAndUserDAO(id, userDAO);
    }

    @Override
    public List<VideoStats> getAll(String userId) {

        UserDAO userDAO = userRepository.findByUsername(userId);

        return videoStatsRepository.findAllByUserDAO(userDAO);
    }
}
