package com.company.youtubeanalyticstool.controller;

import com.company.youtubeanalyticstool.model.VideoStats;
import com.company.youtubeanalyticstool.service.VideoStatsService;
import com.company.youtubeanalyticstool.service.YouTubeAPIService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.io.IOException;
import java.util.List;

//TODO clear tests & add proper implementations
@RestController
@RequestMapping("/api")
public class VideoStatsController {

    @Autowired
    YouTubeAPIService youTubeAPIService;

    @Autowired
    VideoStatsService videoStatsService;


    @PostMapping("/video")
    public VideoStats saveVideoStatistics(@Valid @RequestBody String videoID) throws IOException {
        return youTubeAPIService.saveVideoStats(videoID);
    }

    @GetMapping("video/{id}")
    public VideoStats getVideoStatistics(@PathVariable long id){
        return videoStatsService.get(id);
    }
    @GetMapping("/video")
    public List<VideoStats> getAllVideosStatistics(){
        return videoStatsService.getAll();
    }

    @PutMapping("/video/{id}/update")
    public VideoStats updateVideoStatistics(@PathVariable long id) throws IOException {
        return youTubeAPIService.updateVideoStats(id);

    }
    @DeleteMapping("/video/{id}")
    public void deleteVideoStatistics(@PathVariable long id){
        videoStatsService.delete(id);
    }

}
