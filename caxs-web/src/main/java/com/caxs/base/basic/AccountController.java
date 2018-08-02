package com.caxs.base.basic;

import com.caxs.base.domain.HmUser;
import com.caxs.base.domain.JsonResponse;
import com.caxs.base.domain.TokenUserAccount;
import com.caxs.base.service.IHmUserService;
import com.caxs.base.token.TokenGenerator;
import com.caxs.exception.BusinessException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping(value = "/account")
public class AccountController {

    @Autowired
    private TokenGenerator tokenGenerator;
    @Autowired
    private IHmUserService hmUserService;

    @RequestMapping(value = "/login")
    public JsonResponse login(HttpServletRequest request){
        Map<String, Object> result = new HashMap<>(2);
        TokenUserAccount tokenUserAccount;
        try{
            tokenUserAccount = tokenGenerator.getTokenUserAccount(request);
            if(null != tokenUserAccount.getLoginId()){
                result.put("token",tokenUserAccount.getToken());
                HmUser hmUser = hmUserService.selectByLoginId(tokenUserAccount.getLoginId());
                result.put("userData",hmUser);
            }
            return JsonResponse.success("登录成功",result);
        }catch (BusinessException e){
            return JsonResponse.fail("-1", e.getMessage());
        }
    }
}
