package com.jiuwufen.sdk.model.goods;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import java.util.List;

/**
 * GetBrandIdentifyAbilityResponse
 */
@Data
public class GetBrandIdentifyAbilityResponse {
    
    /**
     * 品牌列表
     */
    @SerializedName("list")
    private List<BrandItem> list;

    
    @Data
    public static class BrandItem {
        
        /**
         * 品牌ID
         */
        @SerializedName("id")
        private Long id;
        
        /**
         * 品牌名称
         */
        @SerializedName("name")
        private String name;
    }
}
