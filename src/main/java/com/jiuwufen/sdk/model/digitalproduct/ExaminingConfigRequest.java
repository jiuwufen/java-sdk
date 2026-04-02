package com.jiuwufen.sdk.model.digitalproduct;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

/**
 * 质检项查询 (ExaminingConfig) 请求
 */
@Data
public class ExaminingConfigRequest {

    @SerializedName("template_id")
    private Long templateId;
}
