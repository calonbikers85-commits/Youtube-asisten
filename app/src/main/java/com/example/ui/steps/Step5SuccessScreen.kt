package com.example.ui.steps

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.History
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.model.ContentIdea
import com.example.ui.theme.AccentGreen
import com.example.ui.theme.AccentGreenBg
import com.example.ui.theme.AccentPurple
import com.example.ui.theme.DarkSurface
import com.example.ui.theme.DarkSurfaceBorder
import com.example.ui.theme.DarkSurfaceElevated
import com.example.ui.theme.TextPrimary
import com.example.ui.theme.TextSecondary
import com.example.ui.theme.TextTertiary
import com.example.ui.theme.TubeRed

@Composable
fun Step5SuccessScreen(
    selectedIdea: ContentIdea?,
    channelName: String,
    onReset: () -> Unit,
    onViewHistory: () -> Unit,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current

    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Spacer(modifier = Modifier.height(20.dp))

        // Success Icon Circle
        Box(
            modifier = Modifier
                .size(96.dp)
                .clip(CircleShape)
                .background(
                    Brush.radialGradient(
                        listOf(AccentGreen, Color(0xFF15803D))
                    )
                )
                .border(2.dp, Color.White.copy(alpha = 0.3f), CircleShape),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = Icons.Default.Check,
                contentDescription = "Success",
                tint = Color.White,
                modifier = Modifier.size(54.dp)
            )
        }

        Spacer(modifier = Modifier.height(24.dp))

        Text(
            text = "Upload Berhasil!",
            fontSize = 26.sp,
            fontWeight = FontWeight.Black,
            color = TextPrimary,
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(10.dp))

        Text(
            text = "Video \"${selectedIdea?.title ?: "Konten Baru"}\" telah dijadwalkan di channel YouTube Anda untuk ${selectedIdea?.schedule ?: "Senin, 18:00 WIB"}.",
            fontSize = 14.sp,
            color = TextSecondary,
            textAlign = TextAlign.Center,
            lineHeight = 20.sp,
            modifier = Modifier.padding(horizontal = 12.dp)
        )

        Spacer(modifier = Modifier.height(24.dp))

        // Scheduled Details Card
        Surface(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(16.dp),
            color = DarkSurface,
            border = androidx.compose.foundation.BorderStroke(1.dp, DarkSurfaceBorder)
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text("Target Channel", fontSize = 12.sp, color = TextTertiary)
                    Text(channelName, fontSize = 12.sp, fontWeight = FontWeight.Bold, color = AccentGreen)
                }
                Spacer(modifier = Modifier.height(8.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text("Jadwal Tayang", fontSize = 12.sp, color = TextTertiary)
                    Text(selectedIdea?.schedule ?: "Senin, 18:00 WIB", fontSize = 12.sp, fontWeight = FontWeight.Bold, color = AccentPurple)
                }
                Spacer(modifier = Modifier.height(8.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text("Format Video", fontSize = 12.sp, color = TextTertiary)
                    Text("1080p 60fps Shorts", fontSize = 12.sp, fontWeight = FontWeight.SemiBold, color = TextPrimary)
                }
                Spacer(modifier = Modifier.height(8.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text("Status API", fontSize = 12.sp, color = TextTertiary)
                    Text("YouTube Data API v3 (OK)", fontSize = 12.sp, fontWeight = FontWeight.SemiBold, color = AccentGreen)
                }
            }
        }

        Spacer(modifier = Modifier.height(30.dp))

        // Reset / Create New Content Button
        Button(
            onClick = onReset,
            modifier = Modifier
                .fillMaxWidth()
                .height(52.dp)
                .testTag("create_new_content_button"),
            shape = RoundedCornerShape(12.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = TubeRed
            )
        ) {
            Icon(
                imageVector = Icons.Default.Add,
                contentDescription = null,
                modifier = Modifier.size(18.dp)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = "Buat Konten Baru",
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold
            )
        }

        Spacer(modifier = Modifier.height(12.dp))

        // View History
        OutlinedButton(
            onClick = onViewHistory,
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
                .testTag("view_history_button"),
            shape = RoundedCornerShape(12.dp),
            border = androidx.compose.foundation.BorderStroke(1.dp, DarkSurfaceBorder),
            colors = ButtonDefaults.outlinedButtonColors(
                contentColor = TextPrimary
            )
        ) {
            Icon(
                imageVector = Icons.Default.History,
                contentDescription = null,
                modifier = Modifier.size(18.dp),
                tint = TextSecondary
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = "Lihat Riwayat & Jadwal Tayang",
                fontSize = 13.sp,
                fontWeight = FontWeight.SemiBold
            )
        }
    }
}
