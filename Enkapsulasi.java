import java.util.Random;

class Pelanggan {
    private String nama;
    private String nomorPelanggan;
    private double saldo;
    private String pin;
    private boolean isBlocked = false;
    private int salahAutentikasi = 0;
    private double totalPembelian = 0;

    // Konstruktor untuk inisialisasi pelanggan
    public Pelanggan(String nama, String kodeJenis, double saldo, String pin) {
        this.nama = nama;
        this.nomorPelanggan = generateNomorPelanggan(kodeJenis);
        this.saldo = saldo;
        this.pin = pin;
    }

    private String generateNomorPelanggan(String kodeJenis) {
        Random random = new Random();
        int angkaAcak = 10000000 + random.nextInt(90000000); 
        return kodeJenis + angkaAcak; // Memanggil 8 digit kode generateNomorPelanggan untuk menghasilkan nomor unik.
    }

    public String getNama() {
        return nama;
    }
    
    public String getNomorPelanggan() {
        return nomorPelanggan;
    }

    public double getSaldo() {
        return saldo;
    }

    public double getTotalPembelian() {
        return totalPembelian;
    }

    // Metode untuk autentikasi PIN pelanggan
    public boolean autentikasi(String inputPin) {
        if (isBlocked) {
            System.out.println("Akun Anda diblokir.");
            return false;
        }
        if (this.pin.equals(inputPin)) {
            salahAutentikasi = 0;
            return true;
        } else {
            salahAutentikasi++;
            if (salahAutentikasi >= 3) {
                isBlocked = true;
                System.out.println("Akun diblokir karena 3x kesalahan PIN.");
            }
            return false;
        }
    }

    // Metode untuk menambah saldo pelanggan
    public void topUp(double jumlah) {
        if (isBlocked) {
            System.out.println("Akun Anda diblokir.");
            return;
        }
        saldo += jumlah;
        System.out.println("Top up berhasil. Saldo sekarang: " + saldo);
    }

    // Metode untuk melakukan pembelian
    public void beli(double jumlah, String inputPin) {
        if (!autentikasi(inputPin)) return;
        
        double cashback = hitungCashback(jumlah);
        double totalBayar = jumlah - cashback;

        if (saldo - totalBayar < 10000) {
            System.out.println("Transaksi gagal. Saldo tidak mencukupi.");
            return;
        }
        saldo -= totalBayar;
        totalPembelian += jumlah;
        System.out.println("Pembelian berhasil! Cashback: " + cashback + " Saldo sekarang: " + saldo);
    }

    // Metode untuk menghitung cashback berdasarkan jenis kartu
    private double hitungCashback(double jumlah) {
        if (jumlah < 1000000) return 0;
        String kodeJenis = nomorPelanggan.substring(0, 2);
        switch (kodeJenis) {
            case "38": return jumlah * 0.05;
            case "56": return jumlah * (jumlah >= 1000000 ? 0.07 : 0.02);
            case "74": return jumlah * (jumlah >= 1000000 ? 0.10 : 0.05);
            default: return 0;
        }
    }
}