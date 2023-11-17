package tech.divij.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("ping")
@CrossOrigin(origins = "*", maxAge = 3600, methods = {RequestMethod.GET})
public class HealthController {


  @GetMapping("")
  public ResponseEntity fetchAuthorities() {

    return ResponseEntity.ok().build();
  }


}
