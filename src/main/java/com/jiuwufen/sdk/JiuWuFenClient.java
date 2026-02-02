package com.jiuwufen.sdk;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.jiuwufen.sdk.api.*;
import com.jiuwufen.sdk.exception.ApiException;
import com.jiuwufen.sdk.model.common.CommonResponse;
import com.jiuwufen.sdk.util.SignatureUtil;
import lombok.Getter;
import okhttp3.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * 95分开放平台 SDK 客户端
 * 
 * <p>提供完整的 API 调用能力，包括商品管理、订单处理、库存同步等功能。
 * 
 * <p>使用示例：
 * <pre>{@code
 * JiuWuFenClient client = JiuWuFenClient.builder()
 *     .erpName("your-erp-name")
 *     .thirdPartyId("your-third-party-id")
 *     .merchantSecret("your-merchant-secret")
 *     .platformSecret("your-platform-secret")
 *     .baseUrl("http://d1.95fenapp.com")
 *     .build();
 * 
 * // 调用 API
 * SendSMSRequest request = new SendSMSRequest();
 * request.setMobile("13800000000");
 * SendSMSResponse response = client.merchant().sendSMSCaptcha(request);
 * }</pre>
 * 
 * @author 95分开放平台团队
 * @version 1.0.0
 */
public class JiuWuFenClient {
    
    private static final Logger logger = LoggerFactory.getLogger(JiuWuFenClient.class);
    private static final MediaType JSON = MediaType.get("application/json; charset=utf-8");
    
    @Getter
    private final String erpName;
    @Getter
    private final String thirdPartyId;
    private final String merchantSecret;
    private final String platformSecret;
    @Getter
    private final String baseUrl;
    private final OkHttpClient httpClient;
    private final Gson gson;
    @Getter
    private final boolean debug;
    
    // API 服务
    private final MerchantApi merchantApi;
    private final GoodsApi goodsApi;
    private final InventoryApi inventoryApi;
    private final OrderApi orderApi;
    private final DeliveryApi deliveryApi;
    
    /**
     * 私有构造函数，使用 Builder 模式创建实例
     */
    private JiuWuFenClient(Builder builder) {
        this.erpName = builder.erpName;
        this.thirdPartyId = builder.thirdPartyId;
        this.merchantSecret = builder.merchantSecret;
        this.platformSecret = builder.platformSecret;
        this.baseUrl = builder.baseUrl;
        this.debug = builder.debug;
        
        // 初始化 HTTP 客户端
        OkHttpClient.Builder httpBuilder = new OkHttpClient.Builder()
            .connectTimeout(builder.connectTimeout, TimeUnit.SECONDS)
            .readTimeout(builder.readTimeout, TimeUnit.SECONDS)
            .writeTimeout(builder.writeTimeout, TimeUnit.SECONDS)
            .retryOnConnectionFailure(true);
        
        // 添加日志拦截器
        if (debug) {
            httpBuilder.addInterceptor(new LoggingInterceptor());
        }
        
        this.httpClient = httpBuilder.build();
        
        // 初始化 Gson
        this.gson = new GsonBuilder()
            .setDateFormat("yyyy-MM-dd HH:mm:ss")
            .create();
        
        // 初始化 API 服务
        this.merchantApi = new MerchantApi(this);
        this.goodsApi = new GoodsApi(this);
        this.inventoryApi = new InventoryApi(this);
        this.orderApi = new OrderApi(this);
        this.deliveryApi = new DeliveryApi(this);
    }
    
    /**
     * 获取商户入驻 API
     */
    public MerchantApi merchant() {
        return merchantApi;
    }
    
    /**
     * 获取商品管理 API
     */
    public GoodsApi goods() {
        return goodsApi;
    }
    
    /**
     * 获取库存管理 API
     */
    public InventoryApi inventory() {
        return inventoryApi;
    }
    
    /**
     * 获取订单管理 API
     */
    public OrderApi order() {
        return orderApi;
    }
    
    /**
     * 获取物流管理 API
     */
    public DeliveryApi delivery() {
        return deliveryApi;
    }
    
