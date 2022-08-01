package com.epam.spring.homework.project.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Report {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String type;
    private Date dateOfCreation;
    private Status status;
    private String comment;
    private String fileName;
    @ManyToOne
    @JoinColumn(referencedColumnName = "user_id")
    private User user;
    @ManyToOne
    @JoinColumn(referencedColumnName = "user_id")
    private User inspector;
}
