package com.example.wantedpreonboardingbackend.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CommonResponse {
    private String message;
    private Integer statusCode;
    private Object data;

    public CommonResponse(String message,Integer status){
        this.message=message;
        this.statusCode=status;
        this.data=null;
    }
}
