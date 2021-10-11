package com.example.dms.service;

import static org.assertj.core.api.Assertions.assertThat;

import com.example.dms.dto.Result;
import com.example.dms.dto.Task;
import io.vavr.collection.Seq;
import java.util.List;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
class DmsServiceTest {

  private final DmsService service = new DmsService();

  @Test
  public void test_1() {
    var z1 = Task.of("Z1", 40, 10, 30);
    var z2 = Task.of("Z2", 50, 10, 50);
    var z3 = Task.of("Z3", 60, 20, 40);
    var z4 = Task.of("Z4", 60, 10, 100);
    List<Task> tasks = List.of(z1, z2, z3, z4);

    Seq<Result> scheduledTasks = service.compute(tasks);
    List<String> names = getNames(scheduledTasks);

    List<String> expectedOrderOfTasks =
        List.of("Z1", "Z3", "Z2", "Z1", "Z2", "Z3", "Z1", "Z4", "Z2");
    assertThat(names).isEqualTo(expectedOrderOfTasks);
  }

  @Test
  public void test_2() {
    var z1 = Task.of("Z1", 20, 3, 7);
    var z2 = Task.of("Z2", 5, 2, 4);
    var z3 = Task.of("Z3", 10, 2, 9);
    List<Task> tasks = List.of(z1, z2, z3);

    Seq<Result> scheduledTasks = service.compute(tasks);
    List<String> names = getNames(scheduledTasks);

    List<String> expectedOrderOfTasks = //todo
        List.of("Z1", "Z3", "Z2", "Z1", "Z2", "Z3", "Z1", "Z4", "Z2");
    assertThat(names).isEqualTo(expectedOrderOfTasks);
  }

  private List<String> getNames(Seq<Result> scheduledTasks) {
    return scheduledTasks.toStream()
        .map(Result::getTaskName)
        .asJava();
  }
}