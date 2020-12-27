package com.company.youtubeanalyticstool.controller;

import com.company.youtubeanalyticstool.model.Channel;
import com.company.youtubeanalyticstool.repository.ChannelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//TODO clear tests & add proper implementations
@RestController
@RequestMapping("/api")
public class ChannelController {

    @Autowired
    ChannelRepository channelRepository;

    @GetMapping("/test")
    public String test(){
        return "Endpoint test";
    }
    @PostMapping("/testdb")
    public Channel testDb(){
        Channel channel = new Channel("name", 100);
        return channelRepository.save(channel);
    }
}
