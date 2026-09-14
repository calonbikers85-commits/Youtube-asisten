package com.example.ui.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
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
import androidx.compose.material.icons.filled.AutoAwesome
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.CloudUpload
import androidx.compose.material.icons.filled.ContentCut
import androidx.compose.material.icons.filled.History
import androidx.compose.material.icons.filled.Image
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.outlined.AccountCircle
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ui.theme.AccentGreen
import com.example.ui.theme.AccentPink
import com.example.ui.theme.AccentPurple
import com.example.ui.theme.DarkSurface
import com.example.ui.theme.DarkSurfaceBorder
import com.example.ui.theme.DarkSurfaceElevated
import com.example.ui.theme.TextPrimary
import com.example.ui.theme.TextSecondary
import com.example.ui.theme.TextTertiary
import com.example.ui.theme.TubeRed
import com.example.ui.theme.TubeRedDark

data class StepInfo(
    val id: Int,
    val title: String,
    val icon: ImageVector,
    val color: Color
)

val APP_STEPS = listOf(
    StepInfo(1, "Ideation", Icons.Default.AutoAwesome, AccentPurple),
    StepInfo(2, "Assets", Icons.Default.Image, Color(0xFF38BDF8)),
    StepInfo(3, "Auto Edit", Icons.Default.ContentCut, AccentPink),
    StepInfo(4, "Publish", Icons.Default.CloudUpload, TubeRed)
)

@Composable
fun TubeAutoHeader(
    channelName: String,
    onOpenHistory: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 12.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Logo
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.testTag("app_branding_header")
        ) {
            Box(
                modifier = Modifier
                    .size(38.dp)
                    .clip(RoundedCornerShape(10.dp))
                    .background(
                        Brush.linearGradient(listOf(TubeRed, TubeRedDark))
                    ),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Default.PlayArrow,
                    contentDescription = "TubeAuto Logo",
                    tint = Color.White,
                    modifier = Modifier.size(24.dp)
                )
            }
            Spacer(modifier = Modifier.width(10.dp))
            Column {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(
                        text = "Tube",
                        fontSize = 19.sp,
                        fontWeight = FontWeight.Black,
                        color = Color.White
                    )
                    Text(
                        text = "Auto",
                        fontSize = 19.sp,
                        fontWeight = FontWeight.Black,
                        color = TubeRed
                    )
                    Box(
                        modifier = Modifier
                            .padding(start = 4.dp)
                            .clip(RoundedCornerShape(4.dp))
                            .background(AccentPurple)
                            .padding(horizontal = 5.dp, vertical = 1.dp)
                    ) {
                        Text(
                            text = "AI",
                            fontSize = 10.sp,
                            fontWeight = FontWeight.ExtraBold,
                            color = Color.White
                        )
                    }
                }
                Text(
                    text = "YouTube Shorts Automation",
                    fontSize = 10.sp,
                    color = TextTertiary
                )
            }
        }

        // Channel Status badge & History action
        Row(verticalAlignment = Alignment.CenterVertically) {
            Surface(
                shape = RoundedCornerShape(20.dp),
                color = DarkSurfaceElevated,
                border = androidx.compose.foundation.BorderStroke(1.dp, DarkSurfaceBorder)
            ) {
                Row(
                    modifier = Modifier.padding(horizontal = 10.dp, vertical = 6.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Box(
                        modifier = Modifier
                            .size(7.dp)
                            .clip(CircleShape)
                            .background(AccentGreen)
                    )
                    Spacer(modifier = Modifier.width(6.dp))
                    Text(
                        text = channelName,
                        fontSize = 11.sp,
                        fontWeight = FontWeight.Medium,
                        color = TextSecondary,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                }
            }

            Spacer(modifier = Modifier.width(8.dp))

            IconButton(
                onClick = onOpenHistory,
                modifier = Modifier
                    .size(38.dp)
                    .clip(CircleShape)
                    .background(DarkSurfaceElevated)
                    .border(1.dp, DarkSurfaceBorder, CircleShape)
                    .testTag("history_button")
            ) {
                Icon(
                    imageVector = Icons.Default.History,
                    contentDescription = "Riwayat Publikasi",
                    tint = TextSecondary,
                    modifier = Modifier.size(18.dp)
                )
            }
        }
    }
}

