package com.company.youtubeanalyticstool.controller;

import com.company.youtubeanalyticstool.model.ChannelStats;
import com.company.youtubeanalyticstool.model.VideoStats;
import com.company.youtubeanalyticstool.service.ChannelStatsService;
import com.company.youtubeanalyticstool.service.YouTubeAPIService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.IOException;
import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api")
public class ChannelStatsController {

    @Autowired
    YouTubeAPIService youTubeAPIService;
    @Autowired
    ChannelStatsService channelStatsService;

    @PostMapping("/channel")
    public ChannelStats saveChannelStats(@Valid @RequestBody String channelId) throws IOException {
        return youTubeAPIService.saveChannelStats(channelId);
    }

    @PostMapping("/channel/{id}")
    public List<VideoStats> saveChannelVideoStats(@PathVariable long id, Principal principal) throws IOException {
        return youTubeAPIService.saveAllChannelVideos(id, principal.getName());
    }

    @GetMapping("/channel")
    public List<ChannelStats> getAllChannels(){
        return channelStatsService.getAll();
    }
    @GetMapping("/channel/{id}")
    public ChannelStats getChannelStats(@PathVariable long id){
        return channelStatsService.get(id);
    }

    @PutMapping("/channel/{id}")
    public ChannelStats updateChannelStats(@PathVariable long id) throws IOException {
        return youTubeAPIService.updateChannel(id);
    }

    @PutMapping("/channel")
    public List<ChannelStats> updateAllChannels() throws IOException {
        return youTubeAPIService.updateAllChannels();
    }

    @RequestMapping("/helloadmin")
    public String getAdmin()
    {
        return "Hello Admin";
    }

}
