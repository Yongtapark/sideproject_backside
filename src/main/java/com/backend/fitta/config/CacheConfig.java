package com.backend.fitta.config;

import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;

import java.time.Duration;

@Configuration
@EnableCaching
public class CacheConfig {
    /**
     * RedisConnectionFactory : Redis 서버에 연결
     * cacheManager : 이 연결을 통해 실제 캐시 데이터를 관리
     */

    @Bean
    public CacheManager cacheManager(RedisConnectionFactory redisConnectionFactory){
        Duration expiration = Duration.ofMinutes(1); //만료 시간을 1분으로 설정
        return RedisCacheManager
                .builder(redisConnectionFactory)
                .cacheDefaults(RedisCacheConfiguration.defaultCacheConfig().entryTtl(expiration))
                .build();
    }
}
