package com.company.youtubeanalyticstool.service;

import com.company.youtubeanalyticstool.model.ChannelStats;

import java.util.List;

public interface ChannelStatsService {
    void save(ChannelStats video);
    void update(ChannelStats updatedVideo);
    void delete(long id, String userId);
    ChannelStats get(long id, String userId);
    List<ChannelStats> getAll(String userId);
}

