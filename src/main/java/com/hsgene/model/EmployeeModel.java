package com.hsgene.model;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by hjc on 2017/2/21.
 */

public class EmployeeModel implements Serializable {

    private static final long serialVersionUID = 8222469710111201442L;
    private String id;
    private String job;
    private Date createDate;

    public String getId() {
        return id;
    }

    public String getJob() {
        return job;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    @Override
    public String toString() {
        return "EmployeeModel{" +
                "id='" + id + '\'' +
                ", job='" + job + '\'' +
                ", createDate=" + createDate +
                '}';
    }
}
