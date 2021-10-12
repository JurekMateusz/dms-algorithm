package com.example.dms.service;

import com.example.dms.dto.Result;
import com.example.dms.dto.Task;
import io.vavr.Tuple;
import io.vavr.Tuple2;
import io.vavr.collection.Queue;
import io.vavr.collection.Seq;
import io.vavr.control.Option;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class DmsService {

  private final Integer DEFAULT_PERIOD = 100;
  private Seq<Task> taskInOrder;
  private Map<Task, Integer> taskComputed = new HashMap<>();

  public Seq<Result> compute(List<Task> tasks) {
    List<Result> result = new ArrayList<>();

    taskInOrder = sortByDeadline(tasks);
    int longestPeriod = 16;//getLongestPeriod(tasks);
    for (int i = 0; i < longestPeriod; ) {
      updateTasksComputed(i);
      Option<Task> withHighestPriority = getTaskWithHighestPriorityAvailable();
      if (withHighestPriority.isDefined()) {
        Task task = withHighestPriority.get();
        taskComputed.put(task, i);
        result.add(Result.of(task.getTaskName(), task.getCompletionTime()));
        i += task.getCompletionTime();
      } else {
        result.add(createEmptyTask());
        i += 1;
      }
    }

    return Queue.ofAll(result);
  }

  private Seq<Task> sortByDeadline(List<Task> tasks) {
    return tasks.stream()
        .map(Task::getDeadline)
        .sorted(Comparator.naturalOrder())
        .map(highestPriority -> tasks.stream()
            .filter(it -> it.getDeadline() == highestPriority)
            .findAny()
            .orElseThrow(IllegalStateException::new))
        .collect(Queue.collector());
  }

  private void updateTasksComputed(int actualPeriod) {

    for (Map.Entry<Task, Integer> entry : new HashMap<>(taskComputed).entrySet()) {
      Tuple2<Integer, Integer> period = findWithinTaskBeenDone(entry);
      if (actualPeriod >= period._1 && actualPeriod < period._2) {
        continue;
      }
      taskComputed.remove(entry.getKey());
    }
  }

  private Tuple2<Integer, Integer> findWithinTaskBeenDone(Entry<Task, Integer> entry) {
    Task task = entry.getKey();
    Integer point = entry.getValue();
    int nextPeriod = 0;
    while (true) {
      nextPeriod += task.getPeriod();
      if (nextPeriod > point) {
        return Tuple.of(nextPeriod - task.getPeriod(), nextPeriod);
      }
    }
  }

  private int getLongestPeriod(List<Task> tasks) {
    return tasks.stream()
        .map(Task::getPeriod)
        .max(Comparator.naturalOrder())
        .orElse(DEFAULT_PERIOD);
  }

  private Result createEmptyTask() {
    return Result.of("", 1);
  }

  private Option<Task> getTaskWithHighestPriorityAvailable() {
    return taskInOrder.find(task -> !taskComputed.containsKey(task)).toOption();
  }
}
