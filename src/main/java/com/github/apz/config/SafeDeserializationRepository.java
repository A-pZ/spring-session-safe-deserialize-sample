package com.github.apz.config;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.SerializationException;
import org.springframework.session.Session;
import org.springframework.session.SessionRepository;

import lombok.extern.log4j.Log4j2;

@Log4j2
public class SafeDeserializationRepository<S extends Session> implements SessionRepository<S> {

	private final SessionRepository<S> delegate;
	private final RedisTemplate<Object, Object> redisTemplate;

	private static final String BOUNDED_HASH_KEY_PREFIX = "spring:session:sessions:";

	public SafeDeserializationRepository(SessionRepository<S> delegate,
			RedisTemplate<Object, Object> redisTemplate) {
		this.delegate = delegate;
		this.redisTemplate = redisTemplate;
	}

	@Override
	public S createSession() {
		return delegate.createSession();
	}

	@Override
	public void save(S session) {
		delegate.save(session);
	}

	@Override
	public S findById(String id) {
		try {
			S s = delegate.findById(id);
			log.info("find session success. {} {}", id, s);
			return s;
		} catch (SerializationException e) {
			log.info("Deleting non-deserializable session with key {}", id);
			redisTemplate.delete(BOUNDED_HASH_KEY_PREFIX + id);
			redisTemplate.delete("spring:session:expirations:" + id);
			redisTemplate.delete("spring:session:sessions:expires:" + id);
			return null;
		}
	}

	@Override
	public void deleteById(String id) {
		delegate.deleteById(id);
		log.info("delete session success. {}", id);
	}

}
