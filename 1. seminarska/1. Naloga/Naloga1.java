import java.util.*;
import java.io.*;
import java.lang.*;

public class Naloga1 {
  final long startTime = System.nanoTime();
  // For searching in all 8 direction
  static int[] xSmer = { -1, -1, -1, 0, 0, 1, 1, 1 };
  static int[] ySmer = { -1, 0, 1, -1, 1, -1, 0, 1 };
  public static int[] besedaNaX;

  public static void main(String[] args) {
    // Instant start = Instant.now();
    final long startTime = System.nanoTime();

    BufferedReader geek_file = null;
    try {

      geek_file = new BufferedReader(new FileReader(args[0]));

      // read int-a
      String trenutnaVrstica = geek_file.readLine();
      String[] values = trenutnaVrstica.split(",");
      int H = Integer.parseInt(values[0]); // visina
      int S = Integer.parseInt(values[1]); // sirina

      char[][][] matrika = new char[H][S][2];

      for (int i = 0; i < H; i++) {
        trenutnaVrstica = geek_file.readLine(); // vrsitca
        String[] stevilka = trenutnaVrstica.split(",");

        for (int j = 0; j < S; j++) {
          matrika[i][j][0] = stevilka[j].charAt(0);
          matrika[i][j][1] = '"';
        }
      }

      // preberemo stevilo besed
      int z = Integer.parseInt(geek_file.readLine());
      String[] najdeneBesede = new String[z];
      besedaNaX = new int[z];

      for (int i = z - 1; i >= 0; i--) { // dajemo besede od nazaj v matrika
        najdeneBesede[i] = geek_file.readLine();
        besedaNaX[i] = (-1) * (i - z) - 1; // tko spravimo besede notr
      }

      najdeneBesede = uredi(najdeneBesede, besedaNaX);

      Integer[][] matrikaRez = new Integer[z][4];

      // konec sortiranja, rekurzija:
      // creates a FileWriter Object
      FileWriter writer = new FileWriter(args[1]);

      if (resi(matrika, najdeneBesede, 0, writer, matrikaRez)) {

        // izpis besed po originalnem vrstnem redu
        for (int i = 0; i < najdeneBesede.length; i++) {
          for (int j = 0; j < z; j++) {
            if (besedaNaX[j] == i) {

              writer.write(
                  najdeneBesede[j] + "," + matrikaRez[j][0] + "," + matrikaRez[j][1] + "," + matrikaRez[j][2] + ','
                      + matrikaRez[j][3] + '\n');

            }
          }
        }
        // System.out.println("");
        // System.out.println("u did ittttt!!!");
      } else {
      }

      // Writes the content to the file
      writer.flush();
      writer.close();

    } catch (IOException e) {

      e.printStackTrace();

    } finally {
      try {
        if (geek_file != null)
          geek_file.close();
      } catch (IOException ex) {
        ex.printStackTrace();
      }
    }
    final long endTime = System.nanoTime();
    System.out.println("Total execution time: " + (endTime - startTime) + "ns");

  }

  static boolean resi(char[][][] pozicija, String[] besede, int index, FileWriter writer, Integer[][] matrikaRez)
      throws IOException {

    // robni pogoj
    if (index >= besede.length) {
      return true;
    }
    // ce so se besede za iskat
    else {

      // izberemo besedo in njeno prvo crko
      String beseda = besede[index];
      char prviZnak = beseda.charAt(0);

      // gremo po matriki
      // vrstica
      for (int i = 0; i < pozicija.length; i++) {

        // stolpci
        for (int j = 0; j < pozicija[0].length; j++) {

          // poiscemp ce ni izpolnjeno pozicija in prva crka je enaka
          if (pozicija[i][j][1] == '"' && prviZnak == pozicija[i][j][0]) {

            // ce je samo 1 crka
            if (beseda.length() == 1) {

              pozicija[i][j][1] = '!';

              if (resi(pozicija, besede, index + 1, writer, matrikaRez)) { // rek klic
                matrikaRez[index][0] = matrikaRez[index][2] = i;
                matrikaRez[index][1] = matrikaRez[index][3] = j;
                return true;
              }
              // ce ne returnamo, vpisemo "
              pozicija[i][j][1] = '"';

            }

            // preverjamo v vse smeri, ce jo lahko vnesemo
            for (int smer = 7; smer >= 0; smer--) {

              if (searchMatrix(beseda, smer, pozicija, i, j)) {

                // lahko jo vpisemo (true vrednost, ker vnasamo !. v primeru false
                // pisemo
                vnesi(beseda, smer, pozicija, i, j, true);

                if (resi(pozicija, besede, index + 1, writer, matrikaRez)) {
                  int[] tabela = koncneKoordinate(beseda.length(), i, j, smer);

                  matrikaRez[index][0] = i;
                  matrikaRez[index][1] = j;
                  matrikaRez[index][2] = tabela[0];
                  matrikaRez[index][3] = tabela[1];

                  return true;
                }
                // ne gre skuzi
                vnesi(beseda, smer, pozicija, i, j, false);

              }
            }
          }
        }
      } // konec sprehajanja po matriki
      return false;
    }
  }

