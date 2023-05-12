package com.example.springboot.controller;

import com.example.springboot.DTO.company.CompanyCreateDTO;
import com.example.springboot.DTO.company.CompanyDeleteDTO;
import com.example.springboot.common.BaseResponse;
import com.example.springboot.model.Company;
import com.example.springboot.security.JwtAuthenticationFilter;
import com.example.springboot.security.JwtTokenProvider;
import com.example.springboot.service.CompanyService;
import com.google.gson.JsonObject;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
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
@RequestMapping("/api/company")
@Tag(name = "Company")
public class CompanyController {
    @Autowired
    CompanyService companyService;
    @Autowired
    JwtAuthenticationFilter jwtAuthenticationFilter;
    @Autowired
    JwtTokenProvider jwtTokenProvider;

    @GetMapping("/get-all")
    @Operation(description = "Quản lý xem toàn bộ công ty")
    @Parameter(name = "Authorization", in = ParameterIn.HEADER, description = "Mã phân quyền", required = true, examples = @ExampleObject(name = "Authorization", value = "Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiIxMiIsImlhdCI6MTY4MzE5MjYxOCwiZXhwIjoxNjgzNzk3NDE4fQ.hi6jAp4EElRGD3_4bBRU3E2CLIVOehdxBH85YyB5L2tUQBNWW5aPGsi1HQkjf2ozyZuBUW9bKAZQ6grcS4fwoA"))
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "0",
            description = "Thành công",
            content = @Content(
                mediaType = MediaType.APPLICATION_JSON_VALUE,
                examples = @ExampleObject(
                    value = "{\"error\":0,\"message\":\"success\",\"data\":[{\"companyID\":\"CP001\",\"companyName\":\"MASIU\",\"taxCode\":\"0987 668 886\",\"phoneNumber\":\"1512673124\"},{\"companyID\":\"CP002\",\"companyName\":\"FPT\",\"taxCode\":\"0985 131 444\",\"phoneNumber\":\"1987465319\"},{\"companyID\":\"CP003\",\"companyName\":\"VIETTEL\",\"taxCode\":\"0971 231 421\",\"phoneNumber\":\"2318765438\"},{\"companyID\":\"CP004\",\"companyName\":\"TOCOTOCO\",\"taxCode\":\"0973 875 123\",\"phoneNumber\":\"9823718649\"},{\"companyID\":\"CP005\",\"companyName\":\"SUNHOUSE\",\"taxCode\":\"0912 764 888\",\"phoneNumber\":\"8472195891\"},{\"companyID\":\"CP006\",\"companyName\":\"VINAPHONE\",\"taxCode\":\"0963 871 625\",\"phoneNumber\":\"7217261385\"},{\"companyID\":\"CP007\",\"companyName\":\"BIOC\",\"taxCode\":\"0965 721 468\",\"phoneNumber\":\"8391771282\"},{\"companyID\":\"CP008\",\"companyName\":\"NIKE\",\"taxCode\":\"0986 671 394\",\"phoneNumber\":\"1221785671\"}]}"
                )
            )),
    })
    public ResponseEntity<?> getAllCompany() {
        try {
            JsonObject res = companyService.getAllCompany();
            return ResponseEntity.ok().body(res.toString());
        }
        catch (Exception e) {
            e.printStackTrace();
            JsonObject res = BaseResponse.createFullMessageResponse(1, "system_error");
            return ResponseEntity.ok().body(res.toString());
        }
    }

    @PostMapping("/create")
    @Operation(description = "Công ty đăng ký thông tin")
    @Parameter(name = "Authorization", in = ParameterIn.HEADER, description = "Mã phân quyền", required = true, examples = @ExampleObject(name = "Authorization", value = "Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiIxMiIsImlhdCI6MTY4MzE5MjYxOCwiZXhwIjoxNjgzNzk3NDE4fQ.hi6jAp4EElRGD3_4bBRU3E2CLIVOehdxBH85YyB5L2tUQBNWW5aPGsi1HQkjf2ozyZuBUW9bKAZQ6grcS4fwoA"))
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "0",
            description = "Thành công",
            content = @Content(
                mediaType = MediaType.APPLICATION_JSON_VALUE,
                examples = @ExampleObject(
                    value = "{\"error\":0,\"message\":\"success\",\"data\":{\"companyID\":1,\"companyName\":\"MASIU\",\"taxCode\":\"1512673124\",\"phoneNumber\":\"0987 668 886\"}}"
                )
            ))
    })
    public ResponseEntity<?> createCompany(@RequestBody CompanyCreateDTO companyCreateDTO) {
        try {
            JsonObject res = companyService.createCompany(companyCreateDTO);
            return ResponseEntity.ok().body(res.toString());
        }
        catch (Exception e) {
            e.printStackTrace();
            JsonObject res = BaseResponse.createFullMessageResponse(1, "system_error");
            return ResponseEntity.ok().body(res.toString());
        }
    }

    @PostMapping("/update")
    @Operation(description = "Công ty cập nhật thông tin")
    @Parameter(name = "Authorization", in = ParameterIn.HEADER, description = "Mã phân quyền", required = true, examples = @ExampleObject(name = "Authorization", value = "Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiIxMiIsImlhdCI6MTY4MzE5MjYxOCwiZXhwIjoxNjgzNzk3NDE4fQ.hi6jAp4EElRGD3_4bBRU3E2CLIVOehdxBH85YyB5L2tUQBNWW5aPGsi1HQkjf2ozyZuBUW9bKAZQ6grcS4fwoA"))
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "0",
            description = "Thành công",
            content = @Content(
                mediaType = MediaType.APPLICATION_JSON_VALUE,
                examples = @ExampleObject(
                    value = "{\"error\":0,\"message\":\"success\",\"data\":{\"companyID\":\"CP010\",\"companyName\":\"Test DucAnh2002\",\"taxCode\":\"VLXX\",\"phoneNumber\":\"0987 838 802\"}}"
                )
            )),
        @ApiResponse(
            responseCode = "10",
            description = "Công ty chưa đăng ký",
            content = @Content(
                mediaType = MediaType.APPLICATION_JSON_VALUE,
                examples = @ExampleObject(
                    value = "{\"error\":10,\"message\":\"company_not_exist\"}"
                )
            )),
    })
    public ResponseEntity<?> updateMyCompany(@RequestBody Company company) {
        try {
            JsonObject res = companyService.updateCompany(company);
            return ResponseEntity.ok().body(res.toString());
        }
        catch (Exception e) {
            e.printStackTrace();
            JsonObject res = BaseResponse.createFullMessageResponse(1, "system_error");
            return ResponseEntity.ok().body(res.toString());
        }
    }

    @PostMapping("/delete")
    @Operation(description = "Xóa công ty")
    @Parameter(name = "Authorization", in = ParameterIn.HEADER, description = "Mã phân quyền", required = true, examples = @ExampleObject(name = "Authorization", value = "Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiIxMiIsImlhdCI6MTY4MzE5MjYxOCwiZXhwIjoxNjgzNzk3NDE4fQ.hi6jAp4EElRGD3_4bBRU3E2CLIVOehdxBH85YyB5L2tUQBNWW5aPGsi1HQkjf2ozyZuBUW9bKAZQ6grcS4fwoA"))
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "0",
            description = "Thành công",
            content = @Content(
                mediaType = MediaType.APPLICATION_JSON_VALUE,
                examples = @ExampleObject(
                    value = "{\"error\":0,\"message\":\"success\"}"
                )
            )),
        @ApiResponse(
            responseCode = "10",
            description = "Công ty chưa đăng ký",
            content = @Content(
                mediaType = MediaType.APPLICATION_JSON_VALUE,
                examples = @ExampleObject(
                    value = "{\"error\":10,\"message\":\"company_not_exist\"}"
                )
            )),
    })
    public ResponseEntity<?> deleteCompany(@RequestBody CompanyDeleteDTO companyDeleteDTO) {
        try {
            JsonObject res = companyService.deleteCompany(companyDeleteDTO);
            return ResponseEntity.ok().body(res.toString());
        }
        catch (Exception e) {
            e.printStackTrace();
            JsonObject res = BaseResponse.createFullMessageResponse(1, "system_error");
            return ResponseEntity.ok().body(res.toString());
        }
    }
}
