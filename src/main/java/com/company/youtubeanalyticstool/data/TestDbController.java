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

    @PostMapping("/test")
    public void fillDatabaseWithData(Principal principal) throws IOException {

        UserDAO userDAO = new UserDAO();
        userDAO.setUsername("jankowalski");
        userDAO.setPassword("password");
        userDAO.setRole("ROLE_ADMIN");

        userRepository.save(userDAO);

        youTubeAPIService.saveVideoStats("GX5IZnS6GJA", userDAO.getUsername());
        youTubeAPIService.saveVideoStats("akSynocjEK0", userDAO.getUsername());
        youTubeAPIService.saveVideoStats("PMxjpUwjzSg", userDAO.getUsername());
        youTubeAPIService.saveVideoStats("4oUb2p0EhYI", userDAO.getUsername());
        youTubeAPIService.saveVideoStats("0t-sG8FhC4E", userDAO.getUsername());


        youTubeAPIService.saveChannelStats("UC4xKdmAXFh4ACyhpiQ_3qBw");

        youTubeAPIService.saveChannelStats("UCs3GloeEzu5rDlQlSLGrr4A");
        youTubeAPIService.saveChannelStats("UCUr1w6sHtgj1JniKV8vWXMw");
        youTubeAPIService.saveChannelStats("UCHOtaAJCOBDUWIcL4372D9A");
        youTubeAPIService.saveChannelStats("UC0WP5P-ufpRfjbNrmOWwLBQ");

        List<ChannelStats> allChannels = channelStatsRepository.findAll();
        for(ChannelStats channelStats : allChannels){
            youTubeAPIService.saveAllChannelVideos(channelStats.getId(), userDAO.getUsername());
        }

    }
}
