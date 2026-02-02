package com.jiuwufen.sdk.model.order;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import java.util.List;

/**
 * ConsignBatchOrderListRequest
 */
@Data
public class ConsignBatchOrderListRequest {
    
    /**
     * 批次号
     */
    @SerializedName("batch_number")
    private String batchNumber;
}
