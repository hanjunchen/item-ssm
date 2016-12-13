package com.hsgene.entity;

import lombok.*;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by hjc on 2016/10/15.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder    // 必须有一个全参的构造方法----而new Employee(){{}}语法不需要
public class Employee implements Serializable{

    private static final long serialVersionUID = 5330869888126501633L;
    private Integer id;
    private String name;
    private Integer sex;    //  性别字典在页面上转换显示
    private String job;
    private Integer accessLevel;
    private String identityCard;
    private Integer deptNo;
    //  属性名和数据库字段名相同，xml映射文件中就不需要其别名或者使用resultMap进行映射
    //  只要属性名和字段名相同，就算类型不一样，mybatis也会自动类型转换
    //  实体类属性类型与数据库字段类型不一致，就不能插入和更新操作，只能查询（会自动转换类型）
    private Date createDate;

    private UpToken upToken;    // 提供测试SystemMetaObject对象

    public Employee(String name,Integer sex,String job,Integer accessLevel,String identityCard,Integer deptNo,Date createDate){
        this.name = name;
        this.sex = sex;
        this.job = job;
        this.accessLevel = accessLevel;
        this.identityCard = identityCard;
        this.deptNo = deptNo;
        this.createDate = createDate;
    }

}
