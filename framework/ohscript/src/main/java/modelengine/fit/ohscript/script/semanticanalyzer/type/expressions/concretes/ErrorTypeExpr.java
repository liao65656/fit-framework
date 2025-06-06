/*---------------------------------------------------------------------------------------------
 *  Copyright (c) 2024 Huawei Technologies Co., Ltd. All rights reserved.
 *  This file is a part of the ModelEngine Project.
 *  Licensed under the MIT License. See License.txt in the project root for license information.
 *--------------------------------------------------------------------------------------------*/

package modelengine.fit.ohscript.script.semanticanalyzer.type.expressions.concretes;

import modelengine.fit.ohscript.script.parser.nodes.SyntaxNode;
import modelengine.fit.ohscript.script.parser.nodes.TerminalNode;
import modelengine.fit.ohscript.script.semanticanalyzer.Type;
import modelengine.fit.ohscript.script.semanticanalyzer.type.expressions.base.SimpleTypeExpr;
import modelengine.fit.ohscript.script.semanticanalyzer.type.expressions.base.TypeExpr;

/**
 * 错误类型表达式
 *
 * @since 1.0
 */
public class ErrorTypeExpr extends SimpleTypeExpr {
    /**
     * 通过语法节点构造 {@link ErrorTypeExpr} 的新实例。
     *
     * @param node 表示语法节点的 {@link SyntaxNode}。
     */
    public ErrorTypeExpr(SyntaxNode node) {
        super(node);
    }

    @Override
    public boolean is(TypeExpr expr) {
        return true;
    }

    @Override
    public TypeExpr duplicate(TerminalNode node) {
        return new ErrorTypeExpr(node);
    }

    @Override
    public Type type() {
        return Type.ERROR;
    }
}
