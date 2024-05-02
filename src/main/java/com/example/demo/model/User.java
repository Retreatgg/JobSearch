package com.example.demo.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Entity
@Builder
@Table(name = "users")
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String surname;
    private Integer age;
    private String email;
    private String password;

    @Column(name = "phone_number")
    private String phoneNumber;
    private String avatar;

    @Column(name = "account_type")
    private String accountType;
    private Boolean enabled;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "applicant")
    private List<Resume> resumes;

    @OneToOne(fetch = FetchType.LAZY, mappedBy = "user")
    private UserRole userRoles;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "author")
    private List<Vacancy> vacancies;
}
