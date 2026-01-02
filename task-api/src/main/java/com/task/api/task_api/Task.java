package com.task.api.task_api;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.time.LocalDateTime;

@Entity
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // automatically generates id when new Task object is created
    private Long id;

    private String title;
    private String description;
    private LocalDateTime createdAt;
    private String status = "TODO"; // TODO - IN_PROGRESS - DONE


    // Constructors
    public Task() {
    }

    public Task(String title, String description) {
        this.title = title;
        this.description = description;
        this.createdAt = LocalDateTime.now();
    }


    // Getters/Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) { // not necessary?
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) { // not necessary?
        this.createdAt = createdAt;
    }
}