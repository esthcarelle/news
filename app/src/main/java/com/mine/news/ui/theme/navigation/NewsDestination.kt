package com.mine.news.ui.theme.navigation

interface NewsDestination {
    val route: String
}
object HeadersList : NewsDestination {
    override val route = "Headers Screen"
}
object HeaderDetail : NewsDestination {
    override val route = "Header Details"
}