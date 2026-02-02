package com.jiuwufen.sdk.model.goods;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import java.util.List;

/**
 * CancelOrderRequest
 */
@Data
public class CancelOrderRequest {
    
    /**
     * 商品编号
     */
    @SerializedName("goods_sn")
    private String goodsSn;
}
