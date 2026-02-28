package com.adbridge.api.config;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AdPlatformClientsConfiguration {

    @JsonProperty
    private MetaConfiguration meta = new MetaConfiguration();

    @JsonProperty
    private GoogleConfiguration google = new GoogleConfiguration();

    @JsonProperty
    private XConfiguration x = new XConfiguration();

    public MetaConfiguration getMeta() {
        return meta;
    }

    public void setMeta(MetaConfiguration meta) {
        this.meta = meta;
    }

    public GoogleConfiguration getGoogle() {
        return google;
    }

    public void setGoogle(GoogleConfiguration google) {
        this.google = google;
    }

    public XConfiguration getX() {
        return x;
    }

    public void setX(XConfiguration x) {
        this.x = x;
    }

    public static class MetaConfiguration {
        @JsonProperty
        private String appId;

        @JsonProperty
        private String appSecret;

        @JsonProperty
        private String accessToken;

        @JsonProperty
        private String adAccountId;

        public String getAppId() {
            return appId;
        }

        public void setAppId(String appId) {
            this.appId = appId;
        }

        public String getAppSecret() {
            return appSecret;
        }

        public void setAppSecret(String appSecret) {
            this.appSecret = appSecret;
        }

        public String getAccessToken() {
            return accessToken;
        }

        public void setAccessToken(String accessToken) {
            this.accessToken = accessToken;
        }

        public String getAdAccountId() {
            return adAccountId;
        }

        public void setAdAccountId(String adAccountId) {
            this.adAccountId = adAccountId;
        }
    }

    public static class GoogleConfiguration {
        @JsonProperty
        private String developerToken;

        @JsonProperty
        private String clientId;

        @JsonProperty
        private String clientSecret;

        @JsonProperty
        private String refreshToken;

        @JsonProperty
        private String loginCustomerId;

        @JsonProperty
        private String customerId;

        public String getDeveloperToken() {
            return developerToken;
        }

        public void setDeveloperToken(String developerToken) {
            this.developerToken = developerToken;
        }

        public String getClientId() {
            return clientId;
        }

        public void setClientId(String clientId) {
            this.clientId = clientId;
        }

        public String getClientSecret() {
            return clientSecret;
        }

        public void setClientSecret(String clientSecret) {
            this.clientSecret = clientSecret;
        }

        public String getRefreshToken() {
            return refreshToken;
        }

        public void setRefreshToken(String refreshToken) {
            this.refreshToken = refreshToken;
        }

        public String getLoginCustomerId() {
            return loginCustomerId;
        }

        public void setLoginCustomerId(String loginCustomerId) {
            this.loginCustomerId = loginCustomerId;
        }

        public String getCustomerId() {
            return customerId;
        }

        public void setCustomerId(String customerId) {
            this.customerId = customerId;
        }
    }

    public static class XConfiguration {
        @JsonProperty
        private String clientId;

        @JsonProperty
        private String clientSecret;

        @JsonProperty
        private String accessToken;

        @JsonProperty
        private String accessTokenSecret;

        public String getClientId() {
            return clientId;
        }

        public void setClientId(String clientId) {
            this.clientId = clientId;
        }

        public String getClientSecret() {
            return clientSecret;
        }

        public void setClientSecret(String clientSecret) {
            this.clientSecret = clientSecret;
        }

        public String getAccessToken() {
            return accessToken;
        }

        public void setAccessToken(String accessToken) {
            this.accessToken = accessToken;
        }

        public String getAccessTokenSecret() {
            return accessTokenSecret;
        }

        public void setAccessTokenSecret(String accessTokenSecret) {
            this.accessTokenSecret = accessTokenSecret;
        }
    }
}