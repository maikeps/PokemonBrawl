package Ataques;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import javaPlay.GameObject;
import javaPlay.Sprite;
import javax.swing.JOptionPane;
import poke.ObjetoComMovimento;

public class FlameBurstGIF extends ObjetoComMovimento{

    boolean desativado;
    int frameElapsed;
    Sprite sprite;
    Sprite spriteVazio;
    Sprite spriteAtual;
    

    public FlameBurstGIF(int x, int y){
        this.desativado = false;
        this.x = x;
        this.y = y;
        
        int frameElapsed = 1;
        
        try{
            this.sprite = new Sprite("resources/ataques/FlameBurst teste nao para.gif",1,321,248);
            this.spriteVazio = new Sprite("resources/ataques/Rage_Vazio.png",1,60,60);
            this.spriteAtual = spriteVazio;
        }catch (Exception ex){
            JOptionPane.showMessageDialog(null, "Recurso não ecnontrado: "+ex.getMessage());
            System.exit(1);
        }
        
        
        
    }

    public void step(long timeElapsed) {
        this.frameElapsed ++;
        
    }

    @Override
    public void draw(Graphics g) {
        if(this.desativado){
            return;
        }
        if(this.desativado == true){
            this.spriteAtual.draw(g, this.x, this.y);
        }
    }
    
    public Rectangle getRetangulo(){
        return new Rectangle(this.x, this.y, 231, 248);
        //get height, get width, Imagem ao inves de Sprite
        //return new Rectangle(this.x, this.y, this.spriteAtual.pegaLargura(), this.spriteAtual.pegaAltura());
    }
    
    public boolean temColisao(Rectangle retangulo){
        if(this.desativado){
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
