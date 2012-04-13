package Personagens;



import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import javaPlay.GameEngine;
import javaPlay.GameObject;
import javaPlay.Keyboard;
import javaPlay.Imagem;
import javax.swing.JOptionPane;
import Ataques.FlameBurst;
import poke.Keys;
import poke.ObjetoComMovimento;
import Ataques.DragonRage;
import Ataques.Twister;



public class Charizard extends ObjetoComMovimento{
    
    //----------------------FAZER COLISAO----------------------\\
    
    private int velocidade = 5;
    private int diametro = 20;
    protected Imagem spriteLeft;
    protected Imagem spriteRight;
    protected Imagem spriteDown;
    protected Imagem spriteUp;
    protected Imagem spriteAtual;
    Imagem sprite;

    int numVida;
    int maxVida;
    
    int controleRage = 50;
    int framesControleRage = 50;
    int controleTwister = 200;
    int framesControleTwister = 200;
    int controleFlameBurst = 150;
    int framesControleFlameBurst = 150;
    
   
    
    public Charizard(){
        this.x = 100;
        this.y = 280;
        this.numVida = 100;
        this.maxVida = 100;
        
        /*try{
            this.sprite = new Sprite("resources/personagens/Gif_Sheet_Charizard.gif", 4, 320, 75);
        }catch (Exception ex){
            JOptionPane.showMessageDialog(null, "Recurso não ecnontrado: "+ex.getMessage());
        }*/
        
        try{
            this.spriteRight = new Imagem("resources/personagens/Charmander/Charizard_Right.gif");
            this.spriteLeft = new Imagem("resources/personagens/Charmander/Charizard_Left.gif");
            this.spriteDown = new Imagem("resources/personagens/Charmander/Charizard_Down.gif");
            this.spriteUp = new Imagem("resources/personagens/Charmander/Charizard_Up.gif");
            this.spriteAtual = this.spriteRight;
        }catch (Exception ex){
            JOptionPane.showMessageDialog(null, "Recurso não ecnontrado: "+ex.getMessage());
            System.exit(1);
        }
        
    }
    
    public void step(long timeElapsed){
        
        this.controleRage ++;
        this.controleTwister ++;
        this.controleFlameBurst ++;

        if(this.numVida<=0){
            JOptionPane.showMessageDialog(null, "Player2 Venceu.");
            System.exit(1);
        }
        
        Keyboard teclado = GameEngine.getInstance().getKeyboard();                   

        if( teclado.keyDown( Keys.ESQUERDA ) && teclado.keyDown( Keys.CIMA) ){
            this.spriteAtual = spriteLeft;
            this.moveEsquerdaCima(7);

        } else if ( teclado.keyDown( Keys.ESQUERDA ) && teclado.keyDown( Keys.BAIXO) ){
            this.spriteAtual = spriteLeft;
            this.moveEsquerdaBaixo(7);

        } else if ( teclado.keyDown( Keys.DIREITA ) && teclado.keyDown( Keys.CIMA) ){
            this.spriteAtual = spriteRight;
            this.moveDireitaCima(7);

        } else if ( teclado.keyDown( Keys.DIREITA ) && teclado.keyDown( Keys.BAIXO) ){
            this.spriteAtual = spriteRight;
            this.moveDireitaBaixo(7);

        } else if( teclado.keyDown( Keys.DIREITA ) ){
            this.spriteAtual = spriteRight;
            this.moveDireita(5);

        } else if( teclado.keyDown( Keys.ESQUERDA ) ){
            this.spriteAtual = spriteLeft;
            this.moveEsquerda(5);

        } else if( teclado.keyDown( Keys.CIMA ) ){
            this.spriteAtual = spriteUp;
            this.moveCima(5);

        } else if( teclado.keyDown( Keys.BAIXO ) ){
            this.spriteAtual = spriteDown;
            this.moveBaixo(5);

        }
        
       
        if(this.tocaCenarioDireita()){
            this.x = 710;
        }
        if(this.tocaCenarioEsquerda()){
            this.x = 7;
        }
        if(this.tocaCenarioBaixo()){
            this.y = 485;
        }
        if(this.tocaCenarioCima()){
            this.y = 70;
        }
        
        
    }
    
    public void draw(Graphics g){
        this.spriteAtual.draw(g, this.x, this.y);
        //esse drawRect mostra onde deveria estar acontecendo a colisao
        //g.drawRect(this.x-40, this.y-40, 90, 80);
        g.setColor(Color.black);
       // g.fillRect(this.x-1, this.y+28, this.maxVida+2, 12);
        g.fillRect(this.x-1, this.y+3, this.maxVida+2, 12);
        g.setColor(Color.GREEN);
        //g.fillRect(this.x, this.y+29, this.numVida, 10);
        g.fillRect(this.x, this.y+4, this.numVida, 10);
        //this.vida.draw(g,this.x+11, this.y+5);
        //g.drawString(this.numVida+"", this.x+5, this.y-15);
        
    }
    
    
    public DragonRage getRage(){
        //O tiro pode sair de qualquer um dos oito lados.
        //E o x e y inicial do tiro podem sempre ser diferentes

        int xTiro = this.x;
        int yTiro = this.y;
        int tamanhoPokemon = 80;
        int metadePokemon = tamanhoPokemon / 2;

        
        
        switch(this.direcao){
            case DIREITA:
                xTiro += tamanhoPokemon - 15;
                yTiro -= 1;
                break;
            case ESQUERDA:
                yTiro += 1;
                xTiro -= tamanhoPokemon + 15;
                break;
            case CIMA:
                xTiro += metadePokemon/2 - 11;
                yTiro -= tamanhoPokemon + 25;
                break;
            case BAIXO:
                xTiro += metadePokemon/2 - 10;
                yTiro += tamanhoPokemon - 50;
                break;
            case DIREITA_CIMA:
                xTiro += tamanhoPokemon - 15;
                yTiro -= tamanhoPokemon - 20;
                break;
            case DIREITA_BAIXO:
                xTiro += tamanhoPokemon - 10;
                yTiro += tamanhoPokemon - 55;
                break;
            case ESQUERDA_CIMA:
                xTiro -= tamanhoPokemon - 10;
                yTiro -= tamanhoPokemon - 25;
                break;
            case ESQUERDA_BAIXO:
                xTiro -= tamanhoPokemon - 5;
                yTiro += tamanhoPokemon - 60;
                break;
        }

        this.setFrameRage(1);
        //this.controleRage = 1;
        return new DragonRage(xTiro, yTiro, this.direcao);
        
    }
    
