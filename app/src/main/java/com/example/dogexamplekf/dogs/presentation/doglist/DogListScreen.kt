package com.example.dogexamplekf.dogs.presentation.doglist

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.saveable.listSaver
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.runtime.toMutableStateList
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.dogexamplekf.MainScreen
import com.example.dogexamplekf.R
import com.example.dogexamplekf.dogs.presentation.UIState
import com.example.dogexamplekf.ui.components.CircularProgress
import com.example.dogexamplekf.ui.components.ErrorDialog
import com.example.dogexamplekf.ui.theme.Dim_16
import com.example.dogexamplekf.ui.theme.Dim_24
import com.example.dogexamplekf.ui.theme.Dim_64
import com.example.roomlib.entity.DogModel

@Composable
fun DogListScreen(
    viewModel: DogListViewModel  = hiltViewModel()
) {
    val dogList = rememberMutableStateListOf<DogModel>()
    val viewmodelState by viewModel.uiState.observeAsState()

    if (dogList.isEmpty()) {
        viewModel.getDogList()
    }
    MainScreen {
        Scaffold { padding ->
            ConstraintLayout(
                Modifier
                    .padding(padding)
                    .fillMaxWidth()
                    .fillMaxHeight()
                    .background(color = colorResource(id = R.color.background_color))
            ) {
                val (backRef, titleRef, listRef, loaderRef, adError) = createRefs()

                when (val uiState = viewmodelState) {
                    is UIState.Loading -> {
                        CircularProgress(
                            modifier = Modifier
                                .width(Dim_64)
                                .constrainAs(ref = loaderRef) {
                                    top.linkTo(parent.top)
                                    start.linkTo(parent.start)
                                    end.linkTo(parent.end)
                                    bottom.linkTo(parent.bottom)
                                })
                    }

                    is UIState.DogListSuccess -> {
                        dogList.clear()
                        dogList.addAll(uiState.dogList)
                    }

                    is UIState.Error -> {
                        ErrorDialog(modifier = Modifier.constrainAs(ref = adError) {
                            top.linkTo(parent.top)
                            start.linkTo(parent.start)
                            end.linkTo(parent.end)
                            bottom.linkTo(parent.bottom)
                        }, uiState.errorMessage)
                    }

                    else -> Unit
                }

                IconButton(
                    onClick = {},
                    modifier = Modifier
                        .constrainAs(backRef) {
                            top.linkTo(titleRef.top)
                            bottom.linkTo(titleRef.bottom)
                            start.linkTo(parent.start, margin = Dim_16)
                        }
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_back),
                        contentDescription = stringResource(id = R.string.lbl_back_image),
                        tint = Color.Unspecified
                    )
                }

                Text(
                    text = stringResource(R.string.lbl_title),
                    fontSize = 24.sp,
                    color = colorResource(id = R.color.primary_text_color),
                    modifier = Modifier
                        .constrainAs(titleRef) {
                            top.linkTo(parent.top, margin = Dim_16)
                            start.linkTo(parent.start)
                            end.linkTo(parent.end)
                        }
                )


                LazyVerticalGrid(
                    columns = GridCells.Fixed(1),
                    verticalArrangement = Arrangement.spacedBy(Dim_24),
                    horizontalArrangement = Arrangement.spacedBy(Dim_16),
                    modifier = Modifier.constrainAs(ref = listRef) {
                        top.linkTo(titleRef.bottom, margin = Dim_24)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                        bottom.linkTo(parent.bottom, margin = Dim_24)
                        width = Dimension.fillToConstraints
                        height = Dimension.fillToConstraints
                    }
                ) {
                    items(dogList) {
                        DogItem(dogItem = it)
                    }
                }

            }
        }
    }



}

@Composable
fun <T : Any> rememberMutableStateListOf(vararg elements: T): SnapshotStateList<T> {
    return rememberSaveable(saver = snapshotStateListSaver()) {
        elements.toList().toMutableStateList()
    }
}

private fun <T : Any> snapshotStateListSaver() = listSaver<SnapshotStateList<T>, T>(
    save = { stateList -> stateList.toList() },
    restore = { it.toMutableStateList() },
)