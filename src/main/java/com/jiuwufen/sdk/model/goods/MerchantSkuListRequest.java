package com.jiuwufen.sdk.model.goods;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import java.util.List;

/**
 * MerchantSkuListRequest
 */
@Data
public class MerchantSkuListRequest {
    
    /**
     * 商家商品编码数组
     */
    @SerializedName("merchant_sku_code")
    private List<String> merchantSkuCode;
    
    /**
     * 查询绑定开始时间
     */
    @SerializedName("start_binding_time")
    private String startBindingTime;
    
    /**
     * 查询绑定结束时间
     */
    @SerializedName("end_binding_time")
    private String endBindingTime;
    
    /**
     * 页码
     */
    @SerializedName("page")
    private Integer page;
    
    /**
     * 每页数量
     */
    @SerializedName("page_size")
    private Integer pageSize;
}
