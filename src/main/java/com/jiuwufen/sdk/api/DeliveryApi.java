package com.jiuwufen.sdk.api;

import com.jiuwufen.sdk.JiuWuFenClient;
import com.jiuwufen.sdk.exception.ApiException;
import com.jiuwufen.sdk.model.delivery.*;

/**
 * 物流管理 API
 */
public class DeliveryApi {

    private final JiuWuFenClient client;

    public DeliveryApi(JiuWuFenClient client) {
        this.client = client;
    }

    /**
     * 发货 & 重打面单
     */
    public DeliveryBizResponse deliveryBiz(DeliveryBizRequest request) throws ApiException {
        return client.execute("/api_tob/delivery/bizDelivery/v1.0", request, DeliveryBizResponse.class);
    }
}
