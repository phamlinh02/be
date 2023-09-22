package com.maidoo.maidoo.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Properties specific to Maidoo Backend.
 * <p>
 * Properties are configured in the {@code application.yml} file.
 */
@Getter
@ConfigurationProperties(prefix = "application", ignoreUnknownFields = false)
public class ApplicationProperties {

    @Value("${file.upload-dir}")
    private String uploadDir;

    @Value("${file.max-size}")
    private Long maxSize;

    @Value("${spring.mail.username}")
    private String userNameEmail;

    @Value("${spring.mail.password}")
    private String passwordEmail;
}
