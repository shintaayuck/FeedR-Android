# Tugas Besar IF3111 Pengembangan Aplikasi pada Platform Khusus
## Subsistem 1 - Mobile

### Deskripsi Umum
![alt text](https://drive.google.com/file/d/17zaatAl2jaf02CTToJ_0AJJAs9_a-iUp/view?usp=sharing)
**FeedR** adalah sebuah aplikasi *pet caring assistant* dengan *integrated game* yang bertujuan untuk mempermudah pemilik hewan peliharaan dalam mengurus kebutuhan hewan peliharaannya. Selain itu, **FeedR** juga dapat memberikan pet caring experience yang lebih menyenangkan melalui sistem *integrated game* yang menggunakan data *realtime*. Sistem **FeedR** ini dapat diwujudkan dengan integrasi dari sistem Android, Arduino, dan Unity.

Sedangkan pada sistem Android, data-data lingkungan yang didapat dari arduino akan dirangkum dan ditampilkan kepada pengguna. Data-data tersebut dapat juga dijadikan sebagai *trigger* untuk memberikan notifikasi *event* kepada pemilik hewan peliharaan. Selain itu, pengguna dapat memberikan perintah secara *remote* kepada arduino melalui Android.

Sistem Arduino akan dipasang di dekat kandang kucing dan terhubung dengan piring/mangkuk makanan kucing. Sistem ini digunakan untuk mendapatkan kondisi lingkungan di kandang kucing, seperti suhu, jumlah makanan, dan keberadaan kucing. Selain itu, Arduino juga dapat menerima perintah untuk memberikan makanan secara otomatis.

Terakhir, sistem kami menggunakan *Unity* untuk mengembangkan sebuah *Integrated Game* yang memanfaatkan data dari hewan peliharaan yang telah terdaftar pada aplikasi android. Dengan data yang diambil dari aplikasi Android, pengguna tidak perlu melakukan pendaftaran atau setup ulang di awal *game*. Selain itu, beberapa komponen *game* seperti bonus atau energi dapat juga ditentukan berdasarkan data yang  diambil dari hasil rangkuman di android ataupun lingkungan di android.

### Instalasi Aplikasi
Jalankan command `./gradlew installDebug` untuk menginstall apk pada emulator ataupun device yang terhubung dengan komputer.

### Penggunaan Aplikasi
1. Jalankan aplikasi FeedR dari Android
2. Login dengan akun Google
3. Isi dan simpan Keterangan Hewan Peliharaan
4. Navigasi fitur dilakukan dengan menyentuh tab atau slide halaman
5. Tombol pengaturan dan Edit Hewan Peliharaan berada pada overflow menu di atas kanan
6. Tombol untuk logout berada didalam setting

### Derivelables
[Proposal](google.com)
[Laporan](google.com) 

### Anggota Kelompok
1. Rizki Alif Salman Alfarisy - 13516005
2. Shinta Ayu Chandra Kemala - 13516029
3. Naufal Putra Pamungkas - 13516110