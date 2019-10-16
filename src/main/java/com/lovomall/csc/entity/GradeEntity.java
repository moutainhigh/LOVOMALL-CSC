package com.lovomall.csc.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * 供应商等级实体类
 */
@Entity
@Table(name = "sys_gradeInfo")
public class GradeEntity {
    //供应商等级ID
    @Id
    @GenericGenerator(name = "uuuuid",strategy = "uuid")
    @GeneratedValue(generator = "uuuuid")
    @Column(length = 32,name = "g_id")
    private String id;
    //等级名称
    @Column(length = 48)
    private String grade;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }
}
