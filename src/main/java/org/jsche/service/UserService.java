package org.jsche.service;

import org.jsche.entity.User;

public interface UserService {
    void save(User user);
    
    User getUserById(int id);
    
    User getUserByEmail(String email);
    
    void updateLastLogin(User user);
    
    void updateUserAvatar(User user);
}
