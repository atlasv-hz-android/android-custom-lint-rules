package com.android.example

import kotlinx.serialization.json.Json

class JsonTest {
    fun testDecodeFromString() {
        Json.decodeFromString<TestModel>("")
    }

    fun testDecodeFromString2() {
        val json = Json {}
        json.decodeFromString<TestModel>("")
    }

    fun testEncodeToString(): String {
        return Json.encodeToString(TestModel(""))
    }

    fun testEncodeToString2(): String {
        val json = Json {}
        return json.encodeToString(TestModel(""))
    }

    val s = "lint"
    fun lint() {}
}
