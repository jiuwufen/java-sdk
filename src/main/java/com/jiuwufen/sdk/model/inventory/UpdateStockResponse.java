package com.jiuwufen.sdk.model.inventory;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import java.util.List;

/**
 * UpdateStockResponse
 */
@Data
public class UpdateStockResponse {
    
    /**
     * 消息
     */
    @SerializedName("msg")
    private String msg;
}
