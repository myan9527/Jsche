package org.jsche.service.impl;

import org.jsche.common.ErrorMessage;
import org.jsche.common.exception.ServiceException;
import org.jsche.common.util.AppUtil;
import org.jsche.dao.UserDao;
import org.jsche.entity.User;
import org.jsche.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Date;

@Service("userService")
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public void save(User user) throws ServiceException {
        if (StringUtils.isEmpty(user.getAvatar())) {
            user.setAvatar(AppUtil.generateAvatar(user.getEmail()));
        }
        if (userDao.getUserById(user.getId()) != null) {
            throw new ServiceException(ErrorMessage.INVALID_OPERATION);
        }
        userDao.save(user);
    }

    @Override
    public User getUserById(int id) {
        return userDao.getUserById(id);
    }

    @Override
    public User getUserByEmail(String email) {
        return userDao.getUserByEmail(email);
    }

    @Override
    public void updateLastLogin(User user) throws ServiceException {
        user.setLastLogin(new Date(System.currentTimeMillis()));
        if (userDao.getUserById(user.getId()) == null) {
            throw new ServiceException(ErrorMessage.INVALID_OPERATION);
        }
        userDao.updateLastLogin(user);
    }

    @Override
    public void updateUserAvatar(User user) {
        if (StringUtils.isEmpty(user.getAvatar())) {
            user.setAvatar(AppUtil.generateAvatar(user.getEmail()));
        } else {
            //get file path
            if (user.isCustomizedAvatar()) {
                user.setAvatar(user.getAvatar());
            }
        }
        userDao.updateUserAvatar(user);
    }

}
