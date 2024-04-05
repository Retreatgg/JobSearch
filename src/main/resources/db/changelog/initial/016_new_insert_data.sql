INSERT INTO USERS(name, surname, age, email, password, phone_number, avatar, account_type, enabled)
VALUES
    ('John', 'Mills', 25, 'john@example.com', '$2a$12$WB2YUbFcCN0tm44SBcKUjua9yiFBsfB3vW02IjuwzY7HGtlQIKzy2', '123456789', '', 'APPLICANT', true),
    ('Jane', 'Jackson', 30, 'jane@example.com', '$2a$12$WB2YUbFcCN0tm44SBcKUjua9yiFBsfB3vW02IjuwzY7HGtlQIKzy2', '987654321', '', 'APPLICANT', true),
    ('FutureTech', '', 35, 'futuretech@example.com', '$2a$12$WB2YUbFcCN0tm44SBcKUjua9yiFBsfB3vW02IjuwzY7HGtlQIKzy2', '111222333', '',  'EMPLOYER', true),
    ('Data Solutions', '', 40, 'datasolutions@example.com', '$2a$12$WB2YUbFcCN0tm44SBcKUjua9yiFBsfB3vW02IjuwzY7HGtlQIKzy2', '444555666',  '', 'EMPLOYER', true),
    ('InnovateSoft', '', 28, 'innovatesoft@example.com', '$2a$12$WB2YUbFcCN0tm44SBcKUjua9yiFBsfB3vW02IjuwzY7HGtlQIKzy2', '777888999', '', 'EMPLOYER', true),
    ('Tech Innovators', '', 32, 'techinnovators@example.com', '$2a$12$WB2YUbFcCN0tm44SBcKUjua9yiFBsfB3vW02IjuwzY7HGtlQIKzy2', '555666777', '', 'EMPLOYER', true),
    ('Digital Enterprises', '', 38, 'digitalenterprises@example.com', '$2a$12$WB2YUbFcCN0tm44SBcKUjua9yiFBsfB3vW02IjuwzY7HGtlQIKzy2', '999888777', '', 'EMPLOYER', true),
    ('Emily', 'Adams', 28, 'emily@example.com', '$2a$12$WB2YUbFcCN0tm44SBcKUjua9yiFBsfB3vW02IjuwzY7HGtlQIKzy2', '555444333', '', 'APPLICANT', true),
    ('William', 'Taylor', 35, 'william@example.com', '$2a$12$WB2YUbFcCN0tm44SBcKUjua9yiFBsfB3vW02IjuwzY7HGtlQIKzy2', '222333444', '', 'APPLICANT', true),
    ('Sophia', 'Roberts', 27, 'sophia@example.com', '$2a$12$WB2YUbFcCN0tm44SBcKUjua9yiFBsfB3vW02IjuwzY7HGtlQIKzy2', '999888777', '', 'APPLICANT', true);

insert into CONTACT_TYPES(TYPE)
values ('Telegram'),
       ('Email'),
       ('Phone number'),
       ('Facebook'),
       ('Linkedin');


insert into AUTHORITIES(authority)
values ( 'EMPLOYER' ),
       ('APPLICANT');

insert into USER_ROLE(user_id, role_id)
VALUES ( 1, 2 ),
       (2, 2),
       (3, 1),
       (4, 1),
       (5, 1),
       (6, 1),
       (7, 1),
    (8, 2),
    (9, 2),
    (10, 2);

INSERT INTO CATEGORIES(name)
VALUES ('Mobile App Dev'),
       ('Full-Stack Dev'),
       ('AI & Machine Learning');

INSERT INTO RESUMES(name, salary, is_active, created_date, update_time, applicant_id, category_id)
VALUES ('Mobile App Developer', 65000, true, CURRENT_DATE, CURRENT_TIMESTAMP, 1, 1),
       ('Front-End Developer', 70000, true, CURRENT_DATE, CURRENT_TIMESTAMP, 2, 3),
       ('AI Engineer', 80000, true, CURRENT_DATE, CURRENT_TIMESTAMP, 8, 2),
       ('Full-Stack Developer', 75000, true, CURRENT_DATE, CURRENT_TIMESTAMP, 9, 3),
       ('Game Programmer', 70000, true, CURRENT_DATE, CURRENT_TIMESTAMP, 10, 1),
       ('Data Scientist', 85000, true, CURRENT_DATE, CURRENT_TIMESTAMP, 1, 2),
       ('UI/UX Designer', 70000, true, CURRENT_DATE, CURRENT_TIMESTAMP, 2, 1),
       ('Backend Developer', 75000, true, CURRENT_DATE, CURRENT_TIMESTAMP, 8, 2),
       ('DevOps Engineer', 80000, true, CURRENT_DATE, CURRENT_TIMESTAMP, 9, 3),
       ('Network Engineer', 70000, true, CURRENT_DATE, CURRENT_TIMESTAMP, 10, 1);

