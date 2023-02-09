import java.io.*;
import java.util.Arrays;


/**
 * Naloga5
 */

class QueueElement
{
	Object element;
	QueueElement next;

	QueueElement()
	{
		element = null;
		next = null;
	}
}

class Queue
{
	private QueueElement front;
	private QueueElement rear;
	
	public Queue()
	{
		makenull();
	}
	
	public void makenull()
	{
		front = null;
		rear = null;
	}
	
	public boolean empty()
	{
		return (front == null);
	}
	
	public Object front()
	{
		if (!empty())
			return front.element;
		else
			return null;
	}
	
	public void enqueue(Object obj)
	{
		QueueElement el = new QueueElement();
		el.element = obj;
		el.next = null;
		
		if (empty())
		{
			front = el;
		}
		else
		{
			rear.next = el;
		}
		
		rear = el;
	}
	
	public void dequeue()
	{
		if (!empty())
		{
			front = front.next;
			
			if (front == null)
				rear = null;
		}
	}
}

class SetElement {
    Slika element;
    SetElement next;
}

class Set {
    private SetElement first;

    void add(Slika premik) {
        SetElement e = new SetElement();
        e.element = premik;
        e.next = first;
        first = e;
    }

    boolean exists(Slika premik) {
        SetElement e = first;
        while (e != null) {

            if (premik.primerjaj(e.element.trenutno,e.element.naVozicku,e.element.lokacijaVozicka)) return true;
            e = e.next;
        }
        return false;
    }
}

class MapOfHashes {
    Set[] table;

    MapOfHashes(int size) {
        table = new Set[size];
    }

    void add(Slika prem) {
        int index = prem.hashCode % table.length;
        if (table[index] == null) table[index] = new Set();
        table[index].add(prem);
    }

    boolean exists(Slika prem) {
        int index = prem.hashCode % table.length;
        if (table[index] == null) return false;
        return table[index].exists(prem);
    }

}


class Slika{
    public char[][] trenutno;
    public String navodila;
    public int lokacijaVozicka;
    public char naVozicku;
    public int hashCode=3;
    public int eval=0;
    public boolean flag=true;
    public Slika(char [][] trenutnoaaaaa,String navodilaaaaaa,int b,char c,int mode){
        //System.out.println(trenutnoaaaaa.length);
        trenutno=Arrays.stream(trenutnoaaaaa).map(a ->  Arrays.copyOf(a, a.length)).toArray(char[][]::new);
        navodila=navodilaaaaaa;
        lokacijaVozicka=b;
        naVozicku=c;
        switch (mode) {
            case 0:
                nalozi();
                break;
            case 1:
                odlozi();
                break;
            case 2:
                gor();
                break;
            case 3:
                dol();
                break;
            case 4:
                levo();
                break;
            case 5:
                desno();
                break;
            default:
                break;

        }
        for (int i = 0; i < trenutno.length; i++) {
            for (int j = 0; j < trenutno[i].length; j++) {
                hashCode = 13 * hashCode + trenutno[i][j]+lokacijaVozicka+naVozicku;
            }

        }
        hashCode=Math.abs(hashCode);
        //flag=true;
        
    }

    public boolean primerjaj(char[][]primerjava){
        return Arrays.deepEquals(trenutno, primerjava);
    }
    public boolean primerjaj(char[][]primerjava,char vozicek,int pos){
        return Arrays.deepEquals(trenutno, primerjava) && vozicek==naVozicku && pos==lokacijaVozicka;
    }

    void nalozi(){
        if(naVozicku==0){
            if(trenutno[lokacijaVozicka][0]!=0){
                naVozicku=trenutno[lokacijaVozicka][0];
                trenutno[lokacijaVozicka][0]=0;
            }else{
                flag=false;
            }
        }else{
            flag=false;
        }
    }
    void odlozi(){
        if(naVozicku!=0){
            if(trenutno[lokacijaVozicka][0]==0){
                trenutno[lokacijaVozicka][0]=naVozicku;
                naVozicku=0;
            }else{
                flag=false;
            }
        }else{
            flag=false;
        }
    }
    void gor(){
        //System.out.println(trenutno.length);
        if(Naloga5.vseCrke.indexOf(trenutno[lokacijaVozicka][trenutno[lokacijaVozicka].length-1])==-1){
            //System.out.println(trenutno[lokacijaVozicka][0]);
            //System.out.println(Naloga5.vseCrke.indexOf(trenutno[lokacijaVozicka][0]));
            System.arraycopy(trenutno[lokacijaVozicka], 0, trenutno[lokacijaVozicka], 1, trenutno.length-1);
            trenutno[lokacijaVozicka][0]=0;
        }else{
            flag=false;
        }
        
        
        
        
    }
    void dol(){
        if(Naloga5.vseCrke.indexOf(trenutno[lokacijaVozicka][0])==-1){
            System.arraycopy(trenutno[lokacijaVozicka], 1, trenutno[lokacijaVozicka], 0, trenutno.length-1);
            trenutno[lokacijaVozicka][trenutno.length-1]=0;
        }else{
            flag=false;
        }
        
        
    }

