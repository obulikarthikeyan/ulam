package com.ulam.casemanagement.controller;

import com.ulam.casemanagement.data.Case;
import com.ulam.casemanagement.repository.CaseRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

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
}
