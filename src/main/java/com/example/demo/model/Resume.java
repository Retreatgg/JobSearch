package com.example.demo.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "resumes")
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Resume {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "applicant_id")
    private User applicant;
    private String name;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;
    private Double salary;

    @Column(name = "is_active")
    private Boolean isActive;

    @Column(name = "created_date")
    private LocalDateTime createdDate;

    @Column(name = "update_time")
    private LocalDateTime updateTime;



    @OneToMany(fetch = FetchType.LAZY, mappedBy = "resume")
    private List<ContactInfo> contactInfos;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "resume")
    private List<EducationInfo> educationInfos;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "resume")
    private List<RespondedApplicant> respondedApplicants;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "resume")
    private List<WorkExperienceInfo> workExperienceInfos;

}
