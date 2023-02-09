import java.io.*;
import java.util.*;

public class Naloga9 {

    static class Node {
        int minVisina;
        int maxVisina;
        Node[] otroci;
        int velikost;

        public Node(int minVisina, int maxVisina, int velikost) {
            this.minVisina = minVisina;
            this.maxVisina = maxVisina;
            this.otroci = new Node[4];
            this.velikost = velikost;
        }
    }

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new FileReader(args[0]));
        int A = Integer.parseInt(br.readLine());
        int[][] heights = new int[A][A];
        for (int i = 0; i < A; i++) {
            String[] parts = br.readLine().split(",");
            for (int j = 0; j < A; j++) {
                heights[i][j] = Integer.parseInt(parts[j]);
            }
        }

        int B = Integer.parseInt(br.readLine());
        int[] waterLevels = new int[B];
        for (int i = 0; i < B; i++) {
            waterLevels[i] = Integer.parseInt(br.readLine());
        }
        br.close();

        Node root = narediDrevo(heights, 0, 0, A);

        PrintWriter pw = new PrintWriter(new OutputStreamWriter(new FileOutputStream(args[1])));
        for (int waterLevel : waterLevels) {
            int[] result = prestejPotopljeneTocke(root, waterLevel);
            pw.println(result[0] + "," + result[1]);
        }
        pw.close();
    }

    static Node narediDrevo(int[][] tabela, int x, int y, int velikost) {

        if (velikost == 1) {
            return new Node(tabela[x][y], tabela[x][y], velikost);
        }

        Node vozlisce = new Node(Integer.MAX_VALUE, Integer.MIN_VALUE, velikost);

        // Delimo trenutno področje na 4 manjša področja
        int novaVelikost = velikost / 2;
        Node[] otroci = new Node[4];
        otroci[0] = narediDrevo(tabela, x, y, novaVelikost);
        otroci[1] = narediDrevo(tabela, x, y + novaVelikost, novaVelikost);
        otroci[2] = narediDrevo(tabela, x + novaVelikost, y, novaVelikost);
        otroci[3] = narediDrevo(tabela, x + novaVelikost, y + novaVelikost, novaVelikost);

        // Posodobimo min in max višino trenutnega vozlišča
        for (Node otrok : otroci) {
            vozlisce.minVisina = Math.min(vozlisce.minVisina, otrok.minVisina);
            vozlisce.maxVisina = Math.max(vozlisce.maxVisina, otrok.maxVisina);
        }

        if (vozlisce.maxVisina == vozlisce.minVisina) {
            vozlisce.otroci = null;
        } else {
            vozlisce.otroci = otroci;
        }

        return vozlisce;
    }

    static int[] prestejPotopljeneTocke(Node vozlisce, int globinaVode) {
        if (vozlisce == null) {
            return new int[] { 0, 0 };
        }
        if (vozlisce.maxVisina > globinaVode && vozlisce.minVisina > globinaVode) {
            return new int[] { 0, 1 };
        }
        if (vozlisce.maxVisina <= globinaVode && vozlisce.minVisina <= globinaVode) {
            return new int[] { (int) Math.pow(vozlisce.velikost, 2), 1 };
        }
        if (vozlisce.otroci == null) {
            if (vozlisce.velikost > 1) {
                return new int[] { (int) Math.pow(vozlisce.velikost, 2), 1 };
            }

            int st = 0;
            if (vozlisce.maxVisina <= globinaVode) {
                st = 1;
            }
            return new int[] { st, 1 };
        }

        int steviloTock = 0;
        int steviloVozlisc = 0;
        for (Node otrok : vozlisce.otroci) {
            int[] rezultat = prestejPotopljeneTocke(otrok, globinaVode);
            steviloTock += rezultat[0];
            steviloVozlisc += rezultat[1];
        }

        return new int[] { steviloTock, steviloVozlisc + 1 };
    }
}
