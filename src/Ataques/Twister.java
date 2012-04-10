package Ataques;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import javaPlay.GameEngine;
import javaPlay.GameObject;
import javaPlay.Keyboard;
import javaPlay.Imagem;
import javaPlayExtras.AudioPlayer;
import poke.ObjetoComMovimento;

public class Twister extends ObjetoComMovimento {

    Imagem spriteInicio;
    Imagem spriteFim;
    Imagem sprite;
    int velocidade = 6;
    private boolean desativado;
    
    int dano;

    public Twister(int x, int y) {
        this.setDano(2);
        
        this.desativado = false;
        
        this.x = x;
        this.y = y;
        try {            
            this.spriteInicio = new Imagem("resources/ataques/Twister/twister2.gif");
            //this.spriteInicio = new Sprite("resources/scr shot util/twister.gif", 1, 95, 95);
            this.sprite = this.spriteInicio;
        } catch (Exception ex) {
            System.out.println("Imagem não encontrada: " + ex.getMessage());
        }
    }

    public void step(long timeElapsed){
        
    }

    @Override
    public void draw(Graphics g) {
        if(this.desativado){
            return;
        }
        this.sprite.draw(g, this.x, this.y);        
    }

    public void persegue(GameObject objeto){
        if(this.desativado){
            return;
        }
        int xPerseguido = objeto.getX();
        int yPerseguido = objeto.getY();

        
        if(this.x < xPerseguido){
            this.x += this.velocidade;
        }
        if(this.x > xPerseguido){
            this.x -= this.velocidade;
        }
        if(this.y < yPerseguido){
            this.y += this.velocidade;
        }
        if(this.y > yPerseguido){
            this.y -= this.velocidade;
        }
        
        
       /* if(this.x < xPerseguido && this.y < yPerseguido){
            //NaveMini Está à esquerda e acima
            //Nave Perseguida está abaixo e à direita.
            this.moveDireitaBaixo(this.velocidade);

        } else if(this.x < xPerseguido && this.y > yPerseguido){
            //NaveMini Está à esquerda e abaixo
            //Nave Perseguida está acima e à direita.
            this.moveDireitaCima(this.velocidade);

        } else if(this.x > xPerseguido && this.y < yPerseguido){
            //NaveMini Está à direita e acima
            //Nave Perseguida está abaixo e à esquerda.
            this.moveEsquerdaBaixo(this.velocidade);

        } else if(this.x > xPerseguido && this.y > yPerseguido){
            //NaveMini Está à direita e abaixo
            //Nave Perseguida está acima e à esquerda.
            this.moveEsquerdaCima(this.velocidade);

        } if(this.x < xPerseguido && this.y == yPerseguido){            
            //Nave Perseguida está a direita.
            this.moveDireita(this.velocidade);

        } else if(this.x > xPerseguido && this.y == yPerseguido){
            //Nave Perseguida está a esquerda.
            this.moveEsquerda(this.velocidade);

        } else if(this.x == xPerseguido && this.y < yPerseguido){
            //Nave Perseguida está abaixo
            this.moveBaixo(this.velocidade);

        } else if(this.x == xPerseguido && this.y > yPerseguido){
            //Nave Perseguida está acima
            this.moveCima(this.velocidade);
        }*/

    }
    
    public Rectangle getRetangulo(){
        return new Rectangle(this.x, this.y, this.sprite.pegaLargura(), this.sprite.pegaAltura());
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

    public void setDano(int n){
        this.dano = n;
    }
    
    public int getDano(){
        return this.dano;
    }

}
