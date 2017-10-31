package cn.sh.yhk.p2p.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import cn.sh.yhk.p2p.model.User;

//CrudRepository save方法是相当于merge+save  它会先判断记录是否存在，如果存在则更新，不存在则插入记录。
public interface UserDao extends PagingAndSortingRepository<User, Long>, JpaRepository<User, Long> {
	/**
	 * Find by name.
	 * 
	 * @param name
	 * @return
	 */
	User findByUserName(String userName);
}
