package GameState;


import Ataques.*;
import Personagens.*;
import java.awt.Color;
import java.awt.Font;
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
public class Fase1 implements GameStateController {

    
    
    private Imagem charizardIcon;
    private Imagem pidgeottoIcon;
    private Imagem cenario;
    private int numExecucoesStep;
    private int tempoSec;
    private Charizard jogador;
    private Pidgeotto jogador2;
    ArrayList<FlameThrower> flameThrower;
    ArrayList<DragonRage> rage;
    ArrayList<WingAttack> wingAttack;
    ArrayList<Twister> twister2;
    ArrayList<FlameBurst> flameBurst1;

    public void load() {
        try {
            this.cenario = new Imagem("resources/Cenario/Wind.png");
            this.charizardIcon = new Imagem("resources/personagens/Charmander/CharizardIcon_Right.gif");
            this.pidgeottoIcon = new Imagem("resources/personagens/Pidgey/PidgeottoIcon_Left.gif");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Recurso não encontrado: " + ex.getMessage());
            System.exit(1);
        }

        this.numExecucoesStep = 0;
        this.jogador = new Charizard();
        this.jogador2 = new Pidgeotto();
        this.flameThrower = new ArrayList<FlameThrower>();
        this.rage = new ArrayList<DragonRage>();
        this.wingAttack = new ArrayList<WingAttack>();
        this.twister2 = new ArrayList<Twister>();
        this.flameBurst1 = new ArrayList<FlameBurst>();



    }

    public void step(long timeElapsed) {

        if (this.jogador.getVida() <= 0) {
            JOptionPane.showMessageDialog(null, "Player2 Venceu.");
            GameEngine.getInstance().setNextGameStateController(4);
        }
        if (this.jogador2.getVida() <= 0) {
            JOptionPane.showMessageDialog(null, "Player1 Venceu.");
            GameEngine.getInstance().setNextGameStateController(4);
        }

        this.numExecucoesStep++;
        this.tempoSec = this.numExecucoesStep / 60;

        this.jogador.step(timeElapsed);
        this.jogador2.step(timeElapsed);

        for (FlameThrower flames : this.flameThrower) {
            flames.step(timeElapsed);
        }

        for (DragonRage rage : this.rage) {
            rage.step(timeElapsed);
        }

        for (WingAttack wingAttack : this.wingAttack) {
            wingAttack.step(timeElapsed);
        }

        for (Twister twister : this.twister2) {
            twister.persegue(this.jogador);
        }

        for (FlameBurst flameBurst : this.flameBurst1) {
            flameBurst.step(timeElapsed);
        }



        this.lancaWingAttack();
        this.lancaRage();
        this.lancaFlameThrower();

        //this.lancaTwister();
        this.lancaTwister2();

        this.lancaFlameBurst();

        this.verificaColisaoComWingAttackNo1(jogador);
        this.verificaColisaoComRageNo2(jogador2);

        this.verificaColisaoTwisterNo1(jogador);
        //this.verificaColisaoTwisterNo2(jogador2);

        this.verificaColisaoFlameBurstNo2(jogador2);
        this.verificaColisaoComFlameThrowerNo2(jogador2);



        /*
         * if(this.jogador.temColisaoLeft(this.jogador2.getRectangleRight())){
         * //esquerda do p1, direita do p2 this.jogador.setX(this.jogador.getX()
         * + 5); this.jogador2.setX(this.jogador2.getX() - 5); }
         * if(this.jogador.temColisaoDown(this.jogador2.getRectangleUp())){ //em
         * cima do p1, em baixo do p2 this.jogador.setY(this.jogador.getY() +
         * 5); this.jogador2.setY(this.jogador2.getY() - 5); }
         * if(this.jogador.temColisaoUp(this.jogador2.getRectangleDown())){ //em
         * baixo do p1, em cima do p2 this.jogador.setY(this.jogador.getY() -
         * 5); this.jogador2.setY(this.jogador2.getY() + 5); }
         * if(this.jogador.temColisaoRight(this.jogador2.getRectangleLeft())){
         * //direita do p1, esquerda do p2 this.jogador.setX(this.jogador.getX()
         * - 5); this.jogador2.setX(this.jogador2.getX() + 5); }
         *
         * //----------------------------------------------------------\\
         *
         * if(this.jogador.temColisaoLeft(this.jogador2.getRectangleRight())){
         * //esquerda do p2, direita do p1 this.jogador.setX(this.jogador.getX()
         * + 5); this.jogador2.setX(this.jogador2.getX() - 5); }
         * if(this.jogador.temColisaoDown(this.jogador2.getRectangleUp())){ //em
         * cima do p2, em baixo do p1 this.jogador.setY(this.jogador.getY() +
         * 5); this.jogador2.setY(this.jogador2.getY() - 5); }
         * if(this.jogador.temColisaoUp(this.jogador2.getRectangleDown())){ //em
         * baixo do p2, em cima do p1 this.jogador.setY(this.jogador.getY() -
         * 5); this.jogador2.setY(this.jogador2.getY() + 5); }
         * if(this.jogador.temColisaoRight(this.jogador2.getRectangleLeft())){
         * //direita do p2, esquerda do p1 this.jogador.setX(this.jogador.getX()
         * - 5); this.jogador2.setX(this.jogador2.getX() + 5);
        }
         */

    }

