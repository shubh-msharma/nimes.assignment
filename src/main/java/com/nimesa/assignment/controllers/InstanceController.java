package com.nimesa.assignment.controllers;

import com.nimesa.assignment.services.InstanceService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class InstanceController {

    private final InstanceService instanceService;
}
