package com.lingaro.web;

import java.util.logging.Logger;
import java.util.stream.Collectors;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SpringBootApplication
public class Application extends SpringBootServletInitializer {
  private static final Logger LOG = Logger.getGlobal();

  @GetMapping(value="/", produces=MimeTypeUtils.TEXT_PLAIN_VALUE)
	public Object info() {
    LOG.info("got info request");
    return System.getProperties().entrySet()
      .stream()
      .map(entry -> entry.getKey()+": "+entry.getValue())
      .sorted()
      .collect(Collectors.joining("\n"));
	}
  
  @Override
  protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
      return builder.sources(Application.class);
  }

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
}
