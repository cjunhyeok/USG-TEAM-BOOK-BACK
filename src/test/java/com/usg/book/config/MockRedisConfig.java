package com.usg.book.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.test.context.ActiveProfiles;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.junit.jupiter.Container;

@Configuration
@ActiveProfiles("test")
public class MockRedisConfig {

    @Container
    public static GenericContainer<?> redis = new GenericContainer<>("redis:latest").withExposedPorts(6379);

    @Bean
    @Primary
    public RedisConnectionFactory redisConnectionFactoryMock() {
        redis.start();
        return new LettuceConnectionFactory(redis.getHost(), redis.getFirstMappedPort());
    }
}
