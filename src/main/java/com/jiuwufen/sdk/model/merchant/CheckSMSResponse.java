package com.jiuwufen.sdk.model.merchant;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

/**
 * 校验短信验证码响应中 {@code data} 载荷（外层 status/msg/req_id 由 {@link com.jiuwufen.sdk.model.common.CommonResponse} 解析）。
 */
@Data
public class CheckSMSResponse {

    /** 文档字段名为 hearder_name */
    @SerializedName("hearder_name")
    private String hearderName;

    @SerializedName("secret_key")
    private String secretKey;
}
