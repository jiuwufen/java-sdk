package com.jiuwufen.sdk.model.order;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

import java.util.List;

/**
 * 寄售订单信息查询请求，对应 ConsignOrderInfoReq。
 */
@Data
public class ConsignOrderInfoRequest {

    /**
     * 商品统一代码
     */
    @SerializedName("upc")
    private String upc;

    /**
     * 95卖家订单号
     */
    @SerializedName("order_number")
    private List<String> orderNumber;

    /**
     * 批次号
     */
    @SerializedName("batch_number")
    private String batchNumber;

    /**
     * 页码，默认 1
     */
    @SerializedName("page")
    private Long page;

    /**
     * 每页个数，默认 20，每次查询不超过 20
     */
    @SerializedName("page_size")
    private Long pageSize;

    /**
     * 商品编号
     */
    @SerializedName("goods_sn")
    private String goodsSn;

    /**
     * 是否查询服务费，默认 0，按需请求
     */
    @SerializedName("is_fee_detail")
    private Integer isFeeDetail;

    /**
     * 是否查询取回费，默认 0，按需请求
     */
    @SerializedName("is_retrieve")
    private Integer isRetrieve;
}
