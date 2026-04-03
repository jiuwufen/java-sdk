package com.jiuwufen.sdk.model.order;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

import java.util.List;

/**
 * 寄售订单信息查询响应
 */
@Data
public class ConsignOrderInfoResponse {

    /**
     * 订单列表
     */
    @SerializedName("order_list")
    private List<OrderItem> orderList;

    @Data
    public static class OrderItem {

        /** 95卖家订单号 */
        @SerializedName("sell_order_number")
        private String sellOrderNumber;

        /** 商品统一代码 */
        @SerializedName("upc")
        private String upc;

        /** 商品信息 */
        @SerializedName("goods_info")
        private GoodsInfo goodsInfo;

        /** 一级状态码 */
        @SerializedName("status")
        private Integer status;

        /** 二级状态码 */
        @SerializedName("sub_status")
        private Integer subStatus;

        /** 状态描述，中文 */
        @SerializedName("status_desc")
        private String statusDesc;

        /** 在售最低价（分）| 同成色同SKU的当前在售中商品最低价，若无则为0 */
        @SerializedName("sku_min_price")
        private Integer skuMinPrice;

        /** 出价(分) | 卖家出价金额，人民币，正整数，未出价时为0 */
        @SerializedName("price")
        private Integer price;

        /** 预计收入（分） | 根据卖家出价对应的预计收入，人民币 */
        @SerializedName("final_price")
        private Integer finalPrice;

        /** 服务费 */
        @SerializedName("total_fees")
        private Integer totalFees;

        /** 服务费明细 | 根据卖家出价对应的服务费，人民币，单位分 */
        @SerializedName("fee_detail")
        private FeeDetail feeDetail;

        /** 取回费用（分） | 根据寄存天数&操作服务费，计算该商品的取回费用 */
        @SerializedName("retrieve_price")
        private Integer retrievePrice;

        /** 是否包邮 | 1是，0否 */
        @SerializedName("is_free_ship")
        private Integer isFreeShip;

        /** 包邮补贴 | 人民币，单位分；买家支付前，卖家承担金额为空 */
        @SerializedName("free_ship_info")
        private FreeShipInfo freeShipInfo;

        /** 上架时间 | 示例2023-11-24 10:31:59 */
        @SerializedName("publish_time")
        private String publishTime;

        /** 图片信息 | 展示图+瑕疵图 */
        @SerializedName("img_list")
        private List<String> imgList;

        /** 瑕疵点 | 所有的瑕疵信息 */
        @SerializedName("flaw_list")
        private List<FlawItem> flawList;

        /** 批次号 */
        @SerializedName("batch_number")
        private String batchNumber;

        /** goods_sn */
        @SerializedName("goods_sn")
        private String goodsSn;

        /** imei */
        @SerializedName("imei")
        private String imei;

        /** 买家成交价 */
        @SerializedName("buyer_price")
        private Integer buyerPrice;

        /** 卖家分摊的优惠券金额(单位: 分) */
        @SerializedName("seller_coupon_amount")
        private Integer sellerCouponAmount;

        /** 满减分摊的优惠券金额(单位: 分) */
        @SerializedName("full_reduction_amount")
        private Integer fullReductionAmount;

        /** 包邮分摊的优惠券金额(单位: 分) */
        @SerializedName("free_shipping_amount")
        private Integer freeShippingAmount;

        /** 是否后验 */
        @SerializedName("is_posterior")
        private Boolean isPosterior;

        /** 后验单状态 */
        @SerializedName("posterior_status")
        private Integer posteriorStatus;

        /** 优惠券列表明细 */
        @SerializedName("promotion_info_list")
        private List<PromotionInfo> promotionInfoList;

        /** 结算金额（分） */
        @SerializedName("bill_price")
        private Integer billPrice;

        /** 买家支付时间 */
        @SerializedName("buyer_pay_time")
        private String buyerPayTime;

        /** 买家创建时间 */
        @SerializedName("buyer_create_time")
        private String buyerCreateTime;

        /** 买家订单号 */
        @SerializedName("buyer_order_number")
        private String buyerOrderNumber;

        @Data
        public static class GoodsInfo {

            @SerializedName("img")
            private String img;

            @SerializedName("title")
            private String title;

            @SerializedName("root_category_name")
            private String rootCategoryName;

            @SerializedName("child_category_name")
            private String childCategoryName;

            @SerializedName("property_value")
            private String propertyValue;

            /** 货号 */
            @SerializedName("size")
            private String size;

            @SerializedName("brand_name")
            private String brandName;

            /** 成色 */
            @SerializedName("quality")
            private String quality;

            /** 商品状态 1待上架 2出售中 3已售出 4已下架 */
            @SerializedName("status")
            private Integer status;

            /** 销售属性字符串，兼容历史接口 */
            @SerializedName("specification")
            private String specification;
        }

        @Data
        public static class FeeDetail {

            @SerializedName("total_fees")
            private Integer totalFees;

            @SerializedName("detail")
            private List<FeeDetailItem> detail;

            @SerializedName("is_activity")
            private Integer isActivity;
        }

        @Data
        public static class FeeDetailItem {

            @SerializedName("desc")
            private String desc;

            @SerializedName("fees")
            private Integer fees;

            @SerializedName("activity")
            private Activity activity;

            @Data
            public static class Activity {

                @SerializedName("desc")
                private String desc;

                @SerializedName("fees")
                private Integer fees;
            }
        }

        @Data
        public static class FreeShipInfo {

            /** 原始邮费 */
            @SerializedName("origin_freight")
            private Integer originFreight;

            /** 平台补贴金额 */
            @SerializedName("allowance_price")
            private Integer allowancePrice;

            /** 卖家承担金额 */
            @SerializedName("seller_freight_price")
            private Integer sellerFreightPrice;
        }

        @Data
        public static class FlawItem {

            /** 瑕疵图 */
            @SerializedName("img")
            private String img;

            /** 瑕疵描述 */
            @SerializedName("desc")
            private String desc;
        }

        @Data
        public static class PromotionInfo {

            @SerializedName("promotion_type")
            private String promotionType;

            @SerializedName("amount")
            private Integer amount;

            @SerializedName("platform_amount")
            private Integer platformAmount;

            @SerializedName("vender_amount")
            private Integer venderAmount;

            @SerializedName("platform_ratio")
            private Double platformRatio;

            @SerializedName("vender_ratio")
            private Double venderRatio;
        }
    }
}
