package com.example.dms.controller;

import com.example.dms.service.DmsService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AlgorithmRestController {

  private final DmsService dmsService;


}
