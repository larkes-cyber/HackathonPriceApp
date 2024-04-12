package com.larkes.hackathonpriceapp.android.navigation


sealed class Screen(val route:String) {
    object MainScreen: Screen("main_screen")
    object  HistoryScreen:Screen("history_screen")

    fun withArgs(vararg args: String):String{
        return buildString {
            append(route)
            args.forEach { arg ->
                append("/$arg")
            }
        }
    }

}