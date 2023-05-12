package com.example.springboot.controller.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FloorRequest implements Serializable {
    @ApiModelProperty(value = "name", example = "Tầng 2")
    private String name;

    @ApiModelProperty(value = "pricePerM2", example = "300000")
    private String pricePerM2;

    @ApiModelProperty(value = "area", example = "600")
    private String area;
}
