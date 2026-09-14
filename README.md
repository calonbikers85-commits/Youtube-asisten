# 📱 Asisten YouTube

Asisten YouTube adalah aplikasi berbasis Android yang dirancang untuk membantu pengguna mengelola aktivitas, menganalisis data, atau mengoptimalkan produktivitas mereka terkait platform YouTube langsung dari perangkat seluler.

---

## ✨ Fitur Utama

* **Manajemen Konten:** Mempermudah pemantauan konten video atau alur kerja kreator.
* **Otomatisasi Tugas:** Menyediakan pintasan cerdas untuk menghemat waktu Anda saat berselancar atau mengelola channel.
* **Antarmuka Ringan:** Didesain agar responsif dan mudah digunakan langsung melalui HP Android.

---

## 🛠️ Persyaratan Sistem

Untuk menjalankan atau memodifikasi proyek ini, pastikan perangkat atau lingkungan pengembangan Anda memenuhi kriteria berikut:

* **Android OS:** Versi 7.0 (Nougat) atau yang lebih baru.
* **Perangkat Lunak Pengembang (Opsional):** Android Studio Jellyfish (atau versi terbaru) jika ingin memodifikasi kode.
* **Versi Java / JDK:** JDK 17.

---

## 🚀 Cara Mengunduh & Memasang (Untuk Pengguna)

Anda tidak perlu membangun aplikasi ini dari awal. File APK yang siap pakai sudah disediakan melalui fitur **Releases** di GitHub:

1. Buka halaman utama repositori ini di browser.
2. Gulir ke bawah atau cari menu **Releases** di sisi kanan (bawah jika di HP).
3. Klik versi rilis terbaru (misal: `v1.0.0`).
4. Unduh berkas `.apk` yang terlampir.
5. Buka berkas tersebut di HP Android Anda dan ikuti petunjuk pemasangan (Pastikan opsi *"Izinkan instalasi dari sumber tidak dikenal"* sudah aktif di pengaturan HP Anda).

---

## 💻 Cara Menjalankan Kode Secara Lokal (Untuk Pengembang)

Jika Anda ingin mengompilasi atau memodifikasi aplikasi ini sendiri:

1. Kloning repositori ini ke komputer atau aplikasi IDE Anda:
   ```bash
   git clone https://github.com
   ```
2. Buka proyek menggunakan **Android Studio**.
3. Sinkronisasikan berkas Gradle hingga selesai (*Sync Project with Gradle Files*).
4. Jalankan aplikasi menggunakan Emulator atau perangkat Android asli yang terhubung.

### Membuat APK Lewat Terminal
Untuk membuat file APK uji coba sendiri, jalankan perintah berikut di tab Terminal Android Studio:
```bash
# Windows
.\gradlew assembleDebug

# Linux / Mac
./gradlew assembleDebug
```

---

## 🤝 Kontribusi

Kontribusi selalu terbuka untuk siapa saja! Jika Anda menemukan kutu (*bug*), ingin menambahkan fitur baru, atau memperbaiki dokumentasi:

1. Lakukan *Fork* pada repositori ini.
2. Buat *Branch* fitur baru Anda (`git checkout -b fitur/FiturBaru`).
3. Kirim perubahan Anda (*Commit*) (`git commit -m 'Menambahkan fitur cerdas baru'`).
4. Lakukan *Push* ke branch tersebut (`git push origin fitur/FiturBaru`).
5. Buat **Pull Request** baru di halaman GitHub ini.

---

## 📄 Lisensi

Proyek ini didistribusikan di bawah lisensi **MIT License**. Anda bebas menggunakan, memodifikasi, dan membagikan proyek ini dengan tetap menyertakan hak cipta asli.
