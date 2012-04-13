package Personagens;


import Personagens.Charizard;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import javaPlay.GameEngine;
import javaPlay.GameObject;
import javaPlay.Keyboard;
import javaPlay.Imagem;
import javax.swing.JOptionPane;
import java.awt.event.KeyEvent;
import poke.Keys;
import poke.ObjetoComMovimento;
import Ataques.Twister;
import Ataques.WingAttack;



public class Pidgeotto extends ObjetoComMovimento{
    
    //----------------------FAZER COLISAO----------------------\\
    
    private int velocidade = 5;
    private int diametro = 20;
    protected Imagem spriteLeft;
    protected Imagem spriteRight;
    protected Imagem spriteDown;
    protected Imagem spriteUp;
    protected Imagem spriteAtual;

    int controleRage = 30;
    int framesControleRage = 30;
    int controleTwister = 200;
    int framesControleTwister = 200;

    int maxVida;
    int numVida;
    
    
    public Pidgeotto(){
        this.x = 600;
        this.y = 280;
        this.numVida = 100;
        this.maxVida = 100;
        
        try{
            this.spriteRight = new Imagem("resources/personagens/Pidgeotto_Right.gif");
            this.spriteLeft = new Imagem("resources/personagens/Pidgeotto_Left.gif");
            this.spriteDown = new Imagem("resources/personagens/Pidgeotto_Down.gif");
            this.spriteUp = new Imagem("resources/personagens/Pidgeotto_Up.gif");
            this.spriteAtual = this.spriteLeft;
        }catch (Exception ex){
            JOptionPane.showMessageDialog(null, "Recurso não ecnontrado: "+ex.getMessage());
            System.exit(1);
        }
        
    }
    
    public void step(long timeElapsed){
        this.controleRage ++;
        this.controleTwister ++;

        Keyboard teclado = GameEngine.getInstance().getKeyboard();                   

        if( teclado.keyDown( Keys.A ) && teclado.keyDown( Keys.W) ){
            this.spriteAtual = spriteLeft;
            this.moveEsquerdaCima(7);

        } else if ( teclado.keyDown( Keys.A ) && teclado.keyDown( Keys.S) ){
            this.spriteAtual = spriteLeft;
            this.moveEsquerdaBaixo(7);

        } else if ( teclado.keyDown( Keys.D ) && teclado.keyDown( Keys.W) ){
            this.spriteAtual = spriteRight;
            this.moveDireitaCima(7);

        } else if ( teclado.keyDown( Keys.D ) && teclado.keyDown( Keys.S) ){
            this.spriteAtual = spriteRight;
            this.moveDireitaBaixo(7);

        } else if( teclado.keyDown( Keys.D ) ){
            this.spriteAtual = spriteRight;
            this.moveDireita(5);

        } else if( teclado.keyDown( Keys.A ) ){
            this.spriteAtual = spriteLeft;
            this.moveEsquerda(5);

        } else if( teclado.keyDown( Keys.W ) ){
            this.spriteAtual = spriteUp;
            this.moveCima(5);

        } else if( teclado.keyDown( Keys.S ) ){
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
        //g.drawString(this.numVida+"", this.x+5, this.y-30);
        
        g.setColor(Color.black);
        g.fillRect(this.x-1, this.y+3, this.maxVida+2, 12);
        g.setColor(Color.GREEN);
        g.fillRect(this.x, this.y+4, this.numVida, 10);
        
        //this.vida.draw(g,this.x+12, this.y+3);
    }
    
    public WingAttack getWingAttack(){
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
        return new WingAttack(xTiro, yTiro, this.direcao);
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
    
    public boolean podeAtirar(){
        return (this.controleRage > this.framesControleRage);
    }
    
    public boolean podeAtirarTwister(){
        return (this.controleTwister > this.framesControleTwister);
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
    
    public boolean temColisao(Charizard p1){
        return this.getRetangulo().intersects( p1.getRetangulo() );
    }
    
    public Rectangle getRetangulo(){
        return new Rectangle(this.x+4, this.y+14, this.spriteAtual.pegaLargura(), this.spriteAtual.pegaAltura());
    }
    
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

    public int getVida(){
        return this.numVida;
    }
    public void setVida(int vida){
        this.numVida = vida;
    }
    
    public void resetPosicao(){
        this.x = 600;
        this.y = 280;
    }
    
}
