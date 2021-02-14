package com.nikolaev.springboottodoapp.controllers;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

/**
 * Обработчик запросов с ошибками
 * @author Nikolaev Artem
 **/
@Controller
public class TaskErrorController implements ErrorController{

    @RequestMapping("/error")
    public String handleError(HttpServletRequest request) {

        Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);

        if (status != null) {
            Integer statusCode = Integer.valueOf(status.toString());

            if(statusCode == HttpStatus.NOT_FOUND.value()) {
                return "errors/404";
            }
            else if(statusCode == HttpStatus.BAD_REQUEST.value()) {
                return "errors/400";
            }
        }

        return "errors/error";
    }


    @Override
    public String getErrorPath() {
        return null;
    }
}
