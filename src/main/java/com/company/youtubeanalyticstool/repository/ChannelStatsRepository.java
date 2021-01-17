package com.company.youtubeanalyticstool.repository;

import com.company.youtubeanalyticstool.model.ChannelStats;
import com.company.youtubeanalyticstool.model.UserDAO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChannelStatsRepository extends JpaRepository<ChannelStats, Long> {
    public List<ChannelStats> findAllByUserDAO(UserDAO userDao);
}
