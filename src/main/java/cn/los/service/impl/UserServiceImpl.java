package cn.los.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.los.base.BaseDao;
import cn.los.entity.UserEntity;
import cn.los.repository.UserRepository;
import cn.los.service.UserService;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Override
    public List<UserEntity> finAll() {
        return userRepository.findAll();
    }

    @Override
    public BaseDao<UserEntity, String> getRepository() {
        return userRepository;
    }

}
