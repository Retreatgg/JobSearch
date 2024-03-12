create table if not exists users
(
    id           long auto_increment primary key,
    name         varchar(45),
    username     varchar(45),
    age          integer,
    email        varchar(45),
    password     varchar(45),
    phone_number varchar(45),
    avatar       varchar(45),
    account_type varchar(45)
);

/* insert into users(name, username, age, email, password, phone_number, avatar, account_type)
values ('John', 'Doe', 33, 'joHn@email.com', 'qwerty', '550230710', '', 'Applicant'),
       ('Alex', 'Maxnez', 27, 'MaxAlex@email.com', 'maxnez111', '330234054', '', 'Applicant'),
       ('Apple', '', 5, 'apple@email.com', 'appleTop', '88888888', '', 'Employer'),
       ('Samsung', '', 7, 'samsung@email.com', 'samsungTop', '777777777', '', 'Employer');
*/

create table if not exists categories
(
    id        long auto_increment primary key,
    name      varchar(45),
    parent_id long,
    constraint fk_parent
        foreign key (parent_id) references categories (id)
);

insert into categories(name, parent_id)
values ( 'Back-end',  null),
    ('Front-end', null);

create table if not exists resumes
(
    id           long auto_increment primary key,
    name         varchar(45),
    salary       double,
    is_active    boolean,
    created_date datetime,
    update_time  datetime,
    applicant_id long,
    category_id  long,
    constraint fk_users_resumes
        foreign key (applicant_id) references users (id),

    constraint fk_categories_resumes
        foreign key (category_id) references categories (id)
);

insert into resumes(name, salary, is_active, created_date, update_time, applicant_id, category_id)
values ('Резюме 1', 50000.00, true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 1, 1),
    ('Резюме 2', 45000.00, true, current_timestamp, current_timestamp, 2, 2);

create table if not exists vacancies
(
    id           long auto_increment primary key,
    description  varchar(45),
    salary       double,
    is_active    boolean,
    created_date datetime,
    update_time  datetime,
    exp_to       integer,
    exp_from     integer,
    category_id  long,
    author_id    long,
    constraint fk_users_vacancies
        foreign key (author_id) references users (id),

    constraint fk_category_vacancies
        foreign key (category_id) references categories (id)
);

insert into vacancies(description, salary, is_active, created_date, update_time, exp_to, exp_from, category_id, author_id)
values ( 'Хорошая работа', 43000.00, true, current_timestamp, current_time, 10, 3, 1, 3),
    ('Лучшая работа', 50000.00, true, current_timestamp, current_timestamp, 5, 2, 2, 4);

create table if not exists responded_applicants
(
    id           long auto_increment primary key,
    confirmation boolean,
    resume_id    long,
    vacancy_id   long,
    constraint fk_resumes_responded_applicants
        foreign key (resume_id) references resumes (id),

    constraint fk_vacancies_responded_applicants
        foreign key (vacancy_id) references vacancies (id)
);

INSERT INTO responded_applicants (confirmation, resume_id, vacancy_id)
VALUES
    (true, 1, 1),
    (false, 2, 2);

create table if not exists messages
(
    id                   long auto_increment primary key,
    content              varchar(45),
    timestamp            timestamp,
    responded_applicants long,
    constraint fk_responded_applicants_messages
        foreign key (responded_applicants) references responded_applicants (id)
);

INSERT INTO messages (content, timestamp, responded_applicants)
VALUES
    ('Приглашаем на интервью', CURRENT_TIMESTAMP, 1),
    ('К сожалению, ваше резюме не подходит', CURRENT_TIMESTAMP, 2);

create table if not exists education_info
(
    id          long auto_increment primary key,
    institution varchar(45),
    program     varchar(45),
    start_date  date,
    end_date    date,
    degree      varchar(45),
    resume_id   long,
    constraint fk_resumes_education_info
        foreign key (resume_id) references resumes (id)
);

INSERT INTO education_info (institution, program, start_date, end_date, degree, resume_id)
VALUES
    ('Университет', 'Информационные технологии', '2015-09-01', '2019-06-30', 'Бакалавр', 1),
    ('Колледж', 'Программирование', '2017-09-01', '2021-06-30', 'Магистр', 2);

create table if not exists work_experience_info
(
    id               long auto_increment primary key,
    years            integer,
    company_name     varchar(45),
    position         varchar(45),
    responsibilities varchar(45),
    resume_id        long,
    constraint fk_work_experience_info_resumes_info
        foreign key (resume_id) references resumes (id)
);

INSERT INTO work_experience_info (years, company_name, position, responsibilities, resume_id)
VALUES
    (3, 'IT Company', 'Developer', 'Разработка программного обеспечения', 1),
    (2, 'Web Agency', 'Designer', 'Дизайн веб-страниц', 2);

create table if not exists CONTACT_TYPES
(
    id   long auto_increment
        primary key,
    type varchar(45)
);

INSERT INTO CONTACT_TYPES (type) VALUES ('Email'),
                                     ('Skype'),
                                     ('Instagram');

create table if not exists contacts_info
(
    id        long auto_increment primary key,
    contact_values    varchar(45),
    resume_id long,
    type_id   long,
    constraint fk_resumes_contacts_info
        foreign key (resume_id) references resumes (id),
    constraint fk_types_contacts_info
        foreign key (type_id) references contact_types (id)
);

INSERT INTO contacts_info (contact_values, resume_id, type_id)
VALUES
    ('joHn@email.com', 1, 1),
    ('MaxAlex@email.com', 2, 1);


