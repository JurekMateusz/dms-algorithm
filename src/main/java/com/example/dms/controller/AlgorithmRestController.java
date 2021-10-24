package com.example.dms.controller;

import com.example.dms.dto.Result;
import com.example.dms.dto.Task;
import com.example.dms.service.DmsService;
import com.example.dms.service.ResultConverter;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class AlgorithmRestController {


  @PostMapping("/dms")
  public List<Result> results(@RequestBody List<Task> tasks) {
    log.info(tasks.toString());
    return ResultConverter.convert(new DmsService().compute(tasks));
  }

}
