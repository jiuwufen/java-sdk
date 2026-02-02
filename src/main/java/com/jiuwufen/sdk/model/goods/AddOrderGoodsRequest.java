package com.jiuwufen.sdk.model.goods;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import java.util.List;

/**
 * 新增商品请求
 */
@Data
public class AddOrderGoodsRequest {
    @SerializedName("goods_sn")
    private String goodsSn;

    @SerializedName("title")
    private String title;

    @SerializedName("brand_id")
    private Long brandId;

    @SerializedName("l1_category_id")
    private Long l1CategoryId;

    @SerializedName("l2_category_id")
    private Long l2CategoryId;

    @SerializedName("first_img")
    private String firstImg;

    @SerializedName("general_imgs")
    private List<String> generalImgs;

    @SerializedName("quality")
    private Integer quality;

    @SerializedName("price")
    private Integer price;

    @SerializedName("size")
    private String size;

    @SerializedName("parts")
    private String parts;

    @SerializedName("code")
    private String code;

    @SerializedName("remark")
    private String remark;

    @SerializedName("flaw_desc")
    private String flawDesc;

    @SerializedName("flaw_imgs")
    private List<String> flawImgs;

    @SerializedName("fit_id")
    private Integer fitId;

    @SerializedName("support_bargain")
    private Integer supportBargain;

    @SerializedName("property_list")
    private List<PropertyItem> propertyList;

    @Data
    public static class PropertyItem {
        @SerializedName("id")
        private Long id;

        @SerializedName("name")
        private String name;

        @SerializedName("property_type")
        private Integer propertyType;

        @SerializedName("value")
        private String value;
    }
}
