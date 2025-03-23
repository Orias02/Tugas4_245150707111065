import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Pelanggan[] pelangganList = {
            new Pelanggan("Andi", "38", 2000000, "1234"),
            new Pelanggan("Doni", "56", 3000000, "4321"),
            new Pelanggan("Fatih", "74", 5000000, "5678")
        };

        while (true) {
            Pelanggan pelangganAktif = null;
            // Loop untuk meminta pengguna memasukkan PIN hingga valid
            while (pelangganAktif == null) {
                System.out.print("Masukkan PIN: ");
                String inputPin = scanner.next();
                
                for (Pelanggan p : pelangganList) {
                    if (p.autentikasi(inputPin)) {
                        pelangganAktif = p;
                        break;
                    }
                }
                
                if (pelangganAktif == null) {
                    System.out.println("PIN salah. Silakan coba lagi.");
                }
            }

            System.out.println("Selamat datang, " + pelangganAktif.getNama() + "!");
            System.out.println("Nomor Pelanggan: " + pelangganAktif.getNomorPelanggan());

            while (true) {
                System.out.println("Menu: 1. Top Up  2. Pembelian  3. Selesai dan Cetak Struk  4. Logout");
                int pilihan = scanner.nextInt();
                if (pilihan == 4) break;
                
                switch (pilihan) {
                    case 1:
                        System.out.print("Masukkan jumlah top up: ");
                        double jumlah = scanner.nextDouble();
                        pelangganAktif.topUp(jumlah);
                        break;
                    case 2:
                        System.out.print("Masukkan jumlah pembelian: ");
                        double beli = scanner.nextDouble();
                        System.out.print("Masukkan PIN: ");
                        String pin = scanner.next();
                        pelangganAktif.beli(beli, pin);
                        break;
                    case 3:
                        System.out.println("\n===== STRUK TRANSAKSI =====");
                        System.out.println("Nama: " + pelangganAktif.getNama());
                        System.out.println("Nomor Pelanggan: " + pelangganAktif.getNomorPelanggan());
                        System.out.println("Total Pembelian: " + pelangganAktif.getTotalPembelian());
                        System.out.println("Saldo Akhir: " + pelangganAktif.getSaldo());
                        System.out.println("===========================\n");
                        break;
                    default:
                        System.out.println("Pilihan tidak valid.");
                }
            }
            System.out.println("Logout berhasil. Kembali ke menu awal.");
        }
    }
}