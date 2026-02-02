package com.jiuwufen.sdk.model.goods;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import java.util.List;

/**
 * BargainSuccessResponse
 */
@Data
public class BargainSuccessResponse {
    
    /**
     * 消息
     */
    @SerializedName("msg")
    private String msg;
}
