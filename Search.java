/* 
 * UTS DAA 2023 - Farouq Mulya Al Simabua - 2022071087
 * 
 * ----------------------------------------------------
 * Program untuk mencari data menggunakan Binary Search
 * ----------------------------------------------------
 */

class Searching {
    public static int[] data = null;
    public static int awal, tengah, akhir, temp, count;

    public static void main(String[] args) {
        // Data yang ingin dicari
        int cari = 10;

        // Data yang akan dicari
        data = new int[] { 4, 15, 10, 5, 0, 8 };

        // Menampilkan data yang akan dicari
        System.out.print("Data yang dicari: " + cari);

        // Sorting data
        sorting();

        // Menampilkan data setelah diurutkan
        System.out.print("\nData setelah diurutkan: ");
        for (int x = 0; x < data.length; x++)
            System.out.print(data[x] + " ");

        // Pencarian menggunakan Binary Search
        boolean temu = false;
        awal = 0;
        akhir = data.length - 1;
        temp = 0;
        count = 0;
        int iterasi = 0;
        System.out.println("\nIterasi Aw Ak Te Ni");

        while (temu != true) {
            tengah = (awal + akhir) / 2;
            iterasi++;

            if (data[tengah] == cari) {
                System.out.print(iterasi + " ");
                System.out.print(awal + " ");
                System.out.print(akhir + " ");
                System.out.print(tengah + " ");
                System.out.print(data[tengah] + "\n");

                temu = true;
                break;
            } else if (data[tengah] < cari) {
                System.out.print(iterasi + " ");
                System.out.print(awal + " ");
                System.out.print(akhir + " ");
                System.out.print(tengah + " ");
                System.out.print(data[tengah] + "\n");
                awal = tengah + 1;
            } else if (data[tengah] > cari) {
                System.out.print(iterasi + " ");
                System.out.print(awal + " ");
                System.out.print(akhir + " ");
                System.out.print(tengah + " ");
                System.out.print(data[tengah] + "\n");
                akhir = tengah - 1;
            }

            // Cek worst case
            if (temp != data[tengah])
                temp = data[tengah];
            else
                count++;

            // Batasan untuk worst case
            if (count == 3)
                break;
        }

        // Output
        if (temu == true)
            System.out.println(
                    "\nData " + cari + " ditemukan pada index ke-" + tengah + "\n" + "dan iterasi ke-" + iterasi);
        else
            System.out.println("\nData " + cari + " tidak ditemukan");

    }

    // Sorting ascending
    public static void sorting() {
        int temp = 0;
        for (int x = 0; x < data.length; x++) {
            for (int y = 0; y < data.length; y++) {
                if (x == y)
                    continue;
                else {
                    if (data[x] < data[y]) {
                        temp = data[y];
                        data[y] = data[x];
                        data[x] = temp;
                    }
                }
            }
        }
    }
}