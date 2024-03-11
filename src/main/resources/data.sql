create table if not exists users
(
    id long auto_increment primary key,
    name varchar(45),
    username varchar(45),
    age integer,
    email varchar(45),
    password varchar(45),
    phone_number varchar(45),
    avatar varchar(45),
    account_type varchar(45)
);

/* create table if not exists resumes
(
    id long auto_increment primary key,
    name varchar(45),
    salary double(45),
    is_active boolean,
    created_date datetime,
    update_date datetime
) */

/* insert into users(name, username, age, email, password, phone_number, avatar, account_type)
values ('John', 'Doe', 33, 'joHn@email.com', 'qwerty', '550230710', '', 'Applicant'),
       ('Alex', 'Maxnez', 27, 'MaxAlex@email.com', 'maxnez111', '330234054', '', 'Applicant'),
       ('Apple', '', 5, 'apple@email.com', 'appleTop', '88888888', '', 'Employer'),
       ('Samsung', '', 7, 'samsung@email.com', 'samsungTop', '777777777', '', 'Employer');
 */

create table if not exists contact_types
(
    id long auto_increment primary key,
    type varchar(45)
)

