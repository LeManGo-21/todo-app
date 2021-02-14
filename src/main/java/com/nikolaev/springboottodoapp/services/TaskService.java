package com.nikolaev.springboottodoapp.services;

import com.nikolaev.springboottodoapp.entities.Task;
import com.nikolaev.springboottodoapp.exceptions.TaskNotFoundException;

/**
 * @author Nikolaev Artem
 **/

public interface TaskService extends EmptyTaskService {
    /**
     * Возвращает задачи из БД в виде списка Iterable<Task>
     * @return Список задач, полученный из БД
     */
    Iterable<Task> getTaskList();

    /**
     * @param id уникальный индентификатор задачи.
     * @return задача найденная по данному id
     * @throws TaskNotFoundException если не существует задачи с данным id
     */
    Task getTaskById(Long id) throws TaskNotFoundException;

    /**
     * Сохраняет задачу
     * @param task задача которую необходимо сохранить
     */
    void putTask(Task task);

    /**
     * Обновляет задачу, полученную по id новыми данными,
     * полученными от пользователя
     * @param id уникальный индентификатор задачи
     * @param task задача с новыми данными
     * @throws TaskNotFoundException если не существует задачи с данным id
     */
    void updateTask(Long id, Task task) throws TaskNotFoundException;

    /**
     * Удаляет задачу
     * @param id уникальный индентификатор задачи.
     * @throws TaskNotFoundException если не существует задачи с данным id
     */
    void deleteTask(Long id) throws TaskNotFoundException;

}
