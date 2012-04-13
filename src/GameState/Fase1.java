package GameState;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


import Ataques.DragonRage;
import Ataques.Twister;
import Ataques.WingAttack;
import Ataques.FlameBurst;
import Personagens.Pidgeotto;
import Personagens.Charizard;
import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javaPlay.GameEngine;
import javaPlay.GameStateController;
import javaPlay.Imagem;
import javaPlay.Keyboard;
import javax.swing.JOptionPane;
import poke.*;


/**
 *
 * @author Maike
 */
public class Fase1 implements GameStateController{

    private Imagem cenario;
    private int numExecucoesStep;
    private int tempoSec;
    private Charizard jogador;
    private Pidgeotto jogador2;
    ArrayList<DragonRage> rage;
    ArrayList<WingAttack> wingAttack;
    ArrayList<Twister> twister1;
    ArrayList<Twister> twister2;
    ArrayList<FlameBurst> flameBurst1;
    
    
    
    public void load() {
        try {
            this.cenario = new Imagem("resources/Cenario/Wind.png");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Recurso não encontrado: " + ex.getMessage());
        }
        
        this.numExecucoesStep = 0;
        this.jogador = new Charizard();
        this.jogador2 = new Pidgeotto();
        this.rage = new ArrayList<DragonRage>();
        this.wingAttack = new ArrayList<WingAttack>();
        this.twister1 = new ArrayList<Twister>();
        this.twister2 = new ArrayList<Twister>();
        this.flameBurst1 = new ArrayList<FlameBurst>();
        
        
        
    }

    public void step(long timeElapsed) {
        
        if(this.jogador.getVida() <= 0){
            JOptionPane.showMessageDialog(null, "Player2 Venceu.");
            GameEngine.getInstance().setNextGameStateController(4); 
        }
        if(this.jogador2.getVida() <= 0){
            JOptionPane.showMessageDialog(null, "Player1 Venceu.");
            GameEngine.getInstance().setNextGameStateController(4); 
        }
        
        this.numExecucoesStep ++;
        this.tempoSec = this.numExecucoesStep/40;
        
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
            twister.persegue(this.jogador);
        }
        
        for (FlameBurst flameBurst : this.flameBurst1) {
            flameBurst.step(timeElapsed);
        }
        
        
        
        this.lancaWingAttack();
        this.lancaRage();
        
        //this.lancaTwister();
        this.lancaTwister2();
        
        this.lancaFlameBurst();
        
        this.verificaColisaoComWingAttackNo1(jogador);
        this.verificaColisaoComRageNo2(jogador2);
        
        this.verificaColisaoTwisterNo1(jogador);
        this.verificaColisaoTwisterNo2(jogador2);
        
        verificaColisaoFlameBurstNo2(jogador2);
        
        
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
        this.cenario.draw(g, 0, 85);
        
        g.setColor(Color.black);
        g.fillRect(0,0,800,100);

        
//        g.setColor(Color.getHSBColor(51, 52, 22));
//        g.fillRect(0,100,800,600);
//        g.setColor(Color.gray);
//        g.fillRect(390, 100, 20, 500);
//        g.fillRect(0,100,40,500);
//        g.fillRect(760,100,40,500);
//        g.fillRect(0, 100, 800, 30);
//        g.fillRect(0,570,800,30);
//        g.setColor(Color.red);
//        g.fillArc(350, 287, 100, 100, 0, 180);
//        g.setColor(Color.white);
//        g.fillArc(350, 287, 100, 100, 0, -180);
//        g.setColor(Color.black);
//        g.fillOval(388, 326, 22, 22);
//        g.setColor(Color.LIGHT_GRAY);
//        g.fillOval(389, 327, 20, 20);
        
        g.setColor(Color.RED);
        g.drawString("Tempo: " + this.tempoSec, 375, 70);
        //g.setColor(Color.getHSBColor(40, 55, 75));
        //g.drawOval(this.jogador.getX(),this.jogador.getY(),10,10);
        this.jogador2.draw(g);
        this.jogador.draw(g);
        
        
        
       
        this.mostraBarraAtaques1(g);
        this.mostraBarraAtaques2(g);
        
        
        
        
        
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
        this.jogador.setVida(100);
        this.jogador2.setVida(100);
        
        this.jogador.resetPosicao();
        this.jogador2.resetPosicao();
        
        this.rage = new ArrayList<DragonRage>();
        this.wingAttack = new ArrayList<WingAttack>();
        this.twister1 = new ArrayList<Twister>();
        this.twister2 = new ArrayList<Twister>();
        this.flameBurst1 = new ArrayList<FlameBurst>();
        
        this.jogador.setFrameFlameBurst(this.jogador.getMaxFlameBurst());
        this.jogador.setFrameRage(this.jogador.getMaxRage());
        this.jogador.setFrameTwister(this.jogador.getMaxTwister());
        
