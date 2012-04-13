/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package poke;

import java.awt.Graphics;
import java.awt.geom.Ellipse2D;
import javaPlay.Sprite;
import javax.swing.JOptionPane;

/**
 *
 * @author ariel_silveira
 */
public class Ellipse extends ObjetoComMovimento{
    
    Sprite ataque;
    boolean desativado;
    
    
    public Ellipse(int x, int y){
        this.desativado = false;
        this.x = x;
        this.y = y;
        
        try{
            this.ataque = new Sprite("resources/Rage_Up_Right.png", 1, 100, 100);
            
            
        }catch (Exception ex){
            JOptionPane.showMessageDialog(null, "Recurso não ecnontrado: "+ex.getMessage());
        }
        
    }
    
    public void step(long timeElapsed){
        if(this.desativado){
            return;
        }
    }
    
    public void draw(Graphics g){
        //Ellipse2D elipse = new Ellipse2D.Double(this.x, this.y, 100, 100);
        //g.drawOval(this.x, this.y, 100, 100);
        
        if(this.desativado){
            return;
        }
        //g.setColor(Color.YELLOW);
        //g.drawLine(this.x, this.y, this.x+2, this.y+2);
        
        this.ataque.draw(g, this.x, this.y);
        
    }
    
    public boolean temColisao(Ellipse2D elipse){
        if(this.desativado){
            return false;
        }
        
        if(elipse.contains(x, y)){
            this.desativado = true;
            return true;            
        } else {
            return false;
        }
    }
    
    
    
}
