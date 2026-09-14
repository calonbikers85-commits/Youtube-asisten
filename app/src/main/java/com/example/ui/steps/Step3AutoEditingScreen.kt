package com.example.ui.steps

import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
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
import androidx.compose.material.icons.filled.Audiotrack
import androidx.compose.material.icons.filled.AutoAwesome
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.ContentCut
import androidx.compose.material.icons.filled.GraphicEq
import androidx.compose.material.icons.filled.Image
import androidx.compose.material.icons.filled.Mic
import androidx.compose.material.icons.filled.Palette
import androidx.compose.material.icons.filled.Subtitles
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.R
import com.example.model.VideoAssets
import com.example.ui.theme.AccentGreen
import com.example.ui.theme.AccentPink
import com.example.ui.theme.AccentPinkBg
import com.example.ui.theme.AccentPurple
import com.example.ui.theme.DarkSurface
import com.example.ui.theme.DarkSurfaceBorder
import com.example.ui.theme.DarkSurfaceElevated
import com.example.ui.theme.TextPrimary
import com.example.ui.theme.TextSecondary
import com.example.ui.theme.TextTertiary

@Composable
fun Step3AutoEditingScreen(
    videoAssets: VideoAssets?,
    isProcessing: Boolean,
    processingMessage: String,
    processingProgress: Float,
    channelName: String,
    onStartEditing: () -> Unit,
    modifier: Modifier = Modifier
) {
    val infiniteTransition = rememberInfiniteTransition(label = "pulse")
    val pulseAlpha by infiniteTransition.animateFloat(
        initialValue = 0.5f,
        targetValue = 1.0f,
        animationSpec = infiniteRepeatable(
            animation = tween(800),
            repeatMode = RepeatMode.Reverse
        ),
        label = "pulseAlpha"
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
                            .background(AccentPinkBg),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            imageVector = Icons.Default.ContentCut,
                            contentDescription = "Edit",
                            tint = AccentPink,
                            modifier = Modifier.size(20.dp)
                        )
                    }
                    Spacer(modifier = Modifier.width(12.dp))
                    Column {
                        Text(
                            text = "Auto Editing Process",
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold,
                            color = TextPrimary
                        )
                        Text(
                            text = "Langkah 3 dari 4: FFmpeg & CapCut AI Engine",
                            fontSize = 11.sp,
                            color = AccentPink
                        )
                    }
                }

                Spacer(modifier = Modifier.height(10.dp))
                Text(
                    text = "Menyatukan visual, Voice Over AI, BGM, Subtitle Dinamis, dan Watermark (Alternatif CapCut otomatis).",
                    fontSize = 13.sp,
                    color = TextSecondary,
                    lineHeight = 18.sp
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Assets Showcase Card
        Surface(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(20.dp),
            color = DarkSurface,
            border = androidx.compose.foundation.BorderStroke(1.dp, DarkSurfaceBorder)
        ) {
            Column(
                modifier = Modifier.padding(18.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "ASET KONTEN YANG DISIAPKAN",
                    fontSize = 11.sp,
                    fontWeight = FontWeight.Bold,
                    color = TextTertiary,
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(12.dp))

                // 2x2 Asset Grid
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    // Image Asset 1
                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .aspectRatio(16f / 9f)
                            .clip(RoundedCornerShape(12.dp))
                            .border(1.dp, DarkSurfaceBorder, RoundedCornerShape(12.dp))
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.thumb_mystery_space),
                            contentDescription = "Scene 1 Asset",
                            contentScale = ContentScale.Crop,
                            modifier = Modifier.matchParentSize()
                        )
                        Box(
                            modifier = Modifier
                                .align(Alignment.BottomStart)
                                .background(Color.Black.copy(alpha = 0.7f))
                                .padding(horizontal = 6.dp, vertical = 2.dp)
                        ) {
                            Text("Scene 1.png", fontSize = 9.sp, color = Color.White)
                        }
                    }

                    // Image Asset 2
                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .aspectRatio(16f / 9f)
                            .clip(RoundedCornerShape(12.dp))
                            .border(1.dp, DarkSurfaceBorder, RoundedCornerShape(12.dp))
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.thumb_vintage_cyber),
                            contentDescription = "Scene 2 Asset",
                            contentScale = ContentScale.Crop,
                            modifier = Modifier.matchParentSize()
                        )
                        Box(
                            modifier = Modifier
                                .align(Alignment.BottomStart)
                                .background(Color.Black.copy(alpha = 0.7f))
                                .padding(horizontal = 6.dp, vertical = 2.dp)
                        ) {
                            Text("Scene 2.png", fontSize = 9.sp, color = Color.White)
                        }
                    }
                }

                Spacer(modifier = Modifier.height(10.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    // Audio Asset
                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .height(72.dp)
                            .clip(RoundedCornerShape(12.dp))
                            .background(DarkSurfaceElevated)
                            .border(1.dp, DarkSurfaceBorder, RoundedCornerShape(12.dp)),
                        contentAlignment = Alignment.Center
                    ) {
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            Icon(
                                imageVector = Icons.Default.Mic,
                                contentDescription = "TTS Audio",
                                tint = AccentPink,
                                modifier = Modifier.size(20.dp)
                            )
                            Spacer(modifier = Modifier.height(4.dp))
                            Text(
                                text = "TTS_Audio.mp3",
                                fontSize = 11.sp,
                                fontWeight = FontWeight.SemiBold,
                                color = TextPrimary
                            )
                            Text(
                                text = "Indonesian Neural Voice",
                                fontSize = 9.sp,
                                color = TextTertiary
                            )
                        }
                    }

                    // Watermark / Overlay Asset
                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .height(72.dp)
                            .clip(RoundedCornerShape(12.dp))
                            .background(DarkSurfaceElevated)
                            .border(1.dp, DarkSurfaceBorder, RoundedCornerShape(12.dp)),
                        contentAlignment = Alignment.Center
                    ) {
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            Icon(
                                imageVector = Icons.Default.Image,
                                contentDescription = "Watermark",
                                tint = AccentPurple,
                                modifier = Modifier.size(20.dp)
                            )
                            Spacer(modifier = Modifier.height(4.dp))
                            Text(
                                text = "Overlay_Watermark.png",
                                fontSize = 11.sp,
                                fontWeight = FontWeight.SemiBold,
                                color = TextPrimary
                            )
                            Text(
                                text = channelName,
                                fontSize = 9.sp,
                                color = AccentGreen
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.height(20.dp))

                // Action area
                if (!isProcessing) {
                    Button(
                        onClick = onStartEditing,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(54.dp)
                            .testTag("start_rendering_button"),
                        shape = RoundedCornerShape(14.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = AccentPink
                        )
                    ) {
                        Icon(
                            imageVector = Icons.Default.ContentCut,
                            contentDescription = null,
                            modifier = Modifier.size(20.dp)
                        )
                        Spacer(modifier = Modifier.width(10.dp))
                        Text(
                            text = "Mulai Proses Rendering Video",
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
                } else {
                    // Processing / Rendering Active State
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clip(RoundedCornerShape(14.dp))
                            .background(DarkSurfaceElevated)
                            .border(1.dp, AccentPink.copy(alpha = 0.5f), RoundedCornerShape(14.dp))
                            .padding(18.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        CircularProgressIndicator(
                            modifier = Modifier.size(38.dp),
                            color = AccentPink,
                            strokeWidth = 3.5.dp
                        )
                        Spacer(modifier = Modifier.height(12.dp))
                        Text(
                            text = processingMessage,
                            fontSize = 13.sp,
                            fontWeight = FontWeight.Bold,
                            color = TextPrimary
                        )

                        Spacer(modifier = Modifier.height(10.dp))

                        LinearProgressIndicator(
                            progress = { processingProgress },
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(8.dp)
                                .clip(RoundedCornerShape(4.dp)),
                            color = AccentPink,
                            trackColor = DarkSurfaceBorder
                        )

                        Spacer(modifier = Modifier.height(12.dp))

                        // Features active indicators
                        Row(
                            horizontalArrangement = Arrangement.spacedBy(10.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Icon(
                                    imageVector = Icons.Default.Subtitles,
                                    contentDescription = null,
                                    tint = AccentPink.copy(alpha = pulseAlpha),
                                    modifier = Modifier.size(13.dp)
                                )
                                Spacer(modifier = Modifier.width(4.dp))
                                Text("Subtitle Bouncing", fontSize = 10.sp, color = TextSecondary)
                            }
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Icon(
                                    imageVector = Icons.Default.Palette,
                                    contentDescription = null,
                                    tint = AccentPink.copy(alpha = pulseAlpha),
                                    modifier = Modifier.size(13.dp)
                                )
                                Spacer(modifier = Modifier.width(4.dp))
                                Text("Color LUT", fontSize = 10.sp, color = TextSecondary)
                            }
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Icon(
                                    imageVector = Icons.Default.Audiotrack,
                                    contentDescription = null,
                                    tint = AccentPink.copy(alpha = pulseAlpha),
                                    modifier = Modifier.size(13.dp)
                                )
                                Spacer(modifier = Modifier.width(4.dp))
                                Text("Audio Ducking", fontSize = 10.sp, color = TextSecondary)
                            }
                        }
                    }
                }
            }
        }
    }
}
