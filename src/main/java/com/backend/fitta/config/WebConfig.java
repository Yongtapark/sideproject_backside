package com.backend.fitta.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("https://fitta-git-dev-yiminwook.vercel.app")
                .allowedMethods("GET", "POST", "PUT","OPTIONS")
                .allowedHeaders("access-control-allow-credentials", "access-control-allow-origin")
                .allowCredentials(true)
                .maxAge(3000);
    }
}