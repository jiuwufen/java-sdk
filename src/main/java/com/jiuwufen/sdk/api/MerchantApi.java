package com.jiuwufen.sdk.api;

import com.jiuwufen.sdk.JiuWuFenClient;
import com.jiuwufen.sdk.exception.ApiException;
import com.jiuwufen.sdk.model.merchant.*;

/**
 * 商户入驻 API
 * 
 * @author 95分开放平台团队
 * @version 1.0.0
 */
public class MerchantApi {

    private final JiuWuFenClient client;

    public MerchantApi(JiuWuFenClient client) {
        this.client = client;
    }

    /**
     * 发送短信验证码
     * 
     * @param request 请求参数
     * @return 响应结果
     * @throws ApiException API 异常
     */
    public SendSMSResponse sendSMSCaptcha(SendSMSRequest request) throws ApiException {
        return client.execute("/api_tob/erpSendSmsCaptcha/v1.0", request, SendSMSResponse.class);
    }

    /**
     * 校验短信验证码
     * 
     * @param request 请求参数
     * @return 响应结果
     * @throws ApiException API 异常
     */
    public CheckSMSResponse checkSMSCaptcha(CheckSMSRequest request) throws ApiException {
        return client.execute("/api_tob/erpCheckSmsCaptcha/v1.0", request, CheckSMSResponse.class);
    }
}
