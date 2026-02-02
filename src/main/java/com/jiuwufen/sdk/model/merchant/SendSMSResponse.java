package com.jiuwufen.sdk.model.merchant;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

/**
 * 发送短信验证码响应
 */
@Data
public class SendSMSResponse {
    @SerializedName("status")
    private Integer status;

    @SerializedName("req_id")
    private String reqId;
}
