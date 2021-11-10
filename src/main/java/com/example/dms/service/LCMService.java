package com.example.dms.service;

import static java.lang.String.format;

import com.example.dms.dto.Task;
import io.vavr.control.Try;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class LCMService {

  public static int count(List<Task> tasks) {
    List<Integer> periods = mapTaskToPeriods(tasks);
    Try<Integer> tryMax = Try.of(() -> Collections.max(periods));
    if (tryMax.isFailure()) {
      return 0;
    }

    final int listSize = periods.size();
    final int max = tryMax.get();

    for (int leastCommonMutiple = max; leastCommonMutiple < 500;
        leastCommonMutiple += max) {

      if (listSize <= countDivisibleNumbersByCurrentNumber(periods, leastCommonMutiple)) {
        return leastCommonMutiple;
      }
    }
    return 100;
//    throw new IllegalStateException(
//        format("Not found most common multiple for list of integers: %s", periods));
  }

  private static long countDivisibleNumbersByCurrentNumber(List<Integer> periods,
      long leastCommonMutiple) {
    return periods
        .stream()
        .filter(x -> (leastCommonMutiple % x) == 0)
        .count();
  }

  private static List<Integer> mapTaskToPeriods(List<Task> tasks) {
    return tasks.stream()
        .map(Task::getPeriod)
        .collect(Collectors.toList());
  }
}
