package com.todolistpackage;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by megasoch on 25.12.2015.
 */
@Entity
@Table(name = "tasks")
public class Task implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    Long id;

    @Column(name = "user_id")
    Long userId;

    @Column(name = "title")
    String title;

    public Task() {
    }

    public Task(Long userId, String title) {
        this.userId = userId;
        this.title = title;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Task task = (Task) o;

        if (id != null ? !id.equals(task.id) : task.id != null) return false;
        if (userId != null ? !userId.equals(task.userId) : task.userId != null) return false;
        return !(title != null ? !title.equals(task.title) : task.title != null);

    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (userId != null ? userId.hashCode() : 0);
        result = 31 * result + (title != null ? title.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Task{" +
                "id=" + id +
                ", userId=" + userId +
                ", title='" + title + '\'' +
                '}';
    }
}
