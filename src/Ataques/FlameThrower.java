/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Ataques;

import java.awt.Graphics;
import java.awt.Rectangle;
import javaPlay.Sprite;
import javax.swing.JOptionPane;
import poke.ObjetoComMovimento;

/**
 *
 * @author Maike
 */
public class FlameThrower extends ObjetoComMovimento {

    boolean desativado;
    int frameElapsed;
    int frame;
    Sprite up;
    Sprite down;
    Sprite left;
    Sprite right;
    Sprite spriteVazio;
    Sprite spriteAtual;

    public FlameThrower(int x, int y) {
        this.desativado = false;
        this.x = x;
        this.y = y;

        int frames = 1;
        
        try{
            this.up = new Sprite("resources/ataques/Flame Thrower/FlameThrower_Up.gif", 1, 65, 215);
        }catch (Exception ex){
            JOptionPane.showMessageDialog(null, "Recurso não ecnontrado: "+ex.getMessage());
            System.exit(1);
        }

    }

    public void step(long timeElapsed) {
        this.frame++;
        if (this.frame == 25) {
            this.desativado = true;
            this.frame -= 25;
        }
        if (this.desativado) {
            return;
        }
        this.spriteAtual = up;

    }

    @Override
    public void draw(Graphics g) {
        this.spriteAtual.draw(g, this.x, this.y);
    }

    public boolean temColisao(Rectangle retangulo) {
        if (this.desativado) {
            return false;
        }

        if (this.getRetangulo().intersects(retangulo)) {
            if (this.frame >= 25) {
                this.desativado = true;
            }
            return true;
        } else {
            return false;
        }
    }
    
}
