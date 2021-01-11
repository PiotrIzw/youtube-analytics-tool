package com.company.youtubeanalyticstool.service;

import com.company.youtubeanalyticstool.model.VideoStats;
import com.company.youtubeanalyticstool.repository.VideoStatsRepository;
import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpRequestInitializer;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.youtube.YouTube;
import com.google.api.services.youtube.YouTubeRequestInitializer;
import com.google.api.services.youtube.model.Video;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Optional;

@Service
public class YouTubeAPIService {

    public static final HttpTransport HTTP_TRANSPORT = new NetHttpTransport();

    public static final JsonFactory JSON_FACTORY = new JacksonFactory();

    @Autowired
    private Environment env;
    @Autowired
    VideoStatsRepository videoStatsRepository;

    private YouTube youTube;

    @Bean
    public void setYouTubeAPI(){
        youTube = new YouTube.Builder(HTTP_TRANSPORT, JSON_FACTORY, new HttpRequestInitializer() {
            public void initialize(HttpRequest request) throws IOException {
            }
        }).setApplicationName("VideoStats")
                .setYouTubeRequestInitializer(new YouTubeRequestInitializer(env.getProperty("youtube.apikey"))).build();
    }

    public VideoStats saveVideoStats(String videoID) throws IOException {
        YouTube.Videos.List list = youTube.videos().list("statistics");
        list.setId(videoID);
        Video v = list.execute().getItems().get(0);

        VideoStats videoStats = new VideoStats();
        videoStats.setVideoID(videoID);
        videoStats.setLikeCount(v.getStatistics().getLikeCount().longValue());
        videoStats.setDislikeCount(v.getStatistics().getDislikeCount().longValue());
        videoStats.setCommentCount(v.getStatistics().getCommentCount().longValue());
        videoStats.setViewsCount(v.getStatistics().getViewCount().longValue());

        return videoStatsRepository.save(videoStats);
    }

    public VideoStats updateVideoStats(long id) throws IOException{
        VideoStats video = videoStatsRepository.findById(id).get();

        YouTube.Videos.List list = youTube.videos().list("statistics");
        list.setId(video.getVideoID());
        Video v = list.execute().getItems().get(0);

        video.setLikeCount(v.getStatistics().getLikeCount().longValue());
        video.setDislikeCount(v.getStatistics().getDislikeCount().longValue());
        video.setCommentCount(v.getStatistics().getCommentCount().longValue());
        video.setViewsCount(v.getStatistics().getViewCount().longValue());

        return videoStatsRepository.save(video);
    }
}
