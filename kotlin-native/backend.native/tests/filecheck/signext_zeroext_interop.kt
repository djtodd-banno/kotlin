/*
 * Copyright 2010-2021 JetBrains s.r.o. and Kotlin Programming Language contributors.
 * Use of this source code is governed by the Apache 2.0 license that can be found in the license/LICENSE.txt file.
 */
import signext_zeroext_interop_input.*
import kotlinx.cinterop.*

// CHECK: declare zeroext i1 @Kotlin_Char_isHighSurrogate(i16 zeroext)

// Check that we pass attributes to functions imported from runtime.
// CHECK-LABEL: void @"kfun:#checkRuntimeFunctionImport(){}"()
fun checkRuntimeFunctionImport() {
    // CHECK: call zeroext i1 @Kotlin_Char_isHighSurrogate(i16 zeroext {{.*}})
    'c'.isHighSurrogate()
    // CHECK: call zeroext i1 @Kotlin_Float_isNaN(float {{.*}}
    0.0f.isNaN()
}

// CHECK-LABEL: void @"kfun:#checkDirectInterop(){}"()
fun checkDirectInterop() {
    // compiler generates quite lovely names for bridges
    // (e.g. `_66696c65636865636b5f7369676e6578745f7a65726f6578745f696e7465726f70_knbridge0`),
    // so we don't check exact function names here.
    // CHECK: invoke signext i8 {{@_.*_knbridge[0-9]+}}(i8 signext {{.*}})
    char_id(0.toByte())
    // CHECK: invoke zeroext i8 {{@_.*_knbridge[0-9]+}}(i8 zeroext {{.*}})
    unsigned_char_id(0.toUByte())
    // CHECK: invoke signext i16 {{@_.*_knbridge[0-9]+}}(i16 signext {{.*}})
    short_id(0.toShort())
    // CHECK: invoke zeroext i16 {{@_.*_knbridge[0-9]+}}(i16 zeroext {{.*}})
    unsigned_short_id(0.toUShort())
    // CHECK: invoke i32 {{@_.*_knbridge[0-9]+}}(i8* bitcast (i32 ()* {{@_.*_kncfun[0-9]+}} to i8*))
    callbackUser(staticCFunction { int: Int, short: Short -> int + short })
}

// CHECK-LABEL: void @"kfun:#main(){}"()
fun main() {
    checkRuntimeFunctionImport()
    checkDirectInterop()
}