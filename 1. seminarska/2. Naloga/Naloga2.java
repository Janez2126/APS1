import java.util.*;
import java.io.*;
import java.lang.*;

public class Naloga2 {

    public static void main(String args[]) throws IOException {
        // čas
        final long startTime = System.nanoTime();
        //
        // System.out.println(args[0]);
        // System.out.println("MIHAMIHA");
        // System.out.println(args[1]);
        int stCiklov = 0;
        String stevilo = null;
        String randy = null;
        FileWriter geek_file;

        try {
            // the file to be opened for reading
            FileInputStream fis = new FileInputStream(args[0]);
            Scanner sc = new Scanner(fis); // file to be scanned
            geek_file = new FileWriter(args[1]);
            BufferedWriter geekwrite = new BufferedWriter(geek_file);

            // returns true if there is another line to read

            stCiklov = sc.nextInt();
            randy = sc.nextLine(); // "\n"
            stevilo = sc.nextLine();

            // System.out.println(stCiklov + " " + stevilo);
            /*
             * int[] zacetnoStevilo_fake = new int[stevilo.length()];
             * for (int i = 0; i < stevilo.length(); i++) {
             * zacetnoStevilo_fake[i] = stevilo.charAt(i) - '0';
             * }
             */
            // miro board
            // zakodiramo se enrkt stevilo
            Stack S1 = new Stack();
            Stack S2 = new Stack();
            int kodiranoStevilo[] = new int[stevilo.length()];
            int tabela2[] = new int[stevilo.length()];
            int[] zacetnoStevilo_fake = new int[stevilo.length()];
            int[] zacetnoStevilo = new int[stevilo.length()];
            // System.out.print("tabela intov prvic--> ");

            for (int i = 0; i < stevilo.length(); i++) {
                zacetnoStevilo_fake[i] = i;
                // System.out.println(zacetnoStevilo_fake[i]);
            }
            // System.out.println();
            for (int i = 0; i < stevilo.length(); i++) {
                zacetnoStevilo[i] = stevilo.charAt(i) - '0';
                // System.out.println(zacetnoStevilo_fake[i]);
            }
            for (int i = 0; i < stevilo.length(); i++) {
                zacetnoStevilo_fake[i] = i;
                // System.out.println(zacetnoStevilo_fake[i]);
            }
            // System.out.println();
            // korek 0--> 1
            // System.out.println("MIHAMIHA");

            int stevec = 0;
            int trenutnoStevilo = 0;

            while (stevec < stevilo.length()) {
                if (trenutnoStevilo % 2 == 0) {
                    // das stevilo[0,2,4,6,...] v vrsto tabelo2
                    tabela2[trenutnoStevilo] = zacetnoStevilo_fake[trenutnoStevilo];
                    // System.out.println(tabela2[trenutnoStevilo]);

                } else {
                    // das stevilo[1,3,5,7,...] v stack S2
                    S2.push(zacetnoStevilo_fake[trenutnoStevilo]);

                }
                stevec++;
                trenutnoStevilo++;
            }
            // korak 1--> 2
            // System.out.println("MIHAMIHA");

            stevec = 0;
            trenutnoStevilo = 0;
            int pozcija = 0;

            while (stevec < stevilo.length()) {

                if (trenutnoStevilo % 2 == 0) {
                    // stack1[trenutnoStevilo] = tabela2
                    // System.out.println("MIHAMIHA1");

                    S1.push(tabela2[pozcija]);
                    pozcija++;
                    pozcija++;

                    // System.out.print((S1.top()));

                } else {
                    // stack1[trenutnoStevilo] = stack2.pop
                    // System.out.println("MIHAMIHA2");

                    S1.push(S2.top());
                    S2.pop();
                    // System.out.print((S1.top()));

                }
                trenutnoStevilo++;
                stevec++;

            }

            // korak 2 --> 3
            // System.out.println("MIHAMIHA");
            for (int i = 0; i < stevilo.length(); i++) {
                tabela2[i] = -1;
            }
            stevec = 0;
            int stevecZatabelo2 = 0;
            while (!S1.empty()) {
                // S2 = S1.pop
                // S2 = S1.pop
                // S2 = S1.pop
                // V2 = S1.pop
                // V2 = S1.pop
                // V2 = S1.pop
                // po vsakem koraku preveri ce je S1 prazen ce ni nrdi
                // na sklad S2
                if (!S1.empty()) {
                    S2.push(S1.top());
                    S1.pop();
                    stevec++;
                    // System.out.print((S2.top()));
                }
                if (!S1.empty()) {
                    S2.push(S1.top());
                    S1.pop();
                    stevec++;
                    // System.out.print((S2.top()));

                }
                if (!S1.empty()) {
                    S2.push(S1.top());
                    S1.pop();
                    stevec++;
                    // System.out.print((S2.top()));

                }
                // tabela V2
                if (!S1.empty()) {
                    tabela2[stevecZatabelo2] = (int) S1.top();
                    S1.pop();
                    // System.out.print((tabela2[stevecZatabelo2]));

                    stevecZatabelo2++;

                }
                if (!S1.empty()) {
                    tabela2[stevecZatabelo2] = (int) S1.top();

                    S1.pop();
                    // System.out.print((tabela2[stevecZatabelo2]));

                    stevecZatabelo2++;

                }
                if (!S1.empty()) {
                    tabela2[stevecZatabelo2] = (int) S1.top();

                    S1.pop();
                    // System.out.print((tabela2[stevecZatabelo2]));

                    stevecZatabelo2++;

                }

            }
            // System.out.println();

            // korake 3--> 4
            // System.out.println("MIHAMIHA");

            // S2.pop
            // V2.prvielement
            trenutnoStevilo = 0;
            int zaTabelo2 = 0;
            stevec = 0;
            int stevecccccc = stevilo.length();
            for (int i = 0; i < stevecccccc; i++) {
                if (!S2.empty()) {
                    kodiranoStevilo[trenutnoStevilo] = (int) S2.top();
                    S2.pop();
                    // System.out.print(kodiranoStevilo[trenutnoStevilo]);
                    // System.out.print(trenutnoStevilo);
                    // System.out.println("TONCKA");

                    trenutnoStevilo++;
                    stevec++;

                }

                if (zaTabelo2 < tabela2.length && tabela2[zaTabelo2] != -1) {
                    kodiranoStevilo[trenutnoStevilo] = tabela2[zaTabelo2];
                    // System.out.print(kodiranoStevilo[trenutnoStevilo]);

                    // System.out.print(trenutnoStevilo);
                    // System.out.println("JANEZJANEZ");

                    zaTabelo2++;
                    trenutnoStevilo++;
                    stevec++;

                }
            }
            // System.out.println();

            // System.out.println();

            // System.out.print("po dodatnem kodiranju -->");

            // System.out.println();
            // System.out.print("zacetno stevilo -->");

            // System.out.println("index 99 rabela 1 --> " + kodiranoStevilo[0]);

            // mamo novo stevilo dodatno kodirano zdej rabimo ugotovit kku se zamaknjejo

            // zacetnoStevilo_fake
            // kljucPermutacije
            // System.out.println();

            int[] kljucPermutacije = new int[zacetnoStevilo_fake.length];
            for (int i = 0; i < zacetnoStevilo_fake.length; i++) {
                kljucPermutacije[i] = kodiranoStevilo[i];

                // System.out.println(" index --> " + getArrayIndex(zacetnoStevilo_fake,
                // kodiranoStevilo[i]));
            }
            // System.out.print("kljucPermutacije i--> ");
            // System.out.println();

            // System.out.println();

            // Simulacija N kodiranj
            int[] indeksi = new int[zacetnoStevilo_fake.length];
            for (int i = 0; i < zacetnoStevilo_fake.length; i++) {
                indeksi[i] = i;
            }
            // System.out.println();

            int[] noviIndeksi = new int[zacetnoStevilo_fake.length];
            for (int k = 0; k < stCiklov; k++) {
                // System.out.println("tanka k");
                for (int i = 0; i < stevilo.length(); i++) {

                    noviIndeksi[i] = indeksi[kljucPermutacije[i]];
                }
                indeksi = noviIndeksi.clone();
            }
            // Rezultat so indeksi po kodiranju
            // Za vsak indeks
            // System.out.println("OKneccc --> ");
            for (int i = 0; i < zacetnoStevilo_fake.length; i++) {
                for (int j = 0; j < stevilo.length(); j++) {
                    // Izpisemo vzporedno stevko
                    if (indeksi[j] == i) {
                        // System.out.print(zacetnoStevilo[j]);
                        geekwrite.write(Integer.toString(zacetnoStevilo[j]));

                    }
                }
            }
            geekwrite.newLine();

            sc.close();
            geekwrite.close();

            // System.out.println();

        }
        // closes the scanner
        catch (

        IOException e) {
            e.printStackTrace();
        }

        // čas
        final long endTime = System.nanoTime();
        // System.out.println("Total execution time: " + (endTime - startTime) + "ns");
    }

