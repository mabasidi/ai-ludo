/*
* Copyright (C) 2010 Gregor Trefs, Dominique Ritze
* 
* This program is free software: you can redistribute it and/or modify
* it under the terms of the GNU General Public License as published by
* the Free Software Foundation, either version 3 of the License, or
* (at your option) any later version.
* 
* This program is distributed in the hope that it will be useful,
* but WITHOUT ANY WARRANTY; without even the implied warranty of
* MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
* GNU General Public License for more details.
* 
* You should have received a copy of the GNU General Public License
* along with this program.  If not, see <http://www.gnu.org/licenses/>.
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


    public void writeOut(){
        
    }



    public void successfullIntent(Intent intent){
        intent.takeVisitor(successIntentVisitor);
    }

    public void rejectedIntent(Intent intent){
        intent.takeVisitor(rejectIntentVisitor);
    }

    public void gameWonByPlayer(Intent intent, Player winner){
        
    }

    private class SuccessIntentVisitor implements IntentVisitor{

        public void processIntent(TransitionIntent intent) {
           
        }

        public void processIntent(RollDiceIntent intent) {
            
        }

        public void processIntent(MoveIntent intent) {
            
        }

        public void processIntent(EndGameIntent intent) {
            
        }

        public void processIntent(NewGameIntent intent) {
         
        }

    }

    private class RejectIntentVisitor implements IntentVisitor{

        public void processIntent(TransitionIntent intent) {
            
        }

        public void processIntent(RollDiceIntent intent) {
            
        }

        public void processIntent(MoveIntent intent) {
            
        }

        public void processIntent(EndGameIntent intent) {
            
        }

        public void processIntent(NewGameIntent intent) {
           
        }

    }

}