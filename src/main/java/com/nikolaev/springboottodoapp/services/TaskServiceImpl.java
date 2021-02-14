package com.nikolaev.springboottodoapp.services;

import com.nikolaev.springboottodoapp.entities.Task;
import com.nikolaev.springboottodoapp.exceptions.TaskNotFoundException;
import com.nikolaev.springboottodoapp.helpers.TaskDateHelper;
import com.nikolaev.springboottodoapp.repositories.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;

/**
 * @author Nikolaev Artem
 **/

@Service
public class TaskServiceImpl implements TaskService{

    private TaskRepository repository;

    @Autowired
    public TaskServiceImpl(TaskRepository repository) {
        this.repository = repository;
        setStartDataForDB();
    }

    /**
     * Заполняет БД начальными данными
     */
    void setStartDataForDB(){
        String d = LocalDate.now().toString();
        Task first = new Task("Убраться в комнате", "Протетреть пыль, помыть полы, заправить постель", d, 1);
        TaskDateHelper.setTaskEndDate(first);

        Task second = new Task("Сходить в магазин", "Купить хлеб, колбасу, молоко", d, 2);
        TaskDateHelper.setTaskEndDate(second);

        repository.save(first);
        repository.save(second);
    }

    /**
     * Возвращает задачи из БД в виде списка Iterable<Task>
     * @return Список задач, полученный из БД
     */
    public Iterable<Task> getTaskList(){
        return repository.findAll();
    }

    /**
     * @param id уникальный индентификатор задачи.
     * @return задача найденная в БД по данному id
     * @throws TaskNotFoundException если в БД не существует задачи с данным id
     */
    @Override
    public Task getTaskById(Long id) throws TaskNotFoundException{
        Optional<Task> optTask = repository.findById(id);

        if (optTask.isPresent()){
            return optTask.get();
        } else {
            throw new TaskNotFoundException();
        }
    }

    /**
     * Сохраняет задачу в БД
     * @param task задача которую необходимо сохранить
     */
    @Override
    public void putTask(Task task) {
        TaskDateHelper.setTaskEndDate(task);
        repository.save(task);
    }

    /**
     * Обновляет задачу, полученную из БД по id новыми данными,
     * полученными от пользователя
     * @param id уникальный индентификатор задачи.
     * @param newTask задача с новыми данными
     * @throws TaskNotFoundException если в БД не существует задачи с данным id
     */
    @Override
    public void updateTask(Long id, Task newTask) throws TaskNotFoundException{
        //Получаем задачу из БД по id
        Optional<Task> optTask = repository.findById(id);
        if (optTask.isPresent()) {
            Task taskToUpdate = optTask.get();
            //обновляем ее новыми данными
            taskToUpdate.setTitle(newTask.getTitle());
            taskToUpdate.setDescription(newTask.getDescription());
            taskToUpdate.setStartDate(newTask.getStartDate());
            taskToUpdate.setDaysToUse(newTask.getDaysToUse());
            TaskDateHelper.setTaskEndDate(taskToUpdate);
            //сохраняем обновленную задачу в БД
            putTask(taskToUpdate);
        } else {
            throw new TaskNotFoundException();
        }
    }

    /**
     * Удаляет задачу из БД
     * @param id уникальный индентификатор задачи.
     * @throws TaskNotFoundException если в БД не существует задачи с данным id
     */
    @Override
    public void deleteTask(Long id) throws TaskNotFoundException {
        if (!repository.existsById(id)){
            throw new TaskNotFoundException();
        }
        repository.deleteById(id);
    }

    /**
     * Создает задачу с пустыми полями,
     * инициализирует поле startDate текущим временем
     * @return задачу с пустыми полями, кроме поля startDate
     */
    public Task getEmptyTask(){
        Task emptyTask = new Task();
        TaskDateHelper.setCurrentDate(emptyTask);
        return emptyTask;
    }
}
