package com.company.youtubeanalyticstool.model;

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

    public VideoStats(String videoID, long viewsCount, long likeCount, long dislikeCount, long commentCount) {
        this.videoID = videoID;
        this.viewsCount = viewsCount;
        this.likeCount = likeCount;
        this.dislikeCount = dislikeCount;
        this.commentCount = commentCount;
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
}
