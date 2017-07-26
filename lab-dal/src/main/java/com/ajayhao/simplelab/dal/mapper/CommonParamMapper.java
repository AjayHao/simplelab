package com.ajayhao.simplelab.dal.mapper;

import com.ajayhao.simplelab.dal.entity.CommonParamDO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by AjayHao on 2017/7/26.
 */
public interface CommonParamMapper {

    List<CommonParamDO> queryCommonParamList();

    List<CommonParamDO> queryCommonParamList(@Param("paramGroup") String paramGroup);

    CommonParamDO queryCommonParamDO(@Param("paramGroup") String paramGroup,
                                     @Param("paramName") String paramName);
}
