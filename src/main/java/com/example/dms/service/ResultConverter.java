package com.example.dms.service;

import com.example.dms.dto.Result;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import org.apache.logging.log4j.util.Strings;

public class ResultConverter {

  public static List<Result> convert(List<String> tasks) {
    if (tasks.isEmpty()) {
      return Collections.emptyList();
    }
    List<Result> results = new ArrayList<>();
    String earlierTask = tasks.get(0);
    int start = 0;
    for (int i = 1; i < tasks.size(); i++) {
      String current = tasks.get(i);
      if (!earlierTask.equals(current)) {
        results.add(Result.of(earlierTask, start, i));
        earlierTask = current;
        start = i;
      }
    }
    return results.stream()
        .filter(task -> Strings.isNotBlank(task.getTaskName()))
        .collect(Collectors.toList());
  }
}
