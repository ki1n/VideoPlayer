package com.test.videoplayer.data.response

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class BaseResponse<T>(
    @SerializedName("a") @Expose val a: String? = null,
    @SerializedName("task_id") @Expose val taskId: Long? = null,
    @SerializedName("status") @Expose val status: Int? = null,
    @SerializedName("results") @Expose val results: T? = null
)
