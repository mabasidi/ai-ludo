/*
 *  Copyright (C) 2010 Gregor Trefs, Dominique Ritze
 * 
 *  This program is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 * 
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 * 
 *  You should have received a copy of the GNU General Public License
 *  along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package de.uni_mannheim.informatik.ai.ludo.model.ai.geneticAlgo;

import de.uni_mannheim.informatik.ai.ludo.model.ai.geneticAlgo.TermValues;

/**
 *
 * @author Dominique Ritze
 *
 * Representation of an individual used in the genetic algorithm.
 * Every individual has its term values as well as a score
 * assigned by the fitness function. The individuals
 * can be compared based on the score of the fitness function.
 * In this case, the score of the fitness funtion is just the number
 * of games an individual won. 
 *
 */
public class Individual implements Comparable{

    //number of games won
    private int score = 0;
    //term values
    private TermValues values;

    /**
     * Create a new individual with predefined values.
     *
     * @param values
     */
    public Individual(TermValues values) {
        this.values = values;
    }

    /**
     * Create a new individual with unknown values.
     * In this case random values are assigned.
     */
    public Individual() {
        this.values = new TermValues();
        this.score = 0;
    }

    /**
     *
     * @return Number of games which have been won.
     */
    public int getScore() {
        return this.score;
    }

    /**
     * Compare the individuals based on their score.
     *
     * @param o
     * @return
     */
    @Override
    public int compareTo(Object o) {
        return this.getScore()-((Individual)o).getScore();
    }

    /**
     * @param score the score to set
     */
    public void setScore(int score) {
        this.score = score;
    }

    public void increaseScore() {
        this.score++;
    }

    /**
     * @return the values
     */
    public TermValues getWeights() {
        return values;
    }

    /**
     * @param values the values to set
     */
    public void setWeights(TermValues weights) {
        this.values = weights;
    }
}


