/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Personagens;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import javaPlay.GameEngine;
import javaPlay.Imagem;
import javaPlay.Keyboard;
import javax.swing.JOptionPane;
import poke.Keys;
import poke.ObjetoComMovimento;

/**
 *
 * @author Maike
 */
public abstract class Personagem extends ObjetoComMovimento {

    private int velocidade = 5;
    protected Imagem spriteLeft;
    protected Imagem spriteRight;
    protected Imagem spriteDown;
    protected Imagem spriteUp;
    protected Imagem spriteAtual;
    Imagem sprite;
    int numVida;
    int maxVida;
    

    public void step(long timeElapsed) {

        if (this.tocaCenarioDireita()) {
            this.x = 730;
        }
        if (this.tocaCenarioEsquerda()) {
            this.x = 5;
        }
        if (this.tocaCenarioBaixo()) {
            this.y = 635;
        }
        if (this.tocaCenarioCima()) {
            this.y = 190;
        }

    }

    public void draw(Graphics g) {
        this.spriteAtual.draw(g, this.x, this.y);
//        g.setColor(Color.black);
//        g.fillRect(this.x - 10, this.y - 11, this.maxVida + 2, 12);
//        g.setColor(Color.GREEN);
//        g.fillRect(this.x - 9, this.y - 10, this.numVida, 10);
    }

    public boolean tocaCenarioDireita() {
        return (this.x >= 730);
    }

    public boolean tocaCenarioEsquerda() {
        return (this.x < 5);
    }

    public boolean tocaCenarioBaixo() {
        return (this.y > 635);
    }

    public boolean tocaCenarioCima() {
        return (this.y < 190);
    }

    public boolean temColisao(Personagem player) {
        return this.getRetangulo().intersects(player.getRetangulo());
    }

    public Rectangle getRetangulo() {
        return new Rectangle(this.x, this.y + 35, this.spriteAtual.pegaLargura(), this.spriteAtual.pegaAltura());
    }

    public boolean estaMorto() {
        return (this.numVida <= 0);
    }

    public void perdeVida(int numPontos){
        this.numVida -= numPontos;  
    }
    
    public int getVida() {
        return this.numVida;
    }

    public void setVida(int vida) {
        this.numVida = vida;
    }

    public void resetPosicao(int x, int y) {
        this.x = x;
        this.y = y;
    }
}
