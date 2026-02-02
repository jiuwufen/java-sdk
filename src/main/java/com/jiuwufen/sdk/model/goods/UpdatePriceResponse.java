package com.jiuwufen.sdk.model.goods;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import java.util.List;

/**
 * UpdatePriceResponse
 */
@Data
public class UpdatePriceResponse {
    
    /**
     * 请求ID
     */
    @SerializedName("req_id")
    private String reqId;
}
