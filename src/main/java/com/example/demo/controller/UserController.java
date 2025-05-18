package com.example.demo.controller;

import com.example.demo.common.ApiResponse;
import com.example.demo.entity.User;
import com.example.demo.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/users")
public class UserController {

  private final UserService userService;

  @GetMapping
  public ApiResponse<List<User>> findAll() {
    List<User> resultList = userService.getUser();
    return ApiResponse.success(resultList);
  }
}
