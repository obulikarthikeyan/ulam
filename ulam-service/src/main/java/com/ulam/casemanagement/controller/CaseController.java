package com.ulam.casemanagement.controller;

import com.ulam.casemanagement.beans.CaseRequest;
import com.ulam.casemanagement.beans.GenericResponse;
import com.ulam.casemanagement.constants.ErrorType;
import com.ulam.casemanagement.data.Case;
import com.ulam.casemanagement.exception.GenericError;
import com.ulam.casemanagement.repository.CaseRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.PersistenceException;
import java.util.List;
@Slf4j
@CrossOrigin(origins = "${frontend.proxy.url}")
@RestController
@RequestMapping(path = "${cases.api.baseURI}")
public class CaseController {

    private final CaseRepository caseRepository;

    @Autowired
    public CaseController(CaseRepository caseRepository) {
        this.caseRepository = caseRepository;
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
        } catch (PersistenceException e) {
            String errorMsg = "Unable to save user: " + userRequestBuilder;
            log.error(errorMsg, e);
            return ResponseEntity.unprocessableEntity().body(new GenericResponse(new GenericError(ErrorType.PROCESSING_ERROR, errorMsg)));
        }
        return ResponseEntity.ok(new GenericResponse("User created with Id: " + createdUser.getId()));
    }

}
