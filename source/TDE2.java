import processing.core.*; 
import processing.data.*; 
import processing.event.*; 
import processing.opengl.*; 

import java.util.HashMap; 
import java.util.ArrayList; 
import java.io.File; 
import java.io.BufferedReader; 
import java.io.PrintWriter; 
import java.io.InputStream; 
import java.io.OutputStream; 
import java.io.IOException; 

public class TDE2 extends PApplet {

int branco=0xffFFFFFF, vermelho=0xffFF0000, azul=0xff0000FF, amarelo=0xffFFFF00, verde=0xff00FF00; //variáveis das cores
Matriz2D matrizObjeto, matrizTransf, matrizObjetoTransformado; //criando as matrizes que serão usadas
Vetor local, velocidade, mouse, origem; //criando os vetores que serão usados
TSR tsr = new TSR(); //instanciando a classe TSR
int i = 0, A = 5, B = 10;
int pontos=0, contador=0; //variável que irá contar os pontos
float X, Y;
float [][] objeto = { {130, 100, 1}, //matriz que criará o objeto vetorial
                      {200, 150, 1},
                      {270, 100, 1},
                      {250, 180, 1},
                      {310, 220, 1}, 
                      {230, 230, 1},                      
                      {200, 300, 1},
                      {170, 230, 1},
                      { 90, 220, 1},
                      {150, 180, 1},
                      {130, 100, 1}}; 

public void setup() {
  
  rectMode(CENTER); //coloca a posição dos retângulos no centro deles
  textSize(20);
  local = new Vetor(width/2,10); //inicializa a posição do tiro
  velocidade = new Vetor(8,18); //inicializa a velocidade do tiro
  origem = new Vetor(width/2,10); //inicializa a origem do tiro
  mouse = new Vetor(mouseX, height-mouseY); //inicializa a posição do mouse
  matrizObjeto = new Matriz2D(objeto); //inicializa a matriz do objeto
  matrizTransf = new Matriz2D(3,3); //inicializa a matriz de transformação
}

public void draw() {
  background(25);
  
  mouse.Display(width/2,0,verde); //desenha o vetor do mouse toda vez que ele é clicado
  local.SomaVetor(velocidade); //posição soma a velocidade para criar movimento do tiro
  
  X=local.xcomp; 
  Y=height-local.ycomp;
  
  fill(amarelo); text(pontos,15,30); //texto que mostrará os pontos
  
  fill(verde);
  rect(X,Y,A,B); // cria o tiro
  
  stroke(255,0,0);
  tsr.Translate(-200,-200); //translada objeto para a origem
  matrizObjetoTransformado = matrizObjeto.times(matrizTransf);
  
  tsr.Rotate(45+i); //rotaciona o objeto
  matrizObjetoTransformado = matrizObjetoTransformado.times(matrizTransf);
  
  tsr.Translate(320,500); //translada novamente para o local desejado
  matrizObjetoTransformado = matrizObjetoTransformado.times(matrizTransf);
  
  stroke(255,0,0); fill(random(255),0,0);
  matrizObjetoTransformado.desenhaObjeto(); //desenha o objeto
  i++; //faz com que o objeto fique rodando, pois incrementa o ângulo
  
  float [][] c = {{(objeto[0][0]+objeto[2][0]+objeto[4][0]+objeto[6][0]+objeto[8][0]+objeto[10][0]/6)},
                 {(objeto[0][1]+objeto[2][1]+objeto[4][1]+objeto[6][1]+objeto[8][1]+objeto[10][1]/6)}};
                 //tentei fazer a colisão de várias formas e mesmo pesquisando não consegui
  
  if ((X>c[0][0]+50) && (Y>c[0][0]+50)) { //tentativa de colisão
    contador++;
    pontos++;
  }
    
  if (contador>=5 && contador<10) { //muda o objeto de lugar e diminui o tamanho
    tsr.Translate(-200,-200);
    matrizObjetoTransformado = matrizObjeto.times(matrizTransf);
    
    tsr.Rotate(45+i);
    matrizObjetoTransformado = matrizObjetoTransformado.times(matrizTransf);
    
    tsr.Scale(0.8f,0.8f);
    matrizObjetoTransformado = matrizObjetoTransformado.times(matrizTransf);
    
    tsr.Translate(120,300);
    matrizObjetoTransformado = matrizObjetoTransformado.times(matrizTransf);
    
    stroke(225,0,0); fill(random(255),0,0);
    matrizObjetoTransformado.desenhaObjeto();
    pontos+=5;
  }
  
  if (contador>=10 && contador<15) {
    tsr.Translate(-200,-200);
    matrizObjetoTransformado = matrizObjeto.times(matrizTransf);
    
    tsr.Rotate(45+i);
    matrizObjetoTransformado = matrizObjetoTransformado.times(matrizTransf);
    
    tsr.Scale(0.8f,0.8f);
    matrizObjetoTransformado = matrizObjetoTransformado.times(matrizTransf);
    
    tsr.Translate(380,300);
    matrizObjetoTransformado = matrizObjetoTransformado.times(matrizTransf);
    
    stroke(200,0,0); fill(random(255),0,0);
    matrizObjetoTransformado.desenhaObjeto();
    pontos+=5;
  }
  
  if (contador>=15 && contador<20) { //muda o objeto de lugar e diminui o tamanho
    tsr.Translate(-200,-200);
    matrizObjetoTransformado = matrizObjeto.times(matrizTransf);
    
    tsr.Rotate(45+i);
    matrizObjetoTransformado = matrizObjetoTransformado.times(matrizTransf);
    
    tsr.Scale(0.8f,0.8f);
    matrizObjetoTransformado = matrizObjetoTransformado.times(matrizTransf);
    
    tsr.Translate(380,550);
    matrizObjetoTransformado = matrizObjetoTransformado.times(matrizTransf);
    
    stroke(175,0,0); fill(random(255),0,0);
    matrizObjetoTransformado.desenhaObjeto();
    pontos+=5;
  }
  
  if (contador>=20 && contador<25) { //muda o objeto de lugar e diminui o tamanho
    tsr.Translate(-200,-200);
    matrizObjetoTransformado = matrizObjeto.times(matrizTransf);
    
    tsr.Rotate(45+i);
    matrizObjetoTransformado = matrizObjetoTransformado.times(matrizTransf);
    
    tsr.Scale(0.8f,0.8f);
    matrizObjetoTransformado = matrizObjetoTransformado.times(matrizTransf);
    
    tsr.Translate(120,550);
    matrizObjetoTransformado = matrizObjetoTransformado.times(matrizTransf);
    
    stroke(150,0,0); fill(random(255),0,0);
    matrizObjetoTransformado.desenhaObjeto();
    pontos+=5;
  }
  
  if (contador >= 25) { //chama o método vitória
    victory();
  }
}

public void mousePressed(){
  setup(); // reinicia a bolinha 
  
  mouse.SubtraiVetor(origem);
  mouse.Normalizar();
  mouse.MultEscalar(18);
  velocidade = mouse;
}

public void victory() {
  fill(0);
  rect(width/2,height/2,640,640);
  fill(random(255),random(255),random(255));
  textSize(50);
  text("VICTORY",200,340);
}
class Matriz2D {
    private int M;             // numero de linhas
    private int N;             // numero de colunas
    private float[][] data;    // M-by-N array

