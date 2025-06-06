/*---------------------------------------------------------------------------------------------
 *  Copyright (c) 2024 Huawei Technologies Co., Ltd. All rights reserved.
 *  This file is a part of the ModelEngine Project.
 *  Licensed under the MIT License. See License.txt in the project root for license information.
 *--------------------------------------------------------------------------------------------*/

package modelengine.fit.http.server.handler.support;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import modelengine.fit.http.server.HttpClassicServerRequest;
import modelengine.fit.http.server.handler.PropertyValueMapper;
import modelengine.fitframework.value.PropertyValue;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Optional;

/**
 * 表示 {@link HttpClassicRequestResolver} 的单元测试。
 *
 * @author 白鹏坤
 * @since 2023-02-15
 */
@DisplayName("测试 HttpClassicRequestResolver 类")
class HttpClassicRequestResolverTest {
    private final HttpClassicRequestResolver resolver = new HttpClassicRequestResolver();
    private final PropertyValue parameter = mock(PropertyValue.class);

    @Test
    @DisplayName("当参数的参数化类型是 HttpClassicServerRequest 时，可以获取到参数映射器")
    void givenParameterIsHttpClassicServerRequestThenReturnParameterMapper() {
        when(this.parameter.getParameterizedType()).thenAnswer(ans -> HttpClassicServerRequest.class);
        final Optional<PropertyValueMapper> resolve = this.resolver.resolve(this.parameter);
        assertThat(resolve).isPresent().get().isInstanceOf(UniqueSourcePropertyValueMapper.class);
    }

    @Test
    @DisplayName("当参数的参数化类型不是 HttpClassicServerRequest 时，返回空 Optional 对象")
    void givenParameterIsNotHttpClassicServerRequestThenReturnEmpty() {
        when(this.parameter.getParameterizedType()).thenAnswer(ans -> String.class);
        final Optional<PropertyValueMapper> resolve = this.resolver.resolve(this.parameter);
        assertThat(resolve).isEmpty();
    }
}
