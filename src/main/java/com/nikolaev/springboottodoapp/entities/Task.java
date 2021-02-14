package com.nikolaev.springboottodoapp.entities;

import org.hibernate.validator.constraints.Length;
import org.springframework.data.annotation.Transient;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.*;

/**
 * @author Nikolaev Artem
 **/

@Entity
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotEmpty(message = "Укажите название задачи")
    @Length(max = 30, message = "Название должно быть короче 30 символов")
    private String title;

    @NotEmpty(message = "Укажите описание задачи")
    private String description;

    @Pattern(regexp = "^\\d{4}-(0[1-9]|1[012])-(0[1-9]|[12][0-9]|3[01])$", message = "Дата должна быть в формате 'YYYY-MM-DD'")
    private String startDate; //дата начала исполнения задачи в формате YYYY-MM-DD

    @Min(value = 0, message = "Должно быть целое положительное число")
    private int daysToUse;//количество дней данных на выполнение задачи

    private String endDate;//дата завершения исполнения задачи в формате YYYY-MM-DD

    public Task(){}

    public Task(String title, String description, String startDate, int daysToUse) {
        this.title = title;
        this.description = description;
        this.startDate = startDate;
        this.daysToUse = daysToUse;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public int getDaysToUse() {
        return daysToUse;
    }

    public void setDaysToUse(int daysToUse) {
        this.daysToUse = daysToUse;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    @Override
    public String toString() {
        return "Task{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", startDate=" + startDate +
                ", daysToUse=" + daysToUse +
                '}';
    }
}
