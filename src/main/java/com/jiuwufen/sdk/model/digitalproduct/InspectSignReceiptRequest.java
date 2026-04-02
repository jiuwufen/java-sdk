package com.jiuwufen.sdk.model.digitalproduct;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

/**
 * 后验退回签收 (InspectSignReceipt) 请求
 */
@Data
public class InspectSignReceiptRequest {

    @SerializedName("express_number")
    private String expressNumber;
}
