package com.example.springboot.controller;


import com.example.springboot.DTO.employees_company.EmployeesCompanyDTO;
import com.example.springboot.DTO.employees_company.EmployeesCompanyDeleteDTO;
import com.example.springboot.common.BaseResponse;
import com.example.springboot.model.EmployeesCompany;
import com.example.springboot.security.JwtAuthenticationFilter;
import com.example.springboot.security.JwtTokenProvider;
import com.example.springboot.service.EmployeesCompanyService;
import com.google.gson.JsonObject;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
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
@RequestMapping("/api/employees-company")
@Tag(name = "Employees-company")
public class EmployeesCompanyController {
    @Autowired
    EmployeesCompanyService employeesCompanyService;
    @Autowired
    JwtAuthenticationFilter jwtAuthenticationFilter;
    @Autowired
    JwtTokenProvider jwtTokenProvider;

    @PostMapping("/create")
    @Operation(description = "Công ty tạo nhân viên")
    @Parameter(name = "Authorization", in = ParameterIn.HEADER, description = "Mã phân quyền", required = true, examples = @ExampleObject(name = "Authorization", value = "Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiIxMiIsImlhdCI6MTY4MzE5MjYxOCwiZXhwIjoxNjgzNzk3NDE4fQ.hi6jAp4EElRGD3_4bBRU3E2CLIVOehdxBH85YyB5L2tUQBNWW5aPGsi1HQkjf2ozyZuBUW9bKAZQ6grcS4fwoA"))
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "0",
            description = "Thành công",
            content = @Content(
                mediaType = MediaType.APPLICATION_JSON_VALUE,
                examples = @ExampleObject(
                    value = "{\"error\":0,\"message\":\"success\",\"data\":{\"employeeID\":\"E001\",\"employeeName\":\"DUCANH\",\"date\":\"May 10, 2023, 7:00:00 AM\",\"phoneNumber\":\"0987838802\",\"companyID\":1}}"
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
        @ApiResponse(
            responseCode = "11",
            description = "Nhân viên đã tồn tại",
            content = @Content(
                mediaType = MediaType.APPLICATION_JSON_VALUE,
                examples = @ExampleObject(
                    value = "{\"error\":11,\"message\":\"employee_already_exist\"}"
                )
            )),
    })
    public ResponseEntity<?> createMyEmployee(@RequestBody EmployeesCompanyDTO employeesCompanyDTO) {
        try {
            JsonObject res = employeesCompanyService.createEmployee(employeesCompanyDTO);
            return ResponseEntity.ok().body(res.toString());
        }
        catch (Exception e) {
            e.printStackTrace();
            JsonObject res = BaseResponse.createFullMessageResponse(1, "system_error");
            return ResponseEntity.ok().body(res.toString());
        }
    }

