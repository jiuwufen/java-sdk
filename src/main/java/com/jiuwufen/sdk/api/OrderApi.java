package com.jiuwufen.sdk.api;

import com.google.gson.reflect.TypeToken;
import com.jiuwufen.sdk.JiuWuFenClient;
import com.jiuwufen.sdk.exception.ApiException;
import com.jiuwufen.sdk.model.order.*;

import java.lang.reflect.Type;
import java.util.List;

/**
 * 订单管理 API
 */
public class OrderApi {

    private final JiuWuFenClient client;

    public OrderApi(JiuWuFenClient client) {
        this.client = client;
    }

    /**
     * 查询商品订单信息
     */
    public ConsignOrderInfoResponse getConsignOrderInfo(ConsignOrderInfoRequest request) throws ApiException {
        return client.execute("/api_tob/consignOrderInfo/v1.0", request, ConsignOrderInfoResponse.class);
    }

    /**
     * 买家地址查询
     */
    public BuyerAddressResponse getBuyerAddress(BuyerAddressRequest request) throws ApiException {
        return client.execute("/api_tob/order/buyerAddress", request, BuyerAddressResponse.class);
    }

    /**
     * 自送货订单明细查询
     */
    public ConsignBatchOrderListResponse getConsignBatchOrderList(ConsignBatchOrderListRequest request)
            throws ApiException {
        return client.execute("/api_tob/consignBatchOrderList/v1.0", request, ConsignBatchOrderListResponse.class);
    }

    /**
     * 获取订单列表（挂售）。接口 {@code data} 为数组，与 {@link HangSaleGetOrderListItem} 列表对应。
     */
    public List<HangSaleGetOrderListItem> getOrderList(GetOrderListRequest request) throws ApiException {
        Type dataType = TypeToken.getParameterized(List.class, HangSaleGetOrderListItem.class).getType();
        return client.execute("/api_tob/getOrderList/v1.0", request, dataType);
    }
}
