#!/usr/bin/env python3
"""
Java SDK 模型类生成器
根据 Go SDK 的模型定义自动生成 Java 模型类
"""

import os
import re

# 基础路径
BASE_PATH = "/Users/admin/promptflow-open/sdk/java-sdk/src/main/java/com/jiuwufen/sdk/model"

# 模型定义（简化版，包含核心字段）
MODELS = {
    # 商品模型
    "goods": {
        "MerchantSkuListRequest": {
            "fields": [
                ("merchant_sku_code", "List<String>", "商家商品编码数组"),
                ("start_binding_time", "String", "查询绑定开始时间"),
                ("end_binding_time", "String", "查询绑定结束时间"),
                ("page", "Integer", "页码"),
                ("page_size", "Integer", "每页数量"),
            ]
        },
        "MerchantSkuListResponse": {
            "fields": [
                ("total", "Long", "总记录数"),
                ("list", "List<SkuItem>", "SKU列表"),
            ],
            "inner_classes": [
                ("SkuItem", [
                    ("sku_id", "Long", "SKU ID"),
                    ("title", "String", "标题"),
                    ("code", "String", "货号"),
                ])
            ]
        },
        "AddOrderGoodsResponse": {
            "fields": [
                ("status", "Integer", "状态码"),
                ("msg", "String", "消息"),
            ]
        },
        "GoodsInfoRequest": {
            "fields": [
                ("goods_sn", "String", "商品编号"),
            ]
        },
        "GoodsInfoResponse": {
            "fields": [
                ("status", "Integer", "状态"),
                ("channel_sku_url", "String", "商品详情链接"),
            ]
        },
        "UpdatePriceRequest": {
            "fields": [
                ("goods_sn", "String", "商品编号"),
                ("price", "Long", "售价（元）"),
            ]
        },
        "UpdatePriceResponse": {
            "fields": [
                ("req_id", "String", "请求ID"),
            ]
        },
        "CancelOrderRequest": {
            "fields": [
                ("goods_sn", "String", "商品编号"),
            ]
        },
        "CancelOrderResponse": {
            "fields": [
                ("req_id", "String", "请求ID"),
            ]
        },
        "UpdateSellerBargainRequest": {
            "fields": [
                ("goods_sn", "String", "商品编号"),
                ("price", "Long", "议价价格"),
            ]
        },
        "UpdateSellerBargainResponse": {
            "fields": [
                ("msg", "String", "消息"),
            ]
        },
        "BargainSuccessRequest": {
            "fields": [
                ("goods_sn", "String", "商品编号"),
            ]
        },
        "BargainSuccessResponse": {
            "fields": [
                ("msg", "String", "消息"),
            ]
        },
        "QueryPropertiesRequest": {
            "fields": [
                ("child_category_id", "Long", "三级类目ID"),
            ]
        },
        "QueryPropertiesResponse": {
            "fields": [
                ("list", "List<PropertyItem>", "属性列表"),
            ],
            "inner_classes": [
                ("PropertyItem", [
                    ("id", "Long", "属性ID"),
                    ("name", "String", "属性名"),
                    ("property_type", "Integer", "属性类型"),
                    ("is_required", "Integer", "是否必填"),
                ])
            ]
        },
        "GetBrandIdentifyAbilityRequest": {
            "fields": [
                ("l1_category_id", "Long", "一级类目ID"),
                ("brand_name", "String", "品牌名称"),
            ]
        },
        "GetBrandIdentifyAbilityResponse": {
            "fields": [
                ("list", "List<BrandItem>", "品牌列表"),
            ],
            "inner_classes": [
                ("BrandItem", [
                    ("id", "Long", "品牌ID"),
                    ("name", "String", "品牌名称"),
                ])
            ]
        },
        "CopyOnSaleRequest": {
            "fields": [
                ("goods_sn", "String", "原商品编号"),
            ]
        },
        "CopyOnSaleResponse": {
            "fields": [
                ("msg", "String", "消息"),
            ]
        },
        "ReferencePriceRequest": {
            "fields": [
                ("goods_sn", "String", "商品编号"),
            ]
        },
        "ReferencePriceResponse": {
            "fields": [
                ("platform_min_price", "Integer", "平台最低价"),
                ("consign_min_price", "Integer", "寄售最低价"),
                ("recent_trans_price", "Integer", "最近成交均价"),
                ("new_market_price", "Integer", "全新市场价"),
            ]
        },
    },
    # 库存模型
    "inventory": {
        "InventorySyncRequest": {
            "fields": [
                ("detail", "List<InventorySyncItem>", "详情"),
            ],
            "inner_classes": [
                ("InventorySyncItem", [
                    ("merchant_sku_code", "String", "商家商品编码"),
                    ("sku_id", "Long", "SKU ID"),
                    ("qty", "Long", "实际库存"),
                    ("salable_qty", "Long", "可售库存"),
                ])
            ]
        },
        "InventorySyncResponse": {
            "fields": [
                ("sync_result", "Boolean", "同步结果"),
                ("succ_list", "List<SyncResultItem>", "成功列表"),
                ("fail_list", "List<SyncResultItem>", "失败列表"),
            ],
            "inner_classes": [
                ("SyncResultItem", [
                    ("merchant_code", "String", "商家编码"),
                    ("code", "Integer", "状态码"),
                    ("msg", "String", "消息"),
                ])
            ]
        },
        "InventoryListRequest": {
            "fields": [
                ("detail", "List<InventoryListReqItem>", "详情"),
            ],
            "inner_classes": [
                ("InventoryListReqItem", [
                    ("merchant_sku_code", "String", "商家商品编码"),
                    ("sku_id", "Long", "SKU ID"),
                ])
            ]
        },
        "InventoryListResponse": {
            "fields": [
                ("detail", "List<InventoryItem>", "库存详情"),
            ],
            "inner_classes": [
                ("InventoryItem", [
                    ("merchant_sku_code", "String", "商家商品编码"),
                    ("sku_id", "Long", "SKU ID"),
                    ("available_qty", "Long", "上架中库存"),
                    ("lock_qty", "Long", "预占库存"),
                ])
            ]
        },
        "UpdateStockRequest": {
            "fields": [
                ("goods_sn", "String", "商品编号"),
                ("stock", "Integer", "库存状态"),
            ]
        },
        "UpdateStockResponse": {
            "fields": [
                ("msg", "String", "消息"),
            ]
        },
    },
    # 订单模型
    "order": {
        "ConsignOrderInfoRequest": {
            "fields": [
                ("upc", "String", "商品统一代码"),
                ("order_number", "List<String>", "订单号"),
                ("batch_number", "String", "批次号"),
                ("goods_sn", "String", "商品编号"),
                ("page", "Long", "页码"),
                ("page_size", "Long", "每页数量"),
            ]
        },
        "ConsignOrderInfoResponse": {
            "fields": [
                ("order_list", "List<OrderItem>", "订单列表"),
            ],
            "inner_classes": [
                ("OrderItem", [
                    ("sell_order_number", "String", "卖家订单号"),
                    ("status_desc", "String", "状态描述"),
                ])
            ]
        },
        "BuyerAddressRequest": {
            "fields": [
                ("order_number", "String", "订单号"),
            ]
        },
        "BuyerAddressResponse": {
            "fields": [
                ("address", "String", "加密地址"),
            ]
        },
        "ConsignBatchOrderListRequest": {
            "fields": [
                ("batch_number", "String", "批次号"),
            ]
        },
        "ConsignBatchOrderListResponse": {
            "fields": [
                ("list", "List<BatchOrderItem>", "订单列表"),
            ],
            "inner_classes": [
                ("BatchOrderItem", [
                    ("order_number", "String", "订单号"),
                ])
            ]
        },
        "GetOrderListRequest": {
            "fields": [
                ("page", "Integer", "页码"),
                ("page_size", "Integer", "每页数量"),
            ]
        },
        "GetOrderListResponse": {
            "fields": [
                ("list", "List<OrderListItem>", "订单列表"),
            ],
            "inner_classes": [
                ("OrderListItem", [
                    ("order_number", "String", "订单号"),
                ])
            ]
        },
    },
    # 物流模型
    "delivery": {
        "DeliveryBizRequest": {
            "fields": [
                ("order_number", "String", "订单号"),
                ("send_address", "DeliveryAddress", "发货地址"),
            ],
            "inner_classes": [
                ("DeliveryAddress", [
                    ("name", "String", "姓名"),
                    ("province", "String", "省"),
                    ("city", "String", "市"),
                    ("county", "String", "区/县"),
                    ("mobile", "String", "联系方式"),
                ])
            ]
        },
        "DeliveryBizResponse": {
            "fields": [
                ("list", "List<DeliveryItem>", "发货信息列表"),
            ],
            "inner_classes": [
                ("DeliveryItem", [
                    ("express_number", "String", "快递单号"),
                ])
            ]
        },
    },
}


