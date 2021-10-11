package com.example.dms.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Task {

  private String taskName;
  private int period;
  private int completionTime;
  private int deadline;

  public static Task of(String taskName, int period, int completionTime, int deadline) {
    return new Task(taskName, period, completionTime, deadline);
  }
}
