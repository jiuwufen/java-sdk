package com.jiuwufen.sdk.model.order;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import java.util.List;

/**
 * GetOrderListRequest
 */
@Data
public class GetOrderListRequest {
    
    /**
     * 页码
     */
    @SerializedName("page")
    private Integer page;
    
    /**
     * 每页数量
     */
    @SerializedName("page_size")
    private Integer pageSize;
}
