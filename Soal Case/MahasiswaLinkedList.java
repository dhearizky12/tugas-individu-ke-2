import java.util.Scanner;

class Mahasiswa {
    String nim;
    String nama;
    String jurusan;
    Mahasiswa next;

    public Mahasiswa(String nim, String nama, String jurusan) {
        this.nim = nim;
        this.nama = nama;
        this.jurusan = jurusan;
        this.next = null;
    }
}

public class MahasiswaLinkedList {
    private Mahasiswa head;
    private int size = 0;
    private final int MAX_SIZE = 5;

    // Validasi input
    private boolean isValid(String nim, String nama, String jurusan) {
        // Validasi panjang NIM maksimal 10 dan hanya berisi angka
        if (nim.length() > 10)
            return false;
        for (int i = 0; i < nim.length(); i++) {
            if (!Character.isDigit(nim.charAt(i))) {
                return false;
            }
        }

        // Validasi panjang nama dan jurusan
        if (nama.length() > 30 || jurusan.length() > 50) {
            return false;
        }

        return true;
    }

    public void push(String nim, String nama, String jurusan) {
        if (size >= MAX_SIZE) {
            System.out.println("Data sudah mencapai batas maksimum (" + MAX_SIZE + " mahasiswa).");
            return;
        }

        if (!isValid(nim, nama, jurusan)) {
            System.out.println("Input tidak valid! Periksa kembali batas panjang dan format NIM.");
            return;
        }

        Mahasiswa newNode = new Mahasiswa(nim, nama, jurusan);

        // Jika linked list kosong
        if (head == null) {
            head = newNode;
        } else if (nim.compareTo(head.nim) < 0) {
            // Sisip di depan
            newNode.next = head;
            head = newNode;
        } else {
            // Sisip di posisi sesuai urutan NIM
            Mahasiswa current = head;
            while (current.next != null && current.next.nim.compareTo(nim) < 0) {
                current = current.next;
            }
            newNode.next = current.next;
            current.next = newNode;
        }

        size++;
        System.out.println("Data mahasiswa berhasil ditambahkan.");
    }

    // Tampilkan semua data
    public void display() {
        if (head == null) {
            System.out.println("Tidak ada data mahasiswa.");
            return;
        }

        System.out.println("\nDaftar Mahasiswa:");
        Mahasiswa current = head;
        int i = 1;
        while (current != null) {
            System.out.println(
                    i++ + ". NIM: " + current.nim + ", Nama: " + current.nama + ", Jurusan: " + current.jurusan);
            current = current.next;
        }
    }

    // Hapus semua data
    public void popAll() {
        head = null;
        size = 0;
        System.out.println("Semua data mahasiswa telah dihapus.");
    }

    // Main menu
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        MahasiswaLinkedList list = new MahasiswaLinkedList();
        int choice;

        do {
            System.out.println("\n=== Menu ===");
            System.out.println("1. Push data mahasiswa");
            System.out.println("2. Tampilkan semua data mahasiswa");
            System.out.println("3. Hapus semua data mahasiswa");
            System.out.println("4. Keluar");
            System.out.print("Pilihan Anda: ");
            choice = scanner.nextInt();
            scanner.nextLine(); // bersihkan newline

            switch (choice) {
                case 1:
                    System.out.print("Masukkan NIM: ");
                    String nim = scanner.nextLine();
                    System.out.print("Masukkan Nama: ");
                    String nama = scanner.nextLine();
                    System.out.print("Masukkan Jurusan: ");
                    String jurusan = scanner.nextLine();
                    list.push(nim, nama, jurusan);
                    break;
                case 2:
                    list.display();
                    break;
                case 3:
                    list.popAll();
                    break;
                case 4:
                    System.out.println("Program selesai. Terima kasih!");
                    break;
                default:
                    System.out.println("Pilihan tidak valid.");
            }
        } while (choice != 4);

        scanner.close();
    }
}
