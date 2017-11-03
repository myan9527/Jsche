package org.jsche.config;

import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Configuration;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

/**
 * Created by myan on 2017/11/3.
 * Intellij IDEA
 */
@Configuration
@EnableCaching
@EnableRedisHttpSession
public class RedisConfig {

}
