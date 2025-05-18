package com.example.demo.common;

import lombok.Getter;

@Getter
public enum ApiErrorCode {
  BAD_REQUEST(400, "잘못된 요청입니다"),
  UNAUTHORIZED(401, "인증이 필요합니다"),
  FORBIDDEN(403, "권한이 없습니다"),
  NOT_FOUND(404, "데이터를 찾을 수 없습니다"),
  INTERNAL_ERROR(500, "서버 오류가 발생했습니다");

  private int code;
  private String message;

  ApiErrorCode(int code, String message) {
    this.code = code;
    this.message = message;
  }

  public static ApiErrorCode fromCode(int code) {
    for (ApiErrorCode e : values()) {
      System.out.println(e);
      if (e.getCode() == code) return e;
    }
    return INTERNAL_ERROR;
  }
}
