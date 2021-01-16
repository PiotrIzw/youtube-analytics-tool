package com.company.youtubeanalyticstool.repository;

import com.company.youtubeanalyticstool.model.videos.VideoStats;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VideoStatsRepository extends JpaRepository<VideoStats, Long> {
}
