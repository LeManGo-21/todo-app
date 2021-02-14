package com.nikolaev.springboottodoapp.services;

import com.nikolaev.springboottodoapp.entities.Task;

public interface EmptyTaskService {
    /**
     * Создает задачу с пустыми полями
     * @return задача с пустыми полями
     */
    Task getEmptyTask();
}
