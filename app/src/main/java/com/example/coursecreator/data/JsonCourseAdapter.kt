package com.example.coursecreator.data

import com.example.coursecreator.model.AudioLesson
import com.example.coursecreator.model.Course
import com.example.coursecreator.model.CourseComponent
import com.example.coursecreator.model.Lesson
import com.example.coursecreator.model.TextLesson
import com.google.gson.JsonArray
import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import com.google.gson.JsonObject
import com.google.gson.JsonParseException
import com.google.gson.JsonSerializationContext
import com.google.gson.JsonSerializer
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type

class JsonCourseAdapter : JsonSerializer<CourseComponent>, JsonDeserializer<CourseComponent> {

    companion object {
        private const val TYPE = "type"
        private const val COURSE = "Course"
        private const val LESSON = "Lesson"
        private const val AUDIO_LESSON = "AudioLesson"
        private const val TEXT_LESSON = "TextLesson"
    }

    override fun serialize(
        src: CourseComponent,
        typeOfSrc: Type,
        context: JsonSerializationContext
    ): JsonElement {
        val jsonObject = JsonObject()
        when (src) {
            is Course -> {
                jsonObject.addProperty(TYPE, COURSE)
                jsonObject.addProperty("name", src.name)
                jsonObject.add(
                    "components",
                    JsonArray().apply {
                        src.components.forEach {
                            add(serialize(it, typeOfSrc, context))
                        }
                    }
                )
            }

            is AudioLesson -> {
                jsonObject.addProperty(TYPE, AUDIO_LESSON)
                jsonObject.addProperty("audio_lesson_name", src.name)
                jsonObject.addProperty("audioRes", src.audioRes)
            }

            is TextLesson -> {
                jsonObject.addProperty(TYPE, TEXT_LESSON)
                jsonObject.addProperty("text_lesson_name", src.name)
                jsonObject.addProperty("content", src.content)
            }

            is Lesson -> {
                jsonObject.addProperty(TYPE, LESSON)
                jsonObject.addProperty("lesson_name", src.name)
            }
        }
        return jsonObject
    }

    override fun deserialize(
        json: JsonElement,
        typeOfT: Type,
        context: JsonDeserializationContext
    ): CourseComponent {
        val jsonObject = json.asJsonObject
        val type = jsonObject.get(TYPE).asString
        return when (type) {
            COURSE -> {
                val name = jsonObject.get("name").asString
                val components = context.deserialize<List<CourseComponent>>(
                    jsonObject.get("components"),
                    object : TypeToken<List<CourseComponent>>() {}.type
                )
                Course(name, components)
            }

            AUDIO_LESSON -> {
                val name = jsonObject.get("audio_lesson_name").asString
                val audioRes = jsonObject.get("audioRes").asInt
                AudioLesson(name, audioRes)
            }

            TEXT_LESSON -> {
                val name = jsonObject.get("text_lesson_name").asString
                val content = jsonObject.get("content").asString
                TextLesson(name, content)
            }

            LESSON -> {
                val name = jsonObject.get("lesson_name").asString
                Lesson(name)
            }

            else -> throw JsonParseException("Unknown type: $type")
        }
    }
}
