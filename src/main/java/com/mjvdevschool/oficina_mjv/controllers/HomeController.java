package com.mjvdevschool.oficina_mjv.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping
public class HomeController {

    /** Metodo que inicia a pagina home.
     * @return
     */
    @GetMapping
    public String home() {
        return "home";
    }
}
