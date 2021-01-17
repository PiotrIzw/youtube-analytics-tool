package com.company.youtubeanalyticstool.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "user")
public class UserDAO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(unique = true, nullable = false)
    private String username;
    @Column(nullable = false)
    private String password;
    @Column(nullable = false)
    private String role;


    @OneToMany(cascade = CascadeType.ALL, mappedBy = "userDAO")
    List<ChannelStats> channelStatsList;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "userDAO")
    List<VideoStats> videoStatsList;

    public UserDAO(String username, String password, List<SimpleGrantedAuthority> roles) {
    }

    public UserDAO() {

    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public List<ChannelStats> getChannelStatsList() {
        return channelStatsList;
    }

    public void setChannelStatsList(List<ChannelStats> channelStatsList) {
        this.channelStatsList = channelStatsList;
    }

    public List<VideoStats> getVideoStatsList() {
        return videoStatsList;
    }

    public void setVideoStatsList(List<VideoStats> videoStatsList) {
        this.videoStatsList = videoStatsList;
    }
}