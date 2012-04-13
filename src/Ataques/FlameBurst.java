package Ataques;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import javaPlay.GameObject;
import javaPlay.Sprite;
import javaPlayExtras.AudioPlayer;
import javax.swing.JOptionPane;
import poke.ObjetoComMovimento;

public class FlameBurst extends ObjetoComMovimento{

    boolean desativado;
    int frameElapsed;
    int frame;
    Sprite sprite;
    Sprite spriteVazio;
    Sprite spriteAtual;
    

    public FlameBurst(int x, int y){
        AudioPlayer.play("resources/sounds/Sound 1.wav");
        this.desativado = false;
        this.x = x;
        this.y = y;
        this.frame = 0;
        
        try{
            this.sprite = new Sprite("resources/ataques/Flame Burst/FlameBurst.png", 4, 240, 250);
        }catch (Exception ex){
            JOptionPane.showMessageDialog(null, "Recurso não encontrado: "+ex.getMessage());
            System.exit(1);
        }
        
        
        
    }

    public void step(long timeElapsed) {
        if(this.frame >= 4){
            return;
        }
        
        this.frameElapsed += 1;
        if(this.frameElapsed > 4){
            this.frame++;
            this.sprite.setCurrAnimFrame(this.frame);
            this.frameElapsed -= 4;
        }        
    }

    @Override
    public void draw(Graphics g) {
        //if(this.desativado){
        //    return;
        //}
        //if(this.desativado == true){
            this.sprite.draw(g, this.x, this.y);
        //}
    }
    
    public Rectangle getRetangulo(){
        return new Rectangle(this.x, this.y, 231, 248);
        //get height, get width, Imagem ao inves de Sprite
        //return new Rectangle(this.x, this.y, this.spriteAtual.pegaLargura(), this.spriteAtual.pegaAltura());
    }
    
    public boolean temColisao(Rectangle retangulo){
        if(this.desativado || this.frame == 4){
            return false;
        }
        
        if(this.getRetangulo().intersects(retangulo)){
           // if(this.frames >= 25){
                this.desativado = true;
           // }
        return true; 
        } else {
            return false;
        }
    }
    
    public int getFrames(){
        return this.frameElapsed;
    }
    


}
