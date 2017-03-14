package org.jsche.service.impl;

import java.util.Date;

import org.jsche.common.util.AppUtil;
import org.jsche.entity.User;
import org.jsche.repo.UserRepository;
import org.jsche.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service("userService")
public class UserServiceImpl implements UserService{
    @Autowired
    private UserRepository up;
    
    @Override
    public void save(User user) {
        if(StringUtils.isEmpty(user.getAvatar())){
            user.setAvatar(AppUtil.generateAvatar(user.getEmail()));
        }
        up.save(user);
    }

    @Override
    public User getUserById(int id) {
        return up.findOne(id);
    }

    @Override
    public User getUserByEmail(String email) {
        return up.getUserByEmail(email);
    }

	@Override
	public void updateLastLogin(User user) {
		user.setLastLogin(new Date(System.currentTimeMillis()));
		up.save(user);
	}

    @Override
    public void updateUserAvatar(User user) {
        if(StringUtils.isEmpty(user.getAvatar())){
            user.setAvatar(AppUtil.generateAvatar(user.getEmail()));
        }else{
            //get from form data
            user.setAvatar("@@@DD");
        }
        up.save(user);
    }

}
