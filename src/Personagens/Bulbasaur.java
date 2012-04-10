package Personagens;



import java.awt.Graphics;
import javaPlay.GameEngine;
import javaPlay.Imagem;
import javaPlay.Keyboard;
import javax.swing.JOptionPane;
import poke.Keys;



public class Bulbasaur extends Personagem{
    
    //----------------------FAZER COLISAO----------------------\\

    public Bulbasaur(int x, int y){
        this.x = x;
        this.y = y;
        this.numVida = 200;
        this.maxVida = 200;
        
        try{
            this.spriteRight = new Imagem("resources/personagens/Bulbasaur/Bulbasaur_Right.gif");
            this.spriteLeft = new Imagem("resources/personagens/Bulbasaur/Bulbasaur_Left.gif");
            this.spriteDown = new Imagem("resources/personagens/Bulbasaur/Bulbasaur_Down.gif");
            this.spriteUp = new Imagem("resources/personagens/Bulbasaur/Bulbasaur_Up.gif");
            this.spriteAtual = this.spriteRight;
        }catch (Exception ex){
            JOptionPane.showMessageDialog(null, "Recurso não ecnontrado: "+ex.getMessage());
            System.exit(1);
        }
        
    }
    
    public void step(long timeElapsed){
        super.step(timeElapsed);
        

        Keyboard teclado = GameEngine.getInstance().getKeyboard();                   

        if( teclado.keyDown( Keys.ESQUERDA ) && teclado.keyDown( Keys.CIMA) ){
            this.spriteAtual = spriteLeft;
            this.moveEsquerdaCima(7);

        } else if ( teclado.keyDown( Keys.ESQUERDA ) && teclado.keyDown( Keys.BAIXO) ){
            this.spriteAtual = spriteLeft;
            this.moveEsquerdaBaixo(7);

        } else if ( teclado.keyDown( Keys.DIREITA ) && teclado.keyDown( Keys.CIMA) ){
            this.spriteAtual = spriteRight;
            this.moveDireitaCima(7);

        } else if ( teclado.keyDown( Keys.DIREITA ) && teclado.keyDown( Keys.BAIXO) ){
            this.spriteAtual = spriteRight;
            this.moveDireitaBaixo(7);

        } else if( teclado.keyDown( Keys.DIREITA ) ){
            this.spriteAtual = spriteRight;
            this.moveDireita(5);

        } else if( teclado.keyDown( Keys.ESQUERDA ) ){
            this.spriteAtual = spriteLeft;
            this.moveEsquerda(5);

        } else if( teclado.keyDown( Keys.CIMA ) ){
            this.spriteAtual = spriteUp;
            this.moveCima(5);

        } else if( teclado.keyDown( Keys.BAIXO ) ){
            this.spriteAtual = spriteDown;
            this.moveBaixo(5);

        }
    }
    
    public void draw(Graphics g){
        super.draw(g);
    }

}
