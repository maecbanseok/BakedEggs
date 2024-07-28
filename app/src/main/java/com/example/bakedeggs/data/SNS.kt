package com.example.bakedeggs.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
class SNS():Parcelable{
    var instagram = ArrayList<String>()
    var github = ArrayList<String>()
    var discord = ArrayList<String>()
}
