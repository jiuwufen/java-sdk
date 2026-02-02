package com.jiuwufen.sdk.model.order;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import java.util.List;

/**
 * ConsignOrderInfoRequest
 */
@Data
public class ConsignOrderInfoRequest {
    
    /**
     * 商品统一代码
     */
    @SerializedName("upc")
    private String upc;
    
    /**
     * 订单号
     */
    @SerializedName("order_number")
    private List<String> orderNumber;
    
    /**
     * 批次号
     */
    @SerializedName("batch_number")
    private String batchNumber;
    
    /**
     * 商品编号
     */
    @SerializedName("goods_sn")
    private String goodsSn;
    
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
}
