package ar.com.depietro.tuiter.lib

import com.google.gson.Gson
import com.google.gson.GsonBuilder

class GsonProvider : JsonSerializerProvider {
    private var gson: Gson = GsonBuilder().create()

    override fun get(): Gson {
        return this.gson
    }
}