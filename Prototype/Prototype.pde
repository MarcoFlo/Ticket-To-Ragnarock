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
int dim_rect;

int vertice_provenienza;
Giunto giunto_provenienza;

boolean start;

HashMap<String, Giunto> map_giunti = new HashMap<String, Giunto>();
HashMap<Integer, Integer> map_lati = new HashMap<Integer, Integer>(); //considerare la possibilità che si cerchi zero
HashMap<Integer, Integer> map_corrispondenze = new HashMap<Integer, Integer>();

void setup()
{


  start = true;
  map_giunti.put("A1", new Giunto(0, 524, 7, 611));
  map_giunti.put("A2", new Giunto(0, 544, 68, 541));
  map_giunti.put("A3", new Giunto(0, 560, 204, 557));
  map_giunti.put("A4", new Giunto(0, 578, 333, 576));
  map_giunti.put("A5", new Giunto(0, 599, 436, 597));
  map_giunti.put("B1", new Giunto(13, 613, 17, 789));
  map_giunti.put("B2", new Giunto(84, 653, 89, 649));
  map_giunti.put("B3", new Giunto(216, 686, 221, 685));
  map_giunti.put("B4", new Giunto(343, 729, 348, 727));
  map_giunti.put("B5", new Giunto(444, 765, 449, 764));
  map_giunti.put("C1", new Giunto(21, 791, 0, 1070));
  map_giunti.put("C2", new Giunto(106, 844, 0, 842));
  map_giunti.put("C3", new Giunto(231, 906, 0, 902));
  map_giunti.put("C4", new Giunto(357, 976, 0, 973));
  map_giunti.put("C5", new Giunto(460, 1034, 0, 1031));
  coordinate();



  map_lati.put(524, 541);
  map_lati.put(541, 524);
  map_lati.put(613, 649);
  map_lati.put(649, 613);
  map_lati.put(791, 842);
  map_lati.put(842, 791);
  map_lati.put(544, 557);
  map_lati.put(557, 544);
  map_lati.put(653, 685);
  map_lati.put(685, 653);
  map_lati.put(844, 902);
  map_lati.put(902, 844);
  map_lati.put(560, 576);
  map_lati.put(576, 560);
  map_lati.put(686, 727);
  map_lati.put(727, 686);
  map_lati.put(906, 973);
  map_lati.put(973, 906);
  map_lati.put(578, 597);
  map_lati.put(597, 578);
  map_lati.put(729, 764);
  map_lati.put(764, 729);
  map_lati.put(976, 1031);
  map_lati.put(1031, 976);
  map_lati.put(599, 611);
  map_lati.put(611, 599);
  map_lati.put(765, 789);
  map_lati.put(789, 765);
  map_lati.put(1034, 1070);
  map_lati.put(1070, 1034);

  map_lati.put(7, 13);
  map_lati.put(13, 7);
  map_lati.put(17, 21);
  map_lati.put(21, 17);
  map_lati.put(68, 84);
  map_lati.put(84, 68);
  map_lati.put(89, 106);
  map_lati.put(106, 89);
  map_lati.put(204, 216);
  map_lati.put(216, 204);
  map_lati.put(221, 231);
  map_lati.put(231, 221);
  map_lati.put(333, 343);
  map_lati.put(343, 333);
  map_lati.put(348, 357);
  map_lati.put(357, 348);
  map_lati.put(436, 444);
  map_lati.put(444, 436);
  map_lati.put(449, 460);
  map_lati.put(460, 449);


  map_corrispondenze.put(7, 44);
  map_corrispondenze.put(13, 39);
  map_corrispondenze.put(17, 34);
  map_corrispondenze.put(21, 30);
  map_corrispondenze.put(68, 169);
  map_corrispondenze.put(84, 153);
  map_corrispondenze.put(89, 148);
  map_corrispondenze.put(106, 131);
  map_corrispondenze.put(204, 299);
  map_corrispondenze.put(216, 287);
  map_corrispondenze.put(221, 282);
  map_corrispondenze.put(231, 272);
  map_corrispondenze.put(333, 408);
  map_corrispondenze.put(343, 398);
  map_corrispondenze.put(348, 393);
  map_corrispondenze.put(357, 384);
  map_corrispondenze.put(436, 509);
  map_corrispondenze.put(444, 501);
  map_corrispondenze.put(449, 496);
  map_corrispondenze.put(460, 485);


  xback = -1;
  yback = -1;
  size(880, 880);
  background(240);
  magnification = 60;
  moltiplicatore = 2;
  angolo =1.57;
  dim_giunti = 10;
  dim_rect = 50;
  dx=440;
  dy=440;



  //metodo casuale per trovare arrivo e partenza
  ax1 = (int)random(0, 5); //da aggiungere 1 per avere A1,2,3,4,5
  ay1 = (int)random(0, 3); //da trasformare in A,B,C




  //giunto provenienza
  //println((char)(ay1+65)+str(ax1+1)); //nome del giunto da cercare in map_giunti...tipo A3
  vertice_provenienza = 10; //inizializzazione per non dare problemi

  //giunto_provenienza = map_giunti.get((char)(ay1+65)+str(ax1+1));

  giunto_provenienza = map_giunti.get("A1");

  vertice_provenienza = 0;
  while (vertice_provenienza==0)
  {
    vertice_provenienza = giunto_provenienza.getList().get((int)random(0, 4));
  }
}
void draw()
{

  //disegno ragnatela
  x = cos(angolo);
  y = sin(angolo);
  if (xback!=-1)
  {
    fill(0);
    strokeWeight(0);
    rect(10, 90, 50, 50); //sinistra
    rect(45, 20, 50, 50); //dritto
    rect(80, 90, 50, 50); //destra

    int direzione = 10;

    //OPERAZIONI START
    if (start)
    {

      stroke(200, 100, 0);
      strokeWeight(3);
      line(moltiplicatore*magnification*x+dx, -moltiplicatore*magnification*y+dy, moltiplicatore*magnification*xback+dx, -moltiplicatore*magnification*yback+dy);
      line(moltiplicatore*magnification*x+dx, -moltiplicatore*magnification*y+dy, 2*moltiplicatore*magnification*x+dx, -2*moltiplicatore*magnification*y+dy);
      line(2*moltiplicatore*magnification*x+dx, -2*moltiplicatore*magnification*y+dy, 2*moltiplicatore*magnification*xback+dx, -2*moltiplicatore*magnification*yback+dy);
      line(2*moltiplicatore*magnification*x+dx, -2*moltiplicatore*magnification*y+dy, 3*moltiplicatore*magnification*x+dx, -3*moltiplicatore*magnification*y+dy);
      line(3*moltiplicatore*magnification*x+dx, -3*moltiplicatore*magnification*y+dy, 3*moltiplicatore*magnification*xback+dx, -3*moltiplicatore*magnification*yback+dy);
      strokeWeight(15);
      stroke(0);
      ellipse(moltiplicatore*magnification*x+dx, -moltiplicatore*magnification*y+dy, dim_giunti, dim_giunti);
      ellipse(2*moltiplicatore*magnification*x+dx, -2*moltiplicatore*magnification*y+dy, dim_giunti, dim_giunti);
      ellipse(3*moltiplicatore*magnification*x+dx, -3*moltiplicatore*magnification*y+dy, dim_giunti, dim_giunti);

      strokeWeight(15);
      stroke(150, 30, 200);
      ellipse(giunto_provenienza.getX(), giunto_provenienza.getY(), dim_giunti, dim_giunti);
      accendi(vertice_provenienza, map_lati.get(vertice_provenienza));

      if (map_corrispondenze.containsKey(vertice_provenienza)) //Per accendere i tiranti che sono doppi
      {
        accendi(map_corrispondenze.get(vertice_provenienza), map_corrispondenze.get(map_lati.get(vertice_provenienza)));
      }


      if (mousePressed)//viene dato un imput dai tasti usciamo dallo stato start
      {
        vertice_provenienza = map_lati.get(vertice_provenienza); //aggiorno il valore del vertice di provenienza
        start = false;
      }
    } else //se non siamo nello stato start
    {

      stroke(200, 100, 0);
      strokeWeight(3);
      line(moltiplicatore*magnification*x+dx, -moltiplicatore*magnification*y+dy, moltiplicatore*magnification*xback+dx, -moltiplicatore*magnification*yback+dy);
      line(moltiplicatore*magnification*x+dx, -moltiplicatore*magnification*y+dy, 2*moltiplicatore*magnification*x+dx, -2*moltiplicatore*magnification*y+dy);
      line(2*moltiplicatore*magnification*x+dx, -2*moltiplicatore*magnification*y+dy, 2*moltiplicatore*magnification*xback+dx, -2*moltiplicatore*magnification*yback+dy);
      line(2*moltiplicatore*magnification*x+dx, -2*moltiplicatore*magnification*y+dy, 3*moltiplicatore*magnification*x+dx, -3*moltiplicatore*magnification*y+dy);
      line(3*moltiplicatore*magnification*x+dx, -3*moltiplicatore*magnification*y+dy, 3*moltiplicatore*magnification*xback+dx, -3*moltiplicatore*magnification*yback+dy);
      strokeWeight(15);
      stroke(0);
      ellipse(moltiplicatore*magnification*x+dx, -moltiplicatore*magnification*y+dy, dim_giunti, dim_giunti);
      ellipse(2*moltiplicatore*magnification*x+dx, -2*moltiplicatore*magnification*y+dy, dim_giunti, dim_giunti);
      ellipse(3*moltiplicatore*magnification*x+dx, -3*moltiplicatore*magnification*y+dy, dim_giunti, dim_giunti);


      if (mousePressed)
      {

        direzione = getData(direzione);

        Iterator<Giunto> it = map_giunti.values().iterator();
        while (it.hasNext())
        {
          Giunto giunto = (Giunto)it.next();
          if (giunto.check(vertice_provenienza, direzione)>0) //se clicco un tasto che porta a una direzione non accettabile non succede niente
          {
            vertice_provenienza = giunto.check(vertice_provenienza, direzione);
          }
        }
        vertice_provenienza = map_lati.get(vertice_provenienza); //aggiorno il valore del vertice di provenienza
        delay(100);
      }
      accendi(vertice_provenienza, map_lati.get(vertice_provenienza));
      if (map_corrispondenze.containsKey(vertice_provenienza)) //Per accendere i tiranti che sono doppi
      {
        accendi(map_corrispondenze.get(vertice_provenienza), map_corrispondenze.get(map_lati.get(vertice_provenienza)));
      }
    }
  }
  angolo+=1.256;
  angolo = ((float)((int)(angolo*1000))/1000)%6.28;
  xback=x;
  yback=y;
}

