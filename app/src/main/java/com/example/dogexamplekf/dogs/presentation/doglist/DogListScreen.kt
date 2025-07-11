package com.example.dogexamplekf.dogs.presentation.doglist

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.saveable.listSaver
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.runtime.toMutableStateList
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.dogexamplekf.MainAppBar
import com.example.dogexamplekf.MainScreen
import com.example.dogexamplekf.R
import com.example.dogexamplekf.dogs.presentation.UIState
import com.example.dogexamplekf.ui.components.CircularProgress
import com.example.dogexamplekf.ui.components.ErrorDialog
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
        Scaffold(
            topBar = { MainAppBar("") }
        ) { padding ->
            ConstraintLayout(
                Modifier
                    .padding(padding)
                    .fillMaxWidth()
                    .fillMaxHeight()
            ) {
                val (circularLoader, lvgDogList, tvTitle, btnBack, adError) = createRefs()

                when (val uiState = viewmodelState) {
                    is UIState.Loading -> {
                        CircularProgress(
                            modifier = Modifier
                                .width(64.dp)
                                .constrainAs(ref = circularLoader) {
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

                LazyVerticalGrid(
                    columns = GridCells.Fixed(1),
                    contentPadding = PaddingValues(dimensionResource(R.dimen.dim_16)),
                    verticalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.dim_16)),
                    horizontalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.dim_16)),
                    modifier = Modifier.constrainAs(ref = lvgDogList) {
                        top.linkTo(parent.top)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                        bottom.linkTo(parent.bottom)
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