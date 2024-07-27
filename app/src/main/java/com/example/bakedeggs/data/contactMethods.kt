package com.example.bakedeggs.data

import android.content.Context
import android.database.Cursor
import android.provider.BlockedNumberContract
import android.provider.BlockedNumberContract.BlockedNumbers
import android.provider.CallLog
import android.provider.ContactsContract
import androidx.core.net.toUri
import java.text.SimpleDateFormat
import java.util.Date
import java.util.regex.Pattern


fun contactList(context: Context):ArrayList<ContactEntity>{
    val list = ArrayList<ContactEntity>()

    val projection=arrayOf(ContactsContract.CommonDataKinds.Phone.CONTACT_ID, ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME,
    ContactsContract.CommonDataKinds.Phone.NUMBER,ContactsContract.CommonDataKinds.Photo.PHOTO_URI, ContactsContract.Contacts.STARRED)

    val cursor = context.contentResolver.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,projection,null,null,null)
        ?: return list

    while(cursor.moveToNext()){
        val nameidx=cursor.getColumnIndex(projection[1])
        val numberidx=cursor.getColumnIndex(projection[2])
        val photoidx=cursor.getColumnIndex(projection[3])
        val starredidx=cursor.getColumnIndex(projection[4])

        val name=cursor.getString(nameidx)
        val number=cursor.getString(numberidx)
        val photoUri=cursor.getString(photoidx)?.toUri()
        val starred = cursor.getInt(starredidx)

        list+=ContactEntity(name, convertString(name),number,starred,photoUri,null,null)
    }
    return ArrayList(list.sortedWith(compareBy<ContactEntity> {-it.tag}.thenBy { it.name }))
}

fun callHistory(context: Context):ArrayList<CallLogEntity>{
    val logs=ArrayList<CallLogEntity>()
    val callSet= arrayOf(CallLog.Calls.TYPE, CallLog.Calls.NUMBER, CallLog.Calls.DATE, CallLog.Calls.DURATION)
    val cursor = context.contentResolver.query(CallLog.Calls.CONTENT_URI,callSet,null,null,null)
        ?: return logs
    while(cursor.moveToNext()){
        val typeidx=cursor.getColumnIndex(callSet[0])
        val numberidx=cursor.getColumnIndex(callSet[1])
        val dateidx=cursor.getColumnIndex(callSet[2])
        val durationidx=cursor.getColumnIndex(callSet[3])

        val type=if(cursor.getInt(typeidx)==CallLog.Calls.INCOMING_TYPE) "착신" else "발신"
        val number = cursor.getString(numberidx)
        val date=SimpleDateFormat("yyyy-MM-dd").format(Date(cursor.getLong(dateidx)))
        val duration =cursor.getDouble(durationidx).toInt()
        logs+=CallLogEntity(null,type,number,date,duration)
    }
    return logs
}


fun convertString(s:String):String{
    val result=StringBuilder()
    for(i in s){
        if(checkKorean(i)) result.append(getFirstAlphabetKorean(i))
        else result.append(i)
    }
    return result.toString()
}

fun checkKorean(c:Char):Boolean{
    val char=c.toString()
    if(Pattern.matches("^[가-힣]*$",char)) return true
    else return false
}



fun getFirstAlphabetKorean(c:Char):Char{
    val charList = arrayOf('ㄱ', 'ㄲ', 'ㄴ', 'ㄷ', 'ㄸ', 'ㄹ', 'ㅁ', 'ㅂ', 'ㅃ', 'ㅅ', 'ㅆ', 'ㅇ', 'ㅈ', 'ㅉ', 'ㅊ', 'ㅋ', 'ㅌ', 'ㅍ', 'ㅎ')
    return charList[(c.code-44032)/28/21]
}