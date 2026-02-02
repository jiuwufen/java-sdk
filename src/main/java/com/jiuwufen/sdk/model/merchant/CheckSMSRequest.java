package com.jiuwufen.sdk.model.merchant;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

/**
 * 校验短信验证码请求
 */
@Data
public class CheckSMSRequest {
    @SerializedName("mobile")
    private String mobile;

    @SerializedName("captcha")
    private String captcha;
}
