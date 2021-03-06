package com.company.youtubeanalyticstool.controller;

import com.company.youtubeanalyticstool.model.ChannelStats;
import com.company.youtubeanalyticstool.model.VideoStats;
import com.company.youtubeanalyticstool.service.ChannelStatsService;
import com.company.youtubeanalyticstool.service.UserServiceImpl;
import com.company.youtubeanalyticstool.service.YouTubeAPIService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
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
    @Autowired
    UserServiceImpl userService;

    @PostMapping("/channel")
    public ChannelStats saveChannelStats(@Valid @RequestBody String channelId, Principal principal) throws IOException {
        return youTubeAPIService.saveChannelStats(channelId, principal.getName());
    }


    @PostMapping("/channel/{id}")
    public List<VideoStats> saveChannelVideoStats(@PathVariable long id, Principal principal) throws IOException {

        return youTubeAPIService.saveAllChannelVideos(id, principal.getName());
    }


    @GetMapping("/channel")
    public List<ChannelStats> getAllChannels(Principal principal){
        return channelStatsService.getAll(principal.getName());
    }

    @GetMapping("/channel/{id}")
    public ChannelStats getChannelStats(@PathVariable long id, Principal principal){
        return channelStatsService.get(id, principal.getName());
    }

    @PutMapping("/channel/{id}")
    public ChannelStats updateChannelStats(@PathVariable long id, Principal principal) throws IOException {
        return youTubeAPIService.updateChannel(id, principal.getName());
    }

    @PutMapping("/channel")
    public List<ChannelStats> updateAllChannels(Principal principal) throws IOException {
        return youTubeAPIService.updateAllChannels(principal.getName());
    }

    @DeleteMapping("/channel/{id}")
    public void deleteChannel(@PathVariable long id, Principal principal){
        channelStatsService.delete(id, principal.getName());
    }

}
