package com.company.youtubeanalyticstool.service;

import com.company.youtubeanalyticstool.model.videos.ChannelStats;

import java.util.List;

public interface ChannelStatsService {
    void save(ChannelStats video);
    void update(ChannelStats updatedVideo);
    void delete(long id);
    ChannelStats get(long id);
    List<ChannelStats> getAll();
}

