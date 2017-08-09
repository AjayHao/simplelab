package com.ajayhao.simplelab.dal.mapper;

import com.ajayhao.simplelab.dal.entity.InvestInfoDO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by AjayHao on 2017/7/13.
 */
public interface InvestMapper {

    List<InvestInfoDO> queryInvestInfoList();

    int insertInvestInfo(InvestInfoDO investInfo);

    int deleteInvestInfo(InvestInfoDO investInfo);

    InvestInfoDO queryInvestInfo(@Param("id") String id);
}
