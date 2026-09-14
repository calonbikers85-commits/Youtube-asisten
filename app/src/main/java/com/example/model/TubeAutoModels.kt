package com.example.model

import androidx.annotation.DrawableRes

data class ContentIdea(
    val id: Int,
    val title: String,
    val hook: String,
    val script: String,
    val prompts: List<String>,
    val schedule: String,
    val tags: List<String> = emptyList()
)

data class VideoAssets(
    val images: List<String>,
    @DrawableRes val localDrawables: List<Int>,
    val videos: List<String>,
    val ttsAudio: String,
    val bgmTrack: String = "Lofi Documentary Beats (Royalty Free).mp3",
    val voiceActor: String = "ID-Budi Neural Studio"
)

data class SubtitleLine(
    val text: String,
    val timeRange: String
)

data class FinalVideo(
    val thumbnailUrl: String,
    @DrawableRes val thumbnailRes: Int?,
    val duration: String,
    val status: String,
    val featuresApplied: List<String>,
    val subtitles: List<SubtitleLine> = emptyList()
)

data class PublishedVideo(
    val id: String,
    val title: String,
    val schedule: String,
    val status: String,
    @DrawableRes val thumbnailRes: Int,
    val viewsMock: String = "0 (Scheduled)",
    val createdDate: String = "Hari ini"
)

enum class GenerationStage {
    IDLE,
    GENERATING_IDEAS,
    GENERATING_ASSETS,
    STITCHING_VIDEO,
    APPLYING_SUBTITLES,
    AUDIO_MIXING,
    COLOR_GRADING,
    UPLOADING_YOUTUBE,
    COMPLETED
}
