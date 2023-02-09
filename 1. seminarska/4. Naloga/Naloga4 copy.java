import java.io.*;

class LinkedListElement {
    Object element;
    LinkedListElement next;
    LinkedListElement previous;

    LinkedListElement(Object obj) {
        element = obj;
        next = null;
        previous=null;
    }

    LinkedListElement(Object obj, LinkedListElement nxt,LinkedListElement prev) {
        element = obj;
        next = nxt;
        previous=prev;
    }
}

class LinkedList {
    public LinkedListElement first;
    public LinkedListElement last;
    public int length = 0;

    LinkedList() {
        makenull();
    }

    public void makenull() {
        first = new LinkedListElement(null, last,null);
        last = new LinkedListElement(null,null,first);
        first.next=last;
    }

    public void addLast(Object obj) {
        LinkedListElement newEl = new LinkedListElement(obj, null,null);
        length++;
        if (last.previous==first) {
            first.next = newEl;
            last.previous=newEl;

            newEl.next=last;
            newEl.previous=first;
        } else {
            last.previous.next=newEl;
            newEl.previous=last.previous;
            last.previous=newEl;
            newEl.next=last;
        }
    }

    boolean deleteElement(LinkedListElement n){
        length--;
        if(n.next==last && n.previous==first){
            first.next=last;
            last.previous=first;
            return true;
        }
        else{
            n.previous.next=n.next;
            n.next.previous=n.previous;
            return true;
        }
    }

    boolean deleteID(int ID){
        Kupec tmp;
        LinkedListElement tmplist=first.next;
        while(true){
            tmp=(Kupec)tmplist.element;
            if(ID==tmp.ID){
                deleteElement(tmplist);
                return true;
            }
            tmplist=tmplist.next;
        }
    }
}

class QueueElement {
    Object element;
    QueueElement next;

    QueueElement() {
        element = null;
        next = null;
    }
}

class Queue {
    private QueueElement front;
    private QueueElement rear;
    public int length = 0;

    public Queue() {
        makenull();
    }

    public void makenull() {
        front = null;
        rear = null;
    }

    public boolean empty() {
        return (front == null);
    }

    public Object front() {
        if(front==null) return null;
        return front.element;
    }

    public void enqueue(Object obj) {
        length++;
        QueueElement nov = new QueueElement();
        nov.element = obj;
        if (front == null)
            front = nov;
        if (rear == null) {
            rear = nov;
            return;
        }
        rear.next = nov;
        rear = nov;

    }

    public void enqueueFront(Object obj) {
        length++;
        QueueElement nov = new QueueElement();
        nov.element = obj;
        nov.next = front;
        front = nov;
    }

    public Object lastElement() {
        return rear.element;
    }

    public void dequeue() {

        length--;
        front = front.next;
        if (front == null)
            rear = null;

    }
}

class Kupec {
    public int nakupovalniSeznam;
    public int nabiranje;
    public int toleranca;
    public int skupnoNabiranje;
    public int ID;
    public int skupnoSkeniranje;
    public boolean jeNaBlagajni=false;

    public Kupec(int n, int a, int b, int c) {
        ID = n;
        nakupovalniSeznam = a;
        nabiranje = b;
        toleranca = c;
        skupnoNabiranje = nakupovalniSeznam * nabiranje;
    }

    public boolean nabiraj() {
        if (skupnoNabiranje == 0)
            return false;
        skupnoNabiranje--;
        return true;
    }
    public boolean skeniraj(){
        if (skupnoSkeniranje == 0)
            return false;
        skupnoSkeniranje--;
        return true;
    }

}

class Blagajna{
    public int casSkeniranja;
    public int ID;
    public Queue vrsta=new Queue();
    public int [] zakljuceniKupci;
    public int stZaklj=0;
    public Blagajna(int a,int b,int c){
        casSkeniranja=a;
        ID=b;
        zakljuceniKupci=new int[c];
    }
    public void dodajKupca(Kupec joze){
        //System.out.println("Kupec prisel:"+joze.ID);
        joze.skupnoSkeniranje=casSkeniranja*joze.nakupovalniSeznam;
        vrsta.enqueue(joze);
    }
    public void obdelajKupca(LinkedList kupci){
        if(vrsta.length==0){
            return;
        } 
        Kupec tmp=(Kupec)vrsta.front();
        
        if(!tmp.skeniraj()){
            //System.out.println(tmp.ID);
            //System.out.println("SEDAJ BOM FUKNU KUPCA IZ TRGOVINE");
            zakljuceniKupci[stZaklj]=tmp.ID;
            stZaklj++;
            kupci.deleteID(tmp.ID);
            vrsta.dequeue();
            if(vrsta.front()!=null){
                tmp=(Kupec)vrsta.front();
                tmp.skeniraj();
            }
            
        }
    }
    public int stLjudi(){
        return vrsta.length;
    }
    public int [] vrniZakljuceneKupce(){
        return zakljuceniKupci;
    }
}

