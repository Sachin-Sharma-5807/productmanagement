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
public class ApiResponse<T> {
    private boolean status;
    private String message;
    private T data;
    private LocalDate timestamp;

    public static <T> ApiResponse<T> success(String message, T data) {
        return ApiResponse.<T>builder()
                .status(true)
                .message(message)
                .data(data)
                .timestamp(LocalDate.now())
                .build();
    }
}