    // cria a matriz M-by-N de zeros
    public Matriz2D(int M, int N) {
        this.M = M;
        this.N = N;
        data = new float[M][N];
    }
    
    // imprime a matriz na tela 
    public void show(int xpos, int ypos) {
    fill(255,0,0);
    int tx=xpos;
        for (int i = 0; i < M; i++) {
            for (int j = 0; j < N; j++) {
            text(data[i][j], xpos, ypos);
            xpos=xpos+50;
      }
      ypos=ypos+20;
      xpos=tx;
        }
    }
    
    public void desenhaObjeto(){ // somente para matrizes objetos 2D
    int M = this.M;
    for(int i=0; i<M-1; i++) {
    ellipse(data[i][0],height-data[i][1], 5,5);
    line(data[i][0],height-data[i][1], data[i+1][0],height-data[i+1][1]); 
      }
    }

    // cria a matriz baseado num array2d de dados 
    public Matriz2D(float[][] data) {
        M = data.length;
        N = data[0].length;
        this.data = new float[M][N];
        for (int i = 0; i < M; i++)
            for (int j = 0; j < N; j++)
                    this.data[i][j] = data[i][j];
    }

    // transforma em matriz identidade
    public void identity() {
      if(this.M==this.N) {
        for (int i = 0; i < M; i++)
            for (int j = 0; j < N; j++)
                    if (i==j) this.data[i][j] = 1;
                    else this.data[i][j] = 0;
      } else println("Erro: Matriz Identidade somente com M = N");
    }

