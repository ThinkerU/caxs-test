package com.caxs.base.service;

import com.caxs.base.domain.HmUser;

public interface IHmUserService {
    HmUser selectByLoginId(String loginId);

    int insertHmUser(HmUser hmUser);

    int updateById(HmUser hmUser);

    HmUser selectById(String id);
}
