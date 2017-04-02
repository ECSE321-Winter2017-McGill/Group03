-- PLEASE DO NOT EDIT THIS CODE
-- This code was generated using the UMPLE 1.25.0-9e8af9e modeling language!

CREATE DATABASE IF NOT EXISTS `ca_.mcgill_.ecse_321_.tamas`;
USE `ca_.mcgill_.ecse_321_.tamas`;

CREATE TABLE IF NOT EXISTS `ca_.mcgill_.ecse_321_.tamas`.`management_system`
(
  /*------------------------*/
  /* MEMBER VARIABLES       */
  /*------------------------*/
    PRIMARY KEY(/*No Possible Primary Key*/)

);



CREATE DATABASE IF NOT EXISTS `ca_.mcgill_.ecse_321_.tamas`;
USE `ca_.mcgill_.ecse_321_.tamas`;

CREATE TABLE IF NOT EXISTS `ca_.mcgill_.ecse_321_.tamas`.`instructor`
(
  /*------------------------*/
  /* MEMBER VARIABLES       */
  /*------------------------*/

  /*instructor Associations*/
  
  /*instructor Attributes*/
  name VARCHAR(255),
  PRIMARY KEY(name)

);



CREATE DATABASE IF NOT EXISTS `ca_.mcgill_.ecse_321_.tamas`;
USE `ca_.mcgill_.ecse_321_.tamas`;

CREATE TABLE IF NOT EXISTS `ca_.mcgill_.ecse_321_.tamas`.`course`
(
  /*------------------------*/
  /* MEMBER VARIABLES       */
  /*------------------------*/

  /*course Associations*/
  allocation_course_semester VARCHAR(255),
  instructor_name VARCHAR(255),
  
  /*course Attributes*/
  semester VARCHAR(255),
  course_coude VARCHAR(255),
  num_tutorial INT,
  num_lab INT,
  num_student INT,
  credit INT,
  hour_required_ta INT,
  hour_required_grader INT,
  budget_calculated DOUBLE,
  course_semester VARCHAR(255),
  PRIMARY KEY(semester)

);



CREATE DATABASE IF NOT EXISTS `ca_.mcgill_.ecse_321_.tamas`;
USE `ca_.mcgill_.ecse_321_.tamas`;

CREATE TABLE IF NOT EXISTS `ca_.mcgill_.ecse_321_.tamas`.`course_component`
(
  /*------------------------*/
  /* MEMBER VARIABLES       */
  /*------------------------*/

  /*course_component Associations*/
  course_semester VARCHAR(255),
  
  /*CourseComponent State Machines*/
  component ENUM('tutorial', 'lab'),

  /*course_component Attributes*/
  hour VARCHAR(255),
  session_code VARCHAR(255),
  PRIMARY KEY(hour)

);



CREATE DATABASE IF NOT EXISTS `ca_.mcgill_.ecse_321_.tamas`;
USE `ca_.mcgill_.ecse_321_.tamas`;

CREATE TABLE IF NOT EXISTS `ca_.mcgill_.ecse_321_.tamas`.`job_posting`
(
  /*------------------------*/
  /* MEMBER VARIABLES       */
  /*------------------------*/

  /*job_posting Associations*/
  course_semester VARCHAR(255),
  
  /*job_posting Attributes*/
  job_title VARCHAR(255),
  submission_deadline DATE,
  perferred_experience VARCHAR(255),
  num_needed INT,
  hour_rate DOUBLE,
  PRIMARY KEY(job_title)

);



CREATE DATABASE IF NOT EXISTS `ca_.mcgill_.ecse_321_.tamas`;
USE `ca_.mcgill_.ecse_321_.tamas`;

CREATE TABLE IF NOT EXISTS `ca_.mcgill_.ecse_321_.tamas`.`applicant`
(
  /*------------------------*/
  /* MEMBER VARIABLES       */
  /*------------------------*/

  /*applicant Associations*/
  
  /*applicant Attributes*/
  student_id INT,
  name VARCHAR(255),
  previous_experience VARCHAR(255),
  is_under_graduated BIT,
  major VARCHAR(255),
  year VARCHAR(255),
  first_choice VARCHAR(255),
  second_choice VARCHAR(255),
  third_choice VARCHAR(255),
  total_appointment_hours INT,
  PRIMARY KEY(student_id)

);



CREATE DATABASE IF NOT EXISTS `ca_.mcgill_.ecse_321_.tamas`;
USE `ca_.mcgill_.ecse_321_.tamas`;