    // 1
    // 148536729
    static public int getArrayIndex(int[] arr, int value) {

        int k = 0;
        for (int i = 0; i < arr.length; i++) {

            if (arr[i] == value) {
                k = i;
                break;
            }
        }
        return k;
    }

    static class StackElement {
        Object element;
        StackElement next;

        StackElement() {
            element = null;
            next = null;
        }
    }

    static class Stack {
        // StackElement -> StackElement -> StackElement -> ... -> StackElement
        // ^
        // |
        // top
        //
        // elemente dodajamo in brisemo vedno na zacetku seznama (kazalec top)

        private StackElement top;

        public Stack() {
            makenull();
        }

        public void makenull() {
            top = null;
        }

        public boolean empty() {
            return (top == null);
        }

        public Object top() {
            if (!empty())
                return top.element;
            else
                return null;
        }

        public void push(Object obj) {
            StackElement el = new StackElement();
            el.element = obj;
            el.next = top;

            top = el;
        }

        public void pop() {
            if (!empty()) {
                top = top.next;
            }
        }
    }

    static class QueueElement {
        Object element;
        QueueElement next;

        QueueElement() {
            element = null;
            next = null;
        }
    }

    static class Queue {
        // QueueElement -> QueueElement -> QueueElement -> ... -> QueueElement
        // front rear
        //
        // nove elemente dodajamo za element 'rear'
        // elemente jemljemo s front strani

        private QueueElement front;
        private QueueElement rear;

        public Queue() {
            makenull();
        }

        public void rear(String stevilo) {
        }

        public void makenull() {
            front = null;
            rear = null;
        }

        public boolean empty() {
            return (front == null);
        }

        public Object front() {
            if (!empty())
                return front.element;
            else
                return null;
        }

        public void enqueue(Object obj) {
            QueueElement el = new QueueElement();
            el.element = obj;
            el.next = null;

            if (empty()) {
                front = el;
            } else {
                rear.next = el;
            }

            rear = el;
        }

        public void dequeue() {
            if (!empty()) {
                front = front.next;

                if (front == null)
                    rear = null;
            }
        }
    }

}