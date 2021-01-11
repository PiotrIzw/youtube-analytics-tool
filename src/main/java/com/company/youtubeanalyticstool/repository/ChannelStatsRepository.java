package com.company.youtubeanalyticstool.repository;

import com.company.youtubeanalyticstool.model.ChannelStats;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChannelStatsRepository extends JpaRepository<ChannelStats, Long> {
}
