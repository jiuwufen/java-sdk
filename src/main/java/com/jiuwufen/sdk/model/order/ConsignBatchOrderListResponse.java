package com.jiuwufen.sdk.model.order;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import java.util.List;

/**
 * ConsignBatchOrderListResponse
 */
@Data
public class ConsignBatchOrderListResponse {
    
    /**
     * 订单列表
     */
    @SerializedName("list")
    private List<BatchOrderItem> list;

    
    @Data
    public static class BatchOrderItem {
        
        /**
         * 订单号
         */
        @SerializedName("order_number")
        private String orderNumber;
    }
}
