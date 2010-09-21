/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.uni_mannheim.informatik.ai.ludo.model.states;

import de.uni_mannheim.informatik.ai.ludo.intent.IntentFactory.EndGameIntent;
import de.uni_mannheim.informatik.ai.ludo.intent.IntentFactory.MoveIntent;
import de.uni_mannheim.informatik.ai.ludo.intent.IntentFactory.NewGameIntent;
import de.uni_mannheim.informatik.ai.ludo.intent.IntentFactory.RollDiceIntent;
import de.uni_mannheim.informatik.ai.ludo.intent.IntentFactory.TransitionIntent;
import de.uni_mannheim.informatik.ai.ludo.model.Game;

/**
 * GameState which is to be satisfied by any future gamestate.
 * Future game state is the game's current state plus the step the player wants
 * to do. If the next state would be invalid, a InvalidFutureGameStateException
 * is thrown by the method validateGameState.
 * @author gtrefs
 */
public abstract class GameState {

    protected Game game;

    public GameState(Game game) {
        this.game = game;
    }

    public GameState() {
    }

    public abstract void processIntent(TransitionIntent intent);

    public abstract void processIntent(RollDiceIntent intent);

    public abstract void processIntent(MoveIntent intent);

    public abstract void processIntent(EndGameIntent intent);

    public abstract void processIntent(NewGameIntent intent);

    public void setGame(Game game) {
        this.game = game;
    }

    public Game getGame() {
        return game;
    }
}
