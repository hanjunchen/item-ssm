/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.hsgene.model;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Setter
@Getter
public class DcwEvaluationDetail implements Serializable{

	private static final long serialVersionUID = 7347950176130200725L;
	private String id;
	private String fieldKey;
	private String fieldText;
	private BigDecimal fieldPoints;
	private String dcwId;
	private BigDecimal agio;
	private String ruleId;
	private String module;
	private Integer dcwType;
	private Integer orders;
	private Double coefficient;
	private String createBy;
	private Date createDate;
	private String updateBy;
	private Date updateDate;
	private String delFlag;
	private String remarks;
	private String content;

}