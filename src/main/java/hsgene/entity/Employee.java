package hsgene.entity;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by hjc on 2016/10/15.
 */
@Getter
@Setter
public class Employee implements Serializable{

    private static final long serialVersionUID = 5330869888126501633L;
    private Integer id;
    private String name;
    private Integer sex;    //  性别字典在页面上转换显示
    private String job;
    private Integer accessLevel;
    private String identityCard;
    private String deptNo;
    //  属性名和数据库字段名相同，xml映射文件中就不需要其别名或者使用resultMap进行映射
    //  只要属性名和字段名相同，就算类型不一样，mybatis也会自动类型转换
    //  实体类属性类型与数据库字段类型不一致，就不能插入和更新操作，只能查询（会自动转换类型）
    private Date createDate;
}
