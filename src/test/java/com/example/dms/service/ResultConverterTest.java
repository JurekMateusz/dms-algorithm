package com.example.dms.service;

import static org.assertj.core.api.Assertions.assertThat;

import com.example.dms.dto.Result;
import java.util.List;
import org.junit.jupiter.api.Test;

class ResultConverterTest {

  @Test
  void test_1() {
    List<String> tasks =
        List.of("T2", "T2", "T1", "T1", "T1", "T2", "T2", "T3", "T3",
            "", "T2", "T2", "T3", "T3", "", "T2", "T2", "", "", "");

    List<Result> convert = ResultConverter.convert(tasks);

    List<Result> expected = List.of(Result.of("T2", 0, 2),
        Result.of("T1", 2, 5),
        Result.of("T2", 5, 7),
        Result.of("T3", 7, 9),
        Result.of("T2", 10, 12),
        Result.of("T3", 12, 14),
        Result.of("T2", 15, 17));
    assertThat(convert).isEqualTo(expected);
  }
}