    public void draw(Graphics g) {
        this.cenario.draw(g, 0, 185);

        this.jogador2.draw(g);
        this.jogador.draw(g);




        for (FlameThrower flames : this.flameThrower) {
            flames.draw(g);
        }
        for (DragonRage rage : this.rage) {
            rage.draw(g);
        }
        for (WingAttack wingAttack : this.wingAttack) {
            wingAttack.draw(g);
        }
        for (Twister twister : this.twister2) {
            twister.draw(g);
        }
        for (FlameBurst flameBurst : this.flameBurst1) {
            flameBurst.draw(g);
        }

        g.setColor(Color.black);
        g.fillRect(0, 0, 800, 200);

        //this.barraVida.draw(g, 0, 25);
        this.charizardIcon.draw(g, 35, 40);
        this.pidgeottoIcon.draw(g, 725, 40);
        this.mostraBarraAtaques1(g);
        this.mostraBarraAtaques2(g);
        this.mostraBarraVida1(g);
        this.mostraBarraVida2(g);
        g.setColor(Color.white);

        Font font = new Font("Arial", Font.BOLD, 26);
        g.setFont(font);
        g.drawString("VS", 385, 70);
        g.drawString("" + this.tempoSec, 390, 158);



        g.setColor(Color.white);
        g.drawRect(8, 30, 99, 57);
        g.drawRect(692, 30, 99, 58);


    }

    public void unload() {
    }

    public void start() {

        this.numExecucoesStep = 0;


        this.jogador.setVida(200);
        this.jogador2.setVida(200);

        this.jogador.resetPosicao(100, 280);
        this.jogador2.resetPosicao(600, 280);

//        this.rage = new ArrayList<DragonRage>();
//        this.wingAttack = new ArrayList<WingAttack>();
//        this.twister1 = new ArrayList<Twister>();
//        this.twister2 = new ArrayList<Twister>();
//        this.flameBurst1 = new ArrayList<FlameBurst>();
//        this.flameThrower = new ArrayList<FlameThrower>();
        
        this.rage.clear();
        this.wingAttack.clear();
        this.twister2.clear();
        this.flameBurst1.clear();
        this.flameThrower.clear();

        this.jogador.setFrameFlameBurst(this.jogador.getMaxFlameBurst());
        this.jogador.setFrameRage(this.jogador.getMaxRage());
        this.jogador.setFrameFlameThrower(this.jogador.getMaxFlameThrower());

        this.jogador2.setFrameRage(this.jogador2.getMaxRage());
        this.jogador2.setFrameTwister(this.jogador2.getMaxTwister());

    }

    public void stop() {
    }

    private void lancaFlameThrower() {
        Keyboard teclado = GameEngine.getInstance().getKeyboard();

        if (teclado.keyDown(Keys.O)) {
            if (this.jogador.podeAtirarFlameThrower()) {
                this.flameThrower.add(this.jogador.getFlameThrower());
//                this.jogador.setEstado(EstadoPersonagem.FLAMEBURST);
            }
//            int tempoInicial = 0;
//            int tempo = this.tempoSec;
//            if(tempo - tempoInicial >= 1){
//                this.jogador.setEstado(EstadoPersonagem.NORMAL);
//            }

        }
    }

