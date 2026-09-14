package com.example.ui.steps

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CalendarToday
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.CloudUpload
import androidx.compose.material.icons.filled.CropLandscape
import androidx.compose.material.icons.filled.CropPortrait
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.PauseCircle
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.PlayCircle
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.R
import com.example.model.ContentIdea
import com.example.model.FinalVideo
import com.example.ui.components.ProcessingOverlayCard
import com.example.ui.theme.AccentGreen
import com.example.ui.theme.AccentGreenBg
import com.example.ui.theme.AccentPurple
import com.example.ui.theme.AccentPurpleBg
import com.example.ui.theme.DarkSurface
import com.example.ui.theme.DarkSurfaceBorder
import com.example.ui.theme.DarkSurfaceElevated
import com.example.ui.theme.TextPrimary
import com.example.ui.theme.TextSecondary
import com.example.ui.theme.TextTertiary
import com.example.ui.theme.TubeRed
import com.example.ui.theme.TubeRedDark

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun Step4ReviewPublishScreen(
    finalVideo: FinalVideo?,
    selectedIdea: ContentIdea?,
    isPlayingPreview: Boolean,
    activeSubtitleIndex: Int,
    isProcessing: Boolean,
    processingMessage: String,
    processingProgress: Float,
    channelName: String,
    isVerticalShorts: Boolean,
    onToggleShortsOrientation: () -> Unit,
    onTogglePlay: () -> Unit,
    onUpdateTitle: (String) -> Unit,
    onPublish: () -> Unit,
    modifier: Modifier = Modifier
) {
    var isEditingTitle by remember { mutableStateOf(false) }
    var editedTitle by remember(selectedIdea?.title) { mutableStateOf(selectedIdea?.title ?: "") }

    val infiniteTransition = rememberInfiniteTransition(label = "player_glow")
    val glowAlpha by infiniteTransition.animateFloat(
        initialValue = 0.6f,
        targetValue = 1f,
        animationSpec = infiniteRepeatable(
            animation = tween(600),
            repeatMode = RepeatMode.Reverse
        ),
        label = "glowAlpha"
    )

    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        // Step Banner
        Surface(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(20.dp),
            color = DarkSurface,
            border = androidx.compose.foundation.BorderStroke(1.dp, DarkSurfaceBorder)
        ) {
            Column(modifier = Modifier.padding(18.dp)) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Box(
                        modifier = Modifier
                            .size(36.dp)
                            .clip(CircleShape)
                            .background(TubeRed.copy(alpha = 0.15f)),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            imageVector = Icons.Default.CloudUpload,
                            contentDescription = "Publish",
                            tint = TubeRed,
                            modifier = Modifier.size(20.dp)
                        )
                    }
                    Spacer(modifier = Modifier.width(12.dp))
                    Column {
                        Text(
                            text = "Review & Publish",
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold,
                            color = TextPrimary
                        )
                        Text(
                            text = "Langkah 4 dari 4: Final Quality Check & YouTube API",
                            fontSize = 11.sp,
                            color = TubeRed
                        )
                    }
                }

                Spacer(modifier = Modifier.height(10.dp))
                Text(
                    text = "Video telah selesai diedit. Tinjau hasil akhir dan jadwal publish ke channel YouTube Anda.",
                    fontSize = 13.sp,
                    color = TextSecondary,
                    lineHeight = 18.sp
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Video Player Container Card
        Surface(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(20.dp),
            color = DarkSurface,
            border = androidx.compose.foundation.BorderStroke(1.dp, DarkSurfaceBorder)
        ) {
            Column(modifier = Modifier.padding(14.dp)) {
                // Header with Aspect Ratio Toggle
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 10.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "PLAYER PREVIEW SIMULATOR",
                        fontSize = 10.sp,
                        fontWeight = FontWeight.Bold,
                        color = TextTertiary
                    )

                    Surface(
                        shape = RoundedCornerShape(8.dp),
                        color = DarkSurfaceElevated,
                        border = androidx.compose.foundation.BorderStroke(1.dp, DarkSurfaceBorder),
                        modifier = Modifier.clickable { onToggleShortsOrientation() }
                    ) {
                        Row(
                            modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Icon(
                                imageVector = if (isVerticalShorts) Icons.Default.CropPortrait else Icons.Default.CropLandscape,
                                contentDescription = null,
                                tint = AccentPurple,
                                modifier = Modifier.size(13.dp)
                            )
                            Spacer(modifier = Modifier.width(4.dp))
                            Text(
                                text = if (isVerticalShorts) "9:16 Shorts" else "16:9 Landscape",
                                fontSize = 10.sp,
                                color = TextPrimary
                            )
                        }
                    }
                }

                // Video Screen Area
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .aspectRatio(if (isVerticalShorts) (9f / 12f) else (16f / 9f))
                        .clip(RoundedCornerShape(14.dp))
                        .background(Color.Black)
                        .clickable { onTogglePlay() }
                        .testTag("video_player_box")
                ) {
                    // Thumbnail background
                    Image(
                        painter = painterResource(id = R.drawable.thumb_mystery_space),
                        contentDescription = "Video Frame",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier.matchParentSize()
                    )

                    // Dark gradient scrim
                    Box(
                        modifier = Modifier
                            .matchParentSize()
                            .background(
                                Brush.verticalGradient(
                                    listOf(
                                        Color.Black.copy(alpha = 0.4f),
                                        Color.Transparent,
                                        Color.Black.copy(alpha = 0.75f)
                                    )
                                )
                            )
                    )

                    // Channel Watermark (Top Right)
                    Row(
                        modifier = Modifier
                            .align(Alignment.TopEnd)
                            .padding(10.dp)
                            .clip(RoundedCornerShape(6.dp))
                            .background(Color.Black.copy(alpha = 0.6f))
                            .padding(horizontal = 8.dp, vertical = 4.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Box(
                            modifier = Modifier
                                .size(6.dp)
                                .clip(CircleShape)
                                .background(TubeRed)
                        )
                        Spacer(modifier = Modifier.width(5.dp))
                        Text(
                            text = channelName,
                            fontSize = 10.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.White
                        )
                    }

                    // Center Play / Pause Indicator
                    Box(
                        modifier = Modifier
                            .align(Alignment.Center)
                            .size(56.dp)
                            .clip(CircleShape)
                            .background(Color.Black.copy(alpha = 0.65f))
                            .border(
                                1.5.dp,
                                if (isPlayingPreview) TubeRed.copy(alpha = glowAlpha) else Color.White.copy(alpha = 0.8f),
                                CircleShape
                            ),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            imageVector = if (isPlayingPreview) Icons.Default.PauseCircle else Icons.Default.PlayCircle,
                            contentDescription = if (isPlayingPreview) "Pause" else "Play",
                            tint = Color.White,
                            modifier = Modifier.size(36.dp)
                        )
                    }

                    // Dynamic Subtitle Preview (Karaoke Animated Caption in Center-Bottom)
                    if (finalVideo != null && finalVideo.subtitles.isNotEmpty()) {
                        val currentSub = finalVideo.subtitles.getOrNull(activeSubtitleIndex)?.text
                            ?: finalVideo.subtitles.first().text

                        Box(
                            modifier = Modifier
                                .align(Alignment.BottomCenter)
                                .padding(bottom = 34.dp, start = 16.dp, end = 16.dp)
                                .clip(RoundedCornerShape(8.dp))
                                .background(Color(0xE6000000))
                                .border(1.dp, Color(0xFFFFD700).copy(alpha = 0.6f), RoundedCornerShape(8.dp))
                                .padding(horizontal = 12.dp, vertical = 6.dp)
                        ) {
                            Text(
                                text = currentSub,
                                fontSize = 12.sp,
                                fontWeight = FontWeight.Black,
                                color = Color(0xFFFFD700),
                                textAlign = TextAlign.Center
                            )
                        }
                    }

                    // Duration Badge (Bottom Right)
                    Box(
                        modifier = Modifier
                            .align(Alignment.BottomEnd)
                            .padding(10.dp)
                            .clip(RoundedCornerShape(4.dp))
                            .background(Color.Black.copy(alpha = 0.85f))
                            .padding(horizontal = 6.dp, vertical = 2.dp)
                    ) {
                        Text(
                            text = finalVideo?.duration ?: "00:58",
                            fontSize = 10.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.White
                        )
                    }
                }

                Spacer(modifier = Modifier.height(10.dp))

                // Applied Features Chips
                Text(
                    text = "Fitur Otomatis yang Diterapkan:",
                    fontSize = 11.sp,
                    fontWeight = FontWeight.Bold,
                    color = TextSecondary
                )
                Spacer(modifier = Modifier.height(6.dp))
                FlowRow(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(6.dp),
                    verticalArrangement = Arrangement.spacedBy(6.dp)
                ) {
                    finalVideo?.featuresApplied?.forEach { feat ->
                        Surface(
                            shape = RoundedCornerShape(6.dp),
                            color = DarkSurfaceElevated,
                            border = androidx.compose.foundation.BorderStroke(1.dp, DarkSurfaceBorder)
                        ) {
                            Text(
                                text = feat,
                                fontSize = 10.sp,
                                color = TextSecondary,
                                modifier = Modifier.padding(horizontal = 7.dp, vertical = 3.dp)
                            )
                        }
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Publish Details Card
        Surface(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(20.dp),
            color = DarkSurface,
            border = androidx.compose.foundation.BorderStroke(1.dp, DarkSurfaceBorder)
        ) {
            Column(modifier = Modifier.padding(18.dp)) {
                // Video Title
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Judul Video (Generated by AI)",
                        fontSize = 11.sp,
                        fontWeight = FontWeight.Bold,
                        color = TextSecondary
                    )

                    IconButton(
                        onClick = {
                            if (isEditingTitle) {
                                onUpdateTitle(editedTitle)
                            }
                            isEditingTitle = !isEditingTitle
                        },
                        modifier = Modifier.size(24.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Default.Edit,
                            contentDescription = "Edit Title",
                            tint = AccentPurple,
                            modifier = Modifier.size(15.dp)
                        )
                    }
                }

                Spacer(modifier = Modifier.height(4.dp))

                if (isEditingTitle) {
                    OutlinedTextField(
                        value = editedTitle,
                        onValueChange = { editedTitle = it },
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(10.dp),
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedContainerColor = DarkSurfaceElevated,
                            unfocusedContainerColor = DarkSurfaceElevated,
                            focusedBorderColor = TubeRed,
                            unfocusedBorderColor = DarkSurfaceBorder,
                            focusedTextColor = TextPrimary,
                            unfocusedTextColor = TextPrimary
                        )
                    )
                } else {
                    Surface(
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(10.dp),
                        color = DarkSurfaceElevated,
                        border = androidx.compose.foundation.BorderStroke(1.dp, DarkSurfaceBorder)
                    ) {
                        Text(
                            text = selectedIdea?.title ?: editedTitle,
                            fontSize = 13.sp,
                            fontWeight = FontWeight.SemiBold,
                            color = TextPrimary,
                            modifier = Modifier.padding(12.dp)
                        )
                    }
                }

                Spacer(modifier = Modifier.height(12.dp))

                // Schedule Publish
                Text(
                    text = "Jadwal Publish",
                    fontSize = 11.sp,
                    fontWeight = FontWeight.Bold,
                    color = TextSecondary
                )
                Spacer(modifier = Modifier.height(4.dp))
                Surface(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(10.dp),
                    color = DarkSurfaceElevated,
                    border = androidx.compose.foundation.BorderStroke(1.dp, DarkSurfaceBorder)
                ) {
                    Row(
                        modifier = Modifier.padding(12.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            imageVector = Icons.Default.CalendarToday,
                            contentDescription = null,
                            tint = AccentPurple,
                            modifier = Modifier.size(15.dp)
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = selectedIdea?.schedule ?: "Senin, 18:00 WIB",
                            fontSize = 13.sp,
                            fontWeight = FontWeight.Bold,
                            color = AccentPurple
                        )
                    }
                }

                Spacer(modifier = Modifier.height(12.dp))

                // Status Rendering
                Text(
                    text = "Status Rendering",
                    fontSize = 11.sp,
                    fontWeight = FontWeight.Bold,
                    color = TextSecondary
                )
                Spacer(modifier = Modifier.height(4.dp))
                Surface(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(10.dp),
                    color = AccentGreenBg,
                    border = androidx.compose.foundation.BorderStroke(1.dp, AccentGreen.copy(alpha = 0.4f))
                ) {
                    Row(
                        modifier = Modifier.padding(12.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            imageVector = Icons.Default.CheckCircle,
                            contentDescription = null,
                            tint = AccentGreen,
                            modifier = Modifier.size(16.dp)
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = "1080p 60fps Ready (FFmpeg Verified)",
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Bold,
                            color = AccentGreen
                        )
                    }
                }

                Spacer(modifier = Modifier.height(18.dp))

                // Publish Action Button
                Button(
                    onClick = onPublish,
                    enabled = !isProcessing,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(52.dp)
                        .testTag("publish_to_youtube_btn"),
                    shape = RoundedCornerShape(12.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = TubeRed,
                        disabledContainerColor = DarkSurfaceElevated
                    )
                ) {
                    if (isProcessing) {
                        CircularProgressIndicator(
                            modifier = Modifier.size(18.dp),
                            strokeWidth = 2.dp,
                            color = Color.White
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = "Uploading to YouTube...",
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.White
                        )
                    } else {
                        Icon(
                            imageVector = Icons.Default.CloudUpload,
                            contentDescription = null,
                            modifier = Modifier.size(18.dp)
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = "Publish ke YouTube Sekarang",
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
            }
        }

        // Processing Overlay
        ProcessingOverlayCard(
            isProcessing = isProcessing,
            message = processingMessage,
            progress = processingProgress
        )
    }
}
