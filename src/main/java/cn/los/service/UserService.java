package cn.los.service;

import java.util.List;

import cn.los.base.BaseService;
import cn.los.entity.UserEntity;

public interface UserService extends BaseService<UserEntity, String> {

    List<UserEntity> finAll();

}
