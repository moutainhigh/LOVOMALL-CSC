package com.lovomall.csc.dao;

import com.lovomall.csc.entity.BiddingInfoEntity;
import com.lovomall.csc.entity.ExamineInfoEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * 招标信息持久接口nfo
 */
@Repository(value = "biddingInfoDao")
public interface IBiddingInfoDao extends CrudRepository<BiddingInfoEntity,String> {

}
