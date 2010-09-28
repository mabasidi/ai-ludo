/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package de.uni_mannheim.informatik.ai.ludo.model.statistics;

import de.uni_mannheim.informatik.ai.ludo.intent.EndGameIntent;
import de.uni_mannheim.informatik.ai.ludo.intent.Intent;
import de.uni_mannheim.informatik.ai.ludo.intent.IntentVisitor;
import de.uni_mannheim.informatik.ai.ludo.intent.MoveIntent;
import de.uni_mannheim.informatik.ai.ludo.intent.NewGameIntent;
import de.uni_mannheim.informatik.ai.ludo.intent.RollDiceIntent;
import de.uni_mannheim.informatik.ai.ludo.intent.TransitionIntent;
import de.uni_mannheim.informatik.ai.ludo.model.Player;

/**
 * The statistics class is used to store statistics specific to the game.
 * This enables easy evaluation of results, so whether a specific AI is better
 * than another.
 * @author gtrefs
 */
public class Statistics {

    private static Statistics INSTANCE = new Statistics();

    public static Statistics getInstance(){
        return INSTANCE;
    }

    private SuccessIntentVisitor successIntentVisitor;
    private RejectIntentVisitor rejectIntentVisitor;

    private Statistics(){
        successIntentVisitor = new SuccessIntentVisitor();
        rejectIntentVisitor = new RejectIntentVisitor();
    }


    public void writeStatistics(){
        
    }

    public void successfullIntent(Intent intent){
        intent.takeVisitor(successIntentVisitor);
    }

    public void rejectedIntent(Intent intent){
        intent.takeVisitor(rejectIntentVisitor);
    }

    public void gameWonByPlayer(Player winner){
        
    }

    private class SuccessIntentVisitor implements IntentVisitor{

        public void processIntent(TransitionIntent intent) {
            throw new UnsupportedOperationException("Not supported yet.");
        }

        public void processIntent(RollDiceIntent intent) {
            throw new UnsupportedOperationException("Not supported yet.");
        }

        public void processIntent(MoveIntent intent) {
            throw new UnsupportedOperationException("Not supported yet.");
        }

        public void processIntent(EndGameIntent intent) {
            throw new UnsupportedOperationException("Not supported yet.");
        }

        public void processIntent(NewGameIntent intent) {
            throw new UnsupportedOperationException("Not supported yet.");
        }

    }

    private class RejectIntentVisitor implements IntentVisitor{

        public void processIntent(TransitionIntent intent) {
            throw new UnsupportedOperationException("Not supported yet.");
        }

        public void processIntent(RollDiceIntent intent) {
            throw new UnsupportedOperationException("Not supported yet.");
        }

        public void processIntent(MoveIntent intent) {
            throw new UnsupportedOperationException("Not supported yet.");
        }

        public void processIntent(EndGameIntent intent) {
            throw new UnsupportedOperationException("Not supported yet.");
        }

        public void processIntent(NewGameIntent intent) {
            throw new UnsupportedOperationException("Not supported yet.");
        }

    }

}
