package com.ess.example.goods.web;

import com.ess.framework.api.response.ApiResponse;
import com.ess.framework.boot.gloabl.AbstractController;
import com.ess.framework.commons.jwt.AuthUser;
import com.ess.framework.commons.jwt.JWTUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 *
 */
@RestController
public class AuthController extends AbstractController {

    /**
     * 获取token
     * @return
     */
    @GetMapping("/auth")
    public ApiResponse<String> auth(){
//        AuthUser authUser = new AuthUser();
        Date expirationTime = DateUtils.addHours(new Date(),1);
        String token = JWTUtils.getInstance().getToken(expirationTime);
        return ApiResponse.successResp(token,"登录成功.");
    }

}
