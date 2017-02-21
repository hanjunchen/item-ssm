package com.hsgene.model;

import org.springframework.data.annotation.Id;

import java.io.Serializable;

/**
 * Created by hjc on 2017/2/21.
 */

public class EmployeeModel implements Serializable {

    private static final long serialVersionUID = 8222469710111201442L;

    @Id
    private String id;
    private String job;
    private String createDate;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public EmployeeModel() {
    }

    public EmployeeModel(String id, String job, String createDate) {
        this.id = id;
        this.job = job;
        this.createDate = createDate;
    }
}
