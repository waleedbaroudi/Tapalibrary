package com.waroudi.tapalibrary.data.models.api

import com.waroudi.tapalibrary.data.models.error.TapaServiceError

data class TapaResult<D>(val data: D?, val error: TapaServiceError? = null)
