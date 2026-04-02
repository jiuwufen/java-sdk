package com.jiuwufen.sdk.model.digitalproduct;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

import java.util.List;

/**
 * 自研商家一键出售 v2 — 质检项节点（examining_list[]）。
 * <p>
 * v2 为扁平结构：key + 可选异常图片数组，无 second/third 层级。
 */
@Data
public class DigitalSuperSaleV2ExaminingItem {

    @SerializedName("key")
    private String key;

    /** 质检项异常图片数组（选填） */
    @SerializedName("imgs")
    private List<String> imgs;
}
