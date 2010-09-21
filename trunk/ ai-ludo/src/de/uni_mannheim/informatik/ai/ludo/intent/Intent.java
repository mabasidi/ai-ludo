/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package de.uni_mannheim.informatik.ai.ludo.intent;

import de.uni_mannheim.informatik.ai.ludo.model.states.GameState;

/**
 * The intent is an intended acion of a player. This means, the correcponding 
 * action has not yet been validated by the game logic and therefore was not
 * done by the game. An intent can be either created by an human player or
 * by an artficial player. The intent itself takes a GameState and calls the
 * correnspond processPlayerIntent method. This conforms with the visitor
 * pattern.
 * There are two types of intents.
 * <b>MoveIntent:</b> This class represents the intent of the user of moving a
 * pawn to a new location.
 * <b>RollDiceIntent:</b> This class represents the intent of roling the dice.
 *
 * @author gtrefs
 */
public interface Intent {
    public void takeGameState(GameState gameState);
    public void execute();
    public void reject();
    public void success();
}
