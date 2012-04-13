package Ataques;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import javaPlay.GameObject;
import javaPlay.Imagem;
import javaPlay.Sprite;
import javaPlayExtras.AudioPlayer;
import javax.swing.JOptionPane;
import poke.Direcao;
import poke.ObjetoComMovimento;

public class WingAttack extends ObjetoComMovimento{

    boolean desativado;
    Direcao direcao;
    int velocidade;
   
    Imagem spriteUp;
    Imagem spriteDown;
    Imagem spriteLeft;
    Imagem spriteRight;
    Imagem spriteVazio;
    
    Imagem spriteAtual;
    Sprite sprite;
    int frame;
    
    public WingAttack(int x, int y, Direcao direcao){
        this.desativado = false;
        this.x = x;
        this.y = y;
        this.direcao = direcao;
        this.velocidade = 30;
        
        try{
            //this.sprite = new Sprite("resources/ataques/Wing Attack/WingAttack.png",5,45,45);
            this.spriteDown = new Imagem("resources/ataques/Wing Attack/WingAttack_Down.png");
            this.spriteUp = new Imagem("resources/ataques/Wing Attack/WingAttack_Up.png");
            this.spriteLeft = new Imagem("resources/ataques/Wing Attack/WingAttack_Left.png");
            this.spriteRight = new Imagem("resources/ataques/Wing Attack/WingAttack_Right.png");
            this.spriteVazio = new Imagem("resources/ataques/Rage_Vazio.png");
            this.spriteAtual = this.spriteVazio;
        }catch (Exception ex){
            JOptionPane.showMessageDialog(null, "Recurso não ecnontrado: "+ex.getMessage());
            System.exit(1);
        }
        
        
        
    }

    public void step(long timeElapsed) {
        if(this.desativado){
            return;
        }
        
        
        
        
        switch(this.direcao){
            case DIREITA:
                this.moveDireita( this.velocidade );
                //this.frame = 4;
                //this.sprite.setCurrAnimFrame(this.frame);
                this.spriteAtual = spriteRight;
                break;
            case ESQUERDA:
                this.moveEsquerda( this.velocidade );
                //this.frame = 2;
                //this.sprite.setCurrAnimFrame(this.frame);
                this.spriteAtual = spriteLeft;
                break;
            case CIMA:
                this.moveCima( this.velocidade );
                //this.frame = 1;
                //this.sprite.setCurrAnimFrame(this.frame);
                this.spriteAtual = spriteUp;
                break;
            case BAIXO:
                this.moveBaixo( this.velocidade );
                //this.frame = 3;
                //this.sprite.setCurrAnimFrame(this.frame);
                this.spriteAtual = spriteDown;
                break;
            case DIREITA_CIMA:
                this.moveDireitaCima( this.velocidade );
                //this.frame = 1;
                //this.sprite.setCurrAnimFrame(this.frame);
                this.spriteAtual = spriteRight;
                break;
            case DIREITA_BAIXO:
                this.moveDireitaBaixo( this.velocidade );
                //this.frame = 1;
                //this.sprite.setCurrAnimFrame(this.frame);
                this.spriteAtual = spriteRight;
                break;
            case ESQUERDA_CIMA:
                this.moveEsquerdaCima( this.velocidade );
                //this.frame = 2;
                //this.sprite.setCurrAnimFrame(this.frame);
                this.spriteAtual = spriteLeft;
                break;
            case ESQUERDA_BAIXO:
                this.moveEsquerdaBaixo( this.velocidade );
                //this.frame = 2;
                //this.sprite.setCurrAnimFrame(this.frame);
                this.spriteAtual = spriteLeft;
                break;
        }
    }

    @Override
    public void draw(Graphics g) {
        if(this.desativado){
            return;
        }
        //g.setColor(Color.YELLOW);
        //g.drawLine(this.x, this.y, this.x+2, this.y+2);
        
        
        this.spriteAtual.draw(g, this.x, this.y);
        //this.sprite.draw(g, this.x, this.y);
        
        
    }
    
    public Rectangle getRetangulo(){
        return new Rectangle(this.x, this.y, this.spriteAtual.pegaLargura(), this.spriteAtual.pegaAltura());
        //get height, get width, Imagem ao inves de Sprite
        //return new Rectangle(this.x, this.y, this.spriteAtual.pegaLargura(), this.spriteAtual.pegaAltura());
    }
    
    public boolean temColisao(Rectangle retangulo){
        if(this.desativado){
            return false;
        }
        
        if(this.getRetangulo().intersects(retangulo)){
            AudioPlayer.play("resources/sounds/Sound 3.wav");
            this.desativado = true;
            this.frame = 5;
            return true;            
        } else {
            return false;
        }
    }

}
