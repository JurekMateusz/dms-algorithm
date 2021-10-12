package com.example.dms.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Result {

  private String taskName;
  private int completionTime;

  public static Result of(String taskName, int completionTime) {
    return new Result(taskName, completionTime);
  }
}
