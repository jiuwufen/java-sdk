package com.jiuwufen.sdk.model.merchant;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

/**
 * 校验短信验证码响应
 */
@Data
public class CheckSMSResponse {
    @SerializedName("status")
    private Integer status;

    @SerializedName("req_id")
    private String reqId;

    @SerializedName("data")
    private CheckSMSData data;

    @Data
    public static class CheckSMSData {
        @SerializedName("hearder_name")
        private String headerName;

        @SerializedName("secret_key")
        private String secretKey;
    }
}