def generate_java_class(package, class_name, fields, inner_classes=None):
    """生成 Java 类代码"""
    code = f"""package com.jiuwufen.sdk.model.{package};

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import java.util.List;

/**
 * {class_name}
 */
@Data
public class {class_name} {{
"""
    
    # 生成字段
    for field_name, field_type, comment in fields:
        code += f"""    
    /**
     * {comment}
     */
    @SerializedName("{field_name}")
    private {field_type} {snake_to_camel(field_name)};
"""
    
    # 生成内部类
    if inner_classes:
        for inner_class_name, inner_fields in inner_classes:
            code += f"""
    
    @Data
    public static class {inner_class_name} {{
"""
            for field_name, field_type, comment in inner_fields:
                code += f"""        
        /**
         * {comment}
         */
        @SerializedName("{field_name}")
        private {field_type} {snake_to_camel(field_name)};
"""
            code += """    }
"""
    
    code += "}\n"
    return code


def snake_to_camel(snake_str):
    """将下划线命名转换为驼峰命名"""
    components = snake_str.split('_')
    return components[0] + ''.join(x.title() for x in components[1:])


def main():
    """主函数"""
    print("开始生成 Java SDK 模型类...")
    
    for package, models in MODELS.items():
        package_path = os.path.join(BASE_PATH, package)
        os.makedirs(package_path, exist_ok=True)
        
        for class_name, definition in models.items():
            fields = definition.get("fields", [])
            inner_classes = definition.get("inner_classes", None)
            
            code = generate_java_class(package, class_name, fields, inner_classes)
            
            file_path = os.path.join(package_path, f"{class_name}.java")
            with open(file_path, 'w', encoding='utf-8') as f:
                f.write(code)
            
            print(f"✅ 生成: {package}/{class_name}.java")
    
    print("\n✨ 所有模型类生成完成！")


if __name__ == "__main__":
    main()
