package com.faizdev.fundamentalsubmission.response

data class SearchResponse(
    val incomplete_results: Boolean,
    val items: UserResponse,
    val total_count: Int
)