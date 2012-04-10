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
public class MainMenu implements GameStateController{

    private Sprite imagem;
    private String string;
    private String musica;
    
    
    public void load() {
        this.musica = "resources/sounds/Pokemon Opening.wav";
        AudioPlayer.play(this.musica);
        
        this.string = "resources/PokemonBrawlRedimensionada.png";
        try {
            this.imagem = new Sprite(this.string, 1, 1000, 800);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Recurso não ecnontrado: "+ex.getMessage());
            System.exit(1);
        }
        
    }

    public void step(long timeElapsed) { 
        
        
        
        Keyboard teclado = GameEngine.getInstance().getKeyboard();

        if(teclado.keyDown(Keys.ESPACO)) {
              //AudioPlayer.play( "resources/sounds/comeon.wav" );
              this.musica = "resources/sounds/comeon.wav";
              GameEngine.getInstance().setNextGameStateController(2);  
        }
    }
        
    

    public void draw(Graphics g) {  
        g.setColor(Color.black);
        g.fillRect(0, 0, 800, 700);
        this.imagem.draw(g, 195, 50);
        
        
        
    }
    
    public void unload() {    }

    public void start() {    }

    public void stop() {    }
    
}
