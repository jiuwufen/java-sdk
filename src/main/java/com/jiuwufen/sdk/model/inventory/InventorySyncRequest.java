package com.jiuwufen.sdk.model.inventory;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import java.util.List;

/**
 * InventorySyncRequest
 */
@Data
public class InventorySyncRequest {
    
    /**
     * 详情
     */
    @SerializedName("detail")
    private List<InventorySyncItem> detail;

    
    @Data
    public static class InventorySyncItem {
        
        /**
         * 商家商品编码
         */
        @SerializedName("merchant_sku_code")
        private String merchantSkuCode;
        
        /**
         * SKU ID
         */
        @SerializedName("sku_id")
        private Long skuId;
        
        /**
         * 实际库存
         */
        @SerializedName("qty")
        private Long qty;
        
        /**
         * 可售库存
         */
        @SerializedName("salable_qty")
        private Long salableQty;
    }
}
