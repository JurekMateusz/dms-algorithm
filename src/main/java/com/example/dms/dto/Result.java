package com.example.dms.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Result {

  private String taskName;
  private int start;
  private int end;

  public static Result of(String taskName, int start, int end) {
    return new Result(taskName, start, end);
  }
}