    void desno(){
        if(lokacijaVozicka+1>=trenutno.length){
            flag=false;
            return;
        }
        lokacijaVozicka++;
    }
    void levo(){
        if(lokacijaVozicka-1<0){
            flag=false;
            return;
        }
        lokacijaVozicka--;
    }
}

public class Naloga5 {
    
    public static char[][] jebemtiMaterResitev;
    public static String vseCrke;

    public static void main(String[] args) throws IOException{
        long startTime = System.nanoTime();
        BufferedReader br = new BufferedReader(new FileReader(args[0]));
        String [] input=br.readLine().split(",");
        int stTrakov=Integer.parseInt(input[0]);
        int dolzina=Integer.parseInt(input[1]);
        char[][]jebemtiMater=new char[stTrakov][dolzina];
        vseCrke="";
        jebemtiMaterResitev=new char[stTrakov][dolzina];
        for(int i = 0;i<stTrakov;i++){
            String []vrstica=br.readLine().split(":");
            int stevec=0;
            
                
            try{
                for(int j = 0;j<vrstica[1].length();j++){
                if(vrstica[1].charAt(j)==','){
                    stevec++;
                }else{
                    jebemtiMater[i][stevec]=vrstica[1].charAt(j);
                    j++;
                    stevec++;
                }
                }
            }catch(Exception e){
                continue;
            }
        }
        for(int i = 0;i<stTrakov;i++){
            String []vrstica=br.readLine().split(":");
            int stevec=0;
            try{
                for(int j = 0;j<vrstica[1].length();j++){
                if(vrstica[1].charAt(j)==','){
                    stevec++;
                }else{
                    vseCrke+=vrstica[1].charAt(j);
                    jebemtiMaterResitev[i][stevec]=vrstica[1].charAt(j);
                    j++;
                    stevec++;
                }
                }
            }catch(Exception e){
                continue;
            }  
        }

        //System.out.println(Arrays.deepEquals(jebemtiMater, jebemtiMater));
        MapOfHashes hashes = new MapOfHashes(5500000);
        Queue queue= new Queue();
        queue.makenull();
		
		Slika s = new Slika(jebemtiMater, "", 0,(char)0,7);

        if (s.primerjaj(jebemtiMaterResitev)) {
            print(s.navodila, args[1]);
            return;
        }
        queue.enqueue(s);
        hashes.add(s);
		/* */
		while (!queue.empty())
		{
			Slika tmp = (Slika)queue.front();
			queue.dequeue();
           // System.out.println(tmp.navodila);
            //System.out.println("bu");
            if (tmp.primerjaj(jebemtiMaterResitev)) {
                print(tmp.navodila, args[1]);
                long endTime   = System.nanoTime();
                long totalTime = endTime - startTime;
                System.out.println(totalTime/1000000000d);
                return;
            }else{
                Slika nova=new Slika(tmp.trenutno, tmp.navodila+";LEVO", tmp.lokacijaVozicka, tmp.naVozicku,4);
                if(nova.flag){
                    if (!hashes.exists(nova)) {
                        queue.enqueue(nova);
                        hashes.add(nova);
                        //System.out.println(Arrays.deepToString(nova.trenutno));
                    }
                }
                
                //System.out.println(Arrays.deepToString(nova.trenutno) );
                nova=new Slika(tmp.trenutno, tmp.navodila+";DESNO", tmp.lokacijaVozicka, tmp.naVozicku,5);
                if(nova.flag){
                    if (!hashes.exists(nova)) {
                        queue.enqueue(nova);
                        hashes.add(nova);
                    }
                }

                nova=new Slika(tmp.trenutno, tmp.navodila+";GOR", tmp.lokacijaVozicka, tmp.naVozicku,2);
                if(nova.flag){
                    if (!hashes.exists(nova)) {
                        queue.enqueue(nova);
                        hashes.add(nova);
                        //System.out.println(Arrays.deepToString(nova.trenutno));
                    }
                }

                nova=new Slika(tmp.trenutno, tmp.navodila+";DOL", tmp.lokacijaVozicka, tmp.naVozicku,3);
                if(nova.flag){
                    if (!hashes.exists(nova)) {
                        queue.enqueue(nova);
                        hashes.add(nova);
                    }
                }

                nova=new Slika(tmp.trenutno, tmp.navodila+";NALOZI", tmp.lokacijaVozicka, tmp.naVozicku,0);
                if(nova.flag){
                    if (!hashes.exists(nova)) {
                        queue.enqueue(nova);
                        hashes.add(nova);
                    }
                }

                nova=new Slika(tmp.trenutno, tmp.navodila+";ODLOZI", tmp.lokacijaVozicka, tmp.naVozicku,1);
                if(nova.flag){
                    if (!hashes.exists(nova)) {
                        queue.enqueue(nova);
                        hashes.add(nova);
                    }
                }
            }
            
		}
        /* 
        Slika tmp = new Slika(jebemtiMater, "", 0,(char)0,7);

        Slika nova=new Slika(tmp.trenutno, tmp.navodila+";LEVO", tmp.lokacijaVozicka, tmp.naVozicku,4);
        if (!hashes.exists(nova)) {
                    queue.enqueue(nova);
                }
        System.out.println("LEVO");
        System.out.println(Arrays.deepToString(nova.trenutno));
        System.out.println(nova.lokacijaVozicka);
        System.out.println(nova.naVozicku);
        System.out.println("-------------------------------------------------------------");
                nova=new Slika(tmp.trenutno, tmp.navodila+";DESNO", tmp.lokacijaVozicka, tmp.naVozicku,5);
                if (!hashes.exists(nova)) {
                    queue.enqueue(nova);
                }
                System.out.println("DESNO");
                System.out.println(Arrays.deepToString(nova.trenutno));
                System.out.println(nova.lokacijaVozicka);
                System.out.println(nova.naVozicku);
                System.out.println("-------------------------------------------------------------");
                nova=new Slika(tmp.trenutno, tmp.navodila+";GOR", tmp.lokacijaVozicka, tmp.naVozicku,2);
                if (!hashes.exists(nova)) {
                    queue.enqueue(nova);
                }
                System.out.println("GOR");
        System.out.println(Arrays.deepToString(nova.trenutno));
        System.out.println(nova.lokacijaVozicka);
        System.out.println(nova.naVozicku);
        System.out.println("-------------------------------------------------------------");
                nova=new Slika(tmp.trenutno, tmp.navodila+";DOL", tmp.lokacijaVozicka, tmp.naVozicku,3);
                if (!hashes.exists(nova)) {
                    queue.enqueue(nova);
                }
                System.out.println("DOL");
                System.out.println(Arrays.deepToString(nova.trenutno));
                System.out.println(nova.lokacijaVozicka);
                System.out.println(nova.naVozicku);
                System.out.println("-------------------------------------------------------------");
                nova=new Slika(tmp.trenutno, tmp.navodila+";NALOZI", tmp.lokacijaVozicka, tmp.naVozicku,0);
                if (!hashes.exists(nova)) {
                    queue.enqueue(nova);
                }
                System.out.println("NALOZI");
        System.out.println(Arrays.deepToString(nova.trenutno));
        System.out.println(nova.lokacijaVozicka);
        System.out.println(nova.naVozicku);
        System.out.println("-------------------------------------------------------------");
                nova=new Slika(tmp.trenutno, tmp.navodila+";ODLOZI", tmp.lokacijaVozicka, tmp.naVozicku,1);
                if (!hashes.exists(nova)) {
                    queue.enqueue(nova);
                }

                System.out.println("ODLOZI");
        System.out.println(Arrays.deepToString(nova.trenutno));
        System.out.println(nova.lokacijaVozicka);
        System.out.println(nova.naVozicku);
        System.out.println("-------------------------------------------------------------");
		*/
        //System.out.println(Arrays.deepToString(jebemtiMaterResitev));
        
        
        
    }
    static void print(String s, String file) throws IOException {
        PrintWriter pw = new PrintWriter(new OutputStreamWriter(new FileOutputStream(file)));
        for (String line : s.split(";"))
            if (!line.isEmpty())
                pw.println(line);
        pw.close();
    }
}