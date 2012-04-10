/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package GameState;

import util.Util;
import java.awt.Color;
import java.awt.Graphics;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javaPlay.*;
import javaPlayExtras.AudioPlayer;
import javax.swing.JOptionPane;
import poke.Keys;

/**
 *
 * @author maike_p_santos
 */
public class CharacterSelect implements GameStateController{

    private Imagem Charizard;
    private Imagem Bulbasaur;
    private Imagem Pidgeotto;
    private String player1;
    private String player2;
    
    
    public void load() {

        try {
            this.Charizard = new Imagem("resources/personagens/Charmander/Charizard_Down.gif");
            this.Bulbasaur = new Imagem("resources/personagens/Bulbasaur/Bulbasaur_Down.gif");
            this.Pidgeotto = new Imagem("resources/personagens/Pidgey/Pidgeotto_Down.gif");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Recurso não ecnontrado: "+ex.getMessage());
            System.exit(1);
        }
        
    }

    public void step(long timeElapsed) { 
        
        
        
        Keyboard teclado = GameEngine.getInstance().getKeyboard();

        if(teclado.keyDown(Keys.C)) {
              AudioPlayer.play( "resources/sounds/comeon.wav" );
              if(this.player1 == null){
                  this.player1 = "Charizard";
              } else if(this.player1 != null){
                  Util.sleep(30);
                  this.player2 = "Charizard";
              }
        }
        if(teclado.keyDown(Keys.P)) {
              AudioPlayer.play( "resources/sounds/comeon.wav" );
              if(this.player1 == null){
                  this.player1 = "Pidgeotto";
              } else if(this.player1 != null){
                  Util.sleep(30);
                  this.player2 = "Pidgeotto";
              }
        }
        if(teclado.keyDown(Keys.B)) {
              AudioPlayer.play( "resources/sounds/comeon.wav" );
              if(this.player1 == null){
                  this.player1 = "Bulbasaur";
              } else if(this.player1 != null){
                  Util.sleep(30);
                  this.player2 = "Bulbasaur";
              }
        }
        
        if(this.player1 != "bulbasaur" && this.player2 != null){
            GameEngine.getInstance().setNextGameStateController(3); 
        }
        
        
    }
        
    

    public void draw(Graphics g) {  
        g.setColor(Color.black);
        g.fillRect(0, 0, 800, 700);
        
        g.drawString("escolha o personagem", 325, 420);
        
        this.Charizard.draw(g, 200, 150);
        this.Bulbasaur.draw(g, 350, 150);
        this.Pidgeotto.draw(g, 500, 150);
        
        
        
        
        
    }
    
    public void unload() {    }
    public void start() {    }
    public void stop() {    }
    
    public String getPlayer1(){
        return this.player1;
    }
    public String getPlayer2(){
        return this.player2;
    }
    
    
}
