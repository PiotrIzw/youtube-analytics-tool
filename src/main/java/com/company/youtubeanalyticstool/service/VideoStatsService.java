package com.company.youtubeanalyticstool.service;

import com.company.youtubeanalyticstool.model.VideoStats;

import java.util.List;

public interface VideoStatsService {
    void save(VideoStats video);
    void update(VideoStats updatedVideo);
    void delete(long id);
    VideoStats get(long id);
    List<VideoStats> getAll();
}
