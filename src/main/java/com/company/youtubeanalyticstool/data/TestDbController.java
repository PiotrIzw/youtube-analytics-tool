package com.company.youtubeanalyticstool.data;

import com.company.youtubeanalyticstool.model.ChannelStats;
import com.company.youtubeanalyticstool.model.UserDAO;
import com.company.youtubeanalyticstool.repository.ChannelStatsRepository;
import com.company.youtubeanalyticstool.repository.UserRepository;
import com.company.youtubeanalyticstool.service.YouTubeAPIService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api")
public class TestDbController {

    @Autowired
    YouTubeAPIService youTubeAPIService;
    @Autowired
    ChannelStatsRepository channelStatsRepository;
    @Autowired
    UserRepository userRepository;

    //only admin
    @PostMapping("/test")
    public void fillDatabaseWithData(Principal principal) throws IOException {

        String username = principal.getName();
        youTubeAPIService.saveVideoStats("GX5IZnS6GJA", username);
        youTubeAPIService.saveVideoStats("akSynocjEK0", username);
        youTubeAPIService.saveVideoStats("PMxjpUwjzSg", username);
        youTubeAPIService.saveVideoStats("4oUb2p0EhYI", username);
        youTubeAPIService.saveVideoStats("0t-sG8FhC4E", username);


        youTubeAPIService.saveChannelStats("UC4xKdmAXFh4ACyhpiQ_3qBw", username);

        youTubeAPIService.saveChannelStats("UCs3GloeEzu5rDlQlSLGrr4A", username);
        youTubeAPIService.saveChannelStats("UCUr1w6sHtgj1JniKV8vWXMw", username);
        youTubeAPIService.saveChannelStats("UCHOtaAJCOBDUWIcL4372D9A", username);
        youTubeAPIService.saveChannelStats("UC0WP5P-ufpRfjbNrmOWwLBQ", username);

        List<ChannelStats> allChannels = channelStatsRepository.findAll();
        for(ChannelStats channelStats : allChannels){
            youTubeAPIService.saveAllChannelVideos(channelStats.getId(), username);
        }

    }
}