    // cria e retorna a transposta da matriz que invocou o método
    public Matriz2D transpose() {
        Matriz2D A = new Matriz2D(N, M);
        for (int i = 0; i < M; i++)
            for (int j = 0; j < N; j++)
                A.data[j][i] = this.data[i][j];
        return A;
    }

    // return C = A + B
    public Matriz2D plus(Matriz2D B) {
        Matriz2D A = this;
        if (B.M == A.M && B.N == A.N){
          Matriz2D C = new Matriz2D(M, N);
          for (int i = 0; i < M; i++)
              for (int j = 0; j < N; j++)
                  C.data[i][j] = A.data[i][j] + B.data[i][j];
          return C;}
        else {
          println("Erro: Matrizes de tamanhos diferentes");
          return A;
        }
    }

    // return C = A - B
    public Matriz2D minus(Matriz2D B) {
        Matriz2D A = this;
        if (B.M == A.M && B.N == A.N) {
          Matriz2D C = new Matriz2D(M, N);
          for (int i = 0; i < M; i++)
              for (int j = 0; j < N; j++)
                  C.data[i][j] = A.data[i][j] - B.data[i][j];
          return C;}
         else {
         println("Erro: Matrizes de tamanhos diferentes");
         return A;
        }
    }

    // return C = A * B
    public Matriz2D times(Matriz2D B) {
        Matriz2D A = this;
        if (A.N == B.M) {
        Matriz2D C = new Matriz2D(A.M, B.N);
        for (int i = 0; i < C.M; i++)
            for (int j = 0; j < C.N; j++)
                for (int k = 0; k < A.N; k++)
                    C.data[i][j] += (A.data[i][k] * B.data[k][j]);
        return C;}
       else {
       println("Erro: Matrizes incompatíveis para multiplicação");
       return A;
      }        
    }

    // calcula determinante (método de triangularização)
    // triangulariza a matriz que invoca o método
    public float determinant(){
      Matriz2D A = this;
      if (M==N) {
        float temp, factor; 
        int count = 0;
        // faz a transformação em um triangulo...
        for(int i = 0; i < M - 1; i++)
        {
            if(A.data[i][i] == 0)
            {
                for(int k = i; k < M; k++)
                {
                    if(A.data[k][i] != 0)
                    {
                        for(int j = 0; j < M; j++)
                        {
                            temp = A.data[i][j];
                            A.data[i][j] = A.data[k][j];
                            A.data[k][j] = temp;
                        }
                        k = M;
                    }
                }
                count++;
            } 
            if(A.data[i][i] != 0)
            {
                for(int k = i + 1; k < M; k++)
                {
                    factor = -1.0f * A.data[k][i] /  A.data[i][i];
                    for(int j = i; j < M; j++)
                    {
                        A.data[k][j] = A.data[k][j] + (factor * A.data[i][j]);
                    }
                }
            }
        } 
        temp = 1.0f;
        // Calcula o determinante, multiplica a diagonal
        for(int i = 0; i < M; i++) temp = temp * A.data[i][i];
        return temp; 
        }
        else {
        println("Erro: A Matriz não é quadrada");
        return 0;
        }
    }
}
class TSR {
  
