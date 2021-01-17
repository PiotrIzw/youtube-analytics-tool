package com.company.youtubeanalyticstool.service;

import com.company.youtubeanalyticstool.exception.ResourceNotFoundException;
import com.company.youtubeanalyticstool.model.ChannelStats;
import com.company.youtubeanalyticstool.model.UserDAO;
import com.company.youtubeanalyticstool.model.VideoStats;
import com.company.youtubeanalyticstool.repository.ChannelStatsRepository;
import com.company.youtubeanalyticstool.repository.UserRepository;
import com.company.youtubeanalyticstool.repository.VideoStatsRepository;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.youtube.YouTube;
import com.google.api.services.youtube.YouTubeRequestInitializer;
import com.google.api.services.youtube.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class YouTubeAPIService {

    public static final HttpTransport HTTP_TRANSPORT = new NetHttpTransport();

    public static final JsonFactory JSON_FACTORY = new JacksonFactory();

    @Autowired
    private Environment env;
    @Autowired
    VideoStatsRepository videoStatsRepository;
    @Autowired
    ChannelStatsRepository channelStatsRepository;
    @Autowired
    UserRepository userRepository;

    private YouTube youTube;

    @Bean
    public void setYouTubeAPI() {
        youTube = new YouTube.Builder(HTTP_TRANSPORT, JSON_FACTORY, request -> {
        }).setApplicationName("VideoStats")
                .setYouTubeRequestInitializer(new YouTubeRequestInitializer(env.getProperty("youtube.apikey"))).build();
    }

    public VideoStats saveVideoStats(String videoID, String username) throws IOException {

        UserDAO userDAO = userRepository.findByUsername(username);

        YouTube.Videos.List list = youTube.videos().list("statistics");
        list.setId(videoID);
        Video v = list.execute().getItems().get(0);

        VideoStats videoStats = new VideoStats();
        videoStats.setVideoID(videoID);
        videoStats.setLikeCount(v.getStatistics().getLikeCount().longValue());
        videoStats.setDislikeCount(v.getStatistics().getDislikeCount().longValue());
        videoStats.setCommentCount(v.getStatistics().getCommentCount().longValue());
        videoStats.setViewsCount(v.getStatistics().getViewCount().longValue());
        videoStats.setUserDAO(userDAO);
        return videoStatsRepository.save(videoStats);
    }

    public VideoStats updateVideoStats(long id, String username) throws IOException {

        UserDAO userDAO = userRepository.findByUsername(username);

        VideoStats video = videoStatsRepository.getVideoStatsByIdAndUserDAO(id, userDAO);


        YouTube.Videos.List list = youTube.videos().list("statistics");
        list.setId(video.getVideoID());
        Video v = list.execute().getItems().get(0);

        video.setLikeCount(v.getStatistics().getLikeCount().longValue());
        video.setDislikeCount(v.getStatistics().getDislikeCount().longValue());
        video.setCommentCount(v.getStatistics().getCommentCount().longValue());
        video.setViewsCount(v.getStatistics().getViewCount().longValue());

        return videoStatsRepository.save(video);
    }

    public ChannelStats saveChannelStats(String channelId, String username) throws IOException {

        UserDAO user = userRepository.findByUsername(username);

        YouTube.Channels.List channels = youTube.channels().list("snippet, statistics");
        channels.setId(channelId);
        ChannelListResponse channelResponse = channels.execute();


        Channel c = channelResponse.getItems().get(0);

        ChannelStats channelStats = new ChannelStats();
        channelStats.setChannelId(channelId);
        System.out.println(c.getSnippet().getTitle());
        channelStats.setChannelName(c.getSnippet().getTitle());
        channelStats.setSubscriptionsCount(c.getStatistics().getSubscriberCount().longValue());
        channelStats.setUserDAO(user);


        return channelStatsRepository.save(channelStats);
    }

    public ChannelStats updateChannel(long channelId, String userId) throws IOException {
        ChannelStats channel = channelStatsRepository.findById(channelId)
                .orElseThrow(() -> new ResourceNotFoundException("Channel", "id", channelId));

        if(channel.getUserDAO().getUsername().equals(userId)) {

            YouTube.Channels.List channels = youTube.channels().list("snippet, statistics");
            channels.setId(channel.getChannelId());
            ChannelListResponse channelResponse = channels.execute();

            Channel c = channelResponse.getItems().get(0);

            channel.setChannelName(c.getSnippet().getTitle());
            channel.setSubscriptionsCount(c.getStatistics().getSubscriberCount().longValue());

            for (VideoStats channelStats : channel.getVideoStats()) {
                updateVideoStats(channelStats.getId(), userId);
            }

            return channelStatsRepository.save(channel);
        }
        else return null;
    }

    public List<ChannelStats> updateAllChannels(String userId) throws IOException {

        UserDAO user = userRepository.findByUsername(userId);

        List<ChannelStats> channelStats = channelStatsRepository.findAllByUserDAO(user);

        for (ChannelStats channel : channelStats) {
            updateChannel(channel.getId(), userId);
        }

        return channelStatsRepository.findAll();
    }

    public List<VideoStats> saveAllChannelVideos(long channelId, String username) throws IOException {

        UserDAO user = userRepository.findByUsername(username);
        long userId = user.getId();

        ChannelStats channel = channelStatsRepository.findById(channelId)
                .orElseThrow(() -> new ResourceNotFoundException("Channel", "id", channelId));
        String ytChannelId = channel.getChannelId();

        YouTube.Search.List search = youTube.search().list("id,snippet");
        search.setChannelId(ytChannelId);
        search.setMaxResults(10L);
        search.setOrder("date");

        SearchListResponse searchResponse = search.execute();

        List<SearchResult> searchResultList = searchResponse.getItems();
        if (searchResultList != null) {
            for (SearchResult singleVideo : searchResultList) {

                if(singleVideo.getId().getVideoId() != null){

                    VideoStats videoStats = saveVideoStats(singleVideo.getId().getVideoId(), username);
                    videoStats.setChannelStats(channel);
                    videoStatsRepository.save(videoStats);
                }
            }
        }
        long channelRepoId = channel.getId();
        return channelStatsRepository.findById(channelRepoId)
        .orElseThrow(() -> new ResourceNotFoundException("Channel", "id", channelId))
        .getVideoStats();

    }
}
