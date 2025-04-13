## Saya Hasbi Haqqul Fikri dengan NIM 2309245 mengerjakan soal TP 6 dalam mata kuliah DPBO untuk keberkahanNya maka saya tidak melakukan kecurangan seperti yang telah dispesifikasikan. Aamiin.

# Aplikasi Flappy Bird (TP6 DPBO)

## Deskripsi
Aplikasi ini merupakan implementasi permainan klasik **Flappy Bird** yang dibangun menggunakan **Java Swing** sebagai antarmuka grafis. Pemain mengendalikan seekor burung yang harus melewati rintangan berupa pipa tanpa menyentuhnya. Game ini terdiri dari tampilan main menu dan gameplay utama. Proyek ini dibuat sebagai Tugas Praktikum 5 dalam mata kuliah **Dasar Pemrograman Berorientasi Objek (DPBO)**.

## Fitur Utama
- Tampilan **Main Menu** yang menarik dengan:
  - Gambar background
  - Logo judul game (gambar)
  - Input nama pemain
  - Tombol start berupa gambar `start.png`
- **Gameplay Flappy Bird**:
  - Burung melompat saat tombol spasi ditekan
  - Rintangan (pipa) bergerak dari kanan ke kiri
  - Deteksi tabrakan (collision) dengan pipa dan lantai
- **Game Over & Restart**:
  - Game berhenti saat tabrakan
  - Tampilkan gambar "Game Over"
  - Tekan tombol `R` untuk restart
- **Score Sistem**:
  - Menampilkan skor pemain
  - Skor bertambah saat pemain berhasil melewati pipa

## Desain Program
Program menggunakan **Java Swing** untuk UI dan **timer** dari `javax.swing.Timer` untuk mengatur pergerakan objek secara berkala. Berikut adalah struktur file utama:
- `App.java`: Entry point program, memunculkan `MainMenu`
- `MainMenu.java`: Form GUI awal dengan gambar logo dan tombol start
- `FlappyBird.java`: Panel utama game berisi logic dan tampilan gameplay
- `Player.java`: Class untuk menyimpan posisi dan gambar burung
- `Pipe.java`: Class untuk pipa atas dan bawah

## Alur Program

### 1. Main Menu
- Program pertama kali menampilkan `MainMenu`
- Pemain memasukkan nama dan menekan gambar tombol start
- Menu tertutup dan game dimulai

### 2. Gameplay
- Burung secara otomatis jatuh karena gravitasi
- Pemain menekan `Spasi` agar burung terbang
- Pipa muncul secara berkala dan bergerak ke kiri
- Pemain mendapat skor jika berhasil melewati pipa bawah

### 3. Game Over
- Jika burung menabrak pipa atau menyentuh tanah, permainan berhenti
- Ditampilkan gambar “Game Over”
- Tekan `R` untuk memulai ulang permainan dengan skor kembali ke 0

## Implementasi Validasi dan Efek
- Nama pemain disimpan di variabel statis `MainMenu.playerName`
- Label skor diperbarui setiap melewati pipa
- Gambar tombol dan logo diskalakan proporsional untuk menghindari tampilan gepeng atau blur
- Panel menggunakan background `background.png` agar visual konsisten

## Screenshot Tampilan Program

### Main Menu
![image](https://github.com/user-attachments/assets/ba43dd11-7fe5-412c-8755-30dadd2ac344)

### Gameplay
![image](https://github.com/user-attachments/assets/4eccd130-f514-42b7-a2fb-c07c801d6e71)

### Game Over
![image](https://github.com/user-attachments/assets/0a38c02d-253a-464d-bc14-fc5c80296d04)

