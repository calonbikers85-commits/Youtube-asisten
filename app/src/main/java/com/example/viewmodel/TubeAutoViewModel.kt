package com.example.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.R
import com.example.model.ContentIdea
import com.example.model.FinalVideo
import com.example.model.PublishedVideo
import com.example.model.SubtitleLine
import com.example.model.VideoAssets
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

data class TubeAutoUiState(
    val currentStep: Int = 1,
    val isProcessing: Boolean = false,
    val processingMessage: String = "",
    val processingProgress: Float = 0f,
    val topic: String = "",
    val generatedIdeas: List<ContentIdea>? = null,
    val selectedIdea: ContentIdea? = null,
    val videoAssets: VideoAssets? = null,
    val finalVideo: FinalVideo? = null,
    val isPlayingPreview: Boolean = false,
    val activeSubtitleIndex: Int = 0,
    val channelName: String = "@YourChannel",
    val isChannelActive: Boolean = true,
    val publishedHistory: List<PublishedVideo> = emptyList(),
    val isVerticalShorts: Boolean = true
)

class TubeAutoViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(
        TubeAutoUiState(
            publishedHistory = listOf(
                PublishedVideo(
                    id = "YT-098",
                    title = "5 Fakta Unik Hewan Laut yang Bikin Merinding",
                    schedule = "Kemarin, 18:00 WIB",
                    status = "Telah Tayang",
                    thumbnailRes = R.drawable.thumb_mystery_space,
                    viewsMock = "14.2K views",
                    createdDate = "Kemarin"
                )
            )
        )
    )
    val uiState: StateFlow<TubeAutoUiState> = _uiState.asStateFlow()

    private var playbackJob: Job? = null

    val suggestedTopics = listOf(
        "Fakta Unik Hewan Laut",
        "Misteri Luar Angkasa",
        "Tips Keuangan Gen Z",
        "AI Tools Viral 2026",
        "Sejarah Rahasia Kuno",
        "Kebiasaan Orang Sukses"
    )

    fun onTopicChange(newTopic: String) {
        _uiState.update { it.copy(topic = newTopic) }
    }

    fun selectSuggestedTopic(suggested: String) {
        _uiState.update { it.copy(topic = suggested) }
    }

    fun setStep(step: Int) {
        // Can only jump backwards or to current
        if (step <= _uiState.value.currentStep) {
            _uiState.update { it.copy(currentStep = step) }
        }
    }

    fun toggleShortsOrientation() {
        _uiState.update { it.copy(isVerticalShorts = !it.isVerticalShorts) }
    }

    fun handleGenerateIdeas() {
        val currentTopic = _uiState.value.topic.trim()
        if (currentTopic.isEmpty()) return

        _uiState.update {
            it.copy(
                isProcessing = true,
                processingMessage = "Menghubungkan ke ChatGPT & AI Ideation...",
                processingProgress = 0.2f
            )
        }

        viewModelScope.launch {
            delay(800)
            _uiState.update {
                it.copy(
                    processingMessage = "Menganalisis tren YouTube Shorts untuk \"$currentTopic\"...",
                    processingProgress = 0.6f
                )
            }
            delay(900)
            _uiState.update {
                it.copy(
                    processingMessage = "Membuat skrip hook viral dan prompt gambar 4K...",
                    processingProgress = 0.9f
                )
            }
            delay(700)

            val ideas = listOf(
                ContentIdea(
                    id = 1,
                    title = "5 Fakta Gila Tentang $currentTopic yang Disembunyikan Dunia",
                    hook = "Pernahkah kamu berpikir kalau $currentTopic sebenarnya...",
                    script = "[Intro Hook] Pernahkah kamu berpikir kalau $currentTopic sebenarnya berbeda dari yang kita tahu? [Body] Fakta pertama, ilmuwan menemukan bahwa struktur $currentTopic menyimpan fenomena langka yang jarang terungkap ke publik. Fakta kedua, efek dominonya mempengaruhi peradaban modern lebih dari yang kamu bayangkan. [Outro] Yang mana yang paling bikin kamu kaget? Tulis di komentar!",
                    prompts = listOf(
                        "A cinematic 4k shot of a mysterious scientist analyzing glowing data",
                        "A hyper-realistic 3D render of $currentTopic glowing in deep space"
                    ),
                    schedule = "Senin, 18:00 WIB",
                    tags = listOf("#shorts", "#$currentTopic", "#faktaunik", "#edukasi")
                ),
                ContentIdea(
                    id = 2,
                    title = "Sejarah Rahasia $currentTopic dalam 60 Detik!",
                    hook = "Berhenti scroll! Kamu harus tahu asal usul $currentTopic ini.",
                    script = "[Intro Hook] Berhenti scroll! Kamu harus tahu asal usul $currentTopic ini. [Body] Di dekade silam, awal mula riset $currentTopic sempat dilarang keras hingga dokumennya disimpan di bunker bawah tanah. Kini di era digital, faktanya akhirnya terbongkar luas. [Outro] Save video ini sebelum dihapus!",
                    prompts = listOf(
                        "Vintage sepia tone historical footage documentary style",
                        "A futuristic neon representation of $currentTopic evolving"
                    ),
                    schedule = "Rabu, 15:00 WIB",
                    tags = listOf("#sejarah", "#viral", "#shorts", "#misteri")
                ),
                ContentIdea(
                    id = 3,
                    title = "Kenapa Semua Orang Tiba-Tiba Bicara Tentang $currentTopic?",
                    hook = "Jangan sampai kamu jadi orang terakhir yang paham hal ini!",
                    script = "[Intro Hook] Jangan sampai kamu jadi orang terakhir yang paham hal ini! [Body] Dalam 30 hari terakhir, pencarian tentang $currentTopic meroket 400%. Ada alasan mendesak mengapa para pakar mulai memperingatkan dampaknya terhadap masa depan kita. [Outro] Bagikan ke temanmu yang belum tahu!",
                    prompts = listOf(
                        "High contrast dramatic neon analytics dashboard showing exponential rise",
                        "Cinematic 3D portrait looking at glowing holograms of $currentTopic"
                    ),
                    schedule = "Jumat, 19:30 WIB",
                    tags = listOf("#trending", "#shorts", "#faktadunia", "#fyp")
                )
            )

            _uiState.update {
                it.copy(
                    isProcessing = false,
                    processingMessage = "",
                    processingProgress = 0f,
                    generatedIdeas = ideas,
                    currentStep = 2
                )
            }
        }
    }

    fun handleGenerateVideo(idea: ContentIdea) {
        _uiState.update {
            it.copy(
                selectedIdea = idea,
                isProcessing = true,
                processingMessage = "Menghasilkan visual AI & voiceover...",
                processingProgress = 0.2f
            )
        }

        viewModelScope.launch {
            delay(900)
            _uiState.update {
                it.copy(
                    processingMessage = "Rendering 2 aset gambar 4K sinematik...",
                    processingProgress = 0.55f
                )
            }
            delay(1000)
            _uiState.update {
                it.copy(
                    processingMessage = "Sintesis audio TTS AI (Bahasa Indonesia)...",
                    processingProgress = 0.85f
                )
            }
            delay(900)

            val assets = VideoAssets(
                images = listOf(
                    "https://images.unsplash.com/photo-1618005182384-a83a8bd57fbe?q=80&w=600&auto=format&fit=crop",
                    "https://images.unsplash.com/photo-1535016120720-40c746a6580c?q=80&w=600&auto=format&fit=crop"
                ),
                localDrawables = listOf(
                    R.drawable.thumb_mystery_space,
                    R.drawable.thumb_vintage_cyber
                ),
                videos = listOf("clip_intro_b_roll.mp4", "clip_climax_b_roll.mp4"),
                ttsAudio = "voiceover_ready.mp3",
                bgmTrack = "Lofi Mystery Beats (Royalty-Free).mp3"
            )

            _uiState.update {
                it.copy(
                    isProcessing = false,
                    processingMessage = "",
                    processingProgress = 0f,
                    videoAssets = assets,
                    currentStep = 3
                )
            }
        }
    }

    fun handleEditVideo() {
        _uiState.update {
            it.copy(
                isProcessing = true,
                processingMessage = "Menjahit Video & Audio (CapCut AI Engine)...",
                processingProgress = 0.15f
            )
        }

        viewModelScope.launch {
            delay(800)
            _uiState.update {
                it.copy(
                    processingMessage = "Menerapkan Subtitle Dinamis (Karaoke Pop-up)...",
                    processingProgress = 0.40f
                )
            }
            delay(900)
            _uiState.update {
                it.copy(
                    processingMessage = "Audio Mixing: Voiceover + Background Music Ducking...",
                    processingProgress = 0.70f
                )
            }
            delay(900)
            _uiState.update {
                it.copy(
                    processingMessage = "Color Grading Cinematic LUT & Watermark Channel...",
                    processingProgress = 0.90f
                )
            }
            delay(800)

            val idea = _uiState.value.selectedIdea
            val subtitles = listOf(
                SubtitleLine("Pernahkah kamu berpikir kalau...", "00:00 - 00:03"),
                SubtitleLine("${idea?.title?.take(30) ?: "Topik ini"} sebenarnya berbeda?", "00:03 - 00:08"),
                SubtitleLine("Ilmuwan menemukan fenomena langka...", "00:08 - 00:15"),
                SubtitleLine("Dan fakta berikutnya akan mengejutkanmu!", "00:15 - 00:25")
            )

            val video = FinalVideo(
                thumbnailUrl = "https://images.unsplash.com/photo-1611162617474-5b21e879e113?q=80&w=600&auto=format&fit=crop",
                thumbnailRes = R.drawable.thumb_mystery_space,
                duration = "00:58",
                status = "Ready to Upload",
                featuresApplied = listOf(
                    "Dynamic Subtitles",
                    "AI Voiceover",
                    "Background Music",
                    "Channel Watermark",
                    "Color Grading LUT",
                    "60 FPS 1080p"
                ),
                subtitles = subtitles
            )

            _uiState.update {
                it.copy(
                    isProcessing = false,
                    processingMessage = "",
                    processingProgress = 0f,
                    finalVideo = video,
                    currentStep = 4
                )
            }
        }
    }

    fun togglePlayback() {
        val currentlyPlaying = _uiState.value.isPlayingPreview
        if (currentlyPlaying) {
            playbackJob?.cancel()
            _uiState.update { it.copy(isPlayingPreview = false) }
        } else {
            _uiState.update { it.copy(isPlayingPreview = true) }
            playbackJob = viewModelScope.launch {
                val subsCount = _uiState.value.finalVideo?.subtitles?.size ?: 1
                while (_uiState.value.isPlayingPreview) {
                    delay(1800)
                    _uiState.update {
                        it.copy(activeSubtitleIndex = (it.activeSubtitleIndex + 1) % subsCount)
                    }
                }
            }
        }
    }

    fun updateVideoTitle(newTitle: String) {
        val currentIdea = _uiState.value.selectedIdea ?: return
        _uiState.update {
            it.copy(selectedIdea = currentIdea.copy(title = newTitle))
        }
    }

    fun handleUploadYouTube() {
        _uiState.update {
            it.copy(
                isProcessing = true,
                processingMessage = "Mengunggah video via YouTube Data API v3...",
                processingProgress = 0.3f
            )
        }

        viewModelScope.launch {
            delay(1000)
            _uiState.update {
                it.copy(
                    processingMessage = "Menerapkan metadata, deskripsi SEO, dan thumbnail 1080p...",
                    processingProgress = 0.75f
                )
            }
            delay(1000)

            val idea = _uiState.value.selectedIdea
            val newPublished = PublishedVideo(
                id = "YT-${System.currentTimeMillis() % 10000}",
                title = idea?.title ?: "Video Baru",
                schedule = idea?.schedule ?: "Terjadwal",
                status = "Terjadwal",
                thumbnailRes = R.drawable.thumb_mystery_space,
                viewsMock = "0 (Scheduled)",
                createdDate = "Baru Saja"
            )

            _uiState.update {
                it.copy(
                    isProcessing = false,
                    processingMessage = "",
                    processingProgress = 0f,
                    isPlayingPreview = false,
                    publishedHistory = listOf(newPublished) + it.publishedHistory,
                    currentStep = 5
                )
            }
        }
    }

    fun resetToNew() {
        playbackJob?.cancel()
        _uiState.update {
            it.copy(
                currentStep = 1,
                topic = "",
                generatedIdeas = null,
                selectedIdea = null,
                videoAssets = null,
                finalVideo = null,
                isPlayingPreview = false,
                isProcessing = false,
                processingMessage = "",
                processingProgress = 0f
            )
        }
    }

    override fun onCleared() {
        super.onCleared()
        playbackJob?.cancel()
    }
}
