package com.example.demo.common;

import lombok.Getter;

@Getter
public enum ApiMessage {
  SUCCESS("성공"),
  ERROR("오류");

  private final String message;

  ApiMessage(String message) {
    this.message = message;
  }
}
