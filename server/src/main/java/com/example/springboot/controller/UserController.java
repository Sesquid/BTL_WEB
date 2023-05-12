package com.example.springboot.controller;

import com.example.springboot.common.BaseResponse;
import com.example.springboot.model.User;
import com.example.springboot.service.MyUserDetailsService;
import com.google.gson.JsonObject;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@CrossOrigin("*")
@RequestMapping("/api")
@Tag(name = "User")
public class UserController {
    @Autowired
    MyUserDetailsService myUserDetailsService;

    @PostMapping("/login")
    @Operation(description = "Login send username and password")
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "0",
            description = "Đăng nhập thành công",
            content = @Content(
                mediaType = MediaType.APPLICATION_JSON_VALUE,
                examples = {
                    @ExampleObject(
                        name = "Đăng nhập thành công",
                        description = "token: Mã xác thực.",
                        value = "{\"error\":0,\"message\":\"success\",\"data\":{\"token\":\"eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiIxIiwiaWF0IjoxNjgzMDY2NTY2LCJleHAiOjE2ODM2NzEzNjZ9.fO9m2C3HSONNbBHpbbdHIAmnxUOrA0Tsee8nWGQ36EeHlrnzA7Mi7bjIDOQB6-2yp8gJvptequH2W3q6H0F5yA\"}}"
                    ),
                }
            )),
        @ApiResponse(
            responseCode = "10",
            description = "Tài khoản không tồn tại",
            content = @Content(
                mediaType = MediaType.APPLICATION_JSON_VALUE,
                examples = @ExampleObject(
                        value = "{\"error\":10,\"message\":\"username_not_exist\"}"
                    )
            )),
        @ApiResponse(
            responseCode = "11",
            description = "Sai mật khẩu",
            content = @Content(
                mediaType = MediaType.APPLICATION_JSON_VALUE,
                examples = @ExampleObject(
                        value = "{\"error\":11,\"message\":\"invalid_password\"}"
                    )
            ))
    })
    public ResponseEntity login(@RequestBody User user)  {
        try {
            String username = user.getUsername();
            String password = user.getPassword();
            System.out.println(user.getRole());
            JsonObject res = myUserDetailsService.login(username, password);
            return ResponseEntity.ok().body(res.toString());
        }
        catch (Exception e) {
            e.printStackTrace();
            JsonObject res = BaseResponse.createFullMessageResponse(1, "system_error");
            return ResponseEntity.ok().body(res.toString());
        }
    }

    @PostMapping("/register")
    @Operation(description = "Register send username and password")
    @ApiResponses(value = {
    @ApiResponse(
        responseCode = "0",
        description = "Đăng ký thành công",
        content = @Content(
            mediaType = MediaType.APPLICATION_JSON_VALUE,
            examples = @ExampleObject(
                    name = "Đăng ký thành công",
                    description = "token: Mã xác thực.",
                    value = "{\"error\":0,\"message\":\"success\",\"data\":{\"token\":\"eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiIxIiwiaWF0IjoxNjgzMDY2NTY2LCJleHAiOjE2ODM2NzEzNjZ9.fO9m2C3HSONNbBHpbbdHIAmnxUOrA0Tsee8nWGQ36EeHlrnzA7Mi7bjIDOQB6-2yp8gJvptequH2W3q6H0F5yA\"}}"
                )
        )),
    @ApiResponse(
        responseCode = "10",
        description = "Tài khoản đã tồn tại",
        content = @Content(
            mediaType = MediaType.APPLICATION_JSON_VALUE,
            examples = @ExampleObject(
                    value = "{\"error\":10,\"message\":\"user_exist\"}"
                )
        )),
    })
    public ResponseEntity register(@RequestBody User user)  {
        try {
            String username = user.getUsername();
            String password = user.getPassword();
            JsonObject res = myUserDetailsService.register(username, password);
            return ResponseEntity.ok().body(res.toString());
        }
        catch (Exception e) {
            e.printStackTrace();
            JsonObject res = BaseResponse.createFullMessageResponse(1, "system_error");
            return ResponseEntity.ok().body(res.toString());
        }
    }
}
