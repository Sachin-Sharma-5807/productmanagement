package com.innoquora.productmanagementsystem.payload;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class ErrorResposne <T>{

    private boolean status;
    private String message;
    private T data;
    private LocalDate timestamp;
}
