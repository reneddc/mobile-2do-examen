package com.calyrsoft.frankyapp

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.calyr.data.VocabularyRepository
import com.calyrsoft.frankyapp.ui.theme.AppTheme
import kotlinx.coroutines.launch

class VocabularyActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AppTheme {
                //Vocabulary()
                VocabularySnackBar()
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Vocabulary() {

    val vocabularies = VocabularyRepository().getVocabularies()
    val context = LocalContext.current
    Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
        LazyColumn( modifier = Modifier.padding(innerPadding)) {
            items(vocabularies.size) {
                OutlinedCard(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(5.dp),
                    onClick = {
                        Toast.makeText(context, vocabularies.get(it).description, Toast.LENGTH_LONG).show()
                    }
                ) {
                    Box(
                        modifier = Modifier
                            .height(50.dp)
                            .fillMaxWidth(),
                        contentAlignment = Alignment.TopCenter
                    ) {
                        Text(
                            text = vocabularies.get(it).name
                        )
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun VocabularySnackBar() {
    val vocabularies = VocabularyRepository().getVocabularies()
    val context = LocalContext.current
    val scope = rememberCoroutineScope()
    val snackbarHostState = remember {
        SnackbarHostState()
    }
    Scaffold(
        snackbarHost = {
            SnackbarHost(hostState = snackbarHostState)
        }
    ) {
        it -> LazyColumn(modifier = Modifier.padding(it)) {
            items(vocabularies.size) {
                OutlinedCard(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(5.dp),
                    onClick = {
                        scope.launch {
                            val result = snackbarHostState
                                .showSnackbar(
                                    message = vocabularies.get(it).description,
                                    actionLabel = "Action",
                                    duration = SnackbarDuration.Short
                                )

                            when(result) {
                                SnackbarResult.ActionPerformed -> {
                                    context.startActivity(
                                        Intent(
                                            context,
                                            GitUiActivity::class.java
                                        )
                                    )
                                }
                                SnackbarResult.Dismissed -> {}
                            }
                        }
                    }

                ) {
                    Box(
                        modifier = Modifier
                            .height(50.dp)
                            .fillMaxWidth(),
                        contentAlignment = Alignment.TopCenter
                    ) {
                        Text(text = vocabularies.get(it).name)
                    }
                }
            }
        }
    }
}

//@Preview(showBackground = true)
//@Composable
//fun GreetingPreview() {
//    FrankyAppTheme {
//        Vocabulary()
//    }
//}