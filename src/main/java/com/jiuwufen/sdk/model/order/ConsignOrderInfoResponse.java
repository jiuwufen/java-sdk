package com.jiuwufen.sdk.model.order;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import java.util.List;

/**
 * ConsignOrderInfoResponse
 */
@Data
public class ConsignOrderInfoResponse {
    
    /**
     * 订单列表
     */
    @SerializedName("order_list")
    private List<OrderItem> orderList;

    
    @Data
    public static class OrderItem {
        
        /**
         * 卖家订单号
         */
        @SerializedName("sell_order_number")
        private String sellOrderNumber;
        
        /**
         * 状态描述
         */
        @SerializedName("status_desc")
        private String statusDesc;
    }
}
