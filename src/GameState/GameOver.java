/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package GameState;

import java.awt.Color;
import java.awt.Graphics;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javaPlay.GameEngine;
import javaPlay.GameStateController;
import javaPlay.Keyboard;
import javaPlay.Sprite;
import javaPlayExtras.AudioPlayer;
import javax.swing.JOptionPane;
import poke.Keys;

/**
 *
 * @author maike_p_santos
 */
public class GameOver implements GameStateController{

    private Sprite gameOver;
    private Sprite retry;
    
    public void load() {
        
        try {
            this.gameOver = new Sprite("resources/GameOver.png", 1, 144, 19);
            this.retry = new Sprite("resources/retry.png", 1, 105, 55);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Recurso não ecnontrado: "+ex.getMessage());
            System.exit(1);
        }
        
    }

    public void step(long timeElapsed) { 
        
        
        
        Keyboard teclado = GameEngine.getInstance().getKeyboard();

        if(teclado.keyDown(Keys.S)) {
              GameEngine.getInstance().setNextGameStateController(2);  
        }
        if(teclado.keyDown(Keys.N)) {
              System.exit(1); 
        }
    }
        
    

    public void draw(Graphics g) {  
        g.setColor(Color.black);
        g.fillRect(0, 0, 800, 600);
        this.gameOver.draw(g, 335, 250);
        this.retry.draw(g, 360, 350);
        
    }
    
    public void unload() {    }

    public void start() {    }

    public void stop() {    }
    
}
