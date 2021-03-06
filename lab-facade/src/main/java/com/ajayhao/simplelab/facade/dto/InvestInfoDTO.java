package com.ajayhao.simplelab.facade.dto;

import com.ajayhao.simplelab.facade.dto.common.BaseDTO;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Created by AjayHao on 2017/7/13.
 */
public class InvestInfoDTO extends BaseDTO implements Serializable{

    private static final long serialVersionUID = 3095414893084475849L;

    private String id;
    private String projectName;
    private String projectType;
    private String projectTypeDe;
    private String mainChannel;
    private String mainChannelDe;
    private String subChannel;
    private String subChannelDe;
    private BigDecimal cost;
    private BigDecimal income;
    private String beginDate;
    private String endDate;
    private BigDecimal annualYield;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getProjectType() {
        return projectType;
    }

    public void setProjectType(String projectType) {
        this.projectType = projectType;
    }

    public String getMainChannel() {
        return mainChannel;
    }

    public void setMainChannel(String mainChannel) {
        this.mainChannel = mainChannel;
    }

    public String getSubChannel() {
        return subChannel;
    }

    public void setSubChannel(String subChannel) {
        this.subChannel = subChannel;
    }

    public String getMainChannelDe() {
        return mainChannelDe;
    }

    public void setMainChannelDe(String mainChannelDe) {
        this.mainChannelDe = mainChannelDe;
    }

    public String getSubChannelDe() {
        return subChannelDe;
    }

    public void setSubChannelDe(String subChannelDe) {
        this.subChannelDe = subChannelDe;
    }

    public BigDecimal getCost() {
        return cost;
    }

    public void setCost(BigDecimal cost) {
        this.cost = cost;
    }

    public BigDecimal getIncome() {
        return income;
    }

    public void setIncome(BigDecimal income) {
        this.income = income;
    }

    public String getBeginDate() {
        return beginDate;
    }

    public void setBeginDate(String beginDate) {
        this.beginDate = beginDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public BigDecimal getAnnualYield() {
        return annualYield;
    }

    public void setAnnualYield(BigDecimal annualYield) {
        this.annualYield = annualYield;
    }

    public String getProjectTypeDe() {
        return projectTypeDe;
    }

    public void setProjectTypeDe(String projectTypeDe) {
        this.projectTypeDe = projectTypeDe;
    }
}
