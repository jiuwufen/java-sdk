package com.jiuwufen.sdk.exception;

import lombok.Getter;

/**
 * API 异常类
 * 
 * <p>封装 API 调用过程中的错误信息
 * 
 * @author 95分开放平台团队
 * @version 1.0.0
 */
@Getter
public class ApiException extends Exception {
    
    /**
     * 错误码
     * <ul>
     *   <li>-1: 网络错误或系统错误</li>
     *   <li>0: 成功</li>
     *   <li>>0: 业务错误码</li>
     * </ul>
     */
    private final int code;
    
    /**
     * 错误信息
     */
    private final String message;
    
    /**
     * 请求 ID
     */
    private final String reqId;
    
    /**
     * 构造函数
     * 
     * @param code 错误码
     * @param message 错误信息
     * @param reqId 请求 ID
     */
    public ApiException(int code, String message, String reqId) {
        super(String.format("[%d] %s (req_id: %s)", code, message, reqId));
        this.code = code;
        this.message = message;
        this.reqId = reqId;
    }
    
    /**
     * 是否为业务错误
     * 
     * @return true 表示业务错误
     */
    public boolean isBusinessError() {
        return code > 0;
    }
    
    /**
     * 是否为网络错误
     * 
     * @return true 表示网络错误
     */
    public boolean isNetworkError() {
        return code == -1;
    }
}
