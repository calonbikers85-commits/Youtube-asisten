package com.example

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.ui.components.StepProgressBar
import com.example.ui.components.TubeAutoHeader
import com.example.ui.dialogs.HistoryBottomSheet
import com.example.ui.steps.Step1IdeationScreen
import com.example.ui.steps.Step2AssetSelectionScreen
import com.example.ui.steps.Step3AutoEditingScreen
import com.example.ui.steps.Step4ReviewPublishScreen
import com.example.ui.steps.Step5SuccessScreen
import com.example.ui.theme.DarkBackground
import com.example.ui.theme.MyApplicationTheme
import com.example.viewmodel.TubeAutoViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyApplicationTheme {
                TubeAutoApp()
            }
        }
    }
}

@Composable
fun TubeAutoApp(
    viewModel: TubeAutoViewModel = viewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    var showHistorySheet by remember { mutableStateOf(false) }
    val scrollState = rememberScrollState()

    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .testTag("tube_auto_scaffold"),
        contentWindowInsets = WindowInsets.safeDrawing,
        containerColor = DarkBackground
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .background(DarkBackground)
        ) {
            // Header with YouTube branding & channel
            TubeAutoHeader(
                channelName = uiState.channelName,
                onOpenHistory = { showHistorySheet = true }
            )

            // Step Indicator (Steps 1 to 4)
            if (uiState.currentStep <= 4) {
                StepProgressBar(
                    currentStep = uiState.currentStep,
                    onStepClick = { step -> viewModel.setStep(step) }
                )
            }

            // Scrollable Content for current Step
            Box(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth()
                    .verticalScroll(scrollState)
            ) {
                AnimatedContent(
                    targetState = uiState.currentStep,
                    transitionSpec = { fadeIn() togetherWith fadeOut() },
                    label = "step_transition"
                ) { step ->
                    when (step) {
                        1 -> Step1IdeationScreen(
                            topic = uiState.topic,
                            onTopicChange = { viewModel.onTopicChange(it) },
                            suggestedTopics = viewModel.suggestedTopics,
                            onSelectSuggested = { viewModel.selectSuggestedTopic(it) },
                            isProcessing = uiState.isProcessing,
                            processingMessage = uiState.processingMessage,
                            processingProgress = uiState.processingProgress,
                            onGenerateIdeas = { viewModel.handleGenerateIdeas() }
                        )

                        2 -> Step2AssetSelectionScreen(
                            ideas = uiState.generatedIdeas,
                            selectedIdea = uiState.selectedIdea,
                            isProcessing = uiState.isProcessing,
                            processingMessage = uiState.processingMessage,
                            processingProgress = uiState.processingProgress,
                            onSelectAndGenerateVideo = { idea ->
                                viewModel.handleGenerateVideo(idea)
                            }
                        )

                        3 -> Step3AutoEditingScreen(
                            videoAssets = uiState.videoAssets,
                            isProcessing = uiState.isProcessing,
                            processingMessage = uiState.processingMessage,
                            processingProgress = uiState.processingProgress,
                            channelName = uiState.channelName,
                            onStartEditing = { viewModel.handleEditVideo() }
                        )

                        4 -> Step4ReviewPublishScreen(
                            finalVideo = uiState.finalVideo,
                            selectedIdea = uiState.selectedIdea,
                            isPlayingPreview = uiState.isPlayingPreview,
                            activeSubtitleIndex = uiState.activeSubtitleIndex,
                            isProcessing = uiState.isProcessing,
                            processingMessage = uiState.processingMessage,
                            processingProgress = uiState.processingProgress,
                            channelName = uiState.channelName,
                            isVerticalShorts = uiState.isVerticalShorts,
                            onToggleShortsOrientation = { viewModel.toggleShortsOrientation() },
                            onTogglePlay = { viewModel.togglePlayback() },
                            onUpdateTitle = { viewModel.updateVideoTitle(it) },
                            onPublish = { viewModel.handleUploadYouTube() }
                        )

                        5 -> Step5SuccessScreen(
                            selectedIdea = uiState.selectedIdea,
                            channelName = uiState.channelName,
                            onReset = { viewModel.resetToNew() },
                            onViewHistory = { showHistorySheet = true }
                        )
                    }
                }

                Spacer(
                    modifier = Modifier
                        .height(24.dp)
                        .windowInsetsPadding(WindowInsets.navigationBars)
                )
            }
        }

        // History Bottom Sheet
        if (showHistorySheet) {
            HistoryBottomSheet(
                historyList = uiState.publishedHistory,
                channelName = uiState.channelName,
                onDismiss = { showHistorySheet = false }
            )
        }
    }
}

// Kept for backward compatibility with GreetingScreenshotTest
@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(text = "Hello $name!", modifier = modifier)
}

@Preview(showBackground = true)
@Composable
fun TubeAutoPreview() {
    MyApplicationTheme {
        TubeAutoApp()
    }
}
