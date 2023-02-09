import java.io.*;
import java.util.*;

/**
 * Naloga10
 */
public class Naloga10 {
  public static class Node{
    int id;
    int crka;
    ArrayList<Integer> otroci;
    
    public Node(int id, int crka){
      this.id=id;
      this.crka=crka;
      otroci=new ArrayList<>();
    }

    public void dodajOtroka(int otrok){
      otroci.add(otrok);
    }

    public int stOtrok(){
      return otroci.size();
    }
  }
  public static void main(String[] args) throws IOException{
    BufferedReader br = new BufferedReader(new FileReader(args[0]));
    int stIskano=Integer.parseInt(br.readLine());
    boolean flag=false;
    char iskaniZnak='0';
    int pojavitev=0;
    if(stIskano==1){
      flag=true;
    }
    HashMap<Integer,Node>iskanoDrevo=new HashMap<>();
    for(int i=0;i<stIskano;i++){
      String [] buffer=br.readLine().split(",");
      int ID = Integer.parseInt(buffer[0]);
      char crka=buffer[1].charAt(0);
      
      if(flag){
        iskaniZnak=crka;
      }
      Node tmp=new Node(ID, crka);
      for(int j=2;j<buffer.length;j++){
        tmp.dodajOtroka(Integer.parseInt(buffer[j]));
      }
      iskanoDrevo.put(ID, tmp);
    }

    int stCiljno=Integer.parseInt(br.readLine());
    HashMap<Integer,Node>ciljnoDrevo=new HashMap<>();
    for(int i=0;i<stCiljno;i++){
      String [] buffer=br.readLine().split(",");
      int ID = Integer.parseInt(buffer[0]);
      char crka=buffer[1].charAt(0);
      if(crka==iskaniZnak)pojavitev++;
      Node tmp=new Node(ID, crka);
      for(int j=2;j<buffer.length;j++){
        tmp.dodajOtroka(Integer.parseInt(buffer[j]));
      }
      ciljnoDrevo.put(ID, tmp);
    }
    PrintWriter pw = new PrintWriter(new OutputStreamWriter(new FileOutputStream(args[1])));
    if(flag){
      pw.println(pojavitev);
      pw.close();
      br.close();
      return;
    }
    Node rootI=iskanoDrevo.get(1);
    Node rootC=ciljnoDrevo.get(1);
    pw.println(prestejPojavitve(rootI, rootC, iskanoDrevo, ciljnoDrevo));
    pw.close();
    br.close();
    //System.out.println(prestejPojavitve(rootI, rootC, iskanoDrevo, ciljnoDrevo));
    //System.out.println(rootP.id+" "+rootP.crka+" "+rootP.otroci.toString());

  }

  public static boolean primerjaj(Node rootI,Node rootC,HashMap<Integer,Node> iskano,HashMap<Integer,Node> ciljno){
    if(rootI.otroci.size()==0){
      return rootI.crka==rootC.crka;
    }
    if(rootI.crka!=rootC.crka){
      return false;
    }
    if(rootI.stOtrok()!=rootC.stOtrok()){
      return false;
    }
    int stOtrok=rootI.stOtrok();
    for(int i=0;i<stOtrok;i++){
      if(!primerjaj(iskano.get(rootI.otroci.get(i)), ciljno.get(rootC.otroci.get(i)), iskano, ciljno))return false;
    }
    return true;

  }
  
  public static int prestejPojavitve(Node rootI,Node rootC,HashMap<Integer,Node> iskano,HashMap<Integer,Node> ciljno){
    int stevec=0;
    if(iskano.get(rootI.id).crka==ciljno.get(rootC.id).crka){
      if(primerjaj(rootI, rootC, iskano, ciljno)){
        stevec++;
      }
    }
    if(rootC.stOtrok()==0){
      return 0;
    }
    for (Integer id_otrok : rootC.otroci) {
      stevec+=prestejPojavitve(rootI, ciljno.get(id_otrok), iskano, ciljno);
    }

    return stevec;
  }
}