    private void lancaRage() {
        Keyboard teclado = GameEngine.getInstance().getKeyboard();

        if (teclado.keyDown(Keys.P)) {
            if (this.jogador.podeAtirar()) {
                this.rage.add(this.jogador.getRage());
            }
        }
    }

    private void lancaWingAttack() {
        Keyboard teclado = GameEngine.getInstance().getKeyboard();

        if (teclado.keyDown(Keys.V)) {
            if (this.jogador2.podeAtirar()) {
                this.wingAttack.add(this.jogador2.getWingAttack());
            }
        }
    }

    private void lancaTwister2() {
        Keyboard teclado = GameEngine.getInstance().getKeyboard();

        if (teclado.keyDown(Keys.C)) {
            if (this.jogador2.podeAtirarTwister()) {
                this.twister2.add(this.jogador2.getTwister());
            }
        }
    }

    private void lancaFlameBurst() {
        Keyboard teclado = GameEngine.getInstance().getKeyboard();

        if (teclado.keyDown(Keys.I)) {
            if (this.jogador.podeAtirarFlameBurst()) {
                this.flameBurst1.add(this.jogador.getFlameBurst());
            }
        }
    }

    private void verificaColisaoComWingAttackNo1(Charizard p1) {
        for (WingAttack wingAttack : this.wingAttack) {
            if (wingAttack.temColisao(p1.getRetangulo())) {
                p1.perdeVida(wingAttack.getDano());
            }
        }
    }

    private void verificaColisaoComFlameThrowerNo2(Pidgeotto p2) {
        for (FlameThrower flames : this.flameThrower) {
            if (flames.temColisao(p2.getRetangulo())) {
                p2.perdeVida(flames.getDano());
                
                
                
            }
        }
    }

    private void verificaColisaoComRageNo2(Pidgeotto p2) {
        for (DragonRage rage : this.rage) {
            if (rage.temColisao(p2.getRetangulo())) {
                p2.perdeVida(rage.getDano());
            }
        }
    }

    private void verificaColisaoTwisterNo1(Charizard p1) {
        for (Twister twister : this.twister2) {
            if (twister.temColisao(p1.getRetangulo())) {
                p1.perdeVida(twister.getDano());
            }
        }
    }

    private void verificaColisaoFlameBurstNo2(Pidgeotto p2) {
        for (FlameBurst flameBurst : this.flameBurst1) {
            if (flameBurst.temColisao(p2.getRetangulo())) {
                p2.perdeVida(flameBurst.getDano());
            }
        }
    }

    private void mostraBarraAtaques1(Graphics g) {
        if (this.jogador.getFrameRage() < this.jogador.getMaxRage()) {
            g.setColor(Color.red);
            g.fillRect(8, 96, this.jogador.getFrameRage(), 20);
            g.setColor(Color.white);
            g.drawRect(8, 96, this.jogador.getFrameRage(), 19);

        }
        if (this.jogador.getFrameRage() == this.jogador.getMaxRage()) {
            g.setColor(Color.blue);
            g.fillRect(8, 96, this.jogador.getMaxRage(), 20);
            g.setColor(Color.white);
            g.drawRect(8, 96, this.jogador.getMaxRage(), 19);
        }
        if (this.jogador.getFrameRage() > this.jogador.getMaxRage()) {
            g.setColor(Color.blue);
            g.fillRect(8, 96, this.jogador.getMaxRage(), 20);
            g.setColor(Color.white);
            g.drawRect(8, 96, this.jogador.getMaxRage(), 19);
        }

        if (this.jogador.getFrameFlameThrower() < this.jogador.getMaxFlameThrower()) {
            g.setColor(Color.red);
            g.fillRect(8, 124, this.jogador.getFrameFlameThrower(), 20);
            g.setColor(Color.white);
            g.drawRect(8, 124, this.jogador.getFrameFlameThrower(), 19);
        }
        if (this.jogador.getFrameFlameThrower() == this.jogador.getMaxFlameThrower()) {
            g.setColor(Color.blue);
            g.fillRect(8, 124, this.jogador.getMaxFlameThrower(), 20);
            g.setColor(Color.white);
            g.drawRect(8, 124, this.jogador.getMaxFlameThrower(), 19);
        }
        if (this.jogador.getFrameFlameThrower() > this.jogador.getMaxFlameThrower()) {
            g.setColor(Color.blue);
            g.fillRect(8, 124, this.jogador.getMaxFlameThrower(), 20);
            g.setColor(Color.white);
            g.drawRect(8, 124, this.jogador.getMaxFlameThrower(), 19);
        }

        if (this.jogador.getFrameFlameBurst() < this.jogador.getMaxFlameBurst()) {
            g.setColor(Color.red);
            g.fillRect(8, 152, this.jogador.getFrameFlameBurst(), 20);
            g.setColor(Color.white);
            g.drawRect(8, 152, this.jogador.getFrameFlameBurst(), 19);
        }
        if (this.jogador.getFrameFlameBurst() == this.jogador.getMaxFlameBurst()) {
            g.setColor(Color.blue);
            g.fillRect(8, 152, this.jogador.getMaxFlameBurst(), 20);
            g.setColor(Color.white);
            g.drawRect(8, 152, this.jogador.getMaxFlameBurst(), 19);
        }
        if (this.jogador.getFrameFlameBurst() > this.jogador.getMaxFlameBurst()) {
            g.setColor(Color.blue);
            g.fillRect(8, 152, this.jogador.getMaxFlameBurst(), 20);
            g.setColor(Color.white);
            g.drawRect(8, 152, this.jogador.getMaxFlameBurst(), 19);
        }
    }

