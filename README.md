# 95分开放平台 Java SDK

[![Maven Central](https://img.shields.io/badge/maven--central-1.0.0-blue)](https://search.maven.org/)
[![Java Version](https://img.shields.io/badge/Java-%3E%3D%201.8-blue)](https://www.oracle.com/java/technologies/javase-downloads.html)
[![License](https://img.shields.io/badge/license-MIT-green)](LICENSE)

95分开放平台的官方 Java SDK，提供完整的 API 调用能力，包括商品管理、订单处理、库存同步等功能。

## ✨ 特性

- 🏗️ **Builder 模式** - 灵活的客户端配置
- 🔐 **自动签名** - 内置 MD5 + Base64 签名算法
- 🔒 **地址解密** - 支持 AES-ECB 买家地址解密
- 📦 **完整 API** - 覆盖所有开放平台接口
- 🎯 **类型安全** - 完整的类型定义和参数校验
- 🔄 **自动重试** - 内置请求重试机制
- 📝 **详细日志** - 支持调试模式查看请求详情
- ⚡ **高性能** - 基于 OkHttp 的高性能 HTTP 客户端

## 📦 安装

### Maven

```xml
<dependency>
    <groupId>com.jiuwufen</groupId>
    <artifactId>jiuwufen-open-sdk</artifactId>
    <version>1.0.0</version>
</dependency>
```

### Gradle

```gradle
implementation 'com.jiuwufen:jiuwufen-open-sdk:1.0.0'
```

## 🚀 快速开始

### 1. 创建客户端

```java
import com.jiuwufen.sdk.JiuWuFenClient;

JiuWuFenClient client = JiuWuFenClient.builder()
    .erpName("your-erp-name")              // ERP 名称（由95分提供）
    .thirdPartyId("your-third-party-id")   // 第三方应用标识
    .merchantSecret("your-merchant-secret") // 商家密钥
    .platformSecret("your-platform-secret") // 平台密钥
    .baseUrl("http://d1.95fenapp.com")     // 开发环境
    .connectTimeout(10)                     // 连接超时（秒）
    .readTimeout(30)                        // 读取超时（秒）
    .debug(true)                            // 开启调试模式
    .build();
```

### 2. 商户入驻

```java
import com.jiuwufen.sdk.model.merchant.*;

// 发送短信验证码
SendSMSRequest smsRequest = new SendSMSRequest();
smsRequest.setMobile("13800000000");
SendSMSResponse smsResponse = client.merchant().sendSMSCaptcha(smsRequest);
System.out.println("验证码已发送: " + smsResponse.getReqId());

// 校验短信验证码
CheckSMSRequest checkRequest = new CheckSMSRequest();
checkRequest.setMobile("13800000000");
checkRequest.setCaptcha("123456");
CheckSMSResponse checkResponse = client.merchant().checkSMSCaptcha(checkRequest);
System.out.println("应用标识: " + checkResponse.getData().getHeaderName());
System.out.println("应用密钥: " + checkResponse.getData().getSecretKey());
```

### 3. 商品管理

```java
import com.jiuwufen.sdk.model.goods.*;
import java.util.Arrays;

// 新增商品
AddOrderGoodsRequest request = new AddOrderGoodsRequest();
request.setGoodsSn("GOODS-2024-001");
request.setTitle("Nike Air Max 90 经典复古跑鞋");
request.setBrandId(2L);
request.setL1CategoryId(1L);
request.setL2CategoryId(10L);
request.setFirstImg("https://example.com/nike-air-max-90-main.jpg");
request.setGeneralImgs(Arrays.asList(
    "https://example.com/nike-air-max-90-1.jpg",
    "https://example.com/nike-air-max-90-2.jpg"
));
request.setPrice(29900); // 价格单位：分
request.setQuality(20);

AddOrderGoodsResponse response = client.goods().addOrderGoods(request);
System.out.println("商品添加成功!");
```

### 4. 库存同步

```java
import com.jiuwufen.sdk.model.inventory.*;
import java.util.Arrays;

InventorySyncRequest request = new InventorySyncRequest();
InventorySyncRequest.InventorySyncItem item = new InventorySyncRequest.InventorySyncItem();
item.setMerchantSkuCode("SKU-001");
item.setSkuId(1390873L);
item.setQty(100L);
item.setSalableQty(90L);
request.setDetail(Arrays.asList(item));

InventorySyncResponse response = client.inventory().syncInventory(request);
System.out.println("库存同步完成: " + response.getData().getSyncResult());
```

### 5. 订单查询

```java
import com.jiuwufen.sdk.model.order.*;
import java.util.Arrays;

ConsignOrderInfoRequest request = new ConsignOrderInfoRequest();
request.setOrderNumber(Arrays.asList("95025032898155673463"));
request.setPage(1L);
request.setPageSize(20L);

ConsignOrderInfoResponse response = client.order().getConsignOrderInfo(request);
for (ConsignOrderInfoResponse.OrderItem order : response.getData().getOrderList()) {
    System.out.println("订单号: " + order.getSellOrderNumber());
    System.out.println("状态: " + order.getStatusDesc());
}
```

## 📚 API 列表

### 平台商户入驻 (2个接口)

| 方法 | 说明 |
|------|------|
| `merchant().sendSMSCaptcha()` | 发送短信验证码 |
| `merchant().checkSMSCaptcha()` | 校验短信验证码 |

### 商品管理 (12个接口)

| 方法 | 说明 |
|------|------|
| `goods().getMerchantSkuList()` | 查询SKU列表（绑定关系） |
| `goods().getSkuList()` | 查询SKU列表（通用） |
| `goods().addOrderGoods()` | 新增商品 |
| `goods().getGoodsInfo()` | 查询商品状态信息 |
| `goods().updatePrice()` | 改价 |
| `goods().cancelOrder()` | 下架商品 |
| `goods().updateSellerBargain()` | 卖家议价 |
| `goods().bargainSuccess()` | 卖家接受还价 |
| `goods().queryProperties()` | 获取类目属性 |
| `goods().getBrandIdentifyAbility()` | 可鉴品牌查询 |
| `goods().copyOnSale()` | 复制订单上架 |
| `goods().getReferencePrice()` | 订单参考价查询 |

### 库存管理 (3个接口)

| 方法 | 说明 |
|------|------|
| `inventory().syncInventory()` | 库存同步 |
| `inventory().getInventoryList()` | 库存查询 |
| `inventory().updateStock()` | 同步库存（上下架） |

### 订单管理 (4个接口)

| 方法 | 说明 |
|------|------|
| `order().getConsignOrderInfo()` | 查询商品订单信息 |
| `order().getBuyerAddress()` | 买家地址查询 |
| `order().getConsignBatchOrderList()` | 自送货订单明细查询 |
| `order().getOrderList()` | 获取订单列表（挂售） |

### 物流管理 (1个接口)

| 方法 | 说明 |
|------|------|
| `delivery().deliveryBiz()` | 发货 & 重打面单 |

## ⚙️ 配置选项

### 环境配置

```java
// 开发环境
.baseUrl("http://d1.95fenapp.com")

// 测试环境
.baseUrl("http://t1.95fenapp.com")

// 预发环境
.baseUrl("http://stg-www.95fenapp.com")

// 生产环境
.baseUrl("http://www.95fenapp.com")
```

### 超时配置

```java
JiuWuFenClient client = JiuWuFenClient.builder()
    // ... 其他配置
    .connectTimeout(10)  // 连接超时：10秒
    .readTimeout(30)     // 读取超时：30秒
    .writeTimeout(30)    // 写入超时：30秒
    .build();
```

### 调试模式

```java
JiuWuFenClient client = JiuWuFenClient.builder()
    // ... 其他配置
    .debug(true)  // 开启调试模式，打印请求和响应详情
    .build();
```

## 🔧 高级功能

### 错误处理

```java
import com.jiuwufen.sdk.exception.ApiException;

try {
    AddOrderGoodsResponse response = client.goods().addOrderGoods(request);
    System.out.println("成功: " + response.getMsg());
} catch (ApiException e) {
    System.err.println("错误码: " + e.getCode());
    System.err.println("错误信息: " + e.getMessage());
    System.err.println("请求ID: " + e.getReqId());
    
    if (e.isBusinessError()) {
        // 处理业务错误
        System.err.println("业务错误，请检查参数");
    } else if (e.isNetworkError()) {
        // 处理网络错误
        System.err.println("网络错误，请稍后重试");
    }
}
```

### 地址解密

```java
import com.jiuwufen.sdk.util.SignatureUtil;

// 获取加密的买家地址
BuyerAddressRequest request = new BuyerAddressRequest();
request.setOrderNumber("95025032898155673463");
BuyerAddressResponse response = client.order().getBuyerAddress(request);

// 解密地址
byte[] key = "your-platform-secret".getBytes();
String decryptedAddress = SignatureUtil.decryptAddress(
    response.getData().getAddress(), 
    key
);
System.out.println("买家地址: " + decryptedAddress);
```

### 签名验证（用于回调）

```java
import com.jiuwufen.sdk.util.SignatureUtil;
import java.util.Map;

// 验证回调签名
Map<String, Object> params = new HashMap<>();
params.put("goods_sn", "GOODS-001");
params.put("price", 29900);
// ... 其他参数

String receivedToken = "received_token_from_callback";

boolean valid = SignatureUtil.verifySignature(
    params, 
    receivedToken,
    merchantSecret,
    platformSecret
);

if (!valid) {
    throw new RuntimeException("签名验证失败");
}
```

## 🧪 测试

```bash
# 运行所有测试
mvn test

# 运行测试并生成覆盖率报告
mvn clean test jacoco:report

# 查看覆盖率报告
open target/site/jacoco/index.html
```

## 📖 文档

- [API 详细文档](docs/API.md)
- [快速开始指南](docs/QUICKSTART.md)
- [常见问题](docs/FAQ.md)

## 🤝 贡献

欢迎提交 Issue 和 Pull Request！

## 📄 许可证

MIT License

## 📞 技术支持

- GitHub: https://github.com/jiuwufen/java-sdk
- Issues: https://github.com/jiuwufen/java-sdk/issues
- Email: support@95fenapp.com

## 🎯 版本历史

### v1.0.0 (2024-02-02)

- ✅ 完整的 API 实现（22个接口）
- ✅ Builder 模式客户端
- ✅ 自动签名和加解密
- ✅ 完善的错误处理
- ✅ 详细的文档和示例
