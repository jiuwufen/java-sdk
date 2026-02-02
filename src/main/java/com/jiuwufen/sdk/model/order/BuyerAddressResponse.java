package com.jiuwufen.sdk.model.order;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import java.util.List;

/**
 * BuyerAddressResponse
 */
@Data
public class BuyerAddressResponse {
    
    /**
     * 加密地址
     */
    @SerializedName("address")
    private String address;
}
