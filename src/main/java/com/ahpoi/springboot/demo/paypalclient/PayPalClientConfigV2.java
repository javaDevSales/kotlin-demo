package com.ahpoi.springboot.demo.paypalclient;

public final class PayPalClientConfigV2 {

    private final String rootUrl;

    private final String username;

    private final String password;

    private final Long connectTimeout;

    private final Long readTimeout;

    private PayPalClientConfigV2(String rootUrl, String username, String password, Long connectTimeout, Long readTimeout) {
        this.rootUrl = rootUrl;
        this.username = username;
        this.password = password;
        this.connectTimeout = connectTimeout;
        this.readTimeout = readTimeout;
    }

    public String getRootUrl() {
        return rootUrl;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public Long getConnectTimeout() {
        return connectTimeout;
    }

    public Long getReadTimeout() {
        return readTimeout;
    }

    public static final class PayPalPaymentClientConfigV2Builder {

        private String rootUrl;
        private String username;
        private String password;
        private Long connectTimeout;
        private Long readTimeout;

        private PayPalPaymentClientConfigV2Builder() {
        }

        public static PayPalPaymentClientConfigV2Builder newBuilder() {
            return new PayPalPaymentClientConfigV2Builder();
        }

        public PayPalPaymentClientConfigV2Builder withRootUrl(String rootUrl) {
            this.rootUrl = rootUrl;
            return this;
        }

        public PayPalPaymentClientConfigV2Builder withUsername(String username) {
            this.username = username;
            return this;
        }

        public PayPalPaymentClientConfigV2Builder withPassword(String password) {
            this.password = password;
            return this;
        }

        public PayPalPaymentClientConfigV2Builder withConnectTimeout(Long connectTimeout) {
            this.connectTimeout = connectTimeout;
            return this;
        }

        public PayPalPaymentClientConfigV2Builder withReadTimeout(Long readTimeout) {
            this.readTimeout = readTimeout;
            return this;
        }

        public PayPalClientConfigV2 build() {
            return new PayPalClientConfigV2(rootUrl, username, password, connectTimeout, readTimeout);
        }

    }

}

