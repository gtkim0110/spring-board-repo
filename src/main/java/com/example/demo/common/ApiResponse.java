package com.example.demo.common;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ApiResponse<T> {
  private int code;         // 예: 200, 400, 500 등
  private String message;   // 예: "성공", "잘못된 요청" 등
  private T data;           // 실제 응답 데이터

  public static <T> ApiResponse<T> success(T data) {
    return new ApiResponse<>(200, ApiMessage.SUCCESS.getMessage(), data);
  }

  public static <T> ApiResponse<T> error(ApiErrorCode errorCode) {
    return new ApiResponse<>(errorCode.getCode(), errorCode.getMessage(), null);
  }

  public static <T> ApiResponse<T> error(int code, String message) {
    return new ApiResponse<>(code, message, null);
  }

  public static <T> ApiResponse<T> of(int code, String message, T data) {
    return new ApiResponse<>(code, message, data);
  }
}
