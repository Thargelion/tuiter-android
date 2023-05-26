package ar.com.depietro.tuiter.lib

import com.google.gson.Gson


interface JsonSerializerProvider {
    fun get(): Gson
}
