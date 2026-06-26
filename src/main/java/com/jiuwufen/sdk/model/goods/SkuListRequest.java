package com.jiuwufen.sdk.model.goods;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import java.util.List;

/**
 * SkuListRequest
 */
@Data
public class SkuListRequest {
    
    /**
     * 三级类目列表
     */
    @SerializedName("child_category_id_list")
    private List<Long> childCategoryIdList;
    
    /**
     * 页码
     */
    @SerializedName("page")
    private Long page;
    
    /**
     * 每页数量
     */
    @SerializedName("page_size")
    private Long pageSize;
    
    /**
     * 品牌名称
     */
    @SerializedName("brand_name")
    private String brandName;
    
    /**
     * 品牌 ID
     */
    @SerializedName("brand_id")
    private Long brandId;
    
    /**
     * SKU 名称
     */
    @SerializedName("title")
    private String title;
    
    /**
     * 货号
     */
    @SerializedName("code")
    private String code;
    
    /**
     * SPU ID
     */
    @SerializedName("spu_id")
    private Long spuId;
    
    /**
     * 最后一条记录 ID (分页游标)
     */
    @SerializedName("last_id")
    private Long lastId;
    
    /**
     * SKU ID 列表
     */
    @SerializedName("ids")
    private List<Long> ids;
    
    /**
     * 启用状态
     */
    @SerializedName("must_status")
    private Long mustStatus;
}
