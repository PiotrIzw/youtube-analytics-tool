package com.company.youtubeanalyticstool.model;

import javax.persistence.*;

@Entity
public class Channel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "channel_name", nullable = false)
    private String channelName;
    @Column(name = "subscriptions_count", nullable = false)
    private int subscriptionsCount;

    public Channel(String channelName, int subscriptionsCount) {
        this.channelName = channelName;
        this.subscriptionsCount = subscriptionsCount;
    }

    public Channel(){

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

    public int getSubscriptionsCount() {
        return subscriptionsCount;
    }

    public void setSubscriptionsCount(int subscribtionsCount) {
        this.subscriptionsCount = subscribtionsCount;
    }
}
