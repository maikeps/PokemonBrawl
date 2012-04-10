package Personagens;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import javaPlay.GameEngine;
import javaPlay.GameObject;
import javaPlay.Keyboard;
import javaPlay.Imagem;
import javax.swing.JOptionPane;
import Ataques.FlameBurst;
import poke.Keys;
import poke.ObjetoComMovimento;
import Ataques.DragonRage;
import Ataques.FlameThrower;
import Ataques.Twister;
import poke.EstadoPersonagem;

public class Charizard extends Personagem {

    EstadoPersonagem estado;
    int velocidade;
    int velocidadeDiagonal;
    int velocidadeDefault = 7;
    int velocidadeDiagonalDefault = 5;
    int controleRage = 50;
    int framesControleRage = 50;
    int controleFlameThrower = 250;
    int framesControleFlameThrower = 250;
    int controleFlameBurst = 150;
    int framesControleFlameBurst = 150;

    public Charizard() {

        this.estado = estado.NORMAL;

        this.velocidade = 7;
        this.velocidadeDiagonal = 5;

        this.x = 100;
        this.y = 280;
        this.numVida = 200;
        this.maxVida = 200;

        try {
            this.spriteRight = new Imagem("resources/personagens/Charmander/Charizard_Right.gif");
            this.spriteLeft = new Imagem("resources/personagens/Charmander/Charizard_Left.gif");
            this.spriteDown = new Imagem("resources/personagens/Charmander/Charizard_Down.gif");
            this.spriteUp = new Imagem("resources/personagens/Charmander/Charizard_Up.gif");
            this.spriteAtual = this.spriteRight;
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Recurso não ecnontrado: " + ex.getMessage());
            System.exit(1);
        }

    }

    public void step(long timeElapsed) {
        super.step(timeElapsed);

        this.controleRage++;
        this.controleFlameThrower++;
        this.controleFlameBurst++;


        Keyboard teclado = GameEngine.getInstance().getKeyboard();
        if (this.estado == estado.FLAMEBURST) {
            return;
        } else if (this.estado == estado.NORMAL) {
            if (teclado.keyDown(Keys.ESQUERDA) && teclado.keyDown(Keys.CIMA)) {
                this.spriteAtual = spriteLeft;
                this.moveEsquerdaCima(this.velocidade);

            } else if (teclado.keyDown(Keys.ESQUERDA) && teclado.keyDown(Keys.BAIXO)) {
                this.spriteAtual = spriteLeft;
                this.moveEsquerdaBaixo(this.velocidade);

            } else if (teclado.keyDown(Keys.DIREITA) && teclado.keyDown(Keys.CIMA)) {
                this.spriteAtual = spriteRight;
                this.moveDireitaCima(this.velocidade);

            } else if (teclado.keyDown(Keys.DIREITA) && teclado.keyDown(Keys.BAIXO)) {
                this.spriteAtual = spriteRight;
                this.moveDireitaBaixo(this.velocidade);

            } else if (teclado.keyDown(Keys.DIREITA)) {
                this.spriteAtual = spriteRight;
                this.moveDireita(this.velocidadeDiagonal);

            } else if (teclado.keyDown(Keys.ESQUERDA)) {
                this.spriteAtual = spriteLeft;
                this.moveEsquerda(this.velocidadeDiagonal);

            } else if (teclado.keyDown(Keys.CIMA)) {
                this.spriteAtual = spriteUp;
                this.moveCima(this.velocidadeDiagonal);

            } else if (teclado.keyDown(Keys.BAIXO)) {
                this.spriteAtual = spriteDown;
                this.moveBaixo(this.velocidadeDiagonal);

            }

        }


    }

    public void draw(Graphics g) {
        super.draw(g);
    }

    public DragonRage getRage() {
        //O tiro pode sair de qualquer um dos oito lados.
        //E o x e y inicial do tiro podem sempre ser diferentes

        int xTiro = this.x;
        int yTiro = this.y;
        int tamanhoPokemon = 80;
        int metadePokemon = tamanhoPokemon / 2;



        switch (this.direcao) {
            case DIREITA:
                xTiro += tamanhoPokemon - 15;
                yTiro -= 1;
                break;
            case ESQUERDA:
                yTiro += 1;
                xTiro -= tamanhoPokemon + 15;
                break;
            case CIMA:
                xTiro += metadePokemon / 2 - 11;
                yTiro -= tamanhoPokemon + 25;
                break;
            case BAIXO:
                xTiro += metadePokemon / 2 - 10;
                yTiro += tamanhoPokemon - 50;
                break;
            case DIREITA_CIMA:
                xTiro += tamanhoPokemon - 15;
                yTiro -= tamanhoPokemon - 20;
                break;
            case DIREITA_BAIXO:
                xTiro += tamanhoPokemon - 10;
                yTiro += tamanhoPokemon - 55;
                break;
            case ESQUERDA_CIMA:
                xTiro -= tamanhoPokemon - 10;
                yTiro -= tamanhoPokemon - 25;
                break;
            case ESQUERDA_BAIXO:
                xTiro -= tamanhoPokemon - 5;
                yTiro += tamanhoPokemon - 60;
                break;
        }

        this.setFrameRage(1);
        //this.controleRage = 1;
        return new DragonRage(xTiro, yTiro, this.direcao);

    }

