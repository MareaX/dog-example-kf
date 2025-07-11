package com.example.dogexamplekf.dogs.presentation.doglist

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import com.example.dogexamplekf.R
import com.example.roomlib.entity.DogModel

@Composable
fun DogItem(
    dogItem: DogModel, modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .heightIn(min = 200.dp)
            .shadow(
                1.dp,
                shape = RoundedCornerShape(dimensionResource(R.dimen.dim_8))
            )
    ) {

        val density = LocalDensity.current.density
        var padding by remember { mutableStateOf(0.dp) }

        AsyncImage(
            contentScale = ContentScale.None,
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    start = dimensionResource(R.dimen.dim_12),
                    dimensionResource(R.dimen.dim_12),
                    dimensionResource(R.dimen.dim_12)
                )
                .aspectRatio(1f)
                .clip(RoundedCornerShape(dimensionResource(R.dimen.dim_8))),
            model = dogItem.imageUrl,
            placeholder = painterResource(R.drawable.ic_launcher_foreground),
            error = painterResource(R.drawable.ic_launcher_foreground),
            contentDescription = null
        )

        Spacer(modifier = Modifier.height(dimensionResource(R.dimen.dim_4)))
        Text(
            modifier = Modifier.padding(start = 8.dp, end = 8.dp, bottom = padding),
            text =  dogItem.dogName ?: "Not Found",
            fontSize = 12.sp,
            onTextLayout = {
                val lineCount = it.lineCount
                val height = (it.size.height / density).dp
                padding = if (lineCount > 1) 0.dp else height
            }
        )
    }
}