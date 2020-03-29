package com.ess.framework.commons.jwt;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class AuthUser {

    /**
     * 用户ID
     */
    private String userId;

    /**
     * userName
     */
    private String userName;

    /**
     * 用户类型 1 = 管理员，2= 233用户
     */
    private String type;

    /**
     * 系统来源:WX，APP,H5,
     */
    private String system;
}
