package com.jiuwufen.sdk.model.order;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import java.util.List;

/**
 * BuyerAddressRequest
 */
@Data
public class BuyerAddressRequest {
    
    /**
     * 订单号
     */
    @SerializedName("order_number")
    private String orderNumber;
}
