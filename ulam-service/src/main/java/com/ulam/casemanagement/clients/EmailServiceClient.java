package com.ulam.casemanagement.clients;

import com.sendgrid.Content;
import com.sendgrid.Email;
import com.sendgrid.Mail;
import com.sendgrid.Method;
import com.sendgrid.Request;
import com.sendgrid.Response;
import com.sendgrid.SendGrid;
import com.ulam.casemanagement.beans.EmailMessageBuilder.EmailMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Slf4j
@Component
public class EmailServiceClient {

    public boolean sendEmail(EmailMessage emailMessage) throws IOException {
        Email from = new Email(emailMessage.getFrom());
        String subject = emailMessage.getSubject();
        Email to = new Email(emailMessage.getTo());
        Content content = new Content("text/plain", emailMessage.getMessage());
        Mail mail = new Mail(from, subject, to, content);

        SendGrid sg = new SendGrid(emailMessage.getApiKey());
        Request request = new Request();

        request.setMethod(Method.POST);
        request.setEndpoint("mail/send");
        request.setBody(mail.build());
        Response response = sg.api(request);
        log.info("Sendgrid Response code = " + response.getStatusCode());
        log.info(response.getBody());
        log.info("Sendgrid Response Headers = " + response.getHeaders());
        return response.getStatusCode() == HttpStatus.ACCEPTED.value();
    }
}
