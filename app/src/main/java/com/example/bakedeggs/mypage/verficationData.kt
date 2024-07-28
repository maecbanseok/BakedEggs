package com.example.bakedeggs.mypage

fun String.verifyEmail(): Boolean {
    val emailPattern =
        Regex("^([\\w-]+(?:\\.[\\w-]+)*)@((?:[\\w-]+\\.)*\\w[\\w-]{0,66})\\.([a-z]{2,6}(?:\\.[a-z]{2})?)\$")
    if(this.isNotEmpty()) return this.matches(emailPattern)
    else return true
}

fun String.verifyPhoneNumber(): Boolean {
    val phonePattern = Regex("^[0-9]{9,11}\$")
    return this.matches(phonePattern)
}

//fun verifyNotEmpty(vararg str: String): Boolean{
//    for(s in str) {
//        if(s.isEmpty()) return false
//    }
//    return true
//}