package com.ess.framework.boot.gloabl;

import com.ess.framework.api.response.ApiResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class DefaultIndexController extends  AbstractController{

    @Value("${spring.application.name:''}")
    private String applicationName;

    @RequestMapping("/")
    public ApiResponse index(){
        return ApiResponse.successResp(applicationName+" Server is Ok.");
    }

}
