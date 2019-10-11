package com.lovomall.csc.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.sql.Date;
import java.time.LocalDate;

/**
 * Author:     cafebabe
 * Date:       2019/10/10 
 * Time:       下午8:29
 * Description: 消费审核记录实体
 * Version: 1.0
 */
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table (name = "t_con_review")
public class ConsumeReviewRecord {

    /** 消费审核记录id*/
    @Id
    @GenericGenerator (name = "conReviewUUID", strategy = "uuid")
    @GeneratedValue (generator = "conReviewUUID")
    @Column (name = "review_id", length = 32)
    private String reviewId;

    /** 消费记录对象*/
    @ManyToOne
    @JoinColumn (name = "consuId", nullable = false)
    private ConsumeRecord consumeRecord;

    /** 消费审核结果*/
    @Column (name = "review_result", length = 10, nullable = false)
    private String reviewResult = "未通过";

    /** 消费审核意见*/
    @Column (name = "review_advice", length = 50)
    private String reviewAdvice;

    /** 审核人姓名*/
    @Column (name = "review_name", length = 10, nullable = false)
    private String name;

    /** 消费审核日期*/
    @Column (name = "review_date", length = 10, nullable = false)
    private Date reviewDate = Date.valueOf(LocalDate.now());
}
