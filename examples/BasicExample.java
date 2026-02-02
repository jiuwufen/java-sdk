package com.jiuwufen.sdk.example;

import com.jiuwufen.sdk.JiuWuFenClient;
import com.jiuwufen.sdk.exception.ApiException;
import com.jiuwufen.sdk.model.merchant.*;
import com.jiuwufen.sdk.model.goods.*;
import com.jiuwufen.sdk.model.inventory.*;

import java.util.Arrays;

/**
 * 基础使用示例
 * 
 * @author 95分开放平台团队
 */
public class BasicExample {

    public static void main(String[] args) {
        try {
            // 创建客户端
            JiuWuFenClient client = JiuWuFenClient.builder()
                    .erpName("your-erp-name")
                    .thirdPartyId("your-third-party-id")
                    .merchantSecret("your-merchant-secret")
                    .platformSecret("your-platform-secret")
                    .baseUrl("http://d1.95fenapp.com")
                    .debug(true)
                    .build();

            // 示例1: 发送短信验证码
            System.out.println("=== 示例1: 发送短信验证码 ===");
            SendSMSRequest smsRequest = new SendSMSRequest();
            smsRequest.setMobile("13800000000");
            SendSMSResponse smsResponse = client.merchant().sendSMSCaptcha(smsRequest);
            System.out.println("验证码已发送, req_id: " + smsResponse.getReqId());

            // 示例2: 校验短信验证码
            System.out.println("\n=== 示例2: 校验短信验证码 ===");
            CheckSMSRequest checkRequest = new CheckSMSRequest();
            checkRequest.setMobile("13800000000");
            checkRequest.setCaptcha("123456");
            CheckSMSResponse checkResponse = client.merchant().checkSMSCaptcha(checkRequest);
            System.out.println("校验成功!");
            System.out.println("应用标识: " + checkResponse.getData().getHeaderName());
            System.out.println("应用密钥: " + checkResponse.getData().getSecretKey());

            // 示例3: 新增商品
            System.out.println("\n=== 示例3: 新增商品 ===");
            AddOrderGoodsRequest addGoodsRequest = new AddOrderGoodsRequest();
            addGoodsRequest.setGoodsSn("GOODS-2024-001");
            addGoodsRequest.setTitle("Nike Air Max 90");
            addGoodsRequest.setBrandId(2L);
            addGoodsRequest.setL1CategoryId(1L);
            addGoodsRequest.setL2CategoryId(10L);
            addGoodsRequest.setFirstImg("https://example.com/img1.jpg");
            addGoodsRequest.setGeneralImgs(Arrays.asList("https://example.com/img2.jpg"));
            addGoodsRequest.setPrice(29900);
            addGoodsRequest.setQuality(20);

            AddOrderGoodsResponse addResponse = client.goods().addOrderGoods(addGoodsRequest);
            System.out.println("商品添加成功! Status: " + addResponse.getStatus());

            // 示例4: 库存同步
            System.out.println("\n=== 示例4: 库存同步 ===");
            InventorySyncRequest syncRequest = new InventorySyncRequest();
            InventorySyncRequest.InventorySyncItem item = new InventorySyncRequest.InventorySyncItem();
            item.setMerchantSkuCode("SKU-001");
            item.setSkuId(1390873L);
            item.setQty(100L);
            item.setSalableQty(90L);
            syncRequest.setDetail(Arrays.asList(item));

            InventorySyncResponse syncResponse = client.inventory().syncInventory(syncRequest);
            System.out.println("库存同步结果: " + syncResponse.getSyncResult());

            // 示例5: 错误处理
            System.out.println("\n=== 示例5: 错误处理示例 ===");
            try {
                GoodsInfoRequest infoRequest = new GoodsInfoRequest();
                infoRequest.setGoodsSn("INVALID_GOODS_SN");
                client.goods().getGoodsInfo(infoRequest);
            } catch (ApiException e) {
                System.err.println("业务错误:");
                System.err.println("  错误码: " + e.getCode());
                System.err.println("  错误信息: " + e.getMessage());
                System.err.println("  请求ID: " + e.getReqId());

                if (e.isBusinessError()) {
                    System.err.println("  类型: 业务错误");
                } else if (e.isNetworkError()) {
                    System.err.println("  类型: 网络错误");
                }
            }

            System.out.println("\n=== 基础示例完成 ===");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
