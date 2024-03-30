package com.example.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Controller class for handling home-related operations.
 */
@Controller
public class HomeController {

    /**
     * Handles requests to the home page.
     *
     * @return The name of the view template for the home page.
     */
    @GetMapping("/")
    public String index() {
        return "index.html";
    }
}
