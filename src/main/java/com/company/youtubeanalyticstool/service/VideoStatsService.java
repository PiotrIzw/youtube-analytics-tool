package com.company.youtubeanalyticstool.service;

import com.company.youtubeanalyticstool.model.VideoStats;

import java.util.List;

public interface VideoStatsService {
    void save(VideoStats video);
    void update(VideoStats updatedVideo);
    void delete(long id, String userId);
    VideoStats get(long id, String userId);
    List<VideoStats> getAll(String userId);
}
