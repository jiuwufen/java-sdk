package com.jiuwufen.sdk.api;

import com.jiuwufen.sdk.JiuWuFenClient;
import com.jiuwufen.sdk.exception.ApiException;
import com.jiuwufen.sdk.model.inventory.*;

/**
 * 库存管理 API
 */
public class InventoryApi {

    private final JiuWuFenClient client;

    public InventoryApi(JiuWuFenClient client) {
        this.client = client;
    }

    /**
     * 库存同步
     */
    public InventorySyncResponse syncInventory(InventorySyncRequest request) throws ApiException {
        return client.execute("/api_tob/inventory/sync/v1.0", request, InventorySyncResponse.class);
    }

    /**
     * 库存查询
     */
    public InventoryListResponse getInventoryList(InventoryListRequest request) throws ApiException {
        return client.execute("/api_tob/inventory/list/v1.0", request, InventoryListResponse.class);
    }

    /**
     * 同步库存（上下架）
     */
    public UpdateStockResponse updateStock(UpdateStockRequest request) throws ApiException {
        return client.execute("/api_tob/updateStock/v1.0", request, UpdateStockResponse.class);
    }
}
