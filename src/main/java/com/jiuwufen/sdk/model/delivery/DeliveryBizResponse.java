package com.jiuwufen.sdk.model.delivery;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import java.util.List;

/**
 * DeliveryBizResponse
 */
@Data
public class DeliveryBizResponse {
    
    /**
     * 发货信息列表
     */
    @SerializedName("list")
    private List<DeliveryItem> list;

    
    @Data
    public static class DeliveryItem {
        
        /**
         * 快递单号
         */
        @SerializedName("express_number")
        private String expressNumber;
    }
}
