package com.ulam.casemanagement.controller;

import com.ulam.casemanagement.beans.CaseRequest;
import com.ulam.casemanagement.beans.EmailMessageBuilder.EmailMessage;
import com.ulam.casemanagement.beans.GenericResponse;
import com.ulam.casemanagement.clients.EmailServiceClient;
import com.ulam.casemanagement.constants.ErrorType;
import com.ulam.casemanagement.data.Case;
import com.ulam.casemanagement.exception.GenericError;
import com.ulam.casemanagement.repository.CaseRepository;
import com.ulam.casemanagement.util.EmailUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.PersistenceException;
import java.io.IOException;
import java.util.List;
@Slf4j
@CrossOrigin(origins = "${frontend.proxy.url}")
@RestController
@RequestMapping(path = "${cases.api.baseURI}")
public class CaseController {

    private final CaseRepository caseRepository;

    private final  EmailServiceClient emailServiceClient;

    @Value("${sendgrid.api_key}")
    private String sendgridAPIKey;

    @Autowired
    public CaseController(CaseRepository caseRepository, EmailServiceClient emailServiceClient) {
        this.caseRepository = caseRepository;
        this.emailServiceClient = emailServiceClient;
    }

    @RequestMapping(path = "/cases", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE )
    public ResponseEntity<List<Case>> getAllUsers() {
        List<Case> cases = caseRepository.findAll();
        return ResponseEntity.ok(cases);
    }

    @RequestMapping(path = "/cases", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity createUser(@RequestBody CaseRequest.Builder userRequestBuilder) {
        Case createdUser;
        try {
            createdUser = caseRepository.save(userRequestBuilder.build().toUserData());
            EmailMessage emailMessage = EmailUtil.buildEmailMessage(createdUser, sendgridAPIKey);
            boolean isEmailSent = emailServiceClient.sendEmail(emailMessage);
            if (isEmailSent) {
                log.info("Email successfully sent to " + emailMessage.getTo());
            }
        } catch (PersistenceException | IOException e) {
            String errorMsg = "Unable to save user: " + userRequestBuilder;
            log.error(errorMsg, e);
            return ResponseEntity.unprocessableEntity().body(new GenericResponse(new GenericError(ErrorType.PROCESSING_ERROR, errorMsg)));
        }
        return ResponseEntity.ok(new GenericResponse("User created with Id: " + createdUser.getId()));
    }

}
