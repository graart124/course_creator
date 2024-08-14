package com.example.coursecreator.model

import android.os.Parcelable
import com.google.gson.Gson
import com.google.gson.JsonElement
import com.google.gson.JsonObject
import com.google.gson.JsonParseException
import com.google.gson.JsonParser
import com.google.gson.TypeAdapter
import com.google.gson.TypeAdapterFactory
import com.google.gson.reflect.TypeToken
import com.google.gson.stream.JsonReader
import com.google.gson.stream.JsonWriter
import kotlinx.parcelize.Parcelize

@Parcelize
sealed class CourseComponent : Parcelable {
    abstract val name: String
}
