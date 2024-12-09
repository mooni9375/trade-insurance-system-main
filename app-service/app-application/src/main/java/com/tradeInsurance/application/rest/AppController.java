package com.tradeInsurance.application.rest;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/")
public class AppController {

    private Environment env;

    @Autowired
    public AppController(Environment env) {
        this.env = env;
    }

    @GetMapping("/health_check")
    public String status() {

        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append("It's Working in App Service").append(System.lineSeparator());
        stringBuilder.append("port(local.server.port) = " + env.getProperty("local.server.port")).append(System.lineSeparator());
        stringBuilder.append("port(server.port) = " + env.getProperty("server.port")).append(System.lineSeparator());
        stringBuilder.append("token secret = " + env.getProperty("token.secret")).append(System.lineSeparator());
        stringBuilder.append("token expiration time = " + env.getProperty("token.expiration_time"));

        log.info(stringBuilder.toString());

        return stringBuilder.toString();
    }


}
