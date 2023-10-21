package com.nimesa.assignment.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class MainController {

    @GetMapping("/version")
    public ResponseEntity<?> version(){
        return ResponseEntity.ok("v0.0.1");
    }

    @GetMapping("/healthz")
    public ResponseEntity<?> healthz(){
        return ResponseEntity.ok("ok");
    }
}
