/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Ataques;

import java.awt.Graphics;
import java.awt.Rectangle;
import javaPlay.Sprite;
import javax.swing.JOptionPane;
import poke.Direcao;
import poke.ObjetoComMovimento;

/**
 *
 * @author Maike
 */
public class FlameThrower extends ObjetoComMovimento {

//    if(flameThrower.estaAtivo()){
//        Charizard.setVelocidade(0);
//    } else {
//        Charizard.setVelocidade(velocidade inicial, tem que ver quanto é);
//    }
    boolean desativado;
    int frameElapsed;
    int frame;
    Direcao direcao;
    Sprite spriteLeft;
    Sprite spriteRight;
    int dano;

    public FlameThrower(int x, int y, Direcao direcao) {
        this.setDano(15);

        this.direcao = direcao;
        
        this.desativado = false;
        this.x = x;
        this.y = y;

        int frame = 0;

        try {
            this.spriteLeft = new Sprite("resources/ataques/Flame Thrower/FlameThrower_Left.png", 8, 215, 65);
            this.spriteRight = new Sprite("resources/ataques/Flame Thrower/FlameThrower_Right.png", 8, 215, 65);
            
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Recurso não ecnontrado: " + ex.getMessage());
            System.exit(1);
        }

    }

    public void step(long timeElapsed) {
        if (this.frame >= 8) {
            return;
        }

        switch (this.direcao) {
            case DIREITA:
                this.frameElapsed++;
                if (this.frameElapsed > 5) {
                    this.frame++;
                    this.spriteRight.setCurrAnimFrame(this.frame);
                    this.frameElapsed -= 5;
                    
                }
                break;
            case ESQUERDA:
                this.frameElapsed++;
                if (this.frameElapsed > 5) {
                    this.frame++;
                    this.spriteLeft.setCurrAnimFrame(this.frame);
                    this.frameElapsed -= 5;
                    
                }
                break;
        }



    }

    @Override
    public void draw(Graphics g) {
        switch (this.direcao) {
            case DIREITA:
                this.spriteRight.draw(g, this.x, this.y);
                break;
            case ESQUERDA:
                this.spriteLeft.draw(g, this.x, this.y);
                break;
        }
        
    }

    public Rectangle getRetangulo(){
        return new Rectangle(this.x, this.y, 215, 65);
    }
    
    public boolean temColisao(Rectangle retangulo) {
        if(this.desativado || this.frame == 8){
            return false;
        }

        if (this.getRetangulo().intersects(retangulo)) {
            this.desativado = true;
            return true;
        } else {
            return false;
        }
    }

    public boolean estaAtivo() {
        return (this.desativado == false);
    }

    public int getFrames(){
        return this.frameElapsed;
    }
    
    public void setDano(int n) {
        this.dano = n;
    }

    public int getDano() {
        return this.dano;
    }
    

}
