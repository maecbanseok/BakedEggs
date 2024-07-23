package com.example.bakedeggs.data

import android.content.Context
import android.provider.ContactsContract
import androidx.core.net.toUri
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

        list+=ContactEntity(name, convertString(name),number,starred,photoUri,null,null,null)
    }
    return list.sortBy{it.name} as ArrayList<ContactEntity>
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
    if(Pattern.matches("^[ㄱ-ㅎ가-힣]*$",char)) return true
    else return false
}

fun getFirstAlphabetKorean(c:Char):Char{
    return ((c.digitToInt()-0xAC00)/28/21).toChar()
}
