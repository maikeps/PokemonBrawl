package Ataques;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import javaPlay.GameObject;
import javaPlay.Imagem;
import javaPlayExtras.AudioPlayer;
import javax.swing.JOptionPane;
import poke.Direcao;
import poke.ObjetoComMovimento;

public class DragonRage extends ObjetoComMovimento{

    boolean desativado;
    Direcao direcao;
    int velocidade;
   /* Imagem spriteUp;
    Imagem spriteDown;
    Imagem spriteAtual;
    Imagem spriteLeft;
    Imagem spriteVazio;
    Imagem spriteRight;
    Imagem spriteUpLeft;
    Imagem spriteDownLeft;
    Imagem spriteUpRight;
    Imagem spriteDownRight;
    */
    
    Imagem spriteUp;
    Imagem spriteDown;
    Imagem spriteAtual;
    Imagem spriteLeft;
    Imagem spriteVazio;
    Imagem spriteRight;
    Imagem spriteUpLeft;
    Imagem spriteDownLeft;
    Imagem spriteUpRight;
    Imagem spriteDownRight;
    
    
    public DragonRage(int x, int y, Direcao direcao){
        this.desativado = false;
        this.x = x;
        this.y = y;
        this.direcao = direcao;
        this.velocidade = 10;
        
        try{
            this.spriteRight = new Imagem("resources/ataques/Dragon Rage/Rage_Right.png");
            this.spriteUp = new Imagem("resources/ataques/Dragon Rage/Rage_Up.png");
            this.spriteLeft = new Imagem("resources/ataques/Dragon Rage/Rage_Left.png");
            this.spriteVazio = new Imagem("resources/ataques/Rage_Vazio.png");
            this.spriteDown = new Imagem("resources/ataques/Dragon Rage/Rage_Down.png");
            this.spriteDownLeft = new Imagem("resources/ataques/Dragon Rage/Rage_Down_Left.png");
            this.spriteUpLeft = new Imagem("resources/ataques/Dragon Rage/Rage_Up_Left.png");
            this.spriteDownRight = new Imagem("resources/ataques/Dragon Rage/Rage_Down_Right.png");
            this.spriteUpRight = new Imagem("resources/ataques/Dragon Rage/Rage_Up_Right.png");
          
            this.spriteAtual = spriteVazio;
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
                this.spriteAtual = this.spriteRight;
                break;
            case ESQUERDA:
                this.moveEsquerda( this.velocidade );
                this.spriteAtual = this.spriteLeft;
                break;
            case CIMA:
                this.moveCima( this.velocidade );
                this.spriteAtual = this.spriteUp;
                break;
            case BAIXO:
                this.moveBaixo( this.velocidade );
                this.spriteAtual = this.spriteDown;
                break;
            case DIREITA_CIMA:
                this.moveDireitaCima( this.velocidade );
                this.spriteAtual = this.spriteUpRight;
                break;
            case DIREITA_BAIXO:
                this.moveDireitaBaixo( this.velocidade );
                this.spriteAtual = this.spriteDownRight;
                break;
            case ESQUERDA_CIMA:
                this.moveEsquerdaCima( this.velocidade );
                this.spriteAtual = this.spriteUpLeft;
                break;
            case ESQUERDA_BAIXO:
                this.moveEsquerdaBaixo( this.velocidade );
                this.spriteAtual = this.spriteDownLeft;
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
            AudioPlayer.play("resources/sounds/Sound 2.wav");
            this.desativado = true;
            return true;            
        } else {
            return false;
        }
    }

}