    @PostMapping("/delete")
    @Operation(description = "Công ty xóa nhân viên")
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
            responseCode = "11",
            description = "Mã nhân viên nhập vào không hợp lệ",
            content = @Content(
                mediaType = MediaType.APPLICATION_JSON_VALUE,
                examples = @ExampleObject(
                    value = "{\"error\":11,\"message\":\"employee_not_exist\"}"
                )
            )),
    })
    public ResponseEntity<?> deleteMyEmployee(@RequestBody EmployeesCompanyDeleteDTO employeesCompanyDeleteDTO) {
        try {
            JsonObject res = employeesCompanyService.deleteEmployee(employeesCompanyDeleteDTO);
            return ResponseEntity.ok().body(res.toString());
        }
        catch (Exception e) {
            e.printStackTrace();
            JsonObject res = BaseResponse.createFullMessageResponse(1, "system_error");
            return ResponseEntity.ok().body(res.toString());
        }
    }

    @PostMapping("/update")
    @Operation(description = "Công ty cập nhật nhân viên")
    @Parameters(value = {
        @Parameter(name = "Authorization", in = ParameterIn.HEADER, description = "Mã phân quyền", required = true, examples = @ExampleObject(name = "Authorization", value = "Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiIxMiIsImlhdCI6MTY4MzE5MjYxOCwiZXhwIjoxNjgzNzk3NDE4fQ.hi6jAp4EElRGD3_4bBRU3E2CLIVOehdxBH85YyB5L2tUQBNWW5aPGsi1HQkjf2ozyZuBUW9bKAZQ6grcS4fwoA")),
        @Parameter(name = "companyID", in = ParameterIn.QUERY, description = "Id của công ty", required = true, examples = @ExampleObject(name = "companyID", value = "1"))

    })
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
        @ApiResponse(
            responseCode = "11",
            description = "Mã nhân viên nhập vào không tồn tại",
            content = @Content(
                mediaType = MediaType.APPLICATION_JSON_VALUE,
                examples = @ExampleObject(
                    value = "{\"error\":11,\"message\":\"employee_not_exist\"}"
                )
            ))
    })
    public ResponseEntity<?> updateMyEmployee(@RequestBody EmployeesCompanyDTO employeesCompanyDTO) {
        try {
            JsonObject res = employeesCompanyService.updateEmployee(employeesCompanyDTO);
            return ResponseEntity.ok().body(res.toString());
        }
        catch (Exception e) {
            e.printStackTrace();
            JsonObject res = BaseResponse.createFullMessageResponse(1, "system_error");
            return ResponseEntity.ok().body(res.toString());
        }
    }

    @Operation(description = "Lấy thông tin toàn bộ nhân viên")
    @Parameter(name = "Authorization", in = ParameterIn.HEADER, description = "Mã phân quyền", required = true, examples = @ExampleObject(name = "Authorization", value = "Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiIxMiIsImlhdCI6MTY4MzE5MjYxOCwiZXhwIjoxNjgzNzk3NDE4fQ.hi6jAp4EElRGD3_4bBRU3E2CLIVOehdxBH85YyB5L2tUQBNWW5aPGsi1HQkjf2ozyZuBUW9bKAZQ6grcS4fwoA"))
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "0",
            description = "Thành công",
            content = @Content(
                mediaType = MediaType.APPLICATION_JSON_VALUE,
                examples = @ExampleObject(
                    value = "{\"error\":0,\"message\":\"success\",\"data\":[{\"employeeID\":\"E001\",\"employeeName\":\"DUCANH\",\"date\":\"May 10, 2023, 7:00:00 AM\",\"phoneNumber\":\"0987838802\",\"companyID\":1},{\"employeeID\":\"E002\",\"employeeName\":\"DUCANH\",\"date\":\"May 10, 2023, 7:00:00 AM\",\"phoneNumber\":\"0987838802\",\"companyID\":1},{\"employeeID\":\"E003\",\"employeeName\":\"DUCANHV3\",\"date\":\"May 10, 2023, 7:00:00 AM\",\"phoneNumber\":\"0987838802\",\"companyID\":1}]}"
                )
            )),
    })
    @GetMapping("/get-all")
    public ResponseEntity<?> getAllEmployees() {
        try {
            JsonObject res = employeesCompanyService.getAllEmployees();
            return ResponseEntity.ok().body(res.toString());
        }
        catch (Exception e) {
            e.printStackTrace();
            JsonObject res = BaseResponse.createFullMessageResponse(1, "system_error");
            return ResponseEntity.ok().body(res.toString());
        }
    }

    @Operation(description = "Lấy thông tin toàn bộ nhân viên theo id công ty")
    @Parameter(name = "Authorization", in = ParameterIn.HEADER, description = "Mã phân quyền", required = true, examples = @ExampleObject(name = "Authorization", value = "Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiIxMiIsImlhdCI6MTY4MzE5MjYxOCwiZXhwIjoxNjgzNzk3NDE4fQ.hi6jAp4EElRGD3_4bBRU3E2CLIVOehdxBH85YyB5L2tUQBNWW5aPGsi1HQkjf2ozyZuBUW9bKAZQ6grcS4fwoA"))
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "0",
            description = "Thành công",
            content = @Content(
                mediaType = MediaType.APPLICATION_JSON_VALUE,
                examples = @ExampleObject(
                    value = "{\"error\":0,\"message\":\"success\",\"data\":[{\"employeeID\":\"E001\",\"employeeName\":\"DUCANH\",\"date\":\"May 10, 2023, 7:00:00 AM\",\"phoneNumber\":\"0987838802\",\"companyID\":1},{\"employeeID\":\"E002\",\"employeeName\":\"DUCANH\",\"date\":\"May 10, 2023, 7:00:00 AM\",\"phoneNumber\":\"0987838802\",\"companyID\":1},{\"employeeID\":\"E003\",\"employeeName\":\"DUCANHV3\",\"date\":\"May 10, 2023, 7:00:00 AM\",\"phoneNumber\":\"0987838802\",\"companyID\":1}]}"
                )
            )),
    })
    @GetMapping("/get-by-companyid")
    public ResponseEntity<?> getAllEmployeesByCompanyId(@RequestParam(value = "companyID") String companyId) {
        try {
            System.out.println(companyId);
            JsonObject res = employeesCompanyService.getAllEmployeesByCompanyID(companyId);
            return ResponseEntity.ok().body(res.toString());
        }
        catch (Exception e) {
            e.printStackTrace();
            JsonObject res = BaseResponse.createFullMessageResponse(1, "system_error");
            return ResponseEntity.ok().body(res.toString());
        }
    }
}