    private void mostraBarraAtaques2(Graphics g) {
        if (this.jogador2.getFrameRage() < this.jogador2.getMaxRage()) {
            g.setColor(Color.red);
            g.fillRect(791 - this.jogador2.getFrameRage(), 96, this.jogador2.getFrameRage(), 20);
            g.setColor(Color.white);
            g.drawRect(791 - this.jogador2.getFrameRage(), 96, this.jogador2.getFrameRage(), 19);
        }
        if (this.jogador2.getFrameRage() == this.jogador2.getMaxRage()) {
            g.setColor(Color.blue);
            g.fillRect(791 - this.jogador2.getFrameRage(), 96, this.jogador2.getFrameRage(), 20);
            g.setColor(Color.white);
            g.drawRect(791 - this.jogador2.getFrameRage(), 96, this.jogador2.getFrameRage(), 19);
        }
        if (this.jogador2.getFrameRage() > this.jogador2.getMaxRage()) {
            g.setColor(Color.blue);
            g.fillRect(791 - this.jogador2.getMaxRage(), 96, this.jogador2.getMaxRage(), 20);
            g.setColor(Color.white);
            g.drawRect(791 - this.jogador2.getMaxRage(), 96, this.jogador2.getMaxRage(), 19);
        }

        if (this.jogador2.getFrameTwister() < this.jogador2.getMaxTwister()) {
            g.setColor(Color.red);
            g.fillRect(791 - this.jogador2.getFrameTwister(), 124, this.jogador2.getFrameTwister(), 20);
            g.setColor(Color.white);
            g.drawRect(791 - this.jogador2.getFrameTwister(), 124, this.jogador2.getFrameTwister(), 19);
        }
        if (this.jogador2.getFrameTwister() == this.jogador2.getMaxTwister()) {
            g.setColor(Color.blue);
            g.fillRect(791 - this.jogador2.getFrameTwister(), 124, this.jogador2.getFrameTwister(), 20);
            g.setColor(Color.white);
            g.drawRect(791 - this.jogador2.getFrameTwister(), 124, this.jogador2.getFrameTwister(), 19);
        }
        if (this.jogador2.getFrameTwister() > this.jogador2.getMaxTwister()) {
            g.setColor(Color.blue);
            g.fillRect(791 - this.jogador2.getMaxTwister(), 124, this.jogador2.getMaxTwister(), 20);
            g.setColor(Color.white);
            g.drawRect(791 - this.jogador2.getMaxTwister(), 124, this.jogador2.getMaxTwister(), 19);
        }

    }

    public void mostraBarraVida1(Graphics g) {
        g.setColor(Color.GREEN);
        g.fillRect(114, 30, this.jogador.getVida(), 58);
        g.setColor(Color.white);
        g.drawRect(114, 30, this.jogador.getVida(), 57);
    }

    public void mostraBarraVida2(Graphics g) {
        g.setColor(Color.GREEN);
        g.fillRect(686 - this.jogador2.getVida(), 31, this.jogador2.getVida(), 58);
        g.setColor(Color.white);
        g.drawRect(686 - this.jogador2.getVida(), 30, this.jogador2.getVida(), 58);
    }
}
