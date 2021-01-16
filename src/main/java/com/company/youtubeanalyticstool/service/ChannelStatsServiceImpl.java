package com.company.youtubeanalyticstool.service;

import com.company.youtubeanalyticstool.exception.ResourceNotFoundException;
import com.company.youtubeanalyticstool.model.videos.ChannelStats;
import com.company.youtubeanalyticstool.repository.ChannelStatsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChannelStatsServiceImpl implements ChannelStatsService{

    @Autowired
    ChannelStatsRepository channelStatsRepository;

    @Override
    public void save(ChannelStats channel) {
        channelStatsRepository.save(channel);
    }

    @Override
    public void update(ChannelStats updatedChannel) {
        channelStatsRepository.save(updatedChannel);

    }

    @Override
    public void delete(long id) {
        channelStatsRepository.delete(get(id));
    }

    @Override
    public ChannelStats get(long id) {
        return channelStatsRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Channel", "id", id));
    }

    @Override
    public List<ChannelStats> getAll() {
        return channelStatsRepository.findAll();
    }
}
