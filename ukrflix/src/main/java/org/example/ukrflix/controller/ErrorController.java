package org.example.ukrflix.controller;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@Controller
public class ErrorController {
    private static final Logger LOGGER = Logger.getLogger(ErrorController.class);
    @GetMapping("/errors")
    public String renderErrorPage(HttpServletRequest httpRequest, Model model) {
        LOGGER.debug("error page");
        int httpErrorCode = (int) httpRequest.getAttribute("javax.servlet.error.status_code");
        String errorMsg = "";
        String errorCode = "";
        switch (httpErrorCode) {
            case 400: {
                errorMsg = "Http Error Code: 400. Bad Request";
                errorCode = "400";
                break;
            }
            case 401: {
                errorMsg = "Http Error Code: 401. Unauthorized";
                errorCode = "401";
                break;
            }
            case 404: {
                errorMsg = "Http Error Code: 404. Resource not found";
                errorCode = "404";
                break;
            }
            case 500: {
                errorMsg = "Http Error Code: 500. Internal Server Error";
                errorCode = "500";
                break;
            }
        }
        model.addAttribute("errorMessage", errorMsg);
        model.addAttribute("errorCode", errorCode);
        return "errors";
    }
}