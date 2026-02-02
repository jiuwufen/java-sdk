package com.jiuwufen.sdk.model.goods;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import java.util.List;

/**
 * UpdateSellerBargainRequest
 */
@Data
public class UpdateSellerBargainRequest {
    
    /**
     * 商品编号
     */
    @SerializedName("goods_sn")
    private String goodsSn;
    
    /**
     * 议价价格
     */
    @SerializedName("price")
    private Long price;
}