//classe dei giunti, al suo interno gestisce una tabella con 4 entry(il numero del led che costituisce il "vertice"), ha un metodo che controlla se il vertice che si sta cercando è relativo a quel giunto e fornisce il vertice successivo in base alla direzione scelta

//metodi da implementare o già implementati sul rasberry
int getData(int precedente_direzione)
{
  //  rect(10,90,50,50); //sinistra
  //  rect(45,20,50,50); //dritto
  //  rect(80,90,50,50); //destra
  //println("x " +mouseX);
  //println("y " +mouseY);
  if (mouseX>10 && mouseX<60 && mouseY>90 && mouseY<140)
  {
    println(-1 + " sinistra");
    return -1;
  } else if (mouseX>45 && mouseX<95 && mouseY>20 && mouseY<70)
  {
    println(0 + " dritto");
    return 0;
  } else  if (mouseX>80 && mouseX<130 && mouseY>90 && mouseY<140)
  {
    println(1+ " destra");
    return 1;
  } else
  {
    return precedente_direzione;
  }
}
void accendi(int da, int a)
{
  float xpart, ypart, xarr, yarr;
  xpart=0;
  ypart=0;
  xarr=0;
  yarr=0;
  Iterator<Giunto> it = map_giunti.values().iterator();
  int i=0;
  while (it.hasNext())
  {
    Giunto giunto = (Giunto)it.next();
    if (giunto.getList().hasValue(da))
    {
      xpart = giunto.getX();
      ypart = giunto.getY();
      i++;
    }
    if (giunto.getList().hasValue(a))
    {
      xarr = giunto.getX();
      yarr = giunto.getY();
      i++;
    }
    if (i==2)
    {
      break;
    }
  }
  stroke(255, 0, 0);
  strokeWeight(7);
  line(xpart, ypart, xarr, yarr);
}

