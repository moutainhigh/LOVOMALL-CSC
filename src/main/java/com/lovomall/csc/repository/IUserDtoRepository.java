package com.lovomall.csc.repository;

import com.lovomall.csc.dto.UserDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

/**
 * 用户注册请求对象持久接口
 */
public interface IUserDtoRepository extends CrudRepository<UserDto,String> {
    /**
     * 分页显示用户注册请求
     * @param pageable 分页对象
     * @return 用户注册对象分页集合
     */
    @Query("select u from UserDto u")
    public Page<UserDto> findUserDtoPage(Pageable pageable);

    /**
     * 根据用户对象id查询注册信息
     * @param userId 对象id
     * @return 对象注册信息
     */
    @Query("from UserDto where userId=?1")
    public UserDto findUserDtoById(String userId);

    /**
     * 根据用户id删除对象
     * @param userId 用户id
     */
    @Modifying
    @Query("delete from UserDto where userId=?1")
    public void deleteByUserId(String userId);
}
