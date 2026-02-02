package com.jiuwufen.sdk.model.delivery;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import java.util.List;

/**
 * DeliveryBizRequest
 */
@Data
public class DeliveryBizRequest {
    
    /**
     * 订单号
     */
    @SerializedName("order_number")
    private String orderNumber;
    
    /**
     * 发货地址
     */
    @SerializedName("send_address")
    private DeliveryAddress sendAddress;

    
    @Data
    public static class DeliveryAddress {
        
        /**
         * 姓名
         */
        @SerializedName("name")
        private String name;
        
        /**
         * 省
         */
        @SerializedName("province")
        private String province;
        
        /**
         * 市
         */
        @SerializedName("city")
        private String city;
        
        /**
         * 区/县
         */
        @SerializedName("county")
        private String county;
        
        /**
         * 联系方式
         */
        @SerializedName("mobile")
        private String mobile;
    }
}
