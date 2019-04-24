package com.ulam.casemanagement.beans;

import lombok.Data;

public class EmailMessageBuilder {

    private String from;
    private String to;
    private String apiKey;
    private String subject;
    private String message;

    @Data
    public static class EmailMessage {

        private String from;
        private String to;
        private String apiKey;
        private String subject;
        private String message;
    }

    public EmailMessageBuilder from(String from) {
        this.from = from;
        return this;
    }

    public EmailMessageBuilder to(String to) {
        this.to = to;
        return this;
    }

    public EmailMessageBuilder apiKey(String apiKey) {
        this.apiKey = apiKey;
        return this;
    }

    public EmailMessageBuilder subject(String subject) {
        this.subject = subject;
        return this;
    }

    public EmailMessageBuilder message(String message) {
        this.message = message;
        return this;
    }

    public EmailMessage build() {
        EmailMessage emailMessage = new EmailMessage();
        emailMessage.setFrom(this.from);
        emailMessage.setTo(this.to);
        emailMessage.setApiKey(this.apiKey);
        emailMessage.setSubject(this.subject);
        emailMessage.setMessage(this.message);
        return emailMessage;
    }
}
