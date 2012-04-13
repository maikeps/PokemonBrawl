package GameState;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


import Ataques.DragonRage;
import Ataques.Twister;
import Ataques.WingAttack;
import Ataques.FlameBurst;
import Personagens.*;
import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import javaPlay.GameEngine;
import javaPlay.GameStateController;
import javaPlay.Keyboard;
import javax.swing.JOptionPane;
import poke.*;


/**
 *
 * @author Maike
 */
public class FaseBulbasaurTeste implements GameStateController{

    
    private int numExecucoesStep;
    private int tempoSec;
    private Player1 jogador;
    private Player2 jogador2;
    ArrayList<DragonRage> rage;
    ArrayList<WingAttack> wingAttack;
    ArrayList<Twister> twister1;
    ArrayList<Twister> twister2;
    ArrayList<FlameBurst> flameBurst1;
    private Charizard charizard;
    private Bulbasaur bulbasaur;
    
    
    public void load() {
        
        this.numExecucoesStep = 0;
        this.jogador = new Player1(charizard);
        this.jogador2 = new Player2(bulbasaur);
        this.rage = new ArrayList<DragonRage>();
        this.wingAttack = new ArrayList<WingAttack>();
        this.twister1 = new ArrayList<Twister>();
        this.twister2 = new ArrayList<Twister>();
        this.flameBurst1 = new ArrayList<FlameBurst>();
        
        
        
    }

    public void step(long timeElapsed) {
        
        if(this.charizard.getVida() <= 0){
            JOptionPane.showMessageDialog(null, "Player2 Venceu.");
            GameEngine.getInstance().setNextGameStateController(4); 
        }
        if(this.bulbasaur.getVida() <= 0){
            JOptionPane.showMessageDialog(null, "Player1 Venceu.");
            GameEngine.getInstance().setNextGameStateController(4); 
        }
        
        this.numExecucoesStep ++;
        this.tempoSec = this.numExecucoesStep/60;
        
        this.jogador.step(timeElapsed);
        this.jogador2.step(timeElapsed);
        
        for (DragonRage rage : this.rage) {
            rage.step(timeElapsed);
        }
        
        for (WingAttack wingAttack : this.wingAttack) {
            wingAttack.step(timeElapsed);
        }
        
        for (Twister twister : this.twister1) {
            twister.persegue(this.jogador2);
        }
        for (Twister twister : this.twister2) {
            twister.persegue(this.charizard);
        }
        
        for (FlameBurst flameBurst : this.flameBurst1) {
            flameBurst.step(timeElapsed);
        }
        
        
        
        this.lancaRage();
        
        this.lancaTwister();
        
        this.lancaFlameBurst();
        
        this.verificaColisaoComWingAttackNo1(charizard);
        
        this.verificaColisaoTwisterNo1(charizard);
        
        
        
       /* if(this.jogador.temColisaoLeft(this.jogador2.getRectangleRight())){
            //esquerda do p1, direita do p2
            this.jogador.setX(this.jogador.getX() + 5);
            this.jogador2.setX(this.jogador2.getX() - 5);
        }
        if(this.jogador.temColisaoDown(this.jogador2.getRectangleUp())){
            //em cima do p1, em baixo do p2
            this.jogador.setY(this.jogador.getY() + 5);
            this.jogador2.setY(this.jogador2.getY() - 5);
        }
        if(this.jogador.temColisaoUp(this.jogador2.getRectangleDown())){
            //em baixo do p1, em cima do p2
            this.jogador.setY(this.jogador.getY() - 5);
            this.jogador2.setY(this.jogador2.getY() + 5);
        }
        if(this.jogador.temColisaoRight(this.jogador2.getRectangleLeft())){
            //direita do p1, esquerda do p2
            this.jogador.setX(this.jogador.getX() - 5);
            this.jogador2.setX(this.jogador2.getX() + 5);
        }
        
        //----------------------------------------------------------\\
        
        if(this.jogador.temColisaoLeft(this.jogador2.getRectangleRight())){
            //esquerda do p2, direita do p1
            this.jogador.setX(this.jogador.getX() + 5);
            this.jogador2.setX(this.jogador2.getX() - 5);
        }
        if(this.jogador.temColisaoDown(this.jogador2.getRectangleUp())){
            //em cima do p2, em baixo do p1
            this.jogador.setY(this.jogador.getY() + 5);
            this.jogador2.setY(this.jogador2.getY() - 5);
        }
        if(this.jogador.temColisaoUp(this.jogador2.getRectangleDown())){
            //em baixo do p2, em cima do p1
            this.jogador.setY(this.jogador.getY() - 5);
            this.jogador2.setY(this.jogador2.getY() + 5);
        }
        if(this.jogador.temColisaoRight(this.jogador2.getRectangleLeft())){
            //direita do p2, esquerda do p1
            this.jogador.setX(this.jogador.getX() - 5);
            this.jogador2.setX(this.jogador2.getX() + 5);
        }*/
        
        
    }
    
