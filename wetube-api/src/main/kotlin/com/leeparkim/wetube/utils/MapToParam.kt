package com.leeparkim.wetube.utils

fun mapToParam (paramsMap: Map<String, String>): String {
    return paramsMap.map { (key, value) ->
        "$key=$value"
    }.joinToString("&")
}