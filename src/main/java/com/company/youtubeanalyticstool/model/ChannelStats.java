package com.company.youtubeanalyticstool.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.List;

@Entity
@JsonIgnoreProperties(value = {"videoStats", "channelId"},
        allowGetters = false)
public class ChannelStats {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @Column(name = "channel_name", nullable = false)
    private String channelName;
    @Column(name = "subscriptions_count", nullable = false)
    private long subscriptionsCount;
    @Column(name = "channel_id", nullable = false)
    private String channelId;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "channelStats")
    private List<VideoStats> videoStats;

    @ManyToOne()
    @JoinColumn(name = "user_id")
    @JsonIgnore
    private UserDAO userDAO;

    public ChannelStats(String channelName, long subscriptionsCount, String channelId) {
        this.channelName = channelName;
        this.subscriptionsCount = subscriptionsCount;
        this.channelId = channelId;
    }

    public ChannelStats(){

    }
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getChannelName() {
        return channelName;
    }

    public void setChannelName(String channelName) {
        this.channelName = channelName;
    }

    public long getSubscriptionsCount() {
        return subscriptionsCount;
    }

    public void setSubscriptionsCount(long subscriptionsCount) {
        this.subscriptionsCount = subscriptionsCount;
    }

    public String getChannelId() {
        return channelId;
    }

    public void setChannelId(String channelId) {
        this.channelId = channelId;
    }

    public List<VideoStats> getVideoStats() {
        return videoStats;
    }

    public void setVideoStats(List<VideoStats> videoStats) {
        this.videoStats = videoStats;
    }

    public UserDAO getUserDAO() {
        return userDAO;
    }

    public void setUserDAO(UserDAO userDAO) {
        this.userDAO = userDAO;
    }
}