INSERT INTO VACANCIES(name, description, category_id, salary, exp_from, exp_to, is_active, author_id, created_date, update_date)
VALUES ('Mobile App Developer', 'We are seeking an experienced mobile app developer to join our team.', 1, 80000, 3, 6, true, 3, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
       ('Front-End Developer', 'We are looking for a talented front-end developer to create user-friendly web applications.', 2, 75000, 2, 5, true, 3, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
       ('AI Engineer', 'We are hiring an AI engineer to develop and implement AI solutions.', 3, 85000, 3, 7, true, 4, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
       ('Full-Stack Developer', 'Join our team as a full-stack developer and contribute to building innovative software solutions.', 3, 80000, 4, 8, true, 4, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
       ('Game Programmer', 'We are seeking a skilled game programmer to develop exciting game projects.', 2, 70000, 2, 5, true, 5, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
       ('Data Scientist', 'Seeking a data scientist to analyze large datasets and generate actionable insights.', 1, 90000, 4, 8, true, 5, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
       ('UI/UX Designer', 'Join our design team to create visually appealing and user-friendly interfaces.', 2, 70000, 2, 6, true, 6, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
       ('Backend Developer', 'We are hiring a backend developer to design and implement server-side logic.', 3, 75000, 3, 7, true, 6, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
       ('DevOps Engineer', 'Seeking a DevOps engineer to automate and streamline our development processes.', 2, 80000, 3, 6, true, 7, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
       ('Network Engineer', 'Join our IT team as a network engineer to design and maintain our network infrastructure.', 1, 70000, 2, 5, true, 7, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);


INSERT INTO CONTACTS_INFO(contact_value, type_id, resume_id)
VALUES ('john@example.com', 2, 1),
       ('+1122334455', 3, 2),
       ('emily@example.com', 2, 3),
       ('222333444', 3, 4),
       ('sophia@example.com', 2, 5),
       ('123456789', 3, 6),
       ('jane@example.com', 2, 7),
       ('555444333', 3, 8),
       ('william@example.com', 2, 9),
       ('999888777', 3, 10);

INSERT INTO EDUCATION_INFO(resume_id, institution, program, start_date, end_date, degree)
VALUES (3, 'Tech University', 'Computer Engineering', '2015-09-01', '2019-06-30', 'Bachelor'),
       (4, 'Data Institute', 'Data Management', '2012-09-01', '2016-06-30', 'Bachelor'),
       (5, 'Innovation College', 'Software Development', '2016-09-01', '2020-06-30', 'Bachelor'),
       (6, 'AI Academy', 'Artificial Intelligence', '2017-09-01', '2021-06-30', 'Bachelor'),
       (7, 'Game Development School', 'Game Development', '2014-09-01', '2018-06-30', 'Bachelor'),
       (8, 'Data Science Institute', 'Data Science', '2018-09-01', '2022-06-30', 'Bachelor'),
       (9, 'Design College', 'UI/UX Design', '2015-09-01', '2019-06-30', 'Bachelor'),
       (10, 'Tech College', 'Network Engineering', '2013-09-01', '2017-06-30', 'Bachelor');

INSERT INTO WORK_EXPERIENCE_INFO(resume_id, years, company_name, position, responsibilities)
VALUES (3, 5, 'FutureTech Solutions', 'Software Engineer', 'Designed and implemented software solutions.'),
       (4, 4, 'Data Solutions Inc.', 'Data Analyst', 'Analyzed data and provided insights for decision-making.'),
       (5, 3, 'InnovateSoft Technologies', 'AI Engineer', 'Developed AI models for various applications.'),
       (6, 5, 'Tech Innovators Ltd.', 'Full-Stack Developer', 'Developed both front-end and back-end components.'),
       (7, 3, 'Game Development Co.', 'Game Programmer', 'Implemented game mechanics and features.'),
       (8, 4, 'Data Analytics Corp.', 'Data Scientist', 'Performed advanced data analysis using machine learning techniques.'),
       (9, 3, 'Digital Enterprises', 'UI/UX Designer', 'Designed user interfaces for web and mobile applications.'),
       (10, 5, 'Tech Solutions Inc.', 'Backend Developer', 'Developed and maintained server-side applications.'),
       (3, 2, 'InnovateSoft Technologies', 'DevOps Engineer', 'Implemented CI/CD pipelines and automated deployment processes.'),
       (4, 3, 'Network Solutions Ltd.', 'Network Engineer', 'Designed and maintained company network infrastructure.');


INSERT INTO RESPONDED_APPLICANTS(resume_id, vacancy_id, confirmation)
VALUES (3, 2, true),
       (4, 3, true),
       (5, 1, false),
       (6, 3, false),
       (7, 1, true),
       (8, 2, true),
       (9, 1, true),
       (10, 2, true),
       (3, 5, false),
       (4, 4, true);


INSERT INTO MESSAGES(responded_applicants, content, timestamp)
VALUES (3, 'Thank you for your application.', CURRENT_TIMESTAMP),
       (4, 'Your application has been received.', CURRENT_TIMESTAMP),
       (5, 'We regret to inform you that your application was unsuccessful.', CURRENT_TIMESTAMP),
       (6, 'Congratulations! Your application has been successful.', CURRENT_TIMESTAMP),
       (7, 'Your application is under review.', CURRENT_TIMESTAMP),
       (8, 'Thank you for your interest. We will contact you shortly.', CURRENT_TIMESTAMP),
       (9, 'Your application is being reviewed by our team.', CURRENT_TIMESTAMP),
       (10, 'We appreciate your application. We will reach out to you soon.', CURRENT_TIMESTAMP),
       (3, 'Your application for the AI Engineer position has been received.', CURRENT_TIMESTAMP),
       (4, 'Thank you for applying. Your application is under consideration.', CURRENT_TIMESTAMP);
