package com.eternalcode.discordapp.review;

import net.dzikoysk.cdn.entity.Contextual;

@Contextual
public class GitHubReviewUser {

    private Long discordId;
    private String githubUsername;
    private boolean isRecivingDMs;

    public GitHubReviewUser(Long id, String username, boolean isRecivingDMs) {
        this.discordId = id;
        this.githubUsername = username;
        this.isRecivingDMs = isRecivingDMs;
    }

    public GitHubReviewUser() {}

    public Long discordId() {
        return this.discordId;
    }

    public String githubUsername() {
        return this.githubUsername;
    }

    public boolean isRecivingDMs() {
        return this.isRecivingDMs;
    }

    public void setRecivingDMs(boolean isRecivingDMs) {
        this.isRecivingDMs = isRecivingDMs;
    }
}
