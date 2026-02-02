package com.jiuwufen.sdk.model.inventory;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import java.util.List;

/**
 * InventoryListRequest
 */
@Data
public class InventoryListRequest {
    
    /**
     * 详情
     */
    @SerializedName("detail")
    private List<InventoryListReqItem> detail;

    
    @Data
    public static class InventoryListReqItem {
        
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
    }
}