    public Twister getTwister(){
        //O tiro pode sair de qualquer um dos oito lados.
        //E o x e y inicial do tiro podem sempre ser diferentes

        int xTiro = this.x;
        int yTiro = this.y;
        int tamanhoPokemon = 80;
        int metadePokemon = tamanhoPokemon / 2;

        
        
        switch(this.direcao){
            case DIREITA:
                xTiro += tamanhoPokemon - 15;
                yTiro -= 1;
                break;
            case ESQUERDA:
                yTiro += 1;
                xTiro -= tamanhoPokemon + 15;
                break;
            case CIMA:
                xTiro += metadePokemon/2 - 11;
                yTiro -= tamanhoPokemon + 25;
                break;
            case BAIXO:
                xTiro += metadePokemon/2 - 10;
                yTiro += tamanhoPokemon - 50;
                break;
            case DIREITA_CIMA:
                xTiro += tamanhoPokemon - 15;
                yTiro -= tamanhoPokemon - 20;
                break;
            case DIREITA_BAIXO:
                xTiro += tamanhoPokemon - 10;
                yTiro += tamanhoPokemon - 55;
                break;
            case ESQUERDA_CIMA:
                xTiro -= tamanhoPokemon - 10;
                yTiro -= tamanhoPokemon - 25;
                break;
            case ESQUERDA_BAIXO:
                xTiro -= tamanhoPokemon - 5;
                yTiro += tamanhoPokemon - 60;
                break;
        }

        
        this.setFrameTwister(1);
        return new Twister(xTiro, yTiro);
        
    }
    
    public FlameBurst getFlameBurst(){

        int xTiro = this.x-60;
        int yTiro = this.y-85;


        this.setFrameFlameBurst(1);
        return new FlameBurst(xTiro, yTiro);
        
    }
    
    
    
    public boolean podeAtirar(){
        return (this.controleRage > this.framesControleRage);
    }
    
    public boolean podeAtirarTwister(){
        return (this.controleTwister > this.framesControleTwister);
    }
    
    public boolean podeAtirarFlameBurst() {
        return (this.controleFlameBurst > this.framesControleFlameBurst);
    }
    
    public boolean tocaCenarioDireita(){
        return (this.x + this.diametro >= 730);
    }
    
    public boolean tocaCenarioEsquerda(){
        return (this.x < 7);
    }
    
    public boolean tocaCenarioBaixo(){
        return (this.y > 485);
    }
    
    public boolean tocaCenarioCima(){
        return (this.y < 70);
    }
    
    
    /*
    public boolean temColisaoLeft(Rectangle left){
        return this.getRectangleLeft().intersects(left);
    }
    
    public boolean temColisaoRight(Rectangle right){
        return this.getRectangleRight().intersects(right);
    }
    
    public boolean temColisaoUp(Rectangle up){
        return this.getRectangleUp().intersects(up);
    }
    
    public boolean temColisaoDown(Rectangle down){
        return this.getRectangleDown().intersects(down);
    }*/
    
    public boolean temColisao(Pidgeotto p2){
        return this.getRetangulo().intersects( p2.getRetangulo() );
    }
    
    public Rectangle getRetangulo(){
        return new Rectangle(this.x, this.y, this.spriteAtual.pegaLargura(), this.spriteAtual.pegaAltura());
    }
    
    /*public Rectangle getRectangleRight(){
        return new Rectangle(this.x, this.y , 100, 100);
    }
    
    public Rectangle getRectangleLeft(){
        return new Rectangle(this.x, this.y, 100, 100);
    }
    
    public Rectangle getRectangleDown(){
        return new Rectangle(this.x, this.y + 10, 100, 100);
    }
    
    public Rectangle getRectangleUp(){
        return new Rectangle(this.x, this.y, 100, 100);
    }*/
    
    
    public void perdeVida(int numPontos){
        this.numVida -= numPontos;
    }
    
    public boolean estaMorto(){
        return (this.numVida <= 0);
    }
    
    public int getFrameRage(){
        return this.controleRage;
    }
    public int getMaxRage(){
        return this.framesControleRage;
    }
    public void setFrameRage(int n){
        this.controleRage = n;
    }
    
    public int getFrameTwister(){
        return this.controleTwister;
    }
    public int getMaxTwister(){
        return this.framesControleTwister;
    }
    public void setFrameTwister(int n){
        this.controleTwister = n;
    }
    
    public int getFrameFlameBurst(){
        return this.controleFlameBurst;
    }
    public int getMaxFlameBurst(){
        return this.framesControleFlameBurst;
    }
    public void setFrameFlameBurst(int n){
        this.controleFlameBurst = n;
    }
    
    
    public int getVida(){
        return this.numVida;
    }
    
    public void setVida(int vida){
        this.numVida = vida;
    }
    
    public void resetPosicao(){
        this.x = 100;
        this.y = 280;
    }

}
