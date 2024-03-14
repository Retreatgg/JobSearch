insert into USERS(name, surname, age, email, password, phone_number, avatar, account_type)
values ('John', 'Doe', 25, 'john@example.com', 'password1', '123456789', '', 'Applicant'),
       ('Jane', 'Smith', 30, 'jane@example.com', 'password2', '987654321', '', 'Applicant'),
       ('Alice', 'Johnson', 35, 'alice@example.com', 'password3', '111222333', '', 'Employer'),
       ('Bob', 'Miller', 40, 'bob@example.com', 'password4', '444555666', '', 'Employer');

insert into CONTACT_TYPES(TYPE)
values ('Telegram'),
       ('Email'),
       ('Phone number'),
       ('Facebook'),
       ('Linkedin');

insert into CATEGORIES(name)
values ('Web'),
       ('Game Dev');

insert into CATEGORIES(name, parent_id)
values ('Front-End', 13),
       ('Back-End', 13);

insert into RESUMES(name, salary, is_active, created_date, update_time, applicant_id, category_id)
VALUES ('Web Developer Back-End', 60000, false, CURRENT_DATE, CURRENT_TIMESTAMP, 17, 18),
       ('Game Developer', 65000, true, CURRENT_DATE, CURRENT_TIMESTAMP, 18, 14);


insert into VACANCIES(name, description, category_id, salary, exp_from, exp_to, is_active, author_id, created_date,
                      update_date)
VALUES ('Software Engineer', 'We are looking for a skilled software engineer to join our team.', 17, 80000, 2, 5, false,
        19, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
       ('Data Analyst', 'Seeking a data analyst proficient in SQL and Python for data analysis tasks.', 13, 70000, 1, 3,
        true, 20, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

insert into CONTACTS_INFO(contact_value, type_id, resume_id)
VALUES ('john.doe@example.com', 22, 5),
       ('+1234567890', 23, 6);

insert into EDUCATION_INFO(resume_id, institution, program, start_date, end_date, degree)
VALUES (5, 'University of Example', 'Computer Science', '2018-09-01', '2022-06-30', 'Bachelor'),
       (6, 'Example College', 'Data Science', '2022-09-01', '2024-06-30', 'Master');

insert into WORK_EXPERIENCE_INFO(resume_id, years, company_name, position, responsibilities)
VALUES (5, 3, 'Tech Solutions Inc.', 'Software Engineer', 'Developed and maintained software applications.'),
       (6, 2, 'Data Analytics Co.', 'Data Analyst', 'Performed data analysis and generated reports for clients.');

insert into RESPONDED_APPLICANTS(resume_id, vacancy_id, confirmation)
VALUES (5, 4, true),
       (6, 5, false);

insert into MESSAGES(responded_applicants, content, timestamp)
VALUES (1, 'Thank you for your application.', CURRENT_TIMESTAMP),
       (2, 'Unfortunately, your application was not successful at this time.',
        CURRENT_TIMESTAMP);