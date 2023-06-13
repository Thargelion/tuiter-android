package ar.com.depietro.tuiter.ui.screens

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.compose.collectAsLazyPagingItems
import ar.com.depietro.tuiter.ui.components.LoginUser
import ar.com.depietro.tuiter.ui.components.TuitFeed
import ar.com.depietro.tuiter.ui.theme.TuiterTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MainScreen()
        }
    }

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun MainScreen(mainViewModel: MainViewModel = hiltViewModel()) {
        val uiState: MainViewUIState by mainViewModel.uiState.collectAsState()
        val scope = rememberCoroutineScope()
        val snackbarHostState = remember { SnackbarHostState() }
        TuiterTheme {
            // A surface container using the 'background' color from the theme

            Scaffold(snackbarHost = {
                SnackbarHost(hostState = snackbarHostState)
            }) { contentPadding ->
                Surface(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(contentPadding),
                    color = MaterialTheme.colorScheme.background
                ) {
                    when (val userState = uiState.userState) {
                        is UserUIState.Error -> {
                            Box {
                                LoginUser(
                                    modifier = Modifier
                                        .padding(16.dp),
                                    onClickAction = { userName ->
                                        mainViewModel.createUser(userName)
                                    }
                                )
                            }
                            LaunchedEffect(snackbarHostState) {
                                // Show snackbar using a coroutine, when the coroutine is cancelled the
                                // snackbar will automatically dismiss. This coroutine will cancel whenever
                                // `state.hasError` is false, and only start when `state.hasError` is true
                                // (due to the above if-check), or if `scaffoldState.snackbarHostState` changes.
                                snackbarHostState.showSnackbar(
                                    message = userState.message,
                                    actionLabel = "Retry message"
                                )
                            }
                        }

                        UserUIState.Loading -> {
                            LaunchedEffect(snackbarHostState) {
                                // Show snackbar using a coroutine, when the coroutine is cancelled the
                                // snackbar will automatically dismiss. This coroutine will cancel whenever
                                // `state.hasError` is false, and only start when `state.hasError` is true
                                // (due to the above if-check), or if `scaffoldState.snackbarHostState` changes.
                                snackbarHostState.showSnackbar(
                                    message = "Loading",
                                )
                            }
                        }

                        is UserUIState.Success -> {
                            var userTuits = mainViewModel.getUserPosts().collectAsLazyPagingItems()
                            TuitFeed(
                                tuitList = userTuits,
                                likeAction = mainViewModel::likeTuit,
                                listState = mainViewModel.listState,
                            )
                        }

                    }
                }
            }
        }
    }

    @Composable
    fun LoadingIndicator(modifier: Modifier = Modifier) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = modifier
                .fillMaxSize()
        ) {
            CircularProgressIndicator(color = Color.LightGray)
        }
    }
}
