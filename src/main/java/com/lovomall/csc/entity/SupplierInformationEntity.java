package com.lovomall.csc.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Entity;
import javax.persistence.*;
import java.sql.Date;
import java.text.SimpleDateFormat;

/**
 * 供应商信息反馈实体类
 */
@Entity
@Table(name = "s_informatiom")
public class SupplierInformationEntity {

    @Id
    @JsonIgnore
    @Column(length = 32)
    @GenericGenerator(strategy = "uuid",name = "uuid")
    @GeneratedValue(generator = "uuid")
    /**消息id*/
    private String id;
    @Column(length = 50)
    /**消息内容*/
    private  String messageInfo;
    @Column(length = 50)
    /**发送方*/
    private  String messageSender="0";
    @Column(length = 50)
    /**消息生成时间*/
    private String messageDate =
            new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").
                    format(new Date(System.currentTimeMillis()));;
    /**外键供应商退货单id*/
    @Column(length = 32)
    private String returnOrderCode;
    @Column(length = 32)
    @JsonIgnore
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private  Date CollectionTime;

    public Date getCollectionTime() {
        return CollectionTime;
    }

    public void setCollectionTime(Date collectionTime) {
        CollectionTime = collectionTime;
    }


    public String getReturnOrderCode() {
        return returnOrderCode;
    }

    public void setReturnOrderCode(String returnOrderCode) {
        this.returnOrderCode = returnOrderCode;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMessageInfo() {
        return messageInfo;
    }

    public void setMessageInfo(String messageInfo) {
        this.messageInfo = messageInfo;
    }

    public String getMessageSender() {
        return messageSender;
    }

    public void setMessageSender(String messageSender) {
        this.messageSender = messageSender;
    }

    public String getMessageDate() {
        return messageDate;
    }

    public void setMessageDate(String messageDate) {
        this.messageDate = messageDate;
    }
}