    public void draw(Graphics g) {
        g.setColor(Color.black);
        g.fillRect(0,0,800,100);
        g.setColor(Color.getHSBColor(51, 52, 22));
        //g.setColor(Color.ORANGE);
        g.fillRect(0,100,800,600);
        g.setColor(Color.gray);
        g.fillRect(390, 100, 20, 500);
        g.fillRect(0,100,40,500);
        g.fillRect(760,100,40,500);
        g.fillRect(0, 100, 800, 30);
        g.fillRect(0,570,800,30);
        g.setColor(Color.red);
        g.fillArc(350, 287, 100, 100, 0, 180);
        g.setColor(Color.white);
        g.fillArc(350, 287, 100, 100, 0, -180);
        g.setColor(Color.black);
        g.fillOval(388, 326, 22, 22);
        g.setColor(Color.LIGHT_GRAY);
        g.fillOval(389, 327, 20, 20);
        g.setColor(Color.RED);
        g.drawString("Tempo: " + this.tempoSec, 375, 70);
        //g.setColor(Color.getHSBColor(40, 55, 75));
        //g.drawOval(this.jogador.getX(),this.jogador.getY(),10,10);
        this.jogador2.draw(g);
        this.jogador.draw(g);
        
        
        
       
        this.mostraBarraAtaques1(g);
        
        
        
        
        
        for (DragonRage rage : this.rage) {
            rage.draw(g);
        }
        
        for (WingAttack wingAttack : this.wingAttack) {
            wingAttack.draw(g);
        }
        
        for (Twister twister : this.twister1) {
            twister.draw(g);
        }
        
        for (Twister twister : this.twister2) {
            twister.draw(g);
        }
        
        for (FlameBurst flameBurst : this.flameBurst1) {
            flameBurst.draw(g);
        }
        
    }
    
    public void unload() {}
    public void start() {
        
    }
    public void stop() {}
    
    
    private void lancaRage() {
        Keyboard teclado = GameEngine.getInstance().getKeyboard();

        if(teclado.keyDown( Keys.P) ){
            if(this.charizard.podeAtirar()){
                this.rage.add( this.charizard.getRage() );
            }
        }
    }
    
    
    
    private void lancaTwister() {
        Keyboard teclado = GameEngine.getInstance().getKeyboard();

        if(teclado.keyDown( Keys.O) ){
            if(this.charizard.podeAtirarTwister()){
                this.twister1.add( this.charizard.getTwister() );
            }
        }
    }
    
    
    
    private void lancaFlameBurst(){
        Keyboard teclado = GameEngine.getInstance().getKeyboard();

        if(teclado.keyDown( Keys.I) ){
            if(this.charizard.podeAtirarFlameBurst()){
                this.flameBurst1.add(this.charizard.getFlameBurst());
            }
        }
    }
    
    private void verificaColisaoComWingAttackNo1(Charizard p1){
        for(WingAttack wingAttack : this.wingAttack){                        
            if(wingAttack.temColisao(p1.getRetangulo())){                
                p1.perdeVida(5);
                //this.explosoes.add( new ExplosaoFraca(nave.getX(), nave.getY()) );
            }
        }
    }
    
    private void verificaColisaoComRageNo2(Pidgeotto p2){
        for(DragonRage rage : this.rage){                        
            if(rage.temColisao(p2.getRetangulo())){                
                p2.perdeVida(5);
            }
        }
    }
    
    private void verificaColisaoTwisterNo1(Charizard p1){
        for(Twister twister : this.twister2){
            if(twister.temColisao(p1.getRetangulo())){
                p1.perdeVida(2);
            }
        }
    }
    
    private void verificaColisaoTwisterNo2(Pidgeotto p2){
        for(Twister twister : this.twister1){
            if(twister.temColisao(p2.getRetangulo())){
                p2.perdeVida(2);
            }
        }
    }
    
    private void verificaColisaoFlameBurstNo2(Pidgeotto p2){
        for(FlameBurst flameBurst : this.flameBurst1){
            if(flameBurst.temColisao(p2.getRetangulo())){
                //int frames = flameBurst.getFrames();
                //if(frames == 25){
                    p2.perdeVida(10);
               // }
            }
        }
    }
    
    private void mostraBarraAtaques1(Graphics g){
        if(this.charizard.getFrameRage() < this.charizard.getMaxRage()){
            g.setColor(Color.red);
            g.fillRect(50, 50, this.charizard.getFrameRage(), 10);
        }    
        if(this.charizard.getFrameRage() == this.charizard.getMaxRage()){
            g.setColor(Color.green);
            g.fillRect(50, 50, this.charizard.getMaxRage(), 10);
        }
        if(this.charizard.getFrameRage() > this.charizard.getMaxRage()){
            g.setColor(Color.green);
            g.fillRect(50, 50, this.charizard.getMaxRage(), 10);
        }
        
        if(this.charizard.getFrameTwister() < this.charizard.getMaxTwister()){
            g.setColor(Color.red);
            g.fillRect(50, 80, this.charizard.getFrameTwister(), 10);
        }    
        if(this.charizard.getFrameTwister() == this.charizard.getMaxTwister()){
            g.setColor(Color.green);
            g.fillRect(50, 80, this.charizard.getMaxTwister(), 10);
        }
        if(this.charizard.getFrameTwister() > this.charizard.getMaxTwister()){
            g.setColor(Color.green);
            g.fillRect(50, 80, this.charizard.getMaxTwister(), 10);
        }
        
        if(this.charizard.getFrameFlameBurst() < this.charizard.getMaxFlameBurst()){
            g.setColor(Color.red);
            g.fillRect(50, 65, this.charizard.getFrameFlameBurst(), 10);
        }    
        if(this.charizard.getFrameFlameBurst() == this.charizard.getMaxFlameBurst()){
            g.setColor(Color.green);
            g.fillRect(50, 65, this.charizard.getMaxFlameBurst(), 10);
        }
        if(this.charizard.getFrameFlameBurst() > this.charizard.getMaxFlameBurst()){
            g.setColor(Color.green);
            g.fillRect(50, 65, this.charizard.getMaxFlameBurst(), 10);
        }
    }
    

}
