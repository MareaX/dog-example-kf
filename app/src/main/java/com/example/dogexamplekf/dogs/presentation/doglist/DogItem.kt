package com.example.dogexamplekf.dogs.presentation.doglist

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import coil3.compose.AsyncImage
import com.example.dogexamplekf.MainScreen
import com.example.dogexamplekf.R
import com.example.dogexamplekf.ui.theme.Dim_0
import com.example.dogexamplekf.ui.theme.Dim_12
import com.example.dogexamplekf.ui.theme.Dim_16
import com.example.dogexamplekf.ui.theme.Dim_24
import com.example.dogexamplekf.ui.theme.Dim_252
import com.example.dogexamplekf.ui.theme.Dim_4
import com.example.dogexamplekf.ui.theme.Dim_8
import com.example.dogexamplekf.ui.theme.Text_12
import com.example.dogexamplekf.ui.theme.Text_18
import com.example.roomlib.entity.DogModel

@Composable
fun DogItem(
    dogItem: DogModel
) {
    ConstraintLayout(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = Dim_16, vertical = Dim_0)
    ) {
        val guideline = createGuidelineFromStart(0.4f)

        val (imageRef, cardRef) = createRefs()

        AsyncImage(
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .constrainAs(imageRef) {
                    top.linkTo(parent.top)
                    bottom.linkTo(parent.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(guideline)
                    width = Dimension.fillToConstraints
                }
                .height(Dim_252)
                .clip(RoundedCornerShape(Dim_12)) ,
            model = dogItem.imageUrl,
            placeholder = painterResource(R.drawable.ic_launcher_foreground),
            error = painterResource(R.drawable.ic_launcher_foreground),
            contentDescription = null,
        )

        Card(
            modifier = Modifier
                .constrainAs(cardRef) {
                    start.linkTo(guideline)
                    end.linkTo(parent.end)
                    bottom.linkTo(imageRef.bottom)
                    width = Dimension.fillToConstraints
                },
            shape = RoundedCornerShape(topEnd = Dim_16, bottomEnd = Dim_16),
            elevation = CardDefaults.cardElevation(defaultElevation = Dim_4),
            colors = CardDefaults.cardColors(containerColor = colorResource(id = R.color.card_color))
        ) {
            ConstraintLayout(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(Dim_16)
            ) {
                val (nameRef, descRef, ageRef) = createRefs()

                Text(
                    text = dogItem.dogName.toString(),
                    fontSize = Text_18,
                    fontWeight = FontWeight.Bold,
                    color = colorResource(id = R.color.primary_text_color),
                    modifier = Modifier.constrainAs(nameRef) {
                        top.linkTo(parent.top)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                        width = Dimension.fillToConstraints
                    }
                )

                Text(
                    text = dogItem.description.toString(),
                    fontSize = Text_12,
                    lineHeight = Text_12,
                            color = colorResource(id = R.color.secondary_text_color),
                    modifier = Modifier.constrainAs(descRef) {
                        top.linkTo(nameRef.bottom, margin = Dim_8)
                        start.linkTo(nameRef.start)
                        end.linkTo(nameRef.end)
                        width = Dimension.fillToConstraints
                    }
                )

                Text(
                    text = stringResource(R.string.lbl_age, dogItem.age),
                    fontSize = Text_12,
                    color = colorResource(id = R.color.primary_text_color),
                    modifier = Modifier.constrainAs(ageRef) {
                        top.linkTo(descRef.bottom, margin = Dim_24)
                        bottom.linkTo(parent.bottom, margin = Dim_16)
                        start.linkTo(descRef.start)
                        end.linkTo(descRef.end)
                        width = Dimension.fillToConstraints
                    }
                )
            }
        }
    }
}

@Preview
@Composable
fun DogItemPreview() {
    MainScreen {
        DogItem(
            DogModel(
                id = 0,
                dogName = "Rex",
                description = "lol sssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssss ",
                age = 2,
                imageUrl = ""
            )
        )
    }
}