package io.henrikhorbovyi.data.error

import io.henrikhorbovyi.data.error.transformers.ErrorTransformer

private val transformers = listOf<ErrorTransformer>()

suspend fun <T> runOrCatch(execution: suspend () -> T): T {
    return try {
        execution()
    } catch (incoming: Throwable) {
        throw transformers.map {
            it.transform(incoming)
        }.reduce { transformed, next ->
            when {
                transformed == next -> transformed
                next == incoming -> next
                else -> incoming
            }
        }
    }
}