public class Naloga4 {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new FileReader(args[0]));
        long startTime = System.nanoTime();
        LinkedList blagajne = new LinkedList();
        LinkedList ljudje=new LinkedList();
        //System.out.println(ljudje.first);
        int stIteracij=Integer.parseInt(br.readLine());
        String [] casSkeniranjaSTR = br.readLine().split(",");
        String [] zamikPrihodovSTR = br.readLine().split(",");
        String [] nakupovalniSeznamiSTR = br.readLine().split(",");
        String [] casNabiranjaSTR = br.readLine().split(",");
        String [] tolerancaSTR = br.readLine().split(",");
        int [] zaporedjePrihodov=new int[stIteracij];
        System.out.println(stIteracij);
        int dol=casSkeniranjaSTR.length;
        int stKupcev=0;

        int [] casSkeniranja=new int [dol];
        for(int i =0;i<dol;i++){
            casSkeniranja[i]=Integer.parseInt(casSkeniranjaSTR[i]);
            Blagajna b=new Blagajna(Integer.parseInt(casSkeniranjaSTR[i]), i+1,stIteracij);
            blagajne.addLast(b);
            //System.out.print(casSkeniranja[i]+",");
        }
        //System.out.println();
        
        /*LinkedListElement tmp;
        Blagajna tmpb;
        tmp=new LinkedListElement(null);
                tmp=blagajne.first.next;
                tmpb=(Blagajna)tmp.element;
        for(int i=0;i<blagajne.length;i++){
            
            System.out.println(tmpb.casSkeniranja);
            tmp=tmp.next;
            tmpb=(Blagajna)tmp.element;
        }
        tmp=new LinkedListElement(null);
        tmp=blagajne.first.next;
        tmpb=(Blagajna)tmp.element;
        for(int i=0;i<3;i++){
            tmp=tmp.next;
            tmpb=(Blagajna)tmp.element;
        }
        blagajne.deleteElement(tmp);
        tmp=new LinkedListElement(null);
        tmp=blagajne.first.next;
        tmpb=(Blagajna)tmp.element;
        for(int i=0;i<blagajne.length;i++){
            System.out.println(tmpb.casSkeniranja);
            tmp=tmp.next;
            tmpb=(Blagajna)tmp.element;
        }*/



        dol=zamikPrihodovSTR.length;
        int [] zamikPrihodov=new int [dol];
        for(int i =0;i<dol;i++){
            zamikPrihodov[i]=Integer.parseInt(zamikPrihodovSTR[i]);
            //System.out.print( zamikPrihodov[i]+",");
        }
        //System.out.println();

        dol=nakupovalniSeznamiSTR.length;
        int [] nakupovalniSeznami=new int [dol];
        for(int i =0;i<dol;i++){
            nakupovalniSeznami[i]=Integer.parseInt(nakupovalniSeznamiSTR[i]);
            //System.out.print(nakupovalniSeznami[i]+",");
        }
        //System.out.println();

        dol=casNabiranjaSTR.length;
        int [] casNabiranja=new int [dol];
        for(int i =0;i<dol;i++){
            casNabiranja[i]=Integer.parseInt(casNabiranjaSTR[i]);
            //System.out.print(casNabiranja[i]+",");
        }
        //System.out.println();

        dol=tolerancaSTR.length;
        int [] toleranca=new int [dol];
        for(int i =0;i<dol;i++){
            toleranca[i]=Integer.parseInt(tolerancaSTR[i]);
            //System.out.print(toleranca[i]+",");

        }
        //System.out.println();
        zaporedjePrihodov[0]=0;
        int prihodi=0;
        boolean varn=true;
        for(int i=0;i<stIteracij && varn;i++){
            for(int j=0;j<zamikPrihodov.length;j++){
                if(prihodi==0){
                    zaporedjePrihodov[prihodi]+=zamikPrihodov[j];
                    prihodi++;
                }else{
                    if((zaporedjePrihodov[prihodi-1]+zamikPrihodov[j])>stIteracij){
                        varn=false;
                        break;
                    }
                    zaporedjePrihodov[prihodi]=zaporedjePrihodov[prihodi-1]+zamikPrihodov[j];
                    prihodi++;
                }
                
            }
        }
        int [] jezniKupci=new int[prihodi];
        int jezniKupciSt=0;

        for(int i = 1;i<=stIteracij;i++){
            System.out.println("ITER"+i);
            Blagajna b;
            LinkedListElement tmp=blagajne.first.next;
            LinkedListElement tmpLjudek=ljudje.first.next;
            Blagajna minimum=(Blagajna)tmp.element;
            int min;
            if(zaporedjePrihodov[stKupcev]==i){
                //System.out.println("tle");
                stKupcev++;
                Kupec k=new Kupec(stKupcev,nakupovalniSeznami[(stKupcev-1)%nakupovalniSeznami.length],casNabiranja[(stKupcev-1)%casNabiranja.length],toleranca[(stKupcev-1)%toleranca.length]);
                min=prihodi;
                System.out.println("Generiranje kupca: "+k.ID+"("+k.nakupovalniSeznam+","+k.nabiranje+","+k.toleranca);
                
                while(tmp.next!=null){
                    b=(Blagajna)tmp.element;
                    if(b.stLjudi()<=min){
                        if(b.stLjudi()==min){
                            if(minimum.ID>b.ID){
                                minimum=b;
                            }
                        }else{
                            minimum=b;
                            min=b.stLjudi();
                        }
                    }
                    //System.out.println("Min="+min);
                    tmp=tmp.next;
                }
                if(min>k.toleranca){
                    jezniKupci[jezniKupciSt]=k.ID;
                    jezniKupciSt++;
                }else{
                    System.out.println("Kupec "+k.ID+" vstopi");
                    ljudje.addLast(k);
                }
                
            }
            tmp=blagajne.first.next;
            Kupec tmpKupec;
            //System.out.println("TU");
            tmpLjudek=ljudje.first.next;
            while(tmpLjudek.next!=null){
                tmpKupec=(Kupec)tmpLjudek.element;
                if(!tmpKupec.nabiraj() && !tmpKupec.jeNaBlagajni){
                    tmpKupec.jeNaBlagajni=true;
                    System.out.println("Kupec "+tmpKupec.ID+"konca nabiranje in se postavi v "+minimum.ID);
                    minimum.dodajKupca(tmpKupec);
                }
                min=prihodi;
                
                while(tmp.next!=null){
                    b=(Blagajna)tmp.element;
                    if(b.stLjudi()<=min){
                        if(b.stLjudi()==min){
                            if(minimum.ID>b.ID){
                                minimum=b;
                            }
                        }else{
                            minimum=b;
                            min=b.stLjudi();
                        }
                    }
                    tmp=tmp.next;
                }
                tmpLjudek=tmpLjudek.next;

            }
            tmp=blagajne.first.next;
            while(tmp.next!=null){
                b=(Blagajna)tmp.element;
                b.obdelajKupca(ljudje);
                tmp=tmp.next;
            }
            

            
        }
        LinkedListElement tmp;
        Blagajna tmpb;
        tmp=blagajne.first.next;
        tmpb=(Blagajna)tmp.element;
        for(int i=0;i<blagajne.length;i++){
            for(int j = 0;j<tmpb.vrniZakljuceneKupce().length;j++){
                if(tmpb.vrniZakljuceneKupce()[j]==0){
                    break;
                }
                System.out.print(tmpb.vrniZakljuceneKupce()[j]+", ");
            }
            System.out.println();
            tmp=tmp.next;
            tmpb=(Blagajna)tmp.element;
        }
        System.out.println();
        for(int i=0;i<jezniKupciSt;i++){
            System.out.print(jezniKupci[i]+", ");
        }



        /*for(int i=0;i<prihodi;i++){
            System.out.print(zaporedjePrihodov[i]+", ");
        }*/
        
        

      

        //for(int i = 0;i<stIteracij;i++){
            //bum tresk magična koda
        //}
        
        long endTime   = System.nanoTime();
        long totalTime = endTime - startTime;
        System.out.println(totalTime/1000000000d);
    }
}
