/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package de.uni_mannheim.informatik.ai.ludo;

import de.uni_mannheim.informatik.ai.ludo.model.Game;

import de.uni_mannheim.informatik.ai.ludo.view.renderer.Renderer;


/**
 * Starting class and controller.
 * Takes input from any view (either OptionsView or GameView) validates 
 * and dispatches valid input to the model. The validation and dispatching
 * is done with the command pattern to enable undo and redo capabilities.
 * @author gtrefs
 */
public class Ludo {
    // The renderer renders the game in the preferred user interface.
    private Renderer renderer;
    // The model which contains the actual game state.
    private Game game;

    public Ludo(){

    }

    public static void main(String[] args){

    }

}
