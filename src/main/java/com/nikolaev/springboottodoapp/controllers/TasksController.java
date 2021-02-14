package com.nikolaev.springboottodoapp.controllers;

import com.nikolaev.springboottodoapp.entities.Task;
import com.nikolaev.springboottodoapp.exceptions.TaskNotFoundException;
import com.nikolaev.springboottodoapp.services.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;


/**
 * @author Nikolaev Artem
 **/

@Controller
public class TasksController {

    private TaskService taskService;

    @Autowired
    public TasksController(TaskService taskService) {
        this.taskService = taskService;
    }

    /**
     * Обрабатывает Get-запрос на получение домашней(стартовой) страницы
     * @return имя представления, отображающего домашнюю(стартовую) страницу
     */
    @GetMapping()
    public String homePage(){
        return "home-page";
    }

    /**
     * Обрабатывает Get-запрос на получение страницы со списком задач
     * @param model модель для передачи данных на представление
     * @return имя представления, отображающего список задач
     */
    @GetMapping("/tasks")
    public String allTasks(Model model){
        model.addAttribute("tasks", taskService.getTaskList());
        return "tasks";
    }

    /**
     * Обрабатывает Get-запрос на получение информации о задаче
     * @param id уникальный индентификатор задачи. Получается из URI запроса
     * @param model модель для передачи данных на представление
     * @return имя представления, отображающего информацию о задаче
     * или представление с информацией об ошибке в случае неверного ID
     * @throws TaskNotFoundException если задачи с указаным id не существует
     */
    @GetMapping("/tasks/{id}")
    public String showTask(@PathVariable(value = "id") Long id, Model model) throws TaskNotFoundException{
        Task task = taskService.getTaskById(id);
        model.addAttribute("task", task);
        return "task/info";
    }


    /**
     * Обрабатывает Get-запрос на получение формы создания задачи
     * @param model модель для передачи данных на представление
     * @return имя представления, отображающего форму
     */
    @GetMapping("/tasks/new")
    public String createForm(Model model){
        Task emptyTask = taskService.getEmptyTask();
        model.addAttribute("task", emptyTask);
        return "task/create";
    }

    /**
     * Обрабатывает Post-запрос на обработку данных формы.
     * Добавляет задачу в БД.
     * @param task объект задачи с заполнеными полями, получаемый из формы создания задачи
     * @param bindingResult Создержит результаты проверки на валидность введеных данных
     * @return имя представления, отображающего форму в случае некорректных данных
     * или перенаправляет на страницу со списком задач в случае успешного создания формы
     */
    @PostMapping("/tasks/new")
    public String createTask(@ModelAttribute @Valid Task task, BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            return "task/create";
        }
        taskService.putTask(task);

        return "redirect:/tasks";
    }

    /**
     * Обрабатывает Get-запрос на получение формы обновления задачи
     * @param id уникальный индентификатор задачи. Получается из URI запроса
     * @param model модель для передачи данных на представление
     * @return имя представления, отображающего форму
     * или представление с информацией об ошибке в случае неверного ID
     * @throws TaskNotFoundException если задачи с указаным id не существует
     */
    @GetMapping("/tasks/{id}/edit")
    public String updateForm(@PathVariable("id") Long id, Model model) throws TaskNotFoundException{
        Task task = taskService.getTaskById(id);
        model.addAttribute("task", task);
        return "task/update";
    }

    /**
     * Обрабатывает Post-запрос на обработку данных формы.
     * Перенаправляет на страницу со списком задач в случае успешной обработки формы
     * Возвращает ту же форму, но с информацией он некорретных данных,
     * в случае некорректо введенных данных
     * Возвращает страницу с информацией об ошибке в случае неверного ID
     * @param id уникальный индентификатор задачи. Получается из URI запроса
     * @param task объект задачи с заполнеными полями, получаемый из формы создания задачи
     * @param bindingResult Создержит результаты проверки на валидность введеных данных
     * @return имя представления, отображающего форму в случае некорректных данных,
     * или перенаправляет на страницу данной задачи в случае успешного создания формы,
     * или представление с информацией об ошибке в случае неверного ID
     */
    @PostMapping("/tasks/{id}/edit")
    public String updateTask(@PathVariable("id") Long id, @ModelAttribute @Valid Task task, BindingResult bindingResult) throws TaskNotFoundException{
        if (bindingResult.hasErrors()){
            return "task/update";
        }
        taskService.updateTask(id, task);
        return "redirect:/tasks/" + id;
    }

    /**
     * Обрабатывает Get-запрос на удаление задачи
     * @param id уникальный индентификатор задачи. Получается из URI запроса
     * @return перенаправляет на страницу со списком задач
     * @throws TaskNotFoundException если задачи с указаным id не существует
     */
    @GetMapping("/tasks/{id}/delete")
    public String deleteTask(@PathVariable("id") Long id) throws TaskNotFoundException{
        taskService.deleteTask(id);
        return "redirect:/tasks";
    }


}
