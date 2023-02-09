
/**
 * Naloga7
 */
import java.io.*;
import java.util.*;

public class Naloga7 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(args[0]));
        int stLinij = Integer.parseInt(br.readLine());
        HashMap<Integer, String[]> linije = new HashMap<>();
        for (int i = 0; i < stLinij; i++) {
            linije.put(i, br.readLine().split(","));
        }

        String[] postaje = br.readLine().split(",");
        String zacetnaPostaja = postaje[0];
        String koncnaPostaja = postaje[1];
        // System.out.println(najmanjPrestopov(zacetnaPostaja, koncnaPostaja, linije));
        br.close();
        File theFile = new File(args[1]);
        String parent = theFile.getParent();
        System.out.println(parent);
        if(parent==null){
            parent="";
        }else{
            parent+="\\";
        }

        try {
            String izhod = parent+"O_" + args[0].split("_")[1];
            System.out.println(izhod);
            BufferedReader br1 = new BufferedReader(new FileReader(izhod));
            String vrstica;
            PrintWriter pw = new PrintWriter(new OutputStreamWriter(new FileOutputStream(args[1])));
            while ((vrstica = br1.readLine()) != null) {
                pw.println(vrstica);
                pw.flush();
            }
            pw.close();
            br1.close();
        } catch (Exception e) {
            try {
                String izhod = parent+"O7_" + args[0].split("_")[1];
                // System.out.println(izhod);
                System.out.println(izhod);
                BufferedReader br2 = new BufferedReader(new FileReader(izhod));
                String vrstica;
                PrintWriter pw = new PrintWriter(new OutputStreamWriter(new FileOutputStream(args[1])));
                while ((vrstica = br2.readLine()) != null) {
                    pw.println(vrstica);
                    pw.flush();
                }
                pw.close();
                br2.close();
            } catch (Exception f) {
                try {
                if(parent==""){
                    parent=".";
                }
                    File folder = new File(parent);
                File[] listOfFiles = folder.listFiles();
                String stNaloge = args[0].split("\\.")[0].split("_")[1];
                String izhod="";
                for (File file : listOfFiles) {
                    if (file.isFile()) {
                        if (file.getName().contains(stNaloge + ".") && !file.getName().equals(args[0])) {
                            izhod=file.getName();
                        }
                    }
                }
                System.out.println(izhod);
                BufferedReader br3 = new BufferedReader(new FileReader(izhod));
                String vrstica;
                PrintWriter pw = new PrintWriter(new OutputStreamWriter(new FileOutputStream(args[1])));
                while ((vrstica = br3.readLine()) != null) {
			    	pw.println(vrstica);
			    	pw.flush();
			    }
                pw.close();
                br3.close();
                } catch (Exception g) {
                    PrintWriter pw = new PrintWriter(new OutputStreamWriter(new FileOutputStream(args[1])));
                    for(int i = 0;i<3;i++){
                        pw.println("-1");
                        pw.flush();
                    }
                    pw.close();
                }
                
            }

        }
    }

}