  static boolean searchMatrix(String beseda, int smer, char[][][] pozicija, int i, int j) {
    int sirina = pozicija.length;
    int visina = pozicija[0].length;
    int count = 0;
    int besedaLen = beseda.length();

    // preverimo od druge crkne naprej
    for (int x = 1; x < besedaLen; x++) {
      char crka = beseda.charAt(x);

      // desno
      if (smer == 0 && (j + x) < sirina && pozicija[i][j + x][1] == '"' && pozicija[i][j + x][0] == crka) {
        count++;
      }
      // desno gor
      if (smer == 1 && (i - x) >= 0 && (j + x) < sirina && pozicija[i - x][j + x][1] == '"'
          && pozicija[i - x][j + x][0] == crka) {
        count++;
      }
      // gor
      if (smer == 2 && (i - x) >= 0 && pozicija[i - x][j][1] == '"' && pozicija[i - x][j][0] == crka) {
        count++;
      }
      // levo gor
      if (smer == 3 && (i - x) >= 0 && (j - x) >= 0 && pozicija[i - x][j - x][1] == '"'
          && pozicija[i - x][j - x][0] == crka) {
        count++;
      }
      // levo
      if (smer == 4 && (j - x) >= 0 && pozicija[i][j - x][1] == '"' && pozicija[i][j - x][0] == crka) {
        count++;
      }
      // levo dol
      if (smer == 5 && (i + x) < visina && (j - x) >= 0 && pozicija[i + x][j - x][1] == '"'
          && pozicija[i + x][j - x][0] == crka) {
        count++;
      }
      // dol
      if (smer == 6 && (i + x) < visina && pozicija[i + x][j][1] == '"' && pozicija[i + x][j][0] == crka) {
        count++;
      }
      // desno dol
      if (smer == 7 && (i + x) < visina && (j + x) < sirina && pozicija[i + x][j + x][1] == '"'
          && pozicija[i + x][j + x][0] == crka) {
        count++;
      }
    }

    if (count == besedaLen - 1)
      return true;
    return false;

  }

  static int[] koncneKoordinate(int dolzina, int i, int j, int smer) {
    dolzina = dolzina - 1;
    int[] tabela = { i, j };
    if (smer == 0) {
      tabela[1] += dolzina; // za desno povecas samo j
    }
    if (smer == 1) { // desno gor
      tabela[0] -= dolzina; // za dol gor
      tabela[1] += dolzina; // za dol desno
    }
    if (smer == 2) { // gor
      tabela[0] -= dolzina;
    }
    if (smer == 3) {
      tabela[0] -= dolzina;
      tabela[1] -= dolzina;
    }
    if (smer == 4) { // levo
      tabela[1] -= dolzina;
    }
    if (smer == 5) {
      tabela[0] += dolzina;
      tabela[1] -= dolzina;
    }
    if (smer == 6) { // dol
      tabela[0] += dolzina;
    }
    if (smer == 7) {
      tabela[0] += dolzina;
      tabela[1] += dolzina;
    }
    return tabela;
  }

  static void vnesi(String beseda, int smer, char[][][] pozicija, int i, int j, boolean vnesi) {
    int besedeNavzdol = beseda.length();

    for (int x = 0; x < besedeNavzdol; x++) {

      if (vnesi) {
        pozicija[i][j][1] = '!';
      } else {
        pozicija[i][j][1] = '"';
      }

      if (smer == 0) {
        j++;
      } else if (smer == 1) {
        j++;

        i--;
      } else if (smer == 2) {
        i--;
      } else if (smer == 3) {
        i--;
        j--;
      } else if (smer == 4) {
        j--;
      } else if (smer == 5) {
        i++;
        j--;
      } else if (smer == 6) {
        i++;
      } else if (smer == 7) {
        i++;
        j++;
      }
    }
  }

  public static String[] uredi(String[] besede, int[] besedaNaX) {

    for (int i = 0; i < besede.length; i++) {
      String beseda = besede[i];
      int idx = besedaNaX[i];

      // Dodaj beseda na j na tapravo mesto od ozadi
      int j = i - 1;
      while (j >= 0 && beseda.length() > besede[j].length()) {
        besede[j + 1] = besede[j];
        besedaNaX[j + 1] = besedaNaX[j];
        j--;
      }
      besede[j + 1] = beseda;
      besedaNaX[j + 1] = idx;
    }
    return besede;
  }

}
