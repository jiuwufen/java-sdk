package com.jiuwufen.sdk.model.goods;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import java.util.List;

/**
 * QueryPropertiesRequest
 */
@Data
public class QueryPropertiesRequest {
    
    /**
     * 三级类目ID
     */
    @SerializedName("child_category_id")
    private Long childCategoryId;
}
