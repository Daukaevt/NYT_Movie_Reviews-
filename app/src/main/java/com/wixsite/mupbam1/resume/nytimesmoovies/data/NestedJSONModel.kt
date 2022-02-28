package com.wixsite.mupbam1.resume.nytimesmoovies.data

data class NestedJSONModel(
    val copyright: String,
    val has_more: Boolean,
    val num_results: Int,
    val results: List<Result>,
    val status: String
)