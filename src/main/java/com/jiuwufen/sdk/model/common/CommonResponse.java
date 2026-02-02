package com.jiuwufen.sdk.model.common;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

/**
 * 通用响应结构
 * 
 * @param <T> 数据类型
 * @author 95分开放平台团队
 * @version 1.0.0
 */
@Data
public class CommonResponse<T> {
    
    /**
     * 状态码
     * <ul>
     *   <li>0: 成功</li>
     *   <li>其他: 错误码</li>
     * </ul>
     */
    @SerializedName("status")
    private Integer status;
    
    /**
     * 消息
     */
    @SerializedName("msg")
    private String msg;
    
    /**
     * 请求 ID
     */
    @SerializedName("req_id")
    private String reqId;
    
    /**
     * 响应数据
     */
    @SerializedName("data")
    private T data;
}
