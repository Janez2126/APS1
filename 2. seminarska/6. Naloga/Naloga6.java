import java.io.*;
import java.util.*;

public class Naloga6 {
    public static void main(String[] args) throws IOException {

        //long startTime = System.nanoTime();
        BufferedReader br = new BufferedReader(new FileReader(args[0]));
        String[] uspesnostProdaje = br.readLine().split(",");
        String[] nINm = br.readLine().split(",");
        int n = Integer.parseInt(nINm[0]);
        int m = Integer.parseInt(nINm[1]);

        Double [] prodajaCaja=new Double[uspesnostProdaje.length+1];

        for (int i = 0; i < uspesnostProdaje.length; i++) {
            prodajaCaja[i+1]=Double.parseDouble(uspesnostProdaje[i]);
        }

        Map<Integer, List<Integer>> povezave = new HashMap<>();
        for (int i = 0; i < n; i++) {
            String[] aINb = br.readLine().split(",");
            int a = Integer.parseInt(aINb[0]);
            int b = Integer.parseInt(aINb[1]);
            if (!povezave.containsKey(a)) {
                povezave.put(a, new ArrayList<>());
            }
            povezave.get(a).add(b);
            if (!povezave.containsKey(b)) {
                povezave.put(b, new ArrayList<>());
            }
            povezave.get(b).add(a);
        }

        Map<List<Integer>, Integer> turisti = new HashMap<>();
        for (int i = 0; i < m; i++) {
            String[] dejstvo = br.readLine().split(",");
            int a = Integer.parseInt(dejstvo[0]);
            int b = Integer.parseInt(dejstvo[1]);
            int c = Integer.parseInt(dejstvo[2]);
            List<Integer> route = najdiPot(povezave, a, b);
            //System.out.println(route);
            if (route == null) {
                continue;
            }
            if (turisti.containsKey(route)) {
                turisti.put(route, turisti.get(route) + c);
            } else {
                turisti.put(route, c);
            }
        }

        int maxProdajeLokacija = prodajaCaja.length+1;
        int maxTuristiLokacija = prodajaCaja.length+1;
        double maxProdaje = 0;
        int maxTuristi = 0;
        double skupajProdaja=0;
        for (int i = 1;i<prodajaCaja.length;i++) {
            skupajProdaja=0;
            double uspesnostProdajei = prodajaCaja[i];
            int mimoidoci = 0;
            for (Map.Entry<List<Integer>, Integer> pot : turisti.entrySet()) {
                List<Integer> route = pot.getKey();
                if (route.contains(i)) {
                    mimoidoci += pot.getValue();
                }
            }
            skupajProdaja=mimoidoci*uspesnostProdajei;
            if (skupajProdaja > maxProdaje) {
                maxProdaje = skupajProdaja;
                maxProdajeLokacija = i;
            } else if(skupajProdaja==maxProdaje && i<maxProdajeLokacija){
                maxProdaje = skupajProdaja;
                maxProdajeLokacija = i;
            }
            if (mimoidoci > maxTuristi) {
                maxTuristi = mimoidoci;
                maxTuristiLokacija = i;
            }else if(mimoidoci == maxTuristi && i<maxTuristiLokacija){
                maxTuristi = mimoidoci;
                maxTuristiLokacija = i;
            }
        }

        PrintWriter pw = new PrintWriter(new OutputStreamWriter(new FileOutputStream(args[1])));
        pw.println(maxProdajeLokacija + "," + maxTuristiLokacija);
        pw.close();
        br.close();
        //long endTime   = System.nanoTime();
        //long totalTime = endTime - startTime;
        //System.out.println(totalTime/1000000000d);
    }

    private static List<Integer> najdiPot(Map<Integer, List<Integer>> povezave, int zacetek, int konec) {
        // BFS in urejanje po velikosti
        Queue<List<Integer>> queue = new LinkedList<>();
        Set<Integer> visited = new HashSet<>();
        List<Integer> startList = new ArrayList<>();
        startList.add(zacetek);
        queue.add(startList);
        List<Integer> result = null;
        while (!queue.isEmpty()) {
            List<Integer> route = queue.poll();
            int last = route.get(route.size() - 1);
            if (last == konec) {
                if (result == null || vecja(route, result)) {
                    result = route;
                }
            }
            visited.add(last);
            for (int next : povezave.get(last)) {
                if (!visited.contains(next)) {
                    List<Integer> nextRoute = new ArrayList<>(route);
                    nextRoute.add(next);
                    queue.add(nextRoute);
                }
            }
        }
        return result;
    }
    

    private static boolean vecja(List<Integer> route, List<Integer> other) {
        for (int i = 0; i < route.size(); i++) {
            if (route.get(i) > other.get(i)) {
                return true;
            } else if (route.get(i) < other.get(i)) {
                return false;
            }
        }
        return false;
    }
}
