package com.ulam.casemanagement.beans;

import com.ulam.casemanagement.data.Case;
import lombok.Data;
import org.springframework.beans.BeanUtils;

import java.time.Instant;

@Data
public class CaseRequest {

    private String firstName;
    private String lastName;
    private String emailId;
    private Long phoneNumber;
    private String problemSummary;
    private Long timeCreated;
    private Long timeUpdated;

    private CaseRequest() {
    }

    public static class Builder {
        private String firstName;
        private String lastName;
        private String emailId;
        private Long phoneNumber;
        private String problemSummary;
        private Long timeCreated = Instant.now().getEpochSecond();
        private Long timeUpdated = Instant.now().getEpochSecond();

        public Builder firstName(String firstName) {
            this.firstName = firstName;
            return this;
        }

        public Builder lastName(String lastName) {
            this.lastName = lastName;
            return this;
        }

        public Builder emailId(String emailId) {
            this.emailId = emailId;
            return this;
        }

        public Builder phoneNumber(Long phoneNumber) {
            this.phoneNumber = phoneNumber;
            return this;
        }

        public Builder problemSummary(String problemSummary) {
            this.problemSummary = problemSummary;
            return this;
        }

        public Builder timeUpdated(Long timeUpdated) {
            this.timeUpdated = timeUpdated;
            return this;
        }

        public CaseRequest build() {
            CaseRequest caseRequest = new CaseRequest();
            caseRequest.firstName = this.firstName;
            caseRequest.lastName = this.lastName;
            caseRequest.emailId = this.emailId;
            caseRequest.phoneNumber = this.phoneNumber;
            caseRequest.problemSummary = this.problemSummary;
            caseRequest.timeCreated = this.timeCreated;
            caseRequest.timeUpdated = this.timeUpdated;
            return caseRequest;
        }

        public String getFirstName() {
            return firstName;
        }

        public String getLastName() {
            return lastName;
        }

        public String getEmailId() {
            return emailId;
        }

        public Long getPhoneNumber() {
            return phoneNumber;
        }

        public Long getTimeCreated() {
            return timeCreated;
        }

        public Long getTimeUpdated() {
            return timeUpdated;
        }

        public String getProblemSummary() {
            return problemSummary;
        }
    }

    public Case toUserData() {
        Case aCase = new Case();
        BeanUtils.copyProperties(this, aCase);
        return aCase;
    }
}
