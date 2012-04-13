/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package poke;

import java.awt.Graphics;

/**
 *
 * @author Maike
 */
public class Player1 extends ObjetoComMovimento{
    
    ObjetoComMovimento personagem;
    
    public Player1(ObjetoComMovimento personagem){
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
    
    
    
}
