package com.jiuwufen.sdk.model.order;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import java.util.List;

/**
 * GetOrderListResponse
 */
@Data
public class GetOrderListResponse {
    
    /**
     * 订单列表
     */
    @SerializedName("list")
    private List<OrderListItem> list;

    
    @Data
    public static class OrderListItem {
        
        /**
         * 订单号
         */
        @SerializedName("order_number")
        private String orderNumber;
    }
}
