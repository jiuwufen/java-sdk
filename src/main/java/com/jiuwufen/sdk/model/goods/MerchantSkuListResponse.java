package com.jiuwufen.sdk.model.goods;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import java.util.List;

/**
 * MerchantSkuListResponse
 */
@Data
public class MerchantSkuListResponse {
    
    /**
     * 总记录数
     */
    @SerializedName("total")
    private Long total;
    
    /**
     * SKU列表
     */
    @SerializedName("list")
    private List<SkuItem> list;

    
    @Data
    public static class SkuItem {
        
        /**
         * SKU ID
         */
        @SerializedName("sku_id")
        private Long skuId;
        
        /**
         * 标题
         */
        @SerializedName("title")
        private String title;
        
        /**
         * 货号
         */
        @SerializedName("code")
        private String code;
    }
}
