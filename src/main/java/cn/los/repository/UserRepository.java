package cn.los.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import cn.los.entity.UserEntity;

/*User数据持久层操作接口*/
public interface UserRepository extends JpaRepository<UserEntity, Long> {

}