@Composable
fun StepProgressBar(
    currentStep: Int,
    onStepClick: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    Surface(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 6.dp),
        shape = RoundedCornerShape(16.dp),
        color = DarkSurface,
        border = androidx.compose.foundation.BorderStroke(1.dp, DarkSurfaceBorder)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 12.dp, vertical = 10.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            APP_STEPS.forEachIndexed { index, step ->
                val isCompleted = currentStep > step.id
                val isCurrent = currentStep == step.id
                val isAccessible = step.id <= currentStep

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .clickable(enabled = isAccessible) { onStepClick(step.id) }
                        .padding(horizontal = 4.dp, vertical = 4.dp)
                        .testTag("step_indicator_${step.id}")
                ) {
                    Box(
                        modifier = Modifier
                            .size(28.dp)
                            .clip(CircleShape)
                            .background(
                                when {
                                    isCompleted -> AccentGreen
                                    isCurrent -> step.color
                                    else -> DarkSurfaceElevated
                                }
                            )
                            .border(
                                width = 1.dp,
                                color = if (isCurrent) Color.White.copy(alpha = 0.5f) else Color.Transparent,
                                shape = CircleShape
                            ),
                        contentAlignment = Alignment.Center
                    ) {
                        if (isCompleted) {
                            Icon(
                                imageVector = Icons.Default.Check,
                                contentDescription = "Selesai",
                                tint = Color.Black,
                                modifier = Modifier.size(16.dp)
                            )
                        } else {
                            Icon(
                                imageVector = step.icon,
                                contentDescription = step.title,
                                tint = if (isCurrent) Color.White else TextTertiary,
                                modifier = Modifier.size(15.dp)
                            )
                        }
                    }

                    Spacer(modifier = Modifier.width(6.dp))

                    Text(
                        text = step.title,
                        fontSize = 11.sp,
                        fontWeight = if (isCurrent) FontWeight.Bold else FontWeight.Medium,
                        color = when {
                            isCurrent -> TextPrimary
                            isCompleted -> AccentGreen
                            else -> TextTertiary
                        }
                    )
                }

                // Divider line between steps
                if (index < APP_STEPS.size - 1) {
                    Box(
                        modifier = Modifier
                            .height(2.dp)
                            .width(16.dp)
                            .background(
                                if (currentStep > step.id) AccentGreen.copy(alpha = 0.6f)
                                else DarkSurfaceBorder
                            )
                    )
                }
            }
        }
    }
}

@Composable
fun ProcessingOverlayCard(
    isProcessing: Boolean,
    message: String,
    progress: Float,
    modifier: Modifier = Modifier
) {
    AnimatedVisibility(
        visible = isProcessing,
        enter = fadeIn(),
        exit = fadeOut()
    ) {
        Surface(
            modifier = modifier
                .fillMaxWidth()
                .padding(vertical = 12.dp),
            shape = RoundedCornerShape(16.dp),
            color = DarkSurfaceElevated,
            border = androidx.compose.foundation.BorderStroke(1.dp, AccentPurple.copy(alpha = 0.5f)),
            shadowElevation = 8.dp
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(18.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    CircularProgressIndicator(
                        modifier = Modifier.size(20.dp),
                        strokeWidth = 2.5.dp,
                        color = TubeRed
                    )
                    Spacer(modifier = Modifier.width(10.dp))
                    Text(
                        text = message,
                        fontSize = 13.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = TextPrimary
                    )
                }

                if (progress > 0f) {
                    Spacer(modifier = Modifier.height(12.dp))
                    LinearProgressIndicator(
                        progress = { progress },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(6.dp)
                            .clip(RoundedCornerShape(3.dp)),
                        color = TubeRed,
                        trackColor = DarkSurfaceBorder
                    )
                }
            }
        }
    }
}
