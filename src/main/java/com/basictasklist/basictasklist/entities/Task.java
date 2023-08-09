package com.basictasklist.basictasklist.entities;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "task")
@Data
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name ="id")
    private long id;

    @Column(name = "user_id")
    private long userId;

    @Column(name = "description")
    private String description;

    @Column(name = "done")
    private boolean done;


}
