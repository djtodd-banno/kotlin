/*
 * Copyright 2010-2020 JetBrains s.r.o. and Kotlin Programming Language contributors.
 * Use of this source code is governed by the Apache 2.0 license that can be found in the license/LICENSE.txt file.
 */

package org.jetbrains.kotlin.js.test.semantics

import org.jetbrains.kotlin.js.test.BasicBoxTest

abstract class AbstractBoxJsTest : BasicBoxTest(
    TEST_DATA_DIR_PATH + "box/",
    "box/"
) {
    override val runMinifierByDefault: Boolean = true
}

abstract class AbstractIrCodegenWasmJsInteropJsTest : BasicBoxTest(
    "compiler/testData/codegen/wasmJsInterop",
    "codegen/wasmJsInteropWasm"
)
