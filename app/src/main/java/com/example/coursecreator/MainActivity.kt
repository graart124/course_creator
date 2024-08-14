package com.example.coursecreator

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.coursecreator.presentation.audio_lesson.AudioLessonScreen
import com.example.coursecreator.presentation.course.CourseScreen
import com.example.coursecreator.presentation.main.MainScreen
import com.example.coursecreator.presentation.text_lesson.TextLessonScreen
import com.example.coursecreator.ui.theme.CourseCreatorTheme
import com.example.coursecreator.ui.util.Screen
import com.example.coursecreator.ui.util.fromJson

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val viewModel = ViewModelProvider.create(this)[CourseViewModel::class]
        enableEdgeToEdge()
        setContent {
            val navController = rememberNavController()
            val uiState by viewModel.uiState.collectAsState()

            CourseCreatorTheme {
                NavHost(navController = navController, startDestination = Screen.Main.route) {
                    composable(Screen.Main.route) {
                        MainScreen(
                            courses = uiState.courses,
                            onAddClick = {

                            }, onCourseClick = {
                                navController.navigate(Screen.Course.createRoute(it))
                            }
                        )
                    }
                    composable(
                        Screen.Course.route,
                        arguments = listOf(navArgument(Screen.Course.Args) {
                            type = NavType.StringType
                        })
                    ) { backStackEntry ->
                        val courseArgs = backStackEntry.arguments?.getString(Screen.Course.Args)
                            ?.fromJson<Screen.Course.CourseArgs>()
                        courseArgs?.let {
                            CourseScreen(
                                course = it.course,
                                onEditClick = { /* Handle edit course click */ },
                                onBack = {
                                    navController.popBackStack()
                                }, onCourseClick = { course ->
                                    navController.navigate(Screen.Course.createRoute(course))
                                }, onAudioLessonClick = {audioLesson->
                                    navController.navigate(Screen.AudioLesson.createRoute(audioLesson))
                                }, onTextLessonClick = { textLesson ->
                                    navController.navigate(Screen.TextLesson.createRoute(textLesson))
                                }
                            )
                        }
                    }
                    composable(
                        Screen.TextLesson.route,
                        arguments = listOf(navArgument(Screen.TextLesson.Args) {
                            type = NavType.StringType
                        })
                    ) { backStackEntry ->
                        val textLessonArgs =
                            backStackEntry.arguments?.getString(Screen.TextLesson.Args)
                                ?.fromJson<Screen.TextLesson.TextLessonArgs>()
                        textLessonArgs?.let {
                            TextLessonScreen(
                                textLesson = it.textLesson,
                                onBack = {
                                    navController.popBackStack()
                                }
                            )
                        }
                    }
                    composable(
                        Screen.AudioLesson.route,
                        arguments = listOf(navArgument(Screen.AudioLesson.Args) {
                            type = NavType.StringType
                        })
                    ) { backStackEntry ->
                        val audioLessonArgs =
                            backStackEntry.arguments?.getString(Screen.AudioLesson.Args)
                                ?.fromJson<Screen.AudioLesson.AudioLessonArgs>()
                        audioLessonArgs?.let {
                            AudioLessonScreen(
                                viewModel = viewModel,
                                audioLesson = it.audioLesson,
                                onBack = {
                                    navController.popBackStack()
                                }
                            )
                        }
                    }
                }
            }
        }
    }
}