package com.jiuwufen.sdk.api;

import com.google.gson.JsonObject;
import com.jiuwufen.sdk.JiuWuFenClient;
import com.jiuwufen.sdk.exception.ApiException;
import com.jiuwufen.sdk.model.digitalproduct.ImeiQueryRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

/**
 * {@link DigitalProductApi} 单元测试（Mock {@link JiuWuFenClient}，不发起真实 HTTP）。
 * <p>
 * 真实 HTTP 见 {@link DigitalProductApiIntegrationTest#imeiQuery_realHttp()}（需环境变量）。
 */
@ExtendWith(MockitoExtension.class)
class DigitalProductApiTest {

    private static final String IMEI_PATH = "/api_tob/imei/v1.0";

    @Mock
    private JiuWuFenClient client;

    private DigitalProductApi digitalProductApi;

    @BeforeEach
    void setUp() {
        digitalProductApi = new DigitalProductApi(client);
    }

    /**
     * Mock 委托路径与返回值；联调请用 {@link DigitalProductApiIntegrationTest}.
     */
    @Test
    void imeiQuery_delegatesToClientWithImeiPathAndReturnsData() throws ApiException {
        ImeiQueryRequest request = new ImeiQueryRequest();
        request.setImei("860000011112223");

        JsonObject expectedData = new JsonObject();
        expectedData.addProperty("status", 1);

        when(client.execute(eq(IMEI_PATH), same(request), eq(JsonObject.class)))
                .thenReturn(expectedData);

        JsonObject result = digitalProductApi.imeiQuery(request);

        assertSame(expectedData, result);
        verify(client, times(1)).execute(IMEI_PATH, request, JsonObject.class);
    }

    @Test
    void imeiQuery_passesRequestBodyThroughToClient() throws ApiException {
        ImeiQueryRequest request = new ImeiQueryRequest();
        request.setImei("test-imei-value");

        when(client.execute(anyString(), any(ImeiQueryRequest.class), eq(JsonObject.class)))
                .thenReturn(new JsonObject());

        digitalProductApi.imeiQuery(request);

        ArgumentCaptor<ImeiQueryRequest> captor = ArgumentCaptor.forClass(ImeiQueryRequest.class);
        verify(client).execute(eq(IMEI_PATH), captor.capture(), eq(JsonObject.class));
        assertEquals("test-imei-value", captor.getValue().getImei());
    }

    @Test
    void imeiQuery_propagatesApiExceptionFromClient() throws ApiException {
        ImeiQueryRequest request = new ImeiQueryRequest();
        request.setImei("x");

        when(client.execute(anyString(), any(), eq(JsonObject.class)))
                .thenThrow(new ApiException(400, "业务错误", "req-1"));

        ApiException ex = assertThrows(ApiException.class, () -> digitalProductApi.imeiQuery(request));
        assertEquals(400, ex.getCode());
        assertEquals("req-1", ex.getReqId());
    }
}
