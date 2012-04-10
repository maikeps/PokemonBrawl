package Personagens;


import javaPlay.GameEngine;
import javaPlay.Keyboard;
import javaPlay.Imagem;
import javax.swing.JOptionPane;
import poke.Keys;
import Ataques.Twister;
import Ataques.WingAttack;
import java.awt.Graphics;



public class Pidgeotto extends Personagem{
    
    int controleRage = 30;
    int framesControleRage = 30;
    int controleTwister = 200;
    int framesControleTwister = 200;
    
    public Pidgeotto(){
        this.x = 600;
        this.y = 280;
        this.numVida = 200;
        this.maxVida = 200;
        
        try{
            this.spriteRight = new Imagem("resources/personagens/Pidgey/Pidgeotto_Right.gif");
            this.spriteLeft = new Imagem("resources/personagens/Pidgey/Pidgeotto_Left.gif");
            this.spriteDown = new Imagem("resources/personagens/Pidgey/Pidgeotto_Down.gif");
            this.spriteUp = new Imagem("resources/personagens/Pidgey/Pidgeotto_Up.gif");
            this.spriteAtual = this.spriteLeft;
        }catch (Exception ex){
            JOptionPane.showMessageDialog(null, "Recurso não ecnontrado: "+ex.getMessage());
            System.exit(1);
        }
        
    }
    
    public void step(long timeElapsed){
        
        super.step(timeElapsed);
        
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
      
    }
    
    public void draw(Graphics g){
        super.draw(g);
    }
    
    public WingAttack getWingAttack(){

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

}
