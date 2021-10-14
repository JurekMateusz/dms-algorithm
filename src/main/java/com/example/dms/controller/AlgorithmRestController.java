package com.example.dms.controller;

import com.example.dms.dto.Result;
import com.example.dms.dto.Task;
import com.example.dms.service.DmsService;
import com.example.dms.service.ResultConverter;
import java.util.List;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AlgorithmRestController {


  @PostMapping("/dms")
  public List<Result> results(@RequestBody List<Task> tasks) {
    return ResultConverter.convert(new DmsService().compute(tasks));
  }

}
