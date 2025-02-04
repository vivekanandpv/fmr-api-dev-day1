package com.example.fmrapidev.config;

import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableCaching
public class CacheConfig {
    //  Additional cache configurations can go here
    //  In Spring Boot, you can implement caching using Spring Cache Abstraction with providers 
    //  like EhCache, Redis, or simple in-memory caching
    //  Spring Boot provides several cache providers. The default is ConcurrentHashMap (in-memory), 
    //  but for production, Redis, EhCache, or Caffeine is recommended.
}

