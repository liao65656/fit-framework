/*
 * Copyright (c) 2024-2025 Huawei Technologies Co., Ltd. All rights reserved.
 * This file is a part of the ModelEngine Project.
 * Licensed under the MIT License. See License.txt in the project root for license information.
 */

package modelengine.fitframework.json.schema.support;

import modelengine.fitframework.util.MapBuilder;
import modelengine.fitframework.util.StringUtils;

import java.lang.reflect.Type;
import java.util.Map;

/**
 * 表示 {@link modelengine.fitframework.json.schema.JsonSchema} 的浮点数值实现。
 *
 * @author 季聿阶
 * @since 2024-03-31
 */
public class NumberSchema extends AbstractJsonSchema {
    /**
     * 使用指定的类型初始化 {@link NumberSchema} 的新实例。
     *
     * @param type 表示浮点数值类型的 {@link Type}。
     */
    public NumberSchema(Type type) {
        super(type);
    }

    @Override
    public Map<String, Object> toJsonObject() {
        MapBuilder<String, Object> builder = MapBuilder.<String, Object>get().put("type", "number");
        if (StringUtils.isNotBlank(this.description())) {
            builder.put("description", this.description());
        }
        return builder.build();
    }
}
