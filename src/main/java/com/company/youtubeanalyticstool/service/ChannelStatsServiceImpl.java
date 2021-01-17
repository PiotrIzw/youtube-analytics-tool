package com.company.youtubeanalyticstool.service;

import com.company.youtubeanalyticstool.exception.ResourceNotFoundException;
import com.company.youtubeanalyticstool.model.ChannelStats;
import com.company.youtubeanalyticstool.model.UserDAO;
import com.company.youtubeanalyticstool.repository.ChannelStatsRepository;
import com.company.youtubeanalyticstool.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChannelStatsServiceImpl implements ChannelStatsService{

    @Autowired
    ChannelStatsRepository channelStatsRepository;
    @Autowired
    UserRepository userRepository;

    @Override
    public void save(ChannelStats channel) {
        channelStatsRepository.save(channel);
    }

    @Override
    public void update(ChannelStats updatedChannel) {
        channelStatsRepository.save(updatedChannel);

    }

    @Override
    public void delete(long id, String userId) {
        channelStatsRepository.delete(get(id, userId));
    }

    @Override
    public ChannelStats get(long id, String userId) {
        ChannelStats channelStats = channelStatsRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Channel", "id", id));

        if (channelStats.getUserDAO().getUsername().equals(userId)) {
            return channelStats;
        }
        else
            return null;
    }

    @Override
    public List<ChannelStats> getAll(String userId) {
        UserDAO user = userRepository.findByUsername(userId);
        return channelStatsRepository.findAllByUserDAO(user);
    }
}
