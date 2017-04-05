package org.jsche.web.controller;

import com.google.gson.Gson;
import org.jsche.common.exception.ControllerException;
import org.jsche.common.exception.ServiceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Intellij IDEA. Author myan
 * Date 2017/4/5.
 */
public class BasicController {
    private Logger logger = LoggerFactory.getLogger(BasicController.class);
    private final Gson gson = new Gson();

    @ExceptionHandler({ControllerException.class})
    @ResponseStatus(value = HttpStatus.ACCEPTED)
    @ResponseBody
    public String exceptionHandler(HttpServletResponse response,ControllerException e){
        logger.info("Controller exception:"+e.getMessage());
        Map<String, String> result = new HashMap<>();
        result.put("type","exception happens in controller.");
        result.put("message",e.getMessage());
        return gson.toJson(result);
    }

    @ExceptionHandler({ServiceException.class})
    @ResponseStatus(value = HttpStatus.ACCEPTED)
    @ResponseBody
    public String exceptionHandler(HttpServletResponse response,ServiceException e){
        logger.info("Controller exception:"+e.getMessage());
        Map<String, String> result = new HashMap<>();
        result.put("type","exception happens in service.");
        result.put("message",e.getMessage());
        return gson.toJson(result);
    }
}
