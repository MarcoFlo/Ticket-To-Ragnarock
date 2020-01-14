//Per identificare gli ellissi/linee avendo una tabella dei valori assunti possono costruirmi una corrispondenza con il giunto alle coordinate volute

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

void setup()
{
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



//metodo casuale per trovare arrivo e partenza
  ax1 = (int)random(0, 5);
  bx1 = ((int)random(ax1+2, ax1+4))%5;
  ay1 = (int)random(0, 3);
  by1 = ((int)random(ay1+1, ay1+3))%3;




  angolo+=1.256;

  angolo = ((float)((int)(angolo*1000))/1000)%6.28;
  xback=x;
  yback=y;
}

 //classe dei giunti, al suo interno gestisce una tabella con 3 entry(il numero del led che costituisce il "vertice"), ha un metodo che controlla se il vertice che si sta cercando è relativo a quel giunto e fornisce il vertice successivo in base alla direzione scelta

class Giunti
{
  IntList vertici;
  Giunti(int a, int b, int c) //inserire in senso antiorario in qualsiasi ordine
  {
    vertici.append(a);
    vertici.append(b);
    vertici.append(c);
  }

  int check(int vertice, int direzione)
  {
    int pos=-1;
    if (vertici.hasValue(vertice))
    {
      for (int i = 0; i<3; i++)
      {
        if (vertici.get(i)==vertice)
        {
          pos=i;
        }
      }
      if (direzione == 1)//destra
      {
        return (pos+1)%4;
      }
      if (direzione == 0)//centro
      {
        return (pos+2)%4;
      }
      if (direzione == -1)//sinistra
      {
        return (pos+3)%4;
      }
    }
    return pos;
  }
}