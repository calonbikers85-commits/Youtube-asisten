package com.example.ui.steps

import androidx.compose.animation.AnimatedVisibility
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
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CalendarToday
import androidx.compose.material.icons.filled.Description
import androidx.compose.material.icons.filled.ExpandLess
import androidx.compose.material.icons.filled.ExpandMore
import androidx.compose.material.icons.filled.Image
import androidx.compose.material.icons.filled.Mic
import androidx.compose.material.icons.filled.Movie
import androidx.compose.material.icons.filled.PlayCircleFilled
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.model.ContentIdea
import com.example.ui.components.ProcessingOverlayCard
import com.example.ui.theme.AccentBlue
import com.example.ui.theme.AccentBlueBg
import com.example.ui.theme.AccentPurple
import com.example.ui.theme.AccentPurpleBg
import com.example.ui.theme.DarkSurface
import com.example.ui.theme.DarkSurfaceBorder
import com.example.ui.theme.DarkSurfaceElevated
import com.example.ui.theme.TextPrimary
import com.example.ui.theme.TextSecondary
import com.example.ui.theme.TextTertiary
import com.example.ui.theme.TubeRed

@Composable
fun Step2AssetSelectionScreen(
    ideas: List<ContentIdea>?,
    selectedIdea: ContentIdea?,
    isProcessing: Boolean,
    processingMessage: String,
    processingProgress: Float,
    onSelectAndGenerateVideo: (ContentIdea) -> Unit,
    modifier: Modifier = Modifier
) {
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
                            .background(AccentBlueBg),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            imageVector = Icons.Default.Movie,
                            contentDescription = "Film",
                            tint = AccentBlue,
                            modifier = Modifier.size(20.dp)
                        )
                    }
                    Spacer(modifier = Modifier.width(12.dp))
                    Column {
                        Text(
                            text = "Pilih Ide Konten",
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold,
                            color = TextPrimary
                        )
                        Text(
                            text = "Langkah 2 dari 4: Kurasi Naskah & Aset",
                            fontSize = 11.sp,
                            color = AccentBlue
                        )
                    }
                }

                Spacer(modifier = Modifier.height(10.dp))
                Text(
                    text = "ChatGPT telah menghasilkan ide dan script. Pilih satu untuk diproses menjadi video oleh AI Video Generator.",
                    fontSize = 13.sp,
                    color = TextSecondary,
                    lineHeight = 18.sp
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Ideas Cards
        if (ideas.isNullOrEmpty()) {
            Surface(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(16.dp),
                color = DarkSurface,
                border = androidx.compose.foundation.BorderStroke(1.dp, DarkSurfaceBorder)
            ) {
                Column(
                    modifier = Modifier.padding(30.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "Belum ada ide yang dihasilkan. Silakan kembali ke Langkah 1.",
                        color = TextSecondary,
                        fontSize = 13.sp
                    )
                }
            }
        } else {
            ideas.forEach { idea ->
                IdeaCard(
                    idea = idea,
                    isSelected = selectedIdea?.id == idea.id,
                    isProcessing = isProcessing,
                    onGenerate = { onSelectAndGenerateVideo(idea) }
                )
                Spacer(modifier = Modifier.height(14.dp))
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

@OptIn(ExperimentalLayoutApi::class)
@Composable
private fun IdeaCard(
    idea: ContentIdea,
    isSelected: Boolean,
    isProcessing: Boolean,
    onGenerate: () -> Unit
) {
    var isExpanded by remember { mutableStateOf(false) }

    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .testTag("idea_card_${idea.id}"),
        shape = RoundedCornerShape(18.dp),
        color = DarkSurface,
        border = androidx.compose.foundation.BorderStroke(
            width = if (isSelected) 1.5.dp else 1.dp,
            color = if (isSelected) TubeRed else DarkSurfaceBorder
        )
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            // Header with Schedule & Tags
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Surface(
                    shape = RoundedCornerShape(20.dp),
                    color = AccentPurpleBg,
                    border = androidx.compose.foundation.BorderStroke(1.dp, AccentPurple.copy(alpha = 0.4f))
                ) {
                    Row(
                        modifier = Modifier.padding(horizontal = 9.dp, vertical = 4.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            imageVector = Icons.Default.CalendarToday,
                            contentDescription = "Schedule",
                            tint = AccentPurple,
                            modifier = Modifier.size(11.dp)
                        )
                        Spacer(modifier = Modifier.width(5.dp))
                        Text(
                            text = idea.schedule,
                            fontSize = 11.sp,
                            fontWeight = FontWeight.Bold,
                            color = AccentPurple
                        )
                    }
                }

                Text(
                    text = "Otomatisasi YouTube Shorts",
                    fontSize = 10.sp,
                    color = TextTertiary
                )
            }

            Spacer(modifier = Modifier.height(10.dp))

            // Title
            Text(
                text = idea.title,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                color = TextPrimary,
                lineHeight = 22.sp
            )

            Spacer(modifier = Modifier.height(10.dp))

            // Hook / Intro Section
            Surface(
                shape = RoundedCornerShape(10.dp),
                color = DarkSurfaceElevated,
                border = androidx.compose.foundation.BorderStroke(1.dp, DarkSurfaceBorder)
            ) {
                Column(modifier = Modifier.padding(10.dp)) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            imageVector = Icons.Default.Mic,
                            contentDescription = "Hook",
                            tint = Color(0xFFF59E0B),
                            modifier = Modifier.size(13.dp)
                        )
                        Spacer(modifier = Modifier.width(5.dp))
                        Text(
                            text = "HOOK / INTRO 3 DETIK PERTAMA",
                            fontSize = 9.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color(0xFFF59E0B)
                        )
                    }
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = "\"${idea.hook}\"",
                        fontSize = 12.sp,
                        fontStyle = FontStyle.Italic,
                        color = Color(0xFFE5E7EB)
                    )
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            // Script Snippet (Expandable)
            Surface(
                shape = RoundedCornerShape(10.dp),
                color = DarkSurfaceElevated,
                border = androidx.compose.foundation.BorderStroke(1.dp, DarkSurfaceBorder),
                modifier = Modifier.clickable { isExpanded = !isExpanded }
            ) {
                Column(modifier = Modifier.padding(10.dp)) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(
                                imageVector = Icons.Default.Description,
                                contentDescription = "Script",
                                tint = AccentBlue,
                                modifier = Modifier.size(13.dp)
                            )
                            Spacer(modifier = Modifier.width(5.dp))
                            Text(
                                text = "SCRIPT SNIPPET",
                                fontSize = 9.sp,
                                fontWeight = FontWeight.Bold,
                                color = AccentBlue
                            )
                        }

                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Text(
                                text = if (isExpanded) "Tutup" else "Baca Semua",
                                fontSize = 10.sp,
                                color = TextTertiary
                            )
                            Icon(
                                imageVector = if (isExpanded) Icons.Default.ExpandLess else Icons.Default.ExpandMore,
                                contentDescription = null,
                                tint = TextTertiary,
                                modifier = Modifier.size(14.dp)
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(4.dp))

                    Text(
                        text = idea.script,
                        fontSize = 12.sp,
                        color = TextSecondary,
                        maxLines = if (isExpanded) Int.MAX_VALUE else 2,
                        lineHeight = 17.sp
                    )
                }
            }

            // Visual prompts chips
            AnimatedVisibility(visible = isExpanded) {
                Column(modifier = Modifier.padding(top = 8.dp)) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            imageVector = Icons.Default.Image,
                            contentDescription = "Prompts",
                            tint = AccentPurple,
                            modifier = Modifier.size(13.dp)
                        )
                        Spacer(modifier = Modifier.width(5.dp))
                        Text(
                            text = "AI IMAGE PROMPTS (MIDJOURNEY STYLE)",
                            fontSize = 9.sp,
                            fontWeight = FontWeight.Bold,
                            color = AccentPurple
                        )
                    }
                    Spacer(modifier = Modifier.height(4.dp))
                    idea.prompts.forEach { prompt ->
                        Text(
                            text = "• $prompt",
                            fontSize = 10.sp,
                            color = TextTertiary,
                            modifier = Modifier.padding(vertical = 2.dp)
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(14.dp))

            // Action Button
            Button(
                onClick = onGenerate,
                enabled = !isProcessing,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(48.dp)
                    .testTag("generate_video_btn_${idea.id}"),
                shape = RoundedCornerShape(10.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF2563EB),
                    disabledContainerColor = DarkSurfaceElevated
                )
            ) {
                if (isProcessing && isSelected) {
                    CircularProgressIndicator(
                        modifier = Modifier.size(16.dp),
                        strokeWidth = 2.dp,
                        color = Color.White
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = "Generating Assets...",
                        fontSize = 13.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = Color.White
                    )
                } else {
                    Icon(
                        imageVector = Icons.Default.PlayCircleFilled,
                        contentDescription = null,
                        modifier = Modifier.size(18.dp)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = "Generate Video & Audio",
                        fontSize = 13.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }
    }
}
