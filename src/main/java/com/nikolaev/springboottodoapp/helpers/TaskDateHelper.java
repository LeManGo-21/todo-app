package com.nikolaev.springboottodoapp.helpers;

import com.nikolaev.springboottodoapp.entities.Task;
import java.time.LocalDate;

/**
 * @author Nikolaev Artem
 **/

/**
 * Предоставляет статические методы для работы с Датами задач
 */
public class TaskDateHelper {

    /**
     * Считает и утсанавливает дату завершения задачи
     * @param task здача у для которой необходимо установить дату завершения
     */
    public static void setTaskEndDate(Task task){
        LocalDate startDate = LocalDate.parse(task.getStartDate());
        LocalDate endDate = startDate.plusDays(task.getDaysToUse());
        task.setEndDate(endDate.toString());
    }

    /**
     * Устанавливает в качестве даты начала исполнения задачи текущую дату
     * @param task задача для которой необходимо установить дату
     */
    public static void setCurrentDate(Task task){
        task.setStartDate(LocalDate.now().toString());
    }
}
