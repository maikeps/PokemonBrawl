package poke;



import GameState.*;
import javaPlay.GameEngine;
import javaPlayExtras.AudioPlayer;


public class PokemonBrawl{

    public static void main(String[] args) {
        
        GameEngine.getInstance().addGameStateController(1, new MainMenu());
        GameEngine.getInstance().addGameStateController(2, new CharacterSelect());
        GameEngine.getInstance().addGameStateController(3, new Fase1());
        GameEngine.getInstance().addGameStateController(4, new GameOver());
        GameEngine.getInstance().setStartingGameStateController(1);
        
        
        
        GameEngine.getInstance().setFramesPerSecond(60);
        GameEngine.getInstance().run();
        
        
        
        
    }
}