    /**
     * 执行 HTTP 请求
     * 
     * @param path 请求路径
     * @param requestBody 请求体对象
     * @param responseClass 响应类型
     * @param <T> 响应数据类型
     * @return 响应对象
     * @throws ApiException API 异常
     */
    public <T> T execute(String path, Object requestBody, Class<T> responseClass) throws ApiException {
        try {
            // 构建请求参数
            Map<String, Object> params = objectToMap(requestBody);
            
            // 生成签名
            String signature = SignatureUtil.generateSignature(params, merchantSecret, platformSecret);
            params.put("token", signature);
            
            // 构建请求体
            String jsonBody = gson.toJson(params);
            RequestBody body = RequestBody.create(jsonBody, JSON);
            
            // 构建请求
            String url = baseUrl + path;
            Request request = new Request.Builder()
                .url(url)
                .post(body)
                .addHeader("Content-Type", "application/json; charset=UTF-8")
                .addHeader("x-request-sign-version", "m1")
                .addHeader("fen95-external-third-erp-name", erpName)
                .addHeader("fen95-external-third", thirdPartyId)
                .build();
            
            if (debug) {
                logger.debug("Request URL: {}", url);
                logger.debug("Request Body: {}", jsonBody);
            }
            
            // 执行请求
            try (Response response = httpClient.newCall(request).execute()) {
                String responseBody = response.body() != null ? response.body().string() : "";
                
                if (debug) {
                    logger.debug("Response Code: {}", response.code());
                    logger.debug("Response Body: {}", responseBody);
                }
                
                if (!response.isSuccessful()) {
                    throw new ApiException(-1, "HTTP Error: " + response.code(), "");
                }
                
                // 解析响应
                CommonResponse<T> commonResponse = gson.fromJson(responseBody, 
                    com.google.gson.reflect.TypeToken.getParameterized(
                        CommonResponse.class, responseClass).getType());
                
                // 检查业务状态码
                if (commonResponse.getStatus() != 0) {
                    throw new ApiException(
                        commonResponse.getStatus(),
                        commonResponse.getMsg(),
                        commonResponse.getReqId()
                    );
                }
                
                return commonResponse.getData();
            }
        } catch (IOException e) {
            logger.error("Network error", e);
            throw new ApiException(-1, "Network error: " + e.getMessage(), "");
        } catch (Exception e) {
            logger.error("Unexpected error", e);
            throw new ApiException(-1, "Unexpected error: " + e.getMessage(), "");
        }
    }
    
    /**
     * 将对象转换为 Map
     */
    private Map<String, Object> objectToMap(Object obj) {
        String json = gson.toJson(obj);
        return gson.fromJson(json, new com.google.gson.reflect.TypeToken<Map<String, Object>>(){}.getType());
    }
    
    /**
     * 获取 Gson 实例
     */
    public Gson getGson() {
        return gson;
    }
    
    /**
     * 日志拦截器
     */
    private static class LoggingInterceptor implements Interceptor {
        @Override
        public Response intercept(Chain chain) throws IOException {
            Request request = chain.request();
            
            logger.debug("==> {} {}", request.method(), request.url());
            logger.debug("Headers: {}", request.headers());
            
            long startTime = System.currentTimeMillis();
            Response response = chain.proceed(request);
            long duration = System.currentTimeMillis() - startTime;
            
            logger.debug("<== {} {} ({}ms)", response.code(), request.url(), duration);
            
            return response;
        }
    }
    
    /**
     * 创建 Builder
     */
    public static Builder builder() {
        return new Builder();
    }
    
    /**
     * Builder 类
     */
    public static class Builder {
        private String erpName;
        private String thirdPartyId;
        private String merchantSecret;
        private String platformSecret;
        private String baseUrl = "http://www.95fenapp.com";
        private long connectTimeout = 10;
        private long readTimeout = 30;
        private long writeTimeout = 30;
        private boolean debug = false;
        
        /**
         * 设置 ERP 名称（必填）
         */
        public Builder erpName(String erpName) {
            this.erpName = erpName;
            return this;
        }
        
        /**
         * 设置第三方应用标识（必填）
         */
        public Builder thirdPartyId(String thirdPartyId) {
            this.thirdPartyId = thirdPartyId;
            return this;
        }
        
        /**
         * 设置商家密钥（必填）
         */
        public Builder merchantSecret(String merchantSecret) {
            this.merchantSecret = merchantSecret;
            return this;
        }
        
        /**
         * 设置平台密钥（必填）
         */
        public Builder platformSecret(String platformSecret) {
            this.platformSecret = platformSecret;
            return this;
        }
        
        /**
         * 设置 API 基础 URL
         */
        public Builder baseUrl(String baseUrl) {
            this.baseUrl = baseUrl;
            return this;
        }
        
        /**
         * 设置连接超时时间（秒）
         */
        public Builder connectTimeout(long connectTimeout) {
            this.connectTimeout = connectTimeout;
            return this;
        }
        
        /**
         * 设置读取超时时间（秒）
         */
        public Builder readTimeout(long readTimeout) {
            this.readTimeout = readTimeout;
            return this;
        }
        
        /**
         * 设置写入超时时间（秒）
         */
        public Builder writeTimeout(long writeTimeout) {
            this.writeTimeout = writeTimeout;
            return this;
        }
        
        /**
         * 开启调试模式
         */
        public Builder debug(boolean debug) {
            this.debug = debug;
            return this;
        }
        
        /**
         * 构建客户端实例
         */
        public JiuWuFenClient build() {
            // 参数校验
            if (erpName == null || erpName.trim().isEmpty()) {
                throw new IllegalArgumentException("erpName is required");
            }
            if (thirdPartyId == null || thirdPartyId.trim().isEmpty()) {
                throw new IllegalArgumentException("thirdPartyId is required");
            }
            if (merchantSecret == null || merchantSecret.trim().isEmpty()) {
                throw new IllegalArgumentException("merchantSecret is required");
            }
            if (platformSecret == null || platformSecret.trim().isEmpty()) {
                throw new IllegalArgumentException("platformSecret is required");
            }
            
            return new JiuWuFenClient(this);
        }
    }
}
