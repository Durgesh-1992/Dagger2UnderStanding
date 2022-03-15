package com.dsrise.mynote.ui.components

import android.text.Layout
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.dsrise.mynote.R
import com.dsrise.mynote.data.model.Note

@Composable
fun NoteCard(note: Note, onClick: () -> Unit) {
    /**
     * Card composable is equivalent to CardView widget of android
     */
    Card(
        shape = MaterialTheme.shapes.small,
        /**
         * Modifier is used for formatting the component like(adding margin, padding, color,etc
         */
        modifier = Modifier
            .padding(
                top = 5.dp,
                bottom = 5.dp
            )
            .fillMaxWidth()
            .clickable(onClick = onClick),
        elevation = 7.dp

    ) {
        /**
         * Column is used to represent/list the component vertically from top
         * to bottom one below the above
         */
        Column {
            /**
             * Row display the element/component horizontally i.e from left
             * to right
             */
            note?.contentImage?.let { byteArray ->
//                Image(
//                    bitmap = painterResource(R.drawable.note),
//                    modifier = Modifier
//                        .fillMaxWidth(),
//                    contentScale = ContentScale.Crop
//                )
                Row(
                    modifier = Modifier.padding(
                        top = 12.dp,
                        bottom = 12.dp,
                        start = 8.dp,
                        end = 8.dp
                    )
                )
                {
                    note?.title?.let { title ->
                        Text(
                            text = title,
                            modifier = Modifier
                                .fillMaxWidth(0.15f)
                                .wrapContentWidth(Alignment.Start),
                            style = MaterialTheme.typography.h5
                        )
                    }
                    note?.content?.let { content ->
                        Text(
                            text = content,
                            modifier = Modifier
                                .fillMaxWidth()
                                .wrapContentWidth(Alignment.End)
                                .align(Alignment.CenterVertically),
                            style = MaterialTheme.typography.h6
                        )
                    }

                }

            }


        }
    }
}