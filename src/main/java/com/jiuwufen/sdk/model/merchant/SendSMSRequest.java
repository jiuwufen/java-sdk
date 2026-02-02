package com.jiuwufen.sdk.model.merchant;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

/**
 * 发送短信验证码请求
 * 
 * @author 95分开放平台团队
 * @version 1.0.0
 */
@Data
public class SendSMSRequest {
    
    /**
     * 商家95分账号对应手机号
     */
    @SerializedName("mobile")
    private String mobile;
}
