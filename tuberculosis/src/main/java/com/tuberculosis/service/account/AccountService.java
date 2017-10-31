package com.tuberculosis.service.account;

import com.tuberculosis.constant.CommonContants;
import com.tuberculosis.entity.Role;
import com.tuberculosis.entity.User;
import com.tuberculosis.repository.RoleDao;
import com.tuberculosis.repository.UserDao;
import com.tuberculosis.service.ServiceException;
import com.tuberculosis.service.account.ShiroDbRealm.ShiroUser;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springside.modules.persistence.DynamicSpecifications;
import org.springside.modules.persistence.SearchFilter;
import org.springside.modules.security.utils.Digests;
import org.springside.modules.utils.Encodes;

import java.util.List;
import java.util.Map;

/**
 * 用户管理类.
 * 
 * @author Lishaoqing
 */
// Spring Service Bean的标识.
@Component
@Transactional(readOnly = true)
public class AccountService {

    private static Logger logger = LoggerFactory.getLogger(AccountService.class);

    private UserDao userDao;

    private RoleDao roleDao;

    /**
     * 在保存用户时,发送用户修改通知消息, 由消息接收者异步进行较为耗时的通知邮件发送.
     *
     * 如果企图修改超级用户,取出当前操作员用户,打印其信息然后抛出异常.
     *
     */
    @Transactional(readOnly = false)
    public void saveUser(User user) {

//        if (isSupervisor(user)) {
//            logger.warn("操作员{}尝试修改超级管理员用户", getCurrentUserName());
//            throw new ServiceException("不能修改超级管理员用户");
//        }

        // 设定安全的密码，生成随机的salt并经过1024次 sha-1 hash
        if (StringUtils.isNotBlank(user.getPlainPassword())) {
            entryptPassword(user);
        }

        userDao.save(user);
    }

    @Transactional(readOnly = false)
    public void updateUser(User user) {
        if (StringUtils.isNotBlank(user.getPlainPassword())) {
            entryptPassword(user);
        }
        userDao.save(user);
    }

    @Transactional(readOnly = false)
    public void deleteUser(Long id) {
        userDao.delete(id);
    }
    /**
     * 设定安全的密码，生成随机的salt并经过1024次 sha-1 hash
     */
    public void entryptPassword(User user) {
        byte[] salt = Digests.generateSalt(CommonContants.SALT_SIZE);
        user.setSalt(Encodes.encodeHex(salt));

        byte[] hashPassword = Digests.sha1(user.getPlainPassword().getBytes(), salt, CommonContants.HASH_INTERATIONS);
        user.setPassword(Encodes.encodeHex(hashPassword));
    }

    public List<User> searchUser(Map<String, Object> searchParams) {

        Map<String, SearchFilter> filters = SearchFilter.parse(searchParams);
        Specification<User> spec = DynamicSpecifications.bySearchFilter(filters.values(), User.class);
        Sort sort = new Sort(Sort.Direction.DESC, CommonContants.FIELD_ID);
        return userDao.findAll(spec, sort);
    }

    /**
     * 判断是否超级管理员.
     */
    private boolean isSupervisor(User user) {
        return (user.getId() != null && user.getId() == 1L);
    }

    public User getUser(Long id) {
        return userDao.findOne(id);
    }

    public User findUserByLoginName(String loginName) {
        return userDao.findUserByLoginName(loginName);
    }

    public User findUserByToken(String token) {
        return userDao.findUserByToken(token);
    }

    /**
     * 取出Shiro中的当前用户LoginName.
     */
    private String getCurrentUserName() {
        ShiroUser user = (ShiroUser) SecurityUtils.getSubject().getPrincipal();
        return user.loginName;
    }

    public List<Role> getAllRole() {
        return (List<Role>) roleDao.findAll();
    }

    @Autowired
    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    @Autowired
    public void setRoleDao(RoleDao roleDao) {
        this.roleDao = roleDao;
    }
}
