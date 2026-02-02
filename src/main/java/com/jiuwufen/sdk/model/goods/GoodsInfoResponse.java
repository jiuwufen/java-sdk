package com.jiuwufen.sdk.model.goods;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import java.util.List;

/**
 * GoodsInfoResponse
 */
@Data
public class GoodsInfoResponse {
    
    /**
     * 状态
     */
    @SerializedName("status")
    private Integer status;
    
    /**
     * 商品详情链接
     */
    @SerializedName("channel_sku_url")
    private String channelSkuUrl;
}
