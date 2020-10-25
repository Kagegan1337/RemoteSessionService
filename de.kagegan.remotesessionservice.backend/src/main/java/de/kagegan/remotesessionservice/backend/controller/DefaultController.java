package de.kagegan.remotesessionservice.backend.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DefaultController {

    @RequestMapping(value = "/hello")
    public String greeting() {
        return "Hello world.";
    }
}
