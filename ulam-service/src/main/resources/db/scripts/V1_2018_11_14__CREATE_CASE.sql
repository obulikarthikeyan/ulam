/** Create Case Table **/
CREATE TABLE IF NOT EXISTS cases (
    id BIGINT NOT NULL AUTO_INCREMENT,
    first_name VARCHAR(30) NOT NULL,
    last_name VARCHAR(30),
    email_id VARCHAR(50),
    phone_number BIGINT NOT NULL,
    problem_summary VARCHAR(300) NOT NULL,
    time_created BIGINT,
    time_updated BIGINT,
    PRIMARY KEY (id),
    UNIQUE KEY unique_email (email_id)
);

/** Set Initial value of AUTO_INCREMENT **/
ALTER TABLE cases AUTO_INCREMENT=100;

/** Insert Default Cases **/
INSERT INTO cases(first_name, last_name, email_id, phone_number, problem_summary, time_created, time_updated)
values('John', 'Doe', 'johndoe@test.com', 8888888888, 'John''s Problem Summary', 1542216638, 1542216638);

INSERT INTO cases(first_name, last_name, email_id, phone_number, problem_summary, time_created, time_updated)
values('Jane', 'Smith', 'janesmith@test.com', 99999999999, 'Jane''s Problem Summary', 1542216638, 1542216638);
