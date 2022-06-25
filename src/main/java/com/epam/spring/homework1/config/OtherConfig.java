package com.epam.spring.homework1.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import(PetConfig.class)
@ComponentScan("com.epam.spring.homework1.other")
public class OtherConfig {
}
