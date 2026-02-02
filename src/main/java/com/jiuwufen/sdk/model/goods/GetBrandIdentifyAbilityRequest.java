package com.jiuwufen.sdk.model.goods;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import java.util.List;

/**
 * GetBrandIdentifyAbilityRequest
 */
@Data
public class GetBrandIdentifyAbilityRequest {
    
    /**
     * 一级类目ID
     */
    @SerializedName("l1_category_id")
    private Long l1CategoryId;
    
    /**
     * 品牌名称
     */
    @SerializedName("brand_name")
    private String brandName;
}
