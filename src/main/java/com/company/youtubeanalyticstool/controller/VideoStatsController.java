package com.company.youtubeanalyticstool.controller;

import com.company.youtubeanalyticstool.model.VideoStats;
import com.company.youtubeanalyticstool.service.VideoStatsService;
import com.company.youtubeanalyticstool.service.YouTubeAPIService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.io.IOException;
import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api")
public class VideoStatsController {

    @Autowired
    YouTubeAPIService youTubeAPIService;

    @Autowired
    VideoStatsService videoStatsService;


    @PostMapping("/video")
    public VideoStats saveVideoStatistics(@Valid @RequestBody String videoID, Principal principal) throws IOException {

        return youTubeAPIService.saveVideoStats(videoID, principal.getName());
    }

    @GetMapping("video/{id}")
    public VideoStats getVideoStatistics(@PathVariable long id, Principal principal){
        return videoStatsService.get(id, principal.getName());
    }

    @GetMapping("/video")
    public List<VideoStats> getAllVideosStatistics(Principal principal){
        return videoStatsService.getAll(principal.getName());
    }

    @PutMapping("/video/{id}")
    public VideoStats updateVideoStatistics(@PathVariable long id, Principal principal) throws IOException {
        return youTubeAPIService.updateVideoStats(id, principal.getName());

    }
    @DeleteMapping("/video/{id}")
    public void deleteVideoStatistics(@PathVariable long id, Principal principal){
        videoStatsService.delete(id, principal.getName());
    }

}
