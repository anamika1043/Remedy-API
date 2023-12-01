package com.atos.remedy.configuration;

import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
@ComponentScan({ "com.atos.remedy" })
public class RemedyAPIApplication extends SpringBootServletInitializer
{
    public static void main(final String[] args) {
        SpringApplication.run((Class)RemedyAPIApplication.class, args);
    }
}
