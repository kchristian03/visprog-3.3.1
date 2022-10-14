package com.vintech.visprog_331.model

data class Upcoming(
    val dates: Dates,
    val page: Int,
    val results: List<UpcomingResult>,
    val total_pages: Int,
    val total_results: Int
)