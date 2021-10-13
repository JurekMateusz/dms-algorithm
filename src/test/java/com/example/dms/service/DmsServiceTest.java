package com.example.dms.service;

import static org.assertj.core.api.Assertions.assertThat;

import com.example.dms.dto.Task;
import java.util.List;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
public class DmsServiceTest {

  private final DmsService service = new DmsService();

  //https://www.youtube.com/watch?v=E6KGDpY_XoI&ab_channel=ManojMVR
  @Test
  public void test_2() {
    var z1 = Task.of("T1", 20, 3, 7);
    var z2 = Task.of("T2", 5, 2, 4);
    var z3 = Task.of("T3", 10, 2, 9);
    List<Task> tasks = List.of(z1, z2, z3);

    List<String> scheduledTasks = service.compute(tasks);

    List<String> expectedOrderOfTasks =
        List.of("T2", "T2", "T1", "T1", "T1", "T2", "T2", "T3", "T3",
            "", "T2", "T2", "T3", "T3", "", "T2", "T2", "", "", "");
    assertThat(scheduledTasks).isEqualTo(expectedOrderOfTasks);
  }

  // https://www.geeksforgeeks.org/deadline-monotonic-cpu-scheduling/
  @Test
  public void test_3() {
    var z1 = Task.of("T1", 7, 2, 6);
    var z2 = Task.of("T2", 5, 2, 4);

    List<Task> tasks = List.of(z1, z2);
    List<String> scheduledTasks = service.compute(tasks, 16);

    List<String> expectedOrderOfTasks =
        List.of("T2", "T2", "T1", "T1", "", "T2", "T2", "T1", "T1", "", "T2", "T2", "", "", "T1",
            "T2");
    assertThat(scheduledTasks).isEqualTo(expectedOrderOfTasks);
  }

}