package com.example.dms.service;

import com.example.dms.dto.Result;
import com.example.dms.dto.Task;
import io.vavr.NotImplementedError;
import io.vavr.collection.Seq;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class DmsService {


  public Seq<Result> compute(List<Task> tasks) {

    throw new NotImplementedError();
  }
}
