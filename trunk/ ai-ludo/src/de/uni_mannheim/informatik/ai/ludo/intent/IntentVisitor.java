/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package de.uni_mannheim.informatik.ai.ludo.intent;

/**
 *
 * @author gtrefs
 */
public interface IntentVisitor {

    public void processIntent(TransitionIntent intent);

    public void processIntent(RollDiceIntent intent);

    public void processIntent(MoveIntent intent);

    public void processIntent(EndGameIntent intent);

    public void processIntent(NewGameIntent intent);
}