CREATE TABLE IF NOT EXISTS `ca_.mcgill_.ecse_321_.tamas`.`application`
(
  /*------------------------*/
  /* MEMBER VARIABLES       */
  /*------------------------*/
  
  /*application Attributes*/
  application_status VARCHAR(255),
  PRIMARY KEY(application_status)

);



CREATE DATABASE IF NOT EXISTS `ca_.mcgill_.ecse_321_.tamas`;
USE `ca_.mcgill_.ecse_321_.tamas`;

CREATE TABLE IF NOT EXISTS `ca_.mcgill_.ecse_321_.tamas`.`offered_job`
(
  /*------------------------*/
  /* MEMBER VARIABLES       */
  /*------------------------*/

  /*offered_job Associations*/
  course_semester VARCHAR(255),
  applicant_student_id INT,
  
  /*OfferedJob State Machines*/
  job ENUM('ta', 'grader'),

  /*offered_job Attributes*/
  offer_description VARCHAR(255),
  PRIMARY KEY(offer_description)

);



CREATE DATABASE IF NOT EXISTS `ca_.mcgill_.ecse_321_.tamas`;
USE `ca_.mcgill_.ecse_321_.tamas`;

CREATE TABLE IF NOT EXISTS `ca_.mcgill_.ecse_321_.tamas`.`ta_job`
(
  /*------------------------*/
  /* MEMBER VARIABLES       */
  /*------------------------*/
  
  /*ta_job Attributes*/
  appointment_hour INT,
  PRIMARY KEY(appointment_hour)

);



CREATE DATABASE IF NOT EXISTS `ca_.mcgill_.ecse_321_.tamas`;
USE `ca_.mcgill_.ecse_321_.tamas`;

CREATE TABLE IF NOT EXISTS `ca_.mcgill_.ecse_321_.tamas`.`grader_job`
(
  /*------------------------*/
  /* MEMBER VARIABLES       */
  /*------------------------*/
    PRIMARY KEY(/*No Possible Primary Key*/)

);



CREATE DATABASE IF NOT EXISTS `ca_.mcgill_.ecse_321_.tamas`;
USE `ca_.mcgill_.ecse_321_.tamas`;

CREATE TABLE IF NOT EXISTS `ca_.mcgill_.ecse_321_.tamas`.`allocation`
(
  /*------------------------*/
  /* MEMBER VARIABLES       */
  /*------------------------*/

  /*allocation Associations*/
  course_semester VARCHAR(255),
    PRIMARY KEY(course_semester)

);




ALTER TABLE `ca_.mcgill_.ecse_321_.tamas`.`course` ADD CONSTRAINT `fk_course_allocation_course_semester` FOREIGN KEY (`allocation_course_semester`) REFERENCES `ca_.mcgill_.ecse_321_.tamas`.`allocation`(`course_semester`);
ALTER TABLE `ca_.mcgill_.ecse_321_.tamas`.`course` ADD CONSTRAINT `fk_course_instructor_name` FOREIGN KEY (`instructor_name`) REFERENCES `ca_.mcgill_.ecse_321_.tamas`.`instructor`(`name`);

ALTER TABLE `ca_.mcgill_.ecse_321_.tamas`.`course_component` ADD CONSTRAINT `fk_coursecomponent_course_semester` FOREIGN KEY (`course_semester`) REFERENCES `ca_.mcgill_.ecse_321_.tamas`.`course`(`semester`);

ALTER TABLE `ca_.mcgill_.ecse_321_.tamas`.`job_posting` ADD CONSTRAINT `fk_jobposting_course_semester` FOREIGN KEY (`course_semester`) REFERENCES `ca_.mcgill_.ecse_321_.tamas`.`course`(`semester`);



ALTER TABLE `ca_.mcgill_.ecse_321_.tamas`.`offered_job` ADD CONSTRAINT `fk_offeredjob_course_semester` FOREIGN KEY (`course_semester`) REFERENCES `ca_.mcgill_.ecse_321_.tamas`.`course`(`semester`);
ALTER TABLE `ca_.mcgill_.ecse_321_.tamas`.`offered_job` ADD CONSTRAINT `fk_offeredjob_applicant_student_id` FOREIGN KEY (`applicant_student_id`) REFERENCES `ca_.mcgill_.ecse_321_.tamas`.`applicant`(`student_id`);



ALTER TABLE `ca_.mcgill_.ecse_321_.tamas`.`allocation` ADD CONSTRAINT `fk_allocation_course_semester` FOREIGN KEY (`course_semester`) REFERENCES `ca_.mcgill_.ecse_321_.tamas`.`course`(`semester`);
