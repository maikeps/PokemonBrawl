/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package poke;

import Personagens.Personagem;
import java.awt.Graphics;

/**
 *
 * @author Maike
 */
public class Player1 extends Personagem{
    
    ObjetoComMovimento personagem;
    int frame1;
    int maxFrame1;
    int frame2;
    int maxFrame2;
    int frame3;
    int maxFrame3;
    int frame4;
    int maxFrame4;

    
    
    public Player1(Personagem personagem){
        this.personagem = personagem;
    }

    @Override
    public void step(long timeElapsed) {
        this.personagem.step(timeElapsed);
    }

    @Override
    public void draw(Graphics g) {
        this.personagem.draw(g);
    }
    
    public void setFrame1(int n){
        this.frame1 = n;
    }
    public void setMaxFrame1(int n){
        this.maxFrame1 = n;
    }
    
    public void setFrame2(int n){
        this.frame2 = n;
    }
    public void setMaxFrame2(int n){
        this.maxFrame2 = n;
    }
    
    public void setFrame3(int n){
        this.frame3 = n;
    }

    public int getMaxFrame4() {
        return maxFrame4;
    }
    public void setMaxFrame3(int n){
        this.maxFrame3 = n;
    }
    public void setFrame4(int n){
        this.frame4 = n;
    }
    public void setMaxFrame4(int n){
        this.maxFrame4 = n;
    }
    
    public int getFrame1() {
        return frame1;
    }

    public int getFrame2() {
        return frame2;
    }

    public int getFrame3() {
        return frame3;
    }

    public int getFrame4() {
        return frame4;
    }

    public int getMaxFrame1() {
        return maxFrame1;
    }

    public int getMaxFrame2() {
        return maxFrame2;
    }

    public int getMaxFrame3() {
        return maxFrame3;
    }
    
    
}
