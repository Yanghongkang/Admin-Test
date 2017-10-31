package cn.sh.yhk.sql;

import java.util.List;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 
 * @ClassName: SqlSessionDaoSupport
 * @Description: 注入sqlsessionclient
 * @author yanghongkang
 * @date 2015-11-21 下午4:34:06
 * 
 * @param <T>
 */
@Component("sqlmapSupport")
public class SqlMapSupport<T> extends SqlSessionDaoSupport {

	@Autowired
	public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
		super.setSqlSessionFactory(sqlSessionFactory);
	}

	public int insertObject(String map, Object obj) {
		return this.getSqlSession().insert(map, obj);
	}

	public Object selectToOne(String map, Object obj) {
		return this.getSqlSession().selectOne(map, obj);
	}

	public int updateObject(String map, Object obj) {
		return this.getSqlSession().update(map, obj);
	}

	public List<Object> selectToList(String map, Object obj) {
		return this.getSqlSession().selectList(map, obj);
	}
}
