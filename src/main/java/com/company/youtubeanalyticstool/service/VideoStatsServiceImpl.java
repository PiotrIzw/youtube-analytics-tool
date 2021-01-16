package com.company.youtubeanalyticstool.service;

import com.company.youtubeanalyticstool.exception.ResourceNotFoundException;
import com.company.youtubeanalyticstool.model.videos.VideoStats;
import com.company.youtubeanalyticstool.repository.VideoStatsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VideoStatsServiceImpl implements VideoStatsService{

    @Autowired
    VideoStatsRepository videoStatsRepository;
    @Override
    public void save(VideoStats video) {
        videoStatsRepository.save(video);
    }

    @Override
    public void update(VideoStats updatedVideo) {
        videoStatsRepository.save(updatedVideo);

    }

    @Override
    public void delete(long id) {
        videoStatsRepository.delete(get(id));
    }

    @Override
    public VideoStats get(long id) {
        return videoStatsRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Video", "id", id));
    }

    @Override
    public List<VideoStats> getAll() {
        return videoStatsRepository.findAll();
    }
}