        this.jogador2.setFrameRage(this.jogador.getMaxRage());
        this.jogador2.setFrameTwister(this.jogador.getMaxTwister());
        
    }
    public void stop() {}
    
    
    private void lancaRage() {
        Keyboard teclado = GameEngine.getInstance().getKeyboard();

        if(teclado.keyDown( Keys.P) ){
            if(this.jogador.podeAtirar()){
                this.rage.add( this.jogador.getRage() );
            }
        } else {
            
        }
    }
    
    private void lancaWingAttack() {
        Keyboard teclado = GameEngine.getInstance().getKeyboard();

        if(teclado.keyDown( Keys.V) ){
            if(this.jogador2.podeAtirar()){
                this.wingAttack.add( this.jogador2.getWingAttack() );
            }
        }
    }
    
    private void lancaTwister() {
        Keyboard teclado = GameEngine.getInstance().getKeyboard();

        if(teclado.keyDown( Keys.O) ){
            if(this.jogador.podeAtirarTwister()){
                this.twister1.add( this.jogador.getTwister() );
            }
        }
    }
    
    private void lancaTwister2() {
        Keyboard teclado = GameEngine.getInstance().getKeyboard();

        if(teclado.keyDown( Keys.C) ){
            if(this.jogador2.podeAtirarTwister()){
                this.twister2.add( this.jogador2.getTwister() );
            }
        }
    }
    
    private void lancaFlameBurst(){
        Keyboard teclado = GameEngine.getInstance().getKeyboard();

        if(teclado.keyDown( Keys.I) ){
            if(this.jogador.podeAtirarFlameBurst()){
                this.flameBurst1.add(this.jogador.getFlameBurst());
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
        if(this.jogador.getFrameRage() < this.jogador.getMaxRage()){
            g.setColor(Color.red);
            g.fillRect(50, 50, this.jogador.getFrameRage(), 10);
        }    
        if(this.jogador.getFrameRage() == this.jogador.getMaxRage()){
            g.setColor(Color.green);
            g.fillRect(50, 50, this.jogador.getMaxRage(), 10);
        }
        if(this.jogador.getFrameRage() > this.jogador.getMaxRage()){
            g.setColor(Color.green);
            g.fillRect(50, 50, this.jogador.getMaxRage(), 10);
        }
        
        if(this.jogador.getFrameTwister() < this.jogador.getMaxTwister()){
            g.setColor(Color.red);
            g.fillRect(50, 80, this.jogador.getFrameTwister(), 10);
        }    
        if(this.jogador.getFrameTwister() == this.jogador.getMaxTwister()){
            g.setColor(Color.green);
            g.fillRect(50, 80, this.jogador.getMaxTwister(), 10);
        }
        if(this.jogador.getFrameTwister() > this.jogador.getMaxTwister()){
            g.setColor(Color.green);
            g.fillRect(50, 80, this.jogador.getMaxTwister(), 10);
        }
        
        if(this.jogador.getFrameFlameBurst() < this.jogador.getMaxFlameBurst()){
            g.setColor(Color.red);
            g.fillRect(50, 65, this.jogador.getFrameFlameBurst(), 10);
        }    
        if(this.jogador.getFrameFlameBurst() == this.jogador.getMaxFlameBurst()){
            g.setColor(Color.green);
            g.fillRect(50, 65, this.jogador.getMaxFlameBurst(), 10);
        }
        if(this.jogador.getFrameFlameBurst() > this.jogador.getMaxFlameBurst()){
            g.setColor(Color.green);
            g.fillRect(50, 65, this.jogador.getMaxFlameBurst(), 10);
        }
    }
    
    
    private void mostraBarraAtaques2(Graphics g){
        if(this.jogador2.getFrameRage() < this.jogador2.getMaxRage()){
            g.setColor(Color.red);
            g.fillRect(750-this.jogador2.getFrameRage(), 50, this.jogador2.getFrameRage(), 10);
        }    
        if(this.jogador2.getFrameRage() == this.jogador2.getMaxRage()){
            g.setColor(Color.green);
            g.fillRect(750-this.jogador2.getFrameRage(), 50, this.jogador2.getFrameRage(), 10);        
        }
        if(this.jogador2.getFrameRage() > this.jogador2.getMaxRage()){
            g.setColor(Color.green);
            g.fillRect(750-this.jogador2.getMaxRage(), 50, this.jogador2.getMaxRage(), 10);
        }
        
        if(this.jogador2.getFrameTwister() < this.jogador2.getMaxTwister()){
            g.setColor(Color.red);
            g.fillRect(750-this.jogador2.getFrameTwister(), 80, this.jogador2.getFrameTwister(), 10);
        }    
        if(this.jogador2.getFrameTwister() == this.jogador2.getMaxTwister()){
            g.setColor(Color.green);
            g.fillRect(750-this.jogador2.getFrameTwister(), 80, this.jogador2.getFrameTwister(), 10);
        }
        if(this.jogador2.getFrameTwister() > this.jogador2.getMaxTwister()){
            g.setColor(Color.green);
            g.fillRect(750-this.jogador2.getMaxTwister(), 80, this.jogador2.getMaxTwister(), 10);
        }
        
    }
}
