import java.io.*;
import java.util.*;

public class Naloga8 {

    private static boolean obstajaPovezava(String prviOtok, String drugiOtok, Map<String, Set<String>> povezave) {
        Set<String> obdelaniOtoki = new HashSet<>();
        Queue<String> queue = new LinkedList<>();
        queue.add(prviOtok);
        obdelaniOtoki.add(prviOtok);
        while (!queue.isEmpty()) {
            String trenutniOtok = queue.poll();
            if (trenutniOtok.equals(drugiOtok)) {
                return true;
            }
            for (String povezaniOtoki : povezave.get(trenutniOtok)) {
                if (!obdelaniOtoki.contains(povezaniOtoki)) {
                    queue.add(povezaniOtoki);
                    obdelaniOtoki.add(povezaniOtoki);
                }
            }
        }
        return false;
    }
    public static void main(String[] args) throws IOException {
        //long startTime = System.nanoTime();
        BufferedReader br = new BufferedReader(new FileReader(args[0]));
        int stPovezav = Integer.parseInt(br.readLine());

        Map<String, Set<String>> povezave = new HashMap<>();


        PrintWriter pw = new PrintWriter(new OutputStreamWriter(new FileOutputStream(args[1])));

        for (int i = 0; i < stPovezav; i++) {
            String[] otoki = br.readLine().split(",");
            String prviOtok = otoki[0];
            String drugiOtok = otoki[1];


            if (povezave.containsKey(prviOtok) && povezave.containsKey(drugiOtok)) {
                if (obstajaPovezava(prviOtok, drugiOtok, povezave)) {
                    pw.println(prviOtok + "," + drugiOtok);
                }
            }
            Set<String> povezaveOtoka1 = povezave.getOrDefault(prviOtok, new HashSet<>());
            povezaveOtoka1.add(drugiOtok);
            povezave.put(prviOtok, povezaveOtoka1);

            Set<String> povezaveOtoka2 = povezave.getOrDefault(drugiOtok, new HashSet<>());
            povezaveOtoka2.add(prviOtok);
            povezave.put(drugiOtok, povezaveOtoka2);
        }
        br.close();
        pw.close();
        //long endTime   = System.nanoTime();
        //long totalTime = endTime - startTime;
        //System.out.println(totalTime/1000000000d);
    }

    
}