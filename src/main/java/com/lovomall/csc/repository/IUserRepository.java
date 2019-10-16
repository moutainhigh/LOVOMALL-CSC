package com.lovomall.csc.repository;

import com.lovomall.csc.entity.ConsumeRecord;
import com.lovomall.csc.entity.UserEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;


/**
 * 用户持久接口
 */
public interface IUserRepository extends CrudRepository<UserEntity,String> {
    /**
     * 根据用户id查询用户信息
     * @param userId 用户id
     * @return 用户对象
     */
    @Query("from UserEntity where userId=?1")
    public UserEntity findUserById(String userId);

}
