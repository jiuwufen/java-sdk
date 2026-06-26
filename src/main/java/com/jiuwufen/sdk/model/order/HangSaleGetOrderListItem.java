package com.jiuwufen.sdk.model.order;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

/**
 * 挂售订单列表单项，对应接口 {@code data} 为数组时的元素（与 api-docs / Go GetOrderListResponseDataItem 一致）。
 */
@Data
public class HangSaleGetOrderListItem {

    @SerializedName("goods_sn")
    private String goodsSn;

    @SerializedName("sell_order_number")
    private String sellOrderNumber;

    @SerializedName("goods_info")
    private GoodsInfo goodsInfo;

    @SerializedName("buyer_info")
    private BuyerInfo buyerInfo;

    @SerializedName("address_info")
    private AddressInfo addressInfo;

    @Data
    public static class GoodsInfo {
        @SerializedName("goods_sn")
        private String goodsSn;
        @SerializedName("title")
        private String title;
        @SerializedName("code")
        private String code;
        @SerializedName("price")
        private String price;
        @SerializedName("brand_id")
        private Integer brandId;
        @SerializedName("brand_name")
        private String brandName;
        @SerializedName("size")
        private String size;
        @SerializedName("desc")
        private String desc;
        @SerializedName("status")
        private Integer status;
        @SerializedName("order_status")
        private String orderStatus;
        @SerializedName("stock")
        private Integer stock;
        @SerializedName("audit_status")
        private Integer auditStatus;
    }

    @Data
    public static class BuyerInfo {
        @SerializedName("order_number")
        private String orderNumber;
        @SerializedName("buyer_order_number")
        private String buyerOrderNumber;
        @SerializedName("pay_time")
        private String payTime;
    }

    @Data
    public static class AddressInfo {
        @SerializedName("name")
        private String name;
        @SerializedName("postcode")
        private String postcode;
        @SerializedName("province")
        private String province;
        @SerializedName("city")
        private String city;
        @SerializedName("county")
        private String county;
        @SerializedName("mobile")
        private String mobile;
        @SerializedName("region")
        private String region;
        @SerializedName("street")
        private String street;
    }
}
