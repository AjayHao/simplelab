package com.ajayhao.simplelab.dal.entity;

import com.ajayhao.simplelab.util.ObjectUtils;

import java.io.Serializable;
import java.util.Date;


/**
 * 基类DO
 * <p>
 * Created by haozhenjie on 2017/7/26.
 */
public class BaseDO implements Serializable{


    private static final long serialVersionUID = 3263819140404736307L;

    /**
     *  表物理主键
     */
    private Long id;

    /**
     *  创建人id
     */
    private String creatorId = "sys";

    /**
     *  创建时间
     */
    private Date createTm;


    /**
     * 修改人id
     */
    private String modifierId = "sys";


    /**
     * 修改时间
     */
    private Date modifyTm;

    /**
     * Gets id.
     *
     * @return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * Sets id.
     *
     * @param id the id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Gets creator id.
     *
     * @return the creator id
     */
    public String getCreatorId() {
        return creatorId;
    }

    /**
     * Sets creator id.
     *
     * @param creatorId the creator id
     */
    public void setCreatorId(String creatorId) {
        this.creatorId = creatorId;
    }

    /**
     * Gets create tm.
     *
     * @return the create tm
     */
    public Date getCreateTm() {
        return createTm;
    }

    /**
     * Sets create tm.
     *
     * @param createTm the create tm
     */
    public void setCreateTm(Date createTm) {
        this.createTm = createTm;
    }

    /**
     * Gets modifier id.
     *
     * @return the modifier id
     */
    public String getModifierId() {
        return modifierId;
    }

    /**
     * Sets modifier id.
     *
     * @param modifierId the modifier id
     */
    public void setModifierId(String modifierId) {
        this.modifierId = modifierId;
    }

    /**
     * Gets modify tm.
     *
     * @return the modify tm
     */
    public Date getModifyTm() {
        return modifyTm;
    }

    /**
     * Sets modify tm.
     *
     * @param modifyTm the modify tm
     */
    public void setModifyTm(Date modifyTm) {
        this.modifyTm = modifyTm;
    }

    /**
     * Instantiates a new BaseDo.
     */
    public BaseDO() {
    }

    @Override
    public String toString() {
        return ObjectUtils.toJson(this);
    }

}
