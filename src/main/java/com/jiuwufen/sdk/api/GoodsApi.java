package com.jiuwufen.sdk.api;

import com.jiuwufen.sdk.JiuWuFenClient;
import com.jiuwufen.sdk.exception.ApiException;
import com.jiuwufen.sdk.model.goods.*;

/**
 * 商品管理 API
 * 
 * @author 95分开放平台团队
 * @version 1.0.0
 */
public class GoodsApi {

    private final JiuWuFenClient client;

    public GoodsApi(JiuWuFenClient client) {
        this.client = client;
    }

    /**
     * 查询SKU列表（绑定关系）
     */
    public MerchantSkuListResponse getMerchantSkuList(MerchantSkuListRequest request) throws ApiException {
        return client.execute("/api_tob/merchantSkuList/v1.0", request, MerchantSkuListResponse.class);
    }

    /**
     * 新增商品
     */
    public AddOrderGoodsResponse addOrderGoods(AddOrderGoodsRequest request) throws ApiException {
        return client.execute("/api_tob/addOrderGoods/v1.0", request, AddOrderGoodsResponse.class);
    }

    /**
     * 查询商品状态信息
     */
    public GoodsInfoResponse getGoodsInfo(GoodsInfoRequest request) throws ApiException {
        return client.execute("/api_tob/goodsInfo/v1.0", request, GoodsInfoResponse.class);
    }

    /**
     * 改价
     */
    public UpdatePriceResponse updatePrice(UpdatePriceRequest request) throws ApiException {
        return client.execute("/api_tob/updatePrice/v1.0", request, UpdatePriceResponse.class);
    }

    /**
     * 下架商品
     */
    public CancelOrderResponse cancelOrder(CancelOrderRequest request) throws ApiException {
        return client.execute("/api_tob/cancelOrder/v1.0", request, CancelOrderResponse.class);
    }

    /**
     * 卖家议价
     */
    public UpdateSellerBargainResponse updateSellerBargain(UpdateSellerBargainRequest request) throws ApiException {
        return client.execute("/api_tob/updateSellerBargain/v1.0", request, UpdateSellerBargainResponse.class);
    }

    /**
     * 卖家接受还价
     */
    public BargainSuccessResponse bargainSuccess(BargainSuccessRequest request) throws ApiException {
        return client.execute("/api_tob/bargainSuccess/v1.0", request, BargainSuccessResponse.class);
    }

    /**
     * 获取类目属性
     */
    public QueryPropertiesResponse queryProperties(QueryPropertiesRequest request) throws ApiException {
        return client.execute("/api_tob/query_properties/v1.0", request, QueryPropertiesResponse.class);
    }

    /**
     * 可鉴品牌查询
     */
    public GetBrandIdentifyAbilityResponse getBrandIdentifyAbility(GetBrandIdentifyAbilityRequest request)
            throws ApiException {
        return client.execute("/api_tob/get_brand_identify_ability/v1.0", request,
                GetBrandIdentifyAbilityResponse.class);
    }

    /**
     * 复制订单上架
     */
    public CopyOnSaleResponse copyOnSale(CopyOnSaleRequest request) throws ApiException {
        return client.execute("/api_tob/copyOnSale/v1.0", request, CopyOnSaleResponse.class);
    }

    /**
     * 订单参考价查询
     */
    public ReferencePriceResponse getReferencePrice(ReferencePriceRequest request) throws ApiException {
        return client.execute("/api_tob/referencePrice/v1.0", request, ReferencePriceResponse.class);
    }
}