  public TSR() {
  }
  
  public void Translate(float tx, float ty) {
    matrizTransf.data[0][0] = 1;
    matrizTransf.data[0][1] = 0;  
    matrizTransf.data[0][2] = 0;
    matrizTransf.data[1][0] = 0;
    matrizTransf.data[1][1] = 1;  
    matrizTransf.data[1][2] = 0;  
    matrizTransf.data[2][0] = tx;
    matrizTransf.data[2][1] = ty;  
    matrizTransf.data[2][2] = 1;
  }
  
  public void Scale(float sx, float sy) {
    matrizTransf.data[0][0] = sx;
    matrizTransf.data[0][1] = 0;  
    matrizTransf.data[0][2] = 0;
    matrizTransf.data[1][0] = 0;
    matrizTransf.data[1][1] = sy;  
    matrizTransf.data[1][2] = 0;  
    matrizTransf.data[2][0] = 0;
    matrizTransf.data[2][1] = 0;  
    matrizTransf.data[2][2] = 1;
  }
  
  public void Rotate(float angulo) {
    angulo = angulo*PI/180;
    matrizTransf.data[0][0] = cos(angulo);
    matrizTransf.data[0][1] = sin(angulo);  
    matrizTransf.data[0][2] = 0;
    matrizTransf.data[1][0] = -sin(angulo);
    matrizTransf.data[1][1] = cos(angulo);  
    matrizTransf.data[1][2] = 0;  
    matrizTransf.data[2][0] = 0;
    matrizTransf.data[2][1] = 0;  
    matrizTransf.data[2][2] = 1;
  }
}
///////////////////////////////////// classe Vetor

class Vetor {
  float xcomp;
  float ycomp;
  
  Vetor(float txcomp, float tycomp){
    xcomp = txcomp;
    ycomp = tycomp;
  }
  
  public void Display(float xpos, float ypos, int c){
    stroke(c);
    line(xpos,height-ypos, xpos+xcomp,height-(ypos+ycomp));
    ellipse(xpos+xcomp,height-(ypos+ycomp),5,5);
    fill(c);
  }
  
  public float Magnitude(){
     return sqrt(xcomp*xcomp+ycomp*ycomp); 
  }
  
  public void MultEscalar(float k){
    xcomp = k * xcomp;
    ycomp = k * ycomp;
  }
  
  public void SomaVetor(Vetor tVetor){
     xcomp = xcomp + tVetor.xcomp;
     ycomp = ycomp + tVetor.ycomp;
  }
  
  public void SubtraiVetor(Vetor tVetor){
     xcomp = xcomp - tVetor.xcomp;
     ycomp = ycomp - tVetor.ycomp;     
  }
  
  public float ProdutoEscalar(Vetor tVetor){
    return xcomp * tVetor.xcomp + ycomp * tVetor.ycomp;
  }
  
  public boolean Ortogonal(Vetor tVetor){
    if (ProdutoEscalar(tVetor)==0) return true; else return false;
  }
  
  public void Normalizar(){
     float Mag = Magnitude();
     xcomp = xcomp / Mag;
     ycomp = ycomp / Mag;
  }
}
  public void settings() {  size(640,640); }
  static public void main(String[] passedArgs) {
    String[] appletArgs = new String[] { "TDE2" };
    if (passedArgs != null) {
      PApplet.main(concat(appletArgs, passedArgs));
    } else {
      PApplet.main(appletArgs);
    }
  }
}
