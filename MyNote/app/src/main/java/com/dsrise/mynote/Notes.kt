package com.dsrise.mynote

import android.app.Application
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.dsrise.mynote.app.NoteApplication
import com.dsrise.mynote.data.model.Note
//import com.dsrise.mynote.di.component.DaggerNoteKeepingComponent
import com.dsrise.mynote.di.module.NoteServiceModule
import com.dsrise.mynote.di.repository.NoteRepository
import com.dsrise.mynote.di.repository.SqlRepository
import com.dsrise.mynote.di.services.NoteSavingService
import com.dsrise.mynote.ui.theme.MyNoteTheme
import javax.inject.Inject

class MainActivity : ComponentActivity() {

    @Inject
    lateinit var noteSavingService: NoteSavingService

    @Inject
    lateinit var sqlRepository: SqlRepository

    @Inject
    lateinit var sqlRepository1: SqlRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyNoteTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    Greeting("Android")
                }
            }
        }
//       Normal Approach
//        val component = DaggerNoteKeepingComponent.builder()
//            .noteServiceModule(NoteServiceModule(3))
//            .build()
        /**
         * Factory Approach
         */
//        val component = DaggerNoteKeepingComponent.factory().create(3)
        /**
         * Object creation using application level component
         */
//        val appComponent = (application as NoteApplication).appComponent
//        val noteKeepingComponent = DaggerNoteKeepingComponent.factory().create(3,appComponent)

        /**
         * Object creation for subcomponent using appcomponent without exposing its dependencies
         * using method(Factory Methd)
         */
//        val appComponent = (application as NoteApplication).appComponent
//        val noteKeepingComponent = appComponent.getNoteKeepingComponentFactory().create(3)
//
//        noteKeepingComponent.inject(this)

        /**
         * Using Builder Pattern for NoteKeepingComponent Object Creation
         */
        val appComponent = (application as NoteApplication).appComponent
        val noteKeepingComponent =
            appComponent.getNoteKeepingComponentBuilder().retryCount(3).build()

        noteKeepingComponent.inject(this)

        val note = Note(1F, "Today Important", "Get well soon and have hot tea", null)
        noteSavingService.saveNote(note)
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MyNoteTheme {
        Greeting("Android")
    }
}