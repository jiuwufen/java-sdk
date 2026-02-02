package com.jiuwufen.sdk.model.inventory;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import java.util.List;

/**
 * InventorySyncResponse
 */
@Data
public class InventorySyncResponse {
    
    /**
     * 同步结果
     */
    @SerializedName("sync_result")
    private Boolean syncResult;
    
    /**
     * 成功列表
     */
    @SerializedName("succ_list")
    private List<SyncResultItem> succList;
    
    /**
     * 失败列表
     */
    @SerializedName("fail_list")
    private List<SyncResultItem> failList;

    
    @Data
    public static class SyncResultItem {
        
        /**
         * 商家编码
         */
        @SerializedName("merchant_code")
        private String merchantCode;
        
        /**
         * 状态码
         */
        @SerializedName("code")
        private Integer code;
        
        /**
         * 消息
         */
        @SerializedName("msg")
        private String msg;
    }
}
