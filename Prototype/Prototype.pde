//Per identificare gli ellissi/linee avendo una tabella dei valori assunti possono costruirmi una corrispondenza con il giunto alle coordinate volute
import java.util.Iterator;

int ax1;
int ay1;
int bx1;
int by1;
int a2;
int b2;
float x;
float y;
float xback;
float yback;
int dx;
int dy;
int magnification;
int moltiplicatore;
float angolo;
int dim_giunti;

boolean start;

HashMap<String, Giunto> map_giunti = new HashMap<String, Giunto>();
HashMap<Integer, Integer> map_lati = new HashMap<Integer, Integer>(); //DA RIEMPIRE CON LE CORRISPONDENZE UNIVOCHE SUI LATI, considerare la possibilità che si cerchi 0

void setup()
{
  start = true;
  map_giunti.put("A1", new Giunto(0, 524, 7, 611));
  map_giunti.put("B1", new Giunto(13, 613, 17, 789));
  map_giunti.put("C1", new Giunto(21, 791, 0, 1070));
  map_giunti.put("A2", new Giunto(0, 544, 68, 541));
  map_giunti.put("B2", new Giunto(84, 653, 89, 649));
  map_giunti.put("C2", new Giunto(106, 844, 0, 842));
  map_giunti.put("A3", new Giunto(0, 560, 204, 557));
  map_giunti.put("B3", new Giunto(216, 686, 221, 685));
  map_giunti.put("C3", new Giunto(231, 906, 0, 902));
  map_giunti.put("A4", new Giunto(0, 578, 333, 576));
  map_giunti.put("B4", new Giunto(343, 729, 348, 727));
  map_giunti.put("C4", new Giunto(357, 976, 0, 973));
  map_giunti.put("A5", new Giunto(0, 599, 436, 597));
  map_giunti.put("B5", new Giunto(444, 765, 449, 764));
  map_giunti.put("C5", new Giunto(460, 1034, 0, 1031));

  xback = -1;
  yback = -1;
  size(880, 880);
  background(240);
  magnification = 60;
  moltiplicatore = 2;
  angolo =1.57;
  dim_giunti = 10;
  dx=440;
  dy=440;
}
void draw()
{
  int vertice_provenienza = getData();
  int direzione = getData();
  //OPERAZIONI START
  if (start)
  {
    //metodo casuale per trovare arrivo e partenza
    ax1 = (int)random(0, 5); //da aggiungere 1 per avere A1,2,3,4,5
    bx1 = ((int)random(ax1+2, ax1+4))%5;
    ay1 = (int)random(0, 3); //da trasformare in A,B,C
    by1 = ((int)random(ay1+1, ay1+3))%3;


    //giunto provenienza
    //println((char)(ay1+65)+str(ax1+1)); //nome del giunto da cercare in map_giunti...tipo A3
    Giunto giunto_provenienza = map_giunti.get((char)(ay1+65)+str(ax1+1));
    vertice_provenienza = 0;
    while (vertice_provenienza==0)
    {
      vertice_provenienza = giunto_provenienza.getList().get((int)random(0, 4));
    }
    accendi(vertice_provenienza, map_lati.get(vertice_provenienza));
    vertice_provenienza = map_lati.get(vertice_provenienza); //aggiorno il valore del vertice di provenienza

    //if viene dato un imput dai tasti usciamo dallo stato start
    {
      start = false;
    }
  } 
  else //se non siamo nello stato start
  {
    direzione = getData();
    Iterator<Giunto> it = map_giunti.values().iterator();
    while (it.hasNext())
    {
      Giunto giunto = (Giunto)it.next();
      if (giunto.check(vertice_provenienza, direzione)>0) //se clicco un tasto che porta a una direzione non accettabile non succede niente
      {
        vertice_provenienza = giunto.check(vertice_provenienza, direzione);
      }
    }
    accendi(vertice_provenienza, map_lati.get(vertice_provenienza));
    vertice_provenienza = map_lati.get(vertice_provenienza); //aggiorno il valore del vertice di provenienza

    //disegno ragnatela
    x = cos(angolo);
    y = sin(angolo);

    if (xback!=-1)
    {
      stroke(200, 100, 0);
      strokeWeight(3);
      line(moltiplicatore*magnification*x+dx, -moltiplicatore*magnification*y+dy, moltiplicatore*magnification*xback+dx, -moltiplicatore*magnification*yback+dy);
      line(moltiplicatore*magnification*x+dx, -moltiplicatore*magnification*y+dy, 2*moltiplicatore*magnification*x+dx, -2*moltiplicatore*magnification*y+dy);
      line(2*moltiplicatore*magnification*x+dx, -2*moltiplicatore*magnification*y+dy, 2*moltiplicatore*magnification*xback+dx, -2*moltiplicatore*magnification*yback+dy);
      line(2*moltiplicatore*magnification*x+dx, -2*moltiplicatore*magnification*y+dy, 3*moltiplicatore*magnification*x+dx, -3*moltiplicatore*magnification*y+dy);
      line(3*moltiplicatore*magnification*x+dx, -3*moltiplicatore*magnification*y+dy, 3*moltiplicatore*magnification*xback+dx, -3*moltiplicatore*magnification*yback+dy);
    }

    strokeWeight(15);
    stroke(0);
    ellipse(moltiplicatore*magnification*x+dx, -moltiplicatore*magnification*y+dy, dim_giunti, dim_giunti);
    ellipse(2*moltiplicatore*magnification*x+dx, -2*moltiplicatore*magnification*y+dy, dim_giunti, dim_giunti);
    ellipse(3*moltiplicatore*magnification*x+dx, -3*moltiplicatore*magnification*y+dy, dim_giunti, dim_giunti);


    angolo+=1.256;
    angolo = ((float)((int)(angolo*1000))/1000)%6.28;
    xback=x;
    yback=y;
  }
}

//classe dei giunti, al suo interno gestisce una tabella con 4 entry(il numero del led che costituisce il "vertice"), ha un metodo che controlla se il vertice che si sta cercando è relativo a quel giunto e fornisce il vertice successivo in base alla direzione scelta

//metodi da implementare o già implementati sul rasberry
int getData()
{
  return -1;
}
void accendi(int da, int a)
{
}


class Giunto
{
  IntList vertici = new IntList();
  ;
  Giunto(int a, int b, int c, int d) //inserire in senso antiorario in qualsiasi ordine
  {
    vertici.append(a);
    vertici.append(b);
    vertici.append(c);
    vertici.append(d);
  }
  IntList getList()
  {
    return vertici;
  }

  int check(int vertice, int direzione)
  {
    int pos=-1;
    if (vertici.hasValue(vertice))
    {
      for (int i = 0; i<4; i++)
      {
        if (vertici.get(i)==vertice)
        {
          pos=i;
        }
      }
      if (direzione == 1)//destra
      {
        return vertici.get((pos+1)%4);
      }
      if (direzione == 0)//centro
      {
        return vertici.get((pos+2)%4);
      }
      if (direzione == -1)//sinistra
      {
        return vertici.get((pos+3)%4);
      }
    }
    return pos;
  }
}