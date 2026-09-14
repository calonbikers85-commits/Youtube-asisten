package com.example.ui.steps

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
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AutoAwesome
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Lightbulb
import androidx.compose.material.icons.filled.Schedule
import androidx.compose.material.icons.filled.SmartToy
import androidx.compose.material.icons.filled.VideoLibrary
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ui.components.ProcessingOverlayCard
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
fun Step1IdeationScreen(
    topic: String,
    onTopicChange: (String) -> Unit,
    suggestedTopics: List<String>,
    onSelectSuggested: (String) -> Unit,
    isProcessing: Boolean,
    processingMessage: String,
    processingProgress: Float,
    onGenerateIdeas: () -> Unit,
    modifier: Modifier = Modifier
) {
    val focusManager = LocalFocusManager.current

    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        // Step Title Banner
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
                            .background(AccentPurpleBg),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            imageVector = Icons.Default.AutoAwesome,
                            contentDescription = "Ideation",
                            tint = AccentPurple,
                            modifier = Modifier.size(20.dp)
                        )
                    }
                    Spacer(modifier = Modifier.width(12.dp))
                    Column {
                        Text(
                            text = "ChatGPT Ideation & Scripting",
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold,
                            color = TextPrimary
                        )
                        Text(
                            text = "Langkah 1 dari 4: Konsep & Otomasi Naskah",
                            fontSize = 11.sp,
                            color = AccentPurple
                        )
                    }
                }

                Spacer(modifier = Modifier.height(10.dp))
                Text(
                    text = "Tentukan niche atau topik, AI akan membuatkan ide konten, penjadwalan, script hook, dan prompt gambar sinematik.",
                    fontSize = 13.sp,
                    color = TextSecondary,
                    lineHeight = 18.sp
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Main Input Card
        Surface(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(20.dp),
            color = DarkSurface,
            border = androidx.compose.foundation.BorderStroke(1.dp, DarkSurfaceBorder)
        ) {
            Column(modifier = Modifier.padding(18.dp)) {
                Text(
                    text = "Topik Video YouTube Anda",
                    fontSize = 13.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = TextSecondary
                )
                Spacer(modifier = Modifier.height(8.dp))

                OutlinedTextField(
                    value = topic,
                    onValueChange = onTopicChange,
                    modifier = Modifier
                        .fillMaxWidth()
                        .testTag("topic_input_field"),
                    placeholder = {
                        Text(
                            text = "Contoh: Fakta Unik Hewan Laut, Tips Keuangan, Misteri Luar Angkasa...",
                            color = TextTertiary,
                            fontSize = 13.sp
                        )
                    },
                    shape = RoundedCornerShape(12.dp),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedContainerColor = DarkSurfaceElevated,
                        unfocusedContainerColor = DarkSurfaceElevated,
                        disabledContainerColor = DarkSurfaceElevated,
                        focusedBorderColor = TubeRed,
                        unfocusedBorderColor = DarkSurfaceBorder,
                        focusedTextColor = TextPrimary,
                        unfocusedTextColor = TextPrimary,
                        cursorColor = TubeRed
                    ),
                    trailingIcon = {
                        if (topic.isNotEmpty()) {
                            IconButton(onClick = { onTopicChange("") }) {
                                Icon(
                                    imageVector = Icons.Default.Clear,
                                    contentDescription = "Clear",
                                    tint = TextSecondary
                                )
                            }
                        }
                    },
                    keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
                    keyboardActions = KeyboardActions(onDone = {
                        focusManager.clearFocus()
                        if (topic.isNotBlank() && !isProcessing) {
                            onGenerateIdeas()
                        }
                    }),
                    singleLine = false,
                    maxLines = 3
                )

                Spacer(modifier = Modifier.height(14.dp))

                // Suggested Topic Chips
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.padding(bottom = 6.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.Lightbulb,
                        contentDescription = "Suggestions",
                        tint = AccentPurple,
                        modifier = Modifier.size(15.dp)
                    )
                    Spacer(modifier = Modifier.width(6.dp))
                    Text(
                        text = "Ide Topik Populer (Klik Cepat):",
                        fontSize = 11.sp,
                        fontWeight = FontWeight.Medium,
                        color = TextSecondary
                    )
                }

                FlowRow(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(6.dp),
                    verticalArrangement = Arrangement.spacedBy(6.dp)
                ) {
                    suggestedTopics.forEach { item ->
                        val isSelected = topic == item
                        Surface(
                            shape = RoundedCornerShape(8.dp),
                            color = if (isSelected) TubeRed.copy(alpha = 0.2f) else DarkSurfaceElevated,
                            border = androidx.compose.foundation.BorderStroke(
                                1.dp,
                                if (isSelected) TubeRed else DarkSurfaceBorder
                            ),
                            modifier = Modifier
                                .clickable { onSelectSuggested(item) }
                                .testTag("chip_${item.replace(" ", "_")}")
                        ) {
                            Text(
                                text = item,
                                fontSize = 11.sp,
                                color = if (isSelected) TubeRed else TextSecondary,
                                modifier = Modifier.padding(horizontal = 9.dp, vertical = 5.dp)
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                // AI Features Notice Pill
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(10.dp))
                        .background(DarkSurfaceElevated)
                        .padding(horizontal = 12.dp, vertical = 10.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = Icons.Default.SmartToy,
                        contentDescription = "AI Powered",
                        tint = AccentPurple,
                        modifier = Modifier.size(16.dp)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = "Model: OpenAI / Gemini Neural Script Engine (Automated Hooks, Scenes & Captions)",
                        fontSize = 10.sp,
                        color = TextSecondary
                    )
                }

                Spacer(modifier = Modifier.height(18.dp))

                // Generate Button
                Button(
                    onClick = {
                        focusManager.clearFocus()
                        onGenerateIdeas()
                    },
                    enabled = topic.isNotBlank() && !isProcessing,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(52.dp)
                        .testTag("generate_ideas_button"),
                    shape = RoundedCornerShape(12.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = TubeRed,
                        disabledContainerColor = DarkSurfaceElevated,
                        disabledContentColor = TextTertiary
                    )
                ) {
                    if (isProcessing) {
                        CircularProgressIndicator(
                            modifier = Modifier.size(18.dp),
                            strokeWidth = 2.dp,
                            color = Color.White
                        )
                        Spacer(modifier = Modifier.width(10.dp))
                        Text(
                            text = "Menghubungkan ke ChatGPT...",
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.White
                        )
                    } else {
                        Icon(
                            imageVector = Icons.Default.AutoAwesome,
                            contentDescription = null,
                            modifier = Modifier.size(18.dp)
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = "Generate Ide Konten & Script",
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
