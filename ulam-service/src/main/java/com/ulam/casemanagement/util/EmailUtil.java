package com.ulam.casemanagement.util;

import com.ulam.casemanagement.beans.EmailMessageBuilder;
import com.ulam.casemanagement.beans.EmailMessageBuilder.EmailMessage;
import com.ulam.casemanagement.data.Case;

public class EmailUtil {

    public static EmailMessage buildEmailMessage(Case caseInfo, String apiKey) {
        EmailMessageBuilder emailMessageBuilder = new EmailMessageBuilder();
        emailMessageBuilder.apiKey(apiKey)
                .from("kartiksmbox@gmail.com")
                .to("karthik.ssn07@gmail.com")
                .subject("New Consultation Request Received from " + caseInfo.getFirstName() + " " + caseInfo.getLastName())
                .message(caseInfo.getFirstName() + " " + caseInfo.getLastName() + " says " + caseInfo.getProblemSummary());

        return emailMessageBuilder.build();
    }
}
