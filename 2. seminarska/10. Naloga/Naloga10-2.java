import java.io.*;
import java.util.*;

public class Naloga10 {

    public static class Node {

        char oznaka;
        ArrayList<Node> sinovi = new ArrayList<>();

        public Node(char oznaka, ArrayList<Node> arr) {
            this.oznaka = oznaka;
            this.sinovi = arr;
        }
    }

    public static boolean isSubTree(Node trenutno, Node cel) {

        if (trenutno.oznaka != cel.oznaka)
            return false;

        Queue<Node> queue_trenutno = new LinkedList<>();
        Queue<Node> queue_cel = new LinkedList<>();

        queue_trenutno.add(trenutno);
        queue_cel.add(cel);

        while (!queue_cel.isEmpty()) {
            Node temp_trenutno = queue_trenutno.poll();
            Node temp_cel = queue_cel.poll();
            ArrayList<Node> sinovi_trenutno = temp_trenutno.sinovi;
            ArrayList<Node> sinovi_cel = temp_cel.sinovi;

            if (sinovi_cel.size() > 0) {
                if (sinovi_trenutno.size() != sinovi_cel.size())
                    return false;
            }
            for (int i = 0; i < sinovi_cel.size(); i++) {
                queue_cel.add(sinovi_cel.get(i));
                queue_trenutno.add(sinovi_trenutno.get(i));
                if (sinovi_cel.get(i).oznaka != sinovi_trenutno.get(i).oznaka)
                    return false;
            }
        }
        return true;
    }

    public static void main(String[] args) throws IOException {

        FileInputStream inputStream = new FileInputStream(args[0]);
        InputStreamReader reader = new InputStreamReader(inputStream, "UTF-8");
        BufferedReader bufferedReader = new BufferedReader(reader);

        PrintWriter pisi = new PrintWriter(new File(args[1]), "UTF-8");

        String vrstica = bufferedReader.readLine();
        int prvo = Integer.parseInt(vrstica);
        HashMap<Integer, Node> prvoTree = new HashMap<>();
        HashMap<Integer, Node> drugoTree = new HashMap<>();
        for (int i = 0; i < prvo; i++) {
            vrstica = bufferedReader.readLine();
            String[] s = vrstica.split(",");
            if (prvoTree.containsKey(Integer.parseInt(s[0]))) {
                prvoTree.get(Integer.parseInt(s[0])).oznaka = s[1].charAt(0);
            } else {
                Node n = new Node(s[1].charAt(0), new ArrayList<Node>());
                prvoTree.put(Integer.parseInt(s[0]), n);
            }
            if (s.length > 2)
                for (int j = 2; j < s.length; j++) {
                    Node n1 = new Node(' ', new ArrayList<Node>());
                    prvoTree.put(Integer.parseInt(s[j]), n1);
                    prvoTree.get(Integer.parseInt(s[0])).sinovi.add(n1);
                }
        }
        vrstica = bufferedReader.readLine();
        int drugoTreeo = Integer.parseInt(vrstica);
        for (int i = 0; i < drugoTreeo; i++) {
            vrstica = bufferedReader.readLine();
            String[] s = vrstica.split(",");
            if (drugoTree.containsKey(Integer.parseInt(s[0]))) {
                drugoTree.get(Integer.parseInt(s[0])).oznaka = s[1].charAt(0);
            } else {
                Node n = new Node(s[1].charAt(0), new ArrayList<Node>());
                drugoTree.put(Integer.parseInt(s[0]), n);
            }
            if (s.length > 2)
                for (int j = 2; j < s.length; j++) {
                    Node n1 = new Node(' ', new ArrayList<Node>());
                    drugoTree.put(Integer.parseInt(s[j]), n1);
                    drugoTree.get(Integer.parseInt(s[0])).sinovi.add(n1);
                }
        }

        int stevec = 0;

        for (Integer i : drugoTree.keySet()) {
            if (drugoTree.get(i).oznaka == prvoTree.get(1).oznaka) {
                if (prvoTree.get(1).sinovi.size() == 0) {
                    stevec++;
                    continue;
                }
            }
            if (isSubTree(drugoTree.get(i), prvoTree.get(1))) {
                stevec++;
            }
        }

        pisi.print(stevec + "\n");
        pisi.close();
    }
}