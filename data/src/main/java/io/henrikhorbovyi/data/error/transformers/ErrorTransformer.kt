package io.henrikhorbovyi.data.error.transformers

interface ErrorTransformer {
    fun transform(incomeException: Throwable): Throwable
}