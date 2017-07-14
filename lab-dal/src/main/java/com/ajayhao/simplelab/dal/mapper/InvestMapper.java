package com.ajayhao.simplelab.dal.mapper;

import com.ajayhao.simplelab.dal.entity.InvestInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by AjayHao on 2017/7/13.
 */
public interface InvestMapper {

    List<InvestInfo> queryInvestInfoList();

    int insertInvestInfo(InvestInfo investInfo);

    InvestInfo queryInvestInfo(@Param("id") String id);
}
