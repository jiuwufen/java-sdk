package com.jiuwufen.sdk.model.digitalproduct;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

/**
 * IMEI 查询 (Imei) 请求
 */
@Data
public class ImeiQueryRequest {

    @SerializedName("imei")
    private String imei;
}
