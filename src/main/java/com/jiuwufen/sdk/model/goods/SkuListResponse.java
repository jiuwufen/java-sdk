package com.jiuwufen.sdk.model.goods;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import java.util.List;

/**
 * SkuListResponse
 */
@Data
public class SkuListResponse {
    
    /**
     * 总数
     */
    @SerializedName("total")
    private Long total;
    
    /**
     * 商品列表
     */
    @SerializedName("list")
    private List<SkuListItem> list;

    
    @Data
    public static class SkuListItem {
        
        /**
         * SKU ID
         */
        @SerializedName("sku_id")
        private Long skuId;
        
        /**
         * 商品标题
         */
        @SerializedName("title")
        private String title;
        
        /**
         * 销售属性字符串
         */
        @SerializedName("property_value")
        private String propertyValue;
        
        /**
         * 销售属性详情
         */
        @SerializedName("sku_properties")
        private List<PropertyItem> skuProperties;
        
        /**
         * 商品货号
         */
        @SerializedName("code")
        private String code;
        
        /**
         * 一级类目 ID
         */
        @SerializedName("root_category_id")
        private Long rootCategoryId;
        
        /**
         * 一级类目名称
         */
        @SerializedName("root_category_name")
        private String rootCategoryName;
        
        /**
         * 二级类目 ID
         */
        @SerializedName("middle_category_id")
        private Long middleCategoryId;
        
        /**
         * 二级类目名称
         */
        @SerializedName("middle_category_name")
        private String middleCategoryName;
        
        /**
         * 三级类目 ID
         */
        @SerializedName("child_category_id")
        private Long childCategoryId;
        
        /**
         * 三级类目名称
         */
        @SerializedName("child_category_name")
        private String childCategoryName;
        
        /**
         * 品牌 ID
         */
        @SerializedName("brand_id")
        private Long brandId;
        
        /**
         * 品牌名称
         */
        @SerializedName("brand_name")
        private String brandName;
        
        /**
         * SPU ID
         */
        @SerializedName("spu_id")
        private Long spuId;
        
        /**
         * SPU 来源
         */
        @SerializedName("spu_source")
        private String spuSource;
        
        /**
         * SPU 原始来源
         */
        @SerializedName("spu_src_orig")
        private String spuSrcOrig;
    }

    
    @Data
    public static class PropertyItem {
        
        /**
         * 属性名
         */
        @SerializedName("property_name")
        private String propertyName;
        
        /**
         * 属性值
         */
        @SerializedName("property_value")
        private String propertyValue;
    }
}
