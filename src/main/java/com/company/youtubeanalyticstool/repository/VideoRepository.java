package com.company.youtubeanalyticstool.repository;

import com.company.youtubeanalyticstool.model.Video;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VideoRepository extends JpaRepository<Video, Long> {
}
