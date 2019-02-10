package com.testerhome.testapp.workspae;

public class Config {


    public String getAgentId() {
        return agentId;
    }

    public void setAgentId(String agentId) {
        this.agentId = agentId;
    }

    public String getCopId() {
        return copId;
    }

    public void setCopId(String copId) {
        this.copId = copId;
    }

    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }
    String agentId;
    String copId;
    String secret;

    public String getContactToken() {
        return contactToken;
    }

    public void setContactToken(String contactToken) {
        this.contactToken = contactToken;
    }

    String contactToken;

}
