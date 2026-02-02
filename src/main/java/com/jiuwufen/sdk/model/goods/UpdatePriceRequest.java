package com.jiuwufen.sdk.model.goods;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import java.util.List;

/**
 * UpdatePriceRequest
 */
@Data
public class UpdatePriceRequest {
    
    /**
     * 商品编号
     */
    @SerializedName("goods_sn")
    private String goodsSn;
    
    /**
     * 售价（元）
     */
    @SerializedName("price")
    private Long price;
}
