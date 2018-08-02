package com.caxs.base.daos;

import com.caxs.base.domain.HmUser;

public interface HmUserMapper {
    int deleteByPrimaryKey(String id);

    int insert(HmUser record);

    int insertSelective(HmUser record);

    HmUser selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(HmUser record);

    int updateByPrimaryKey(HmUser record);

    HmUser slectByLoginId(String loginid);
}