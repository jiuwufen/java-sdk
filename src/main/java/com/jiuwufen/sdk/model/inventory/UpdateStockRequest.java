package com.jiuwufen.sdk.model.inventory;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import java.util.List;

/**
 * UpdateStockRequest
 */
@Data
public class UpdateStockRequest {
    
    /**
     * 商品编号
     */
    @SerializedName("goods_sn")
    private String goodsSn;
    
    /**
     * 库存状态
     */
    @SerializedName("stock")
    private Integer stock;
}
