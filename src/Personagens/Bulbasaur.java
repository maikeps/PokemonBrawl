package Personagens;



import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import javaPlay.GameEngine;
import javaPlay.Keyboard;
import javaPlay.Imagem;
import javax.swing.JOptionPane;
import poke.Keys;
import poke.ObjetoComMovimento;



public class Bulbasaur extends ObjetoComMovimento{
    
    //----------------------FAZER COLISAO----------------------\\
    
    private int velocidade = 5;
    private int diametro = 20;
    protected Imagem spriteLeft;
    protected Imagem spriteRight;
    protected Imagem spriteDown;
    protected Imagem spriteUp;
    protected Imagem spriteAtual;
    Imagem sprite;

    int numVida;
    int maxVida;
    
    int controleRage = 5;
    int framesControleRage = 5;
    int controleTwister = 200;
    int framesControleTwister = 200;
    int controleFlameBurst = 150;
    int framesControleFlameBurst = 150;
    
   
    
    public Bulbasaur(int x, int y){
        this.x = x;
        this.y = y;
        this.numVida = 100;
        this.maxVida = 100;
        
        /*try{
            this.sprite = new Sprite("resources/personagens/Gif_Sheet_Charizard.gif", 4, 320, 75);
        }catch (Exception ex){
            JOptionPane.showMessageDialog(null, "Recurso não ecnontrado: "+ex.getMessage());
        }*/
        
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
        

        if(this.numVida<=0){
            JOptionPane.showMessageDialog(null, "Player2 Venceu.");
            System.exit(1);
        }
        
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
        
       
        if(this.tocaCenarioDireita()){
            this.x = 710;
        }
        if(this.tocaCenarioEsquerda()){
            this.x = 7;
        }
        if(this.tocaCenarioBaixo()){
            this.y = 485;
        }
        if(this.tocaCenarioCima()){
            this.y = 70;
        }
        
        
    }
    
    public void draw(Graphics g){
        this.spriteAtual.draw(g, this.x, this.y);
        //esse drawRect mostra onde deveria estar acontecendo a colisao
        //g.drawRect(this.x-40, this.y-40, 90, 80);
        g.setColor(Color.black);
       // g.fillRect(this.x-1, this.y+28, this.maxVida+2, 12);
        g.fillRect(this.x-1, this.y+3, this.maxVida+2, 12);
        g.setColor(Color.GREEN);
        //g.fillRect(this.x, this.y+29, this.numVida, 10);
        g.fillRect(this.x, this.y+4, this.numVida, 10);
        //this.vida.draw(g,this.x+11, this.y+5);
        //g.drawString(this.numVida+"", this.x+5, this.y-15);
        
    }
    
    
    public boolean tocaCenarioDireita(){
        return (this.x + this.diametro >= 730);
    }
    
    public boolean tocaCenarioEsquerda(){
        return (this.x < 7);
    }
    
    public boolean tocaCenarioBaixo(){
        return (this.y > 485);
    }
    
    public boolean tocaCenarioCima(){
        return (this.y < 70);
    }
    
    
    /*
    public boolean temColisaoLeft(Rectangle left){
        return this.getRectangleLeft().intersects(left);
    }
    
    public boolean temColisaoRight(Rectangle right){
        return this.getRectangleRight().intersects(right);
    }
    
    public boolean temColisaoUp(Rectangle up){
        return this.getRectangleUp().intersects(up);
    }
    
    public boolean temColisaoDown(Rectangle down){
        return this.getRectangleDown().intersects(down);
    }*/
    
    public boolean temColisao(Pidgeotto p2){
        return this.getRetangulo().intersects( p2.getRetangulo() );
    }
    
    public Rectangle getRetangulo(){
        return new Rectangle(this.x, this.y+35, this.spriteAtual.pegaLargura(), this.spriteAtual.pegaAltura());
    }
    
    /*public Rectangle getRectangleRight(){
        return new Rectangle(this.x, this.y , 100, 100);
    }
    
    public Rectangle getRectangleLeft(){
        return new Rectangle(this.x, this.y, 100, 100);
    }
    
    public Rectangle getRectangleDown(){
        return new Rectangle(this.x, this.y + 10, 100, 100);
    }
    
    public Rectangle getRectangleUp(){
        return new Rectangle(this.x, this.y, 100, 100);
    }*/
    
    
    
    
    public boolean estaMorto(){
        return (this.numVida <= 0);
    }
    
    public int getVida(){
        return this.numVida;
    }
    
    public void setVida(int vida){
        this.numVida = vida;
    }
    
    public void resetPosicao(int x, int y){
        this.x = x;
        this.y = y;
    }

}
