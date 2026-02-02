package com.jiuwufen.sdk.model.goods;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import java.util.List;

/**
 * ReferencePriceResponse
 */
@Data
public class ReferencePriceResponse {
    
    /**
     * 平台最低价
     */
    @SerializedName("platform_min_price")
    private Integer platformMinPrice;
    
    /**
     * 寄售最低价
     */
    @SerializedName("consign_min_price")
    private Integer consignMinPrice;
    
    /**
     * 最近成交均价
     */
    @SerializedName("recent_trans_price")
    private Integer recentTransPrice;
    
    /**
     * 全新市场价
     */
    @SerializedName("new_market_price")
    private Integer newMarketPrice;
}
