package cn.sh.yhk.dao.redis;

public interface RedisService {

	public void set(String key, Object value);

	public Object get(String key);

}