package com.example.dms.service;

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

public class DmsService {

  private Seq<Task> taskInOrder;
  private Map<Task, Tuple2<Integer, Integer>> tasksComputed = new HashMap<>();
  private Map<String, Integer> tasksWhileComputing = new HashMap<>();

  public List<String> compute(List<Task> tasks) {
    return compute(tasks, getLongestPeriod(tasks));
  }

  public List<String> compute(List<Task> tasks, int period) {
    List<String> result = new ArrayList<>();

    taskInOrder = sortByDeadline(tasks);
    for (int i = 0; i < period; i++) {
      updateTasksComputed(i);
      Option<Task> withHighestPriority = getTaskWithHighestPriorityAvailable();
      if (withHighestPriority.isDefined()) {
        Task task = withHighestPriority.get();
        String taskName = task.getTaskName();
        if (!tasksWhileComputing.containsKey(taskName)) {
          tasksWhileComputing.put(taskName, task.getCompletionTime() - 1);
        } else {
          tasksWhileComputing.put(task.getTaskName(),
              tasksWhileComputing.get(task.getTaskName()) - 1);
        }
        ifTaskCompletedAddToComputed(i, task);
        result.add(taskName);
      } else {
        result.add("");
      }
    }
    return result;
  }

  private Option<Task> getTaskWithHighestPriorityAvailable() {
    return taskInOrder.find(task -> !tasksComputed.containsKey(task)).toOption();
  }

  private void ifTaskCompletedAddToComputed(int i, Task task) {
    if (tasksWhileComputing.get(task.getTaskName()) == 0) {
      tasksComputed.put(task, findWithinTaskBeenDone(task, i));
      tasksWhileComputing.remove(task.getTaskName());
    }
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
    for (Map.Entry<Task, Tuple2<Integer, Integer>> entry : new HashMap<>(
        tasksComputed).entrySet()) {
      Tuple2<Integer, Integer> period = entry.getValue();
      if (actualPeriod >= period._1 && actualPeriod < period._2) {
        continue;
      }
      tasksComputed.remove(entry.getKey());
    }
  }

  private Tuple2<Integer, Integer> findWithinTaskBeenDone(Task task, int cycle) {
    int nextPeriod = 0;
    while (true) {
      nextPeriod += task.getPeriod();
      if (nextPeriod > cycle) {
        return Tuple.of(nextPeriod - task.getPeriod(), nextPeriod);
      }
    }
  }

  private int getLongestPeriod(List<Task> tasks) {
    return tasks.stream()
        .map(Task::getPeriod)
        .max(Comparator.naturalOrder())
        .orElse(20);
  }
}
