package com.rocket.movielab.data.remote.api

import io.ktor.client.HttpClient
import io.ktor.client.engine.android.Android
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.request.header
import io.ktor.http.HttpHeaders
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import com.rocket.movielab.BuildConfig

object KtorHttpClient {
    private const val NETWORK_TIMEOUT = 15_000L

    val httpClientAndroid by lazy {
        HttpClient(Android) {
            install(ContentNegotiation) {
                json(
                    Json {
                        prettyPrint = true
                        isLenient = true
                        ignoreUnknownKeys = true
                        useAlternativeNames = true
                        explicitNulls = true
                        useArrayPolymorphism = true
                        encodeDefaults = true
                    }
                )
            }

            install(HttpTimeout) {
                socketTimeoutMillis = NETWORK_TIMEOUT
                connectTimeoutMillis = NETWORK_TIMEOUT
                requestTimeoutMillis = NETWORK_TIMEOUT
            }

            install(Logging) {
                level = LogLevel.ALL
            }

            defaultRequest {
                header(
                    HttpHeaders.Authorization, "Bearer ${BuildConfig.API_TOKEN}"
                )
            }
        }
    }
}