    public FlameThrower getFlameThrower() {

        int xTiro = this.x;
        int yTiro = this.y;




        switch (this.direcao) {
            case DIREITA:
                xTiro += 65;
                yTiro -= 1;
                break;
            case ESQUERDA:
                yTiro -= 5;
                xTiro -= 210;
                break;
            case CIMA:
                xTiro += 9;
                yTiro -= 105;
                break;
            case BAIXO:
                xTiro += 10;
                yTiro += 30;
                break;
            case DIREITA_CIMA:
                xTiro += 65;
                yTiro -= 60;
                break;
            case DIREITA_BAIXO:
                xTiro += 70;
                yTiro += 25;
                break;
            case ESQUERDA_CIMA:
                xTiro -= 70;
                yTiro -= 65;
                break;
            case ESQUERDA_BAIXO:
                xTiro -= 75;
                yTiro += 10;
                break;
        }


        this.setFrameFlameThrower(1);
        return new FlameThrower(xTiro, yTiro, this.direcao);

    }

    public FlameBurst getFlameBurst() {

        int xTiro = this.x - 60;
        int yTiro = this.y - 85;


        this.setFrameFlameBurst(1);
        return new FlameBurst(xTiro, yTiro);

    }

    public boolean podeAtirar() {
        return (this.controleRage > this.framesControleRage);
    }

    public boolean podeAtirarFlameThrower() {
        return (this.controleFlameThrower > this.framesControleFlameThrower);
    }

    public boolean podeAtirarFlameBurst() {
        return (this.controleFlameBurst > this.framesControleFlameBurst);
    }

    /*
     * public boolean temColisaoLeft(Rectangle left){ return
     * this.getRectangleLeft().intersects(left); }
     *
     * public boolean temColisaoRight(Rectangle right){ return
     * this.getRectangleRight().intersects(right); }
     *
     * public boolean temColisaoUp(Rectangle up){ return
     * this.getRectangleUp().intersects(up); }
     *
     * public boolean temColisaoDown(Rectangle down){ return
     * this.getRectangleDown().intersects(down); }
     */
//    public boolean temColisao(Pidgeotto p2){
//        return this.getRetangulo().intersects( p2.getRetangulo() );
//    }
    /*
     * public Rectangle getRectangleRight(){ return new Rectangle(this.x, this.y
     * , 100, 100); }
     *
     * public Rectangle getRectangleLeft(){ return new Rectangle(this.x, this.y,
     * 100, 100); }
     *
     * public Rectangle getRectangleDown(){ return new Rectangle(this.x, this.y
     * + 10, 100, 100); }
     *
     * public Rectangle getRectangleUp(){ return new Rectangle(this.x, this.y,
     * 100, 100); }
     */
    public int getFrameRage() {
        return this.controleRage;
    }

    public int getMaxRage() {
        return this.framesControleRage;
    }

    public void setFrameRage(int n) {
        this.controleRage = n;
    }

    public int getFrameFlameThrower() {
        return this.controleFlameThrower;
    }

    public int getMaxFlameThrower() {
        return this.framesControleFlameThrower;
    }

    public void setFrameFlameThrower(int n) {
        this.controleFlameThrower = n;
    }

    public int getFrameFlameBurst() {
        return this.controleFlameBurst;
    }

    public int getMaxFlameBurst() {
        return this.framesControleFlameBurst;
    }

    public void setFrameFlameBurst(int n) {
        this.controleFlameBurst = n;
    }

    public int getVelocidade() {
        return velocidade;
    }

    public void setVelocidade(int velocidade) {
        this.velocidade = velocidade;
    }

    public int getVelocidadeDefault() {
        return velocidadeDefault;
    }

    public int getVelocidadeDiagonal() {
        return velocidadeDiagonal;
    }

    public void setVelocidadeDiagonal(int velocidadeDiagonal) {
        this.velocidadeDiagonal = velocidadeDiagonal;
    }

    public int getVelocidadeDiagonalDefault() {
        return velocidadeDiagonalDefault;
    }

    public EstadoPersonagem getEstado() {
        return estado;
    }

    public void setEstado(EstadoPersonagem estado) {
        this.estado = estado;
    }
}
