package com.company.youtubeanalyticstool.data;

import com.company.youtubeanalyticstool.model.videos.ChannelStats;
import com.company.youtubeanalyticstool.repository.ChannelStatsRepository;
import com.company.youtubeanalyticstool.service.ChannelStatsService;
import com.company.youtubeanalyticstool.service.YouTubeAPIService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api")
public class TestDbController {

    @Autowired
    YouTubeAPIService youTubeAPIService;
    @Autowired
    ChannelStatsRepository channelStatsRepository;

    @PostMapping("/test")
    public void fillDatabaseWithData() throws IOException {
        youTubeAPIService.saveVideoStats("GX5IZnS6GJA");
        youTubeAPIService.saveVideoStats("akSynocjEK0");
        youTubeAPIService.saveVideoStats("PMxjpUwjzSg");
        youTubeAPIService.saveVideoStats("4oUb2p0EhYI");
        youTubeAPIService.saveVideoStats("0t-sG8FhC4E");


        youTubeAPIService.saveChannelStats("UC4xKdmAXFh4ACyhpiQ_3qBw");

        youTubeAPIService.saveChannelStats("UCs3GloeEzu5rDlQlSLGrr4A");
        youTubeAPIService.saveChannelStats("UCUr1w6sHtgj1JniKV8vWXMw");
        youTubeAPIService.saveChannelStats("UCHOtaAJCOBDUWIcL4372D9A");
        youTubeAPIService.saveChannelStats("UC0WP5P-ufpRfjbNrmOWwLBQ");

        List<ChannelStats> allChannels = channelStatsRepository.findAll();
        for(ChannelStats channelStats : allChannels){
            youTubeAPIService.saveAllChannelVideos(channelStats.getId());
        }

    }
}
