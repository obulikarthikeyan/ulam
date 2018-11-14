package com.ulam.casemanagement.data;

import lombok.Data;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@ToString
@Table(name = "cases")
public class Case {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, updatable = false, unique = true)
    Long id;
    @Column(name = "first_name", nullable = false)
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "email_id", unique = true)
    private String emailId;
    @Column(name = "phone_number", nullable = false)
    private Long phoneNumber;
    @Column(name = "problem_summary", nullable = false)
    private String problemSummary;
    @Column(name = "time_created", updatable = false, nullable = false)
    private Long timeCreated;
    @Column(name = "time_updated", nullable = false)
    private Long timeUpdated;
}
