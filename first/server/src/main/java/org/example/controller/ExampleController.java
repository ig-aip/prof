package org.example.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.example.service.CustomDetailsWorkerService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/example")
@Tag(name = "Example Аутентификация")
public class ExampleController {
    private CustomDetailsWorkerService userService;


    @GetMapping
    @Operation(summary = "Доступ только для авторезировынных")
    public String example(){
        return "Hello World; +_+";
    }

    @GetMapping("/admin")
    @Operation(summary = "Доступ только для ADMINOV")
    @PreAuthorize("hasRole('ROLE_FRANCHAISER')")
    public String exampleAdmit(){
        return "hello FRANCHAISER |_|";
    }


    @GetMapping("/get-admin")
    @Operation(summary = "Получить админку")
    public void getAdmin(){
        userService.getAdmin();
    }


}
