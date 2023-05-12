package com.example.springboot.controller;

import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin("*")
@Tag(name = "Test Api")
public class Controller {
    @RequestMapping("/")
    @Operation(description = "Test Api")
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "0",
            description = "Thành công",
            content = @Content(
                mediaType = MediaType.APPLICATION_JSON_VALUE,
                examples = @ExampleObject(
                    value = "Hello World"
                )
            )),
    })
    public ResponseEntity chatApp() {
        return ResponseEntity.ok().body("Hello World");
    }
}
