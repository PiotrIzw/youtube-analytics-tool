package com.company.youtubeanalyticstool.model;

import com.company.youtubeanalyticstool.model.ChannelStats;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
public class VideoStats {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "video_id", nullable = false)
    private String videoID;
    @Column(name = "views_count", nullable = false)
    private long viewsCount;
    @Column(name = "like_count", nullable = false)
    private long likeCount;
    @Column(name = "dislike_count", nullable = false)
    private long dislikeCount;
    @Column(name = "comment_count", nullable = false)
    private long commentCount;
    @ManyToOne()
    @JoinColumn(name = "channel_id")
    @JsonIgnore
    private ChannelStats channelStats;
    @ManyToOne()
    @JoinColumn(name = "user_id")
    @JsonIgnore
    private UserDAO userDAO;


    public VideoStats(String videoID, long viewsCount, long likeCount, long dislikeCount, long commentCount, ChannelStats channelStats, UserDAO userDAO) {
        this.videoID = videoID;
        this.viewsCount = viewsCount;
        this.likeCount = likeCount;
        this.dislikeCount = dislikeCount;
        this.commentCount = commentCount;
        this.channelStats = channelStats;
        this.userDAO = userDAO;
    }

    public VideoStats(){

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getVideoID() {
        return videoID;
    }

    public void setVideoID(String videoID) {
        this.videoID = videoID;
    }

    public long getViewsCount() {
        return viewsCount;
    }

    public void setViewsCount(long viewsCount) {
        this.viewsCount = viewsCount;
    }

    public long getLikeCount() {
        return likeCount;
    }

    public void setLikeCount(long likeCount) {
        this.likeCount = likeCount;
    }

    public long getDislikeCount() {
        return dislikeCount;
    }

    public void setDislikeCount(long dislikeCount) {
        this.dislikeCount = dislikeCount;
    }

    public long getCommentCount() {
        return commentCount;
    }

    public void setCommentCount(long commentCount) {
        this.commentCount = commentCount;
    }

    public ChannelStats getChannelStats() {
        return channelStats;
    }

    public void setChannelStats(ChannelStats channelStats) {
        this.channelStats = channelStats;
    }

    public UserDAO getUserDAO() {
        return userDAO;
    }

    public void setUserDAO(UserDAO userDAO) {
        this.userDAO = userDAO;
    }
}
