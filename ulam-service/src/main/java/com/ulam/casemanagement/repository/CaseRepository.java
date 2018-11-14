package com.ulam.casemanagement.repository;

import com.ulam.casemanagement.data.Case;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CaseRepository extends CrudRepository<Case, Long> {

    List<Case> findAll();
}
