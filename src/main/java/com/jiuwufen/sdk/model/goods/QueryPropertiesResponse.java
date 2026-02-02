package com.jiuwufen.sdk.model.goods;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import java.util.List;

/**
 * QueryPropertiesResponse
 */
@Data
public class QueryPropertiesResponse {
    
    /**
     * 属性列表
     */
    @SerializedName("list")
    private List<PropertyItem> list;

    
    @Data
    public static class PropertyItem {
        
        /**
         * 属性ID
         */
        @SerializedName("id")
        private Long id;
        
        /**
         * 属性名
         */
        @SerializedName("name")
        private String name;
        
        /**
         * 属性类型
         */
        @SerializedName("property_type")
        private Integer propertyType;
        
        /**
         * 是否必填
         */
        @SerializedName("is_required")
        private Integer isRequired;
    }
}
