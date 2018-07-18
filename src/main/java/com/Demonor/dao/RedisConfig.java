package com.Demonor.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SetOperations;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
@EnableAutoConfiguration
public class RedisConfig {
	/**
	 * 注入RedisConnectionFactory
	 */
	@Autowired
	RedisConnectionFactory redisConnectionFactory;
	
	/**
	 * 实例化RedisTemplate 对象
	 * 
	 * @return
	 * */
	@Bean
	public RedisTemplate<String, Object> functionDomainRedisTemplate() {
		RedisTemplate<String, Object> redisTemplate= new RedisTemplate<>();
		initDomainRedisTemplate(redisTemplate, redisConnectionFactory);
		return redisTemplate;
		}
	
	/**
	 *  设置数据存入redis的序列化方式
	 * @paramredisTemplate
	 * @paramfactory
	 * 
	 * */
	private void initDomainRedisTemplate(RedisTemplate<String, Object> redisTemplate, RedisConnectionFactory factory) {
		redisTemplate.setKeySerializer(new StringRedisSerializer());
		redisTemplate.setHashKeySerializer(new StringRedisSerializer());
		redisTemplate.setHashValueSerializer(new JdkSerializationRedisSerializer());
		redisTemplate.setValueSerializer(new JdkSerializationRedisSerializer());
		redisTemplate.setConnectionFactory(factory);
		}
	
	
	/**
	 *  实例化HashOperations 对象,可以使用Hash 类型操作
	 *   @paramredisTemplate
	 *   @return
	 *   */
	@Bean 
	public HashOperations<String, String, Object> hashOperations(RedisTemplate<String, Object> redisTemplate) {
		return redisTemplate.opsForHash();
		}
	
	/**
	 * 实例化ValueOperations 对象,可以使用String 操作
	 *  @paramredisTemplate
	 *  @return
	 *  */
	@Bean
	public ValueOperations<String, Object> valueOperations(RedisTemplate<String, Object> redisTemplate) {
		return redisTemplate.opsForValue();
		}
	
	/**
	 * 实例化ListOperations 对象,可以使用List 操作
	 * @paramredisTemplate
	 * @return
	 * */
	@Bean
	public ListOperations<String, Object> listOperations(RedisTemplate<String, Object> redisTemplate) {
		return redisTemplate.opsForList();
		}
	
	/**
	 * 实例化SetOperations 对象,可以使用Set 操作
	 * @paramredisTemplate
	 * @return
	 * */
	@Bean
	public SetOperations<String, Object> setOperations(RedisTemplate<String, Object> redisTemplate) {
		return redisTemplate.opsForSet();
		}
	
	/**
	 * 实例化ZSetOperations 对象,可以使用ZSet 操作
	 * @paramredisTemplate
	 * @return
	 * */
	@Bean
	public ZSetOperations<String, Object> zSetOperations(RedisTemplate<String, Object> redisTemplate) {
		return redisTemplate.opsForZSet();
		}

}