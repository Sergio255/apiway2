package com.example.demo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "way2")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Way2Config {
    private String apiVersion;
    private String apiKey;
}