package com.test.videoplayer.data.response

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class VideoResponse(
    @SerializedName("single") @Expose val single: String? = null,
    @SerializedName("split_v") @Expose val splitV: String? = null,
    @SerializedName("split_h") @Expose val splitH: String? = null,
    @SerializedName("src") @Expose val src: String? = null,
    @SerializedName("preview_image") @Expose val previewImage: String? = null
)
