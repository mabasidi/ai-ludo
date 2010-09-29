/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.uni_mannheim.informatik.ai.ludo.model.states;

import de.uni_mannheim.informatik.ai.ludo.intent.IntentVisitor;
import de.uni_mannheim.informatik.ai.ludo.model.Game;

/**
 * The GameState class is the abstract super class of all game state classes.
 * It implements the {@link de.uni_mannheim.informatik.ai.ludo.intent.IntentVisitor IntentVisitor} interface and therfore is able to visit any Intent which emerges within the game.
 * Further, the GameState represents abastract State class of the State Pattern. The next game state is determined by the current game state and the intent which occurs.
 * @author gtrefs
 * {@see http://en.wikipedia.org/wiki/State_pattern State Pattern}
 */
public abstract class GameState implements IntentVisitor{

    protected Game game;

    public GameState(Game game) {
        this.game = game;
    }

    /**
     * Sets the Game instance.
     * @param game the Game instance which this game state belongs to
     */
    public void setGame(Game game) {
        this.game = game;
    }
    /**
     * Gets the Game instance.
     * @return the game associated with this game state
     */
    public Game getGame() {
        return game;
    }
}
