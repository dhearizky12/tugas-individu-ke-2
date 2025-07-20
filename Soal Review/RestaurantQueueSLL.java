import java.util.Scanner;

/**
 * Program manajemen antrian pelanggan restoran
 * Menggunakan Single Linked List
 */
public class RestaurantQueueSLL {
    /*** SINGLE LINKED LIST IMPLEMENTATION ***/
    private static class Node {
        String name;
        Node next;

        Node(String name) {
            this.name = name;
            this.next = null;
        }
    }

    private Node frontSingle, rearSingle;
    private int sizeSingle;

    /*** SINGLE LINKED LIST METHODS ***/
    public void pushSingle(String name) {
        long start = System.nanoTime();
        Node newNode = new Node(name);
        if (rearSingle == null) {
            frontSingle = rearSingle = newNode;
        } else {
            rearSingle.next = newNode;
            rearSingle = newNode;
        }

        sizeSingle++;
        long end = System.nanoTime();
        System.out.println("[SLL] Tambah: " + name + " (" + (end - start) / 1_000_000.0 + " ms)");
    }

    public void popSingle() {
        long start = System.nanoTime();
        if (frontSingle == null) {
            System.out.println("[SLL] Antrian kosong.");
            return;
        }
        System.out.println("[SLL] Layani: " + frontSingle.name);
        frontSingle = frontSingle.next;
        if (frontSingle == null) {
            rearSingle = null;
        }

        sizeSingle--;
        long end = System.nanoTime();
        System.out.println("[SLL] Waktu pop: " + (end - start) / 1_000_000.0 + " ms");
    }

    public void displaySingle() {
        System.out.println("\n[SLL] Antrian Pelanggan:");
        Node current = frontSingle;
        int i = 1;
        while (current != null) {
            System.out.println(i++ + ". " + current.name);
            current = current.next;
        }
        System.out.println("Total: " + sizeSingle + " pelanggan\n");
    }

    /*** MAIN MENU ***/
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        RestaurantQueueSLL queue = new RestaurantQueueSLL();
        int choice;
        do {
            System.out.println("\n=== Menu ===");
            System.out.println("1. Tambah pelanggan (push)");
            System.out.println("2. Layani pelanggan (pop)");
            System.out.println("3. Tampilkan antrian");
            System.out.println("4. Keluar");
            System.out.print("Pilihan Anda: ");

            while (!scanner.hasNextInt()) {
                System.out.print("Masukkan angka 1-4: ");
                scanner.next();
            }

            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    System.out.print("Masukkan nama pelanggan: ");
                    String name = scanner.nextLine();
                    queue.pushSingle(name);
                    break;
                case 2:
                    queue.popSingle();
                    break;
                case 3:
                    queue.displaySingle();
                    break;
                case 4:
                    System.out.println("Terima kasih. Program selesai.");
                    break;
                default:
                    System.out.println("Pilihan tidak valid.");
            }
        } while (choice != 4);
        scanner.close();
    }
}
