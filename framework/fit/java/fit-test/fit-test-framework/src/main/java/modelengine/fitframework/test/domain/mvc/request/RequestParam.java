/*
 * Copyright (c) 2024-2025 Huawei Technologies Co., Ltd. All rights reserved.
 * This file is a part of the ModelEngine Project.
 * Licensed under the MIT License. See License.txt in the project root for license information.
 */

package modelengine.fitframework.test.domain.mvc.request;

import modelengine.fit.http.client.HttpClassicClientRequest;

import java.lang.reflect.Type;

/**
 * 封装客户端的请求参数。
 *
 * @author 王攀博
 * @since 2024-04-09
 */
public class RequestParam {
    private final Type responseType;
    private final HttpClassicClientRequest request;

    /**
     * 使用指定的响应类型和 HTTP 请求初始化 {@link RequestParam} 的新实例。
     *
     * @param responseType 表示响应类型的 {@link Type}。
     * @param request 表示 HTTP 请求的 {@link HttpClassicClientRequest}。
     */
    public RequestParam(Type responseType, HttpClassicClientRequest request) {
        this.responseType = responseType;
        this.request = request;
    }

    /**
     * 获取请求结构体中的返回结果类型。
     *
     * @return 表示返回结果的类型 {@link Type}。
     */
    public Type getResponseType() {
        return this.responseType;
    }

    /**
     * 获取请求结构体中的返回结果类型。
     *
     * @return 表示返回结果的类型 {@link Type}。
     */
    public HttpClassicClientRequest getRequest() {
        return this.request;
    }
}