void coordinate() //aggiunge le coordinate dei giunti
{
  magnification = 60;
  moltiplicatore = 2;
  angolo =1.57;
  dx=440;
  dy=440;


  int num=1;
  String id="";
  for (float i=1; i<4; i+=0.2)
  {
    id = (char)((int)i+64)+str(num);


    x = cos(angolo);
    y = sin(angolo);

    Giunto supp = map_giunti.get(id);
    supp.addCoordinate(((int)i)*moltiplicatore*magnification*x+dx, -((int)i)*moltiplicatore*magnification*y+dy); //occhio che l'origine in processing è in alto a sinistra, quindi qui si sta lavorando nel terzo quadrante

    angolo-=1.256;
    angolo = ((float)((int)(angolo*1000))/1000)%6.28;

    num++;
    if (num%6==0)
    {
      num=1;
    }
  }
}


class Giunto
{
  IntList vertici = new IntList();
  float x;
  float y;

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

  void addCoordinate(float x, float y)
  {
    this.x=x;
    this.y=y;
  }

  float getX()
  {
    return x;
  }
  float getY()
  {
    return y;
  }

  int check(int vertice, int direzione)
  {
    int pos=-1;
    println("new"+vertici.get(0));
    if (vertici.hasValue(vertice))
    {
      if (vertici.get(0)!=0)
      {
        for (int i = 0; i<4; i++)
        {
          if (vertici.get(i)==vertice)
          {
            println("da eguagliare"+vertici.get(i));
            pos=i;
            println("vertice trovato alla pos: "+ pos);
          }
        }
        if (direzione == 1)//destra
        {
          println("destra");
          println("cacca"+vertici.get((pos+1)%4));
          return vertici.get((pos+1)%4);
        }
        if (direzione == 0)//centro
        {
          println("centro");
          println("cacca"+vertici.get((pos+2)%4));
          return vertici.get((pos+2)%4);
        }
        if (direzione == -1)//sinistra
        {
          println("sinistra");
          println("cacca"+vertici.get((pos+3)%4));
          return vertici.get((pos+3)%4);
        }
      } 
      else
      {

        for (int i = 0; i<4; i++)
        {
          if (vertici.get(i)==vertice)
          {
            println("da eguagliare"+vertici.get(i));
            pos=i;
            println("vertice trovato alla pos: "+ pos);
          }
        }
        if (direzione == 1)//destra
        {
          println("destra");
          println("cacca"+vertici.get(1));
          return vertici.get((pos+1)%4);
        }

        if (direzione == -1)//sinistra
        {
          println("sinistra");
          println("cacca"+vertici.get(2));
          return vertici.get((pos+2)%4);
        }
        if (direzione == 0)//centro
        {
          println("centro");
          println("cacca"+vertici.get(3));
          return vertici.get((pos+3)%4);
        }
      }
      return pos;
    }

    return pos;
  }
}