import java.util.*;
import java.io.*;
import java.lang.*;

public class Naloga3 {

    public static void main(String[] args) {
        // čas
        final long startTime = System.nanoTime();

        //
        FileWriter geek_file;
        try {

            FileInputStream fis = new FileInputStream(args[0]);
            geek_file = new FileWriter(args[1]);
            Scanner sc = new Scanner(fis);
            // Initializing BufferedWriter
            BufferedWriter geekwrite = new BufferedWriter(geek_file);
            StringBuffer sbf = new StringBuffer();
            // preštej število številk
            String tabela = sc.nextLine();
            int digits = 0;
            for (int i = 0; i < tabela.length(); i++) {
                if (tabela.charAt(i) != ',' && i > 1 && (tabela.charAt(i - 1) >= 48 && tabela.charAt(i) <= 57)) {
                    digits--;
                }
                if (tabela.charAt(i) >= 48 && tabela.charAt(i) <= 57) {
                    digits++;
                }

            }
            // rpetvori string v inte
            int[] tabelaIntov = new int[digits];

            String parts[] = tabela.split(",");
            for (int i = 0; i < parts.length; i++) {

                int number = Integer.parseInt(parts[i]);
                if (number != 0) {
                    tabelaIntov[i] = number;
                }
            }

            // System.out.println();
            // stevilo pogojev dobimo
            int steviloPogojev = sc.nextInt();
            // System.out.println("steviloPogojev -->" + steviloPogojev);
            String newlinem = sc.nextLine();
            // steviloPogojev
            // branje pogojev
            for (int i = 0; i < steviloPogojev; i++) {

                String pogoj = sc.nextLine();
                // System.out.println("pogoj -->" + pogoj);

                String pogojSplitann[] = pogoj.split(",");
                // pretvori pogoje v int

                if (pogojSplitann[0].equals("o")) {
                    // ce je >
                    if (pogojSplitann[1].equals(">")) {
                        int[] pogojSplitannInt = new int[steviloPogojev];
                        int l = 2;
                        if (l == 2) {
                            int pogojInt = Integer.parseInt(pogojSplitann[l]);
                            pogojSplitannInt[l] = pogojInt;
                        }
                        for (int o = 0; o < Math.min(Math.min(digits, tabelaIntov.length), parts.length); o++) {
                            if (tabelaIntov[o] <= pogojSplitannInt[2]) {
                                tabelaIntov[o] = -10101111;
                            }
                        }
                        int trenutnoSt = 0;
                        int temp = 0;

                        for (int z = 0; z < Math.min(Math.min(digits, tabelaIntov.length), parts.length); z++) {
                            // System.out.println("Math.min --> " + Math.min(digits, tabelaIntov.length));
                            // System.out.println("z --> " + z);

                            if (tabelaIntov[z] != -10101111
                                    && (tabelaIntov[Math.min(Math.min(digits, tabelaIntov.length), parts.length)
                                            - 1] != 0)) {
                                // geekwrite.write(tabelaIntov[z] + ",");
                                if (temp > 0) {
                                    geekwrite.write(",");
                                }
                                geekwrite.write(tabelaIntov[z] + "");
                                temp++;

                            }

                        }
                        // System.out.println();
                        geekwrite.newLine();

                    }
                    // ce je <
                    if (pogojSplitann[1].equals("<")) {
                        int[] pogojSplitannInt = new int[steviloPogojev];
                        int l = 2;
                        if (l == 2) {
                            int pogojInt = Integer.parseInt(pogojSplitann[l]);
                            pogojSplitannInt[l] = pogojInt;
                        }
                        for (int o = 0; o < Math.min(Math.min(digits, tabelaIntov.length), parts.length); o++) {
                            if (tabelaIntov[o] >= pogojSplitannInt[2]) {
                                tabelaIntov[o] = -10101111;
                            }
                        }
                        int trenutnoSt = 0;
                        int temp = 0;

                        for (int z = 0; z < Math.min(Math.min(digits, tabelaIntov.length), parts.length); z++) {
                            // System.out.println("Math.min --> " + Math.min(digits, tabelaIntov.length));
                            // System.out.println("z --> " + z);

                            if (tabelaIntov[z] != -10101111
                                    && (tabelaIntov[Math.min(Math.min(digits, tabelaIntov.length), parts.length)
                                            - 1] != 0)) {
                                // geekwrite.write(tabelaIntov[z] + ",");
                                if (temp > 0) {
                                    geekwrite.write(",");
                                }
                                geekwrite.write(tabelaIntov[z] + "");
                                temp++;

                            }

                        }
                        // System.out.println();
                        geekwrite.newLine();

                    }
                    // ce je =
                    if (pogojSplitann[1].equals("=")) {
                        int[] pogojSplitannInt = new int[steviloPogojev];
                        int l = 2;
                        if (l == 2) {
                            int pogojInt = Integer.parseInt(pogojSplitann[l]);
                            pogojSplitannInt[l] = pogojInt;
                        }
                        for (int o = 0; o < Math.min(Math.min(digits, tabelaIntov.length), parts.length); o++) {
                            if (tabelaIntov[o] != pogojSplitannInt[2]) {
                                tabelaIntov[o] = -10101111;
                            }
                        }

                        int trenutnoSt = 0;
                        int temp = 0;

                        for (int z = 0; z < Math.min(Math.min(digits, tabelaIntov.length), parts.length); z++) {
                            // System.out.println("Math.min --> " + Math.min(digits, tabelaIntov.length));
                            // System.out.println("z --> " + z);

                            if (tabelaIntov[z] != -10101111
                                    && (tabelaIntov[Math.min(Math.min(digits, tabelaIntov.length), parts.length)
                                            - 1] != 0)) {
                                // geekwrite.write(tabelaIntov[z] + ",");
                                if (temp > 0) {
                                    geekwrite.write(",");
                                }
                                geekwrite.write(tabelaIntov[z] + "");
                                temp++;

                            }

                        }

                        // System.out.println();

                        geekwrite.newLine();

                    }

                }
                if (pogojSplitann[0].equals("p")) {
                    // za +
                    if (pogojSplitann[1].equals("+")) {

                        int[] pogojSplitannInt = new int[steviloPogojev];
                        int l = 2;
                        if (l == 2) {
                            int pogojInt = Integer.parseInt(pogojSplitann[l]);
                            pogojSplitannInt[l] = pogojInt;
                        }
                        for (int p = 0; p < Math.min(Math.min(digits, tabelaIntov.length), parts.length); p++) {

                            if (tabelaIntov[p] != -10101111) {
                                tabelaIntov[p] += pogojSplitannInt[2];
                            }

                        }
                        int trenutnoSt = 0;
                        int temp = 0;

                        for (int z = 0; z < Math.min(Math.min(digits, tabelaIntov.length), parts.length); z++) {
                            // System.out.println("Math.min --> " + Math.min(digits, tabelaIntov.length));
                            // System.out.println("z --> " + z);

                            if (tabelaIntov[z] != -10101111) {
                                // geekwrite.write(tabelaIntov[z] + ",");
                                if (temp > 0) {
                                    geekwrite.write(",");
                                }
                                geekwrite.write(tabelaIntov[z] + "");
                                temp++;

                            }

                        }
                        // System.out.println();
                        geekwrite.newLine();

                    }
                    // za * DELA
                    if (pogojSplitann[1].equals("*")) {
                        int[] pogojSplitannInt = new int[steviloPogojev];
                        int l = 2;
                        if (l == 2) {
                            int pogojInt = Integer.parseInt(pogojSplitann[l]);
                            pogojSplitannInt[l] = pogojInt;
                        }
                        // System.out.println("pogojSplitanint 2 -->" + pogojSplitannInt[2]);

                        for (int p = 0; p < Math.min(Math.min(digits, tabelaIntov.length), parts.length); p++) {
                            if (tabelaIntov[p] != -10101111) {
                                tabelaIntov[p] = tabelaIntov[p] * pogojSplitannInt[2];
                            }
                            // System.out.println("tabela intov -->" + tabelaIntov[p]);
                            // System.out.println("faktor -->" + pogojSplitannInt[2]);
                            // System.out.println("zmnožek -->" + (tabelaIntov[p] * pogojSplitannInt[2]));

                        }
                        // System.out.println("Izpis tabele pri >:");
                        int trenutnoSt = 0;
                        int temp = 0;

                        for (int z = 0; z < Math.min(digits, tabelaIntov.length); z++) {
                            // System.out.println("Math.min --> " + Math.min(digits, tabelaIntov.length));
                            // System.out.println("z --> " + z);
                            // System.out.println("digits --> " + (digits + 1));
                            // System.out.println("tabelaIntov.length --> " + tabelaIntov.length);

                            if (tabelaIntov[z] != -10101111 && (tabelaIntov[z] != 0 && pogojSplitannInt[2] != 0)) {
                                // geekwrite.write(tabelaIntov[z] + ",");
                                if (temp > 0) {
                                    geekwrite.write(",");
                                }
                                geekwrite.write(tabelaIntov[z] + "");
                                temp++;

                            }

                        }

                        // System.out.println();
                        geekwrite.newLine();

                    }

                }
                if (pogojSplitann[0].equals("z")) {
                    // za +
                    if (pogojSplitann[1].equals("+")) {
                        int vsota = 0;
                        for (int z = 0; z < Math.min(digits, tabelaIntov.length); z++) {
                            if (tabelaIntov[z] != -10101111) {
                                vsota = vsota + tabelaIntov[z];
                            }

                        }
                        // System.out.println("vsota -->" + vsota);
                        geekwrite.write(Integer.toString(vsota));

                        geekwrite.newLine();

                    }
                    // za *
                    if (pogojSplitann[1].equals("*")) {
                        int zmnozek = 0;
                        for (int z = 0; z < Math.min(digits, tabelaIntov.length); z++) {

                            if (tabelaIntov[z] != -10101111) {
                                zmnozek *= tabelaIntov[z];
                            }

                        }
                        // System.out.println("zmnozek -->" + zmnozek);
                        geekwrite.write(Integer.toString(zmnozek));

                        geekwrite.newLine();

                    }
                }
            }
            geekwrite.close();
            sc.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        final long endTime = System.nanoTime();
        System.out.println("Total execution time: " + (endTime - startTime) + "ns");
    }
}