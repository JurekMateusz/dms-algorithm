package com.example.dms.controller;

import com.example.dms.dto.Result;
import com.example.dms.dto.Task;
import com.example.dms.service.DmsService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AlgorithmRestController {


  @PostMapping("/dms")
  public List<Result> results(@RequestBody Task task) {
    return null;
  }

}
