package com.ajayhao.simplelab.dal.mapper;

import com.ajayhao.simplelab.dal.entity.CommonParamDO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by AjayHao on 2017/7/26.
 */
public interface CommonParamMapper {

    /**
     * 查询配置参数列表.
     *
     * @param paramGroup 参数组名
     * @param paramName  参数名
     * @return 参数列表
     */
    List<CommonParamDO> queryParamList(@Param("paramGroup") String paramGroup,
                                     @Param("paramName") String paramName);
}
