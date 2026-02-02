package com.jiuwufen.sdk.model.goods;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import java.util.List;

/**
 * AddOrderGoodsResponse
 */
@Data
public class AddOrderGoodsResponse {
    
    /**
     * 状态码
     */
    @SerializedName("status")
    private Integer status;
    
    /**
     * 消息
     */
    @SerializedName("msg")
    private String msg;
}
