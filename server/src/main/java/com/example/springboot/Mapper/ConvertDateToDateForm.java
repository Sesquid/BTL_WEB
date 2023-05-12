package com.example.springboot.Mapper;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

@Component
@Data
public class ConvertDateToDateForm {
    public String convertDateToDate(Date date){
        try {
            String result = "";
            DateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy");
            result = dateFormat.format(date).toString();
            return result;
        }
        catch (Exception e){
            return e.toString();
        }
    }

//    public static void main(String[] args) {
//        Date date = new Date(0);
//        ConvertDateToDateForm cdtd = new ConvertDateToDateForm();
//        String result = cdtd.convertDateToDate(date);
//        System.out.println(result);
//    }
}
