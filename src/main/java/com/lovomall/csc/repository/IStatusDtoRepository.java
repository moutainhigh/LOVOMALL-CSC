package com.lovomall.csc.repository;

import com.lovomall.csc.dto.StatusDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface IStatusDtoRepository extends CrudRepository<StatusDto,String> {
    /**
     * 分页显示用户状态修改请求
     * @param pageable 分页对象
     * @return 用户状态修改对象分页集合
     */
    @Query("select s from StatusDto s ")
    public Page<StatusDto> findStatusDtoPage(Pageable pageable);

    /**
     * 根据用户id删除请求
     * @param userId 用户id
     */
    @Modifying
    @Query("delete from StatusDto  where userId=?1")
    public void deleteByUserId(String userId);

    @Query("from StatusDto where userId=?1 ")
    public StatusDto findByUserId(String userId);
}
