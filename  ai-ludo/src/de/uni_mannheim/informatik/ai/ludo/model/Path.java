/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.uni_mannheim.informatik.ai.ludo.model;

import java.util.ArrayList;
import java.util.List;

/**
 * The path represents the path a player has to master with all of its pawns
 * in order to win the game.
 * A path is defined as a line of fields of which the first field is of the
 * type BeginField belonging to the owner of the path, three intermidiate
 * fields BeginFields but belonging to either none or opponent players and
 * four end fields only accessible by the owner of the path.
 * A path consists of 44 fields (including BeginFields and EndFields).
 * Further a player has 4 start fields where all his pawns are placed
 * at the beginning.
 * @author gtrefs
 */
public class Path {

    public final static int MAX_PATH_LENGTH = 44;
    private List<Field> path;
    private List<StartField> startFields;
    private List<EndField> endFields;
    private List<Field> intermediatePathFields;
    // The player to which the path belongs to.
    private Player owner;

    public Path(Player owner, List<Field> fieldsOnPath) {
        this.owner = owner;
        endFields = new ArrayList<EndField>();
        intermediatePathFields = fieldsOnPath;
        buildPath(fieldsOnPath);
        startFields = new ArrayList<StartField>();
        buildStartFields();
    }

    private void buildStartFields() {
    }

    /**
     * This method builds up the path.
     * It takes all shared fields as parameter and adds the 4 end fields to it.
     *
     * @param fieldsOnPath
     */
    private void buildPath(List<Field> fieldsOnPath) {
        path = new ArrayList<Field>(fieldsOnPath);
        for (int i = 0; i < 4; i++) {
            endFields.add(new EndField());
        }
        path.addAll(endFields);
    }

    /**
     * This method returns true if no owner pawn is on the path field (without
     * the end Fields)
     * @return true - if no pawn of the player is on any field which is not a end or start field.
     */
    public boolean noPawnOnIntermediatePathFields() {
        boolean ret = true;
        for (Field f : intermediatePathFields) {
            ret &= (!f.isEmpty());
        }
        return ret;
    }

    public boolean allEndFieldsFull() {
        boolean ret = true;
        for (EndField e : endFields) {
            ret &= (!e.isEmpty());
        }
        return ret;
    }

    public boolean isAtLeastOneStartFieldFull() {
        boolean ret = false;
        for (StartField s : startFields) {
            ret |= (!s.isEmpty());
        }
        return ret;
    }

    public StartField getFullStartField() {
        for (StartField s : startFields) {
            if (!s.isEmpty()) {
                return s;
            }
        }
        return null;
    }

    public boolean placeOnStartField(Pawn p) {
        if (p.getOwner().equals(owner)) {
            for (StartField s : startFields) {
                if (s.isEmpty()) {
                    s.placePawnOnField(p);
                    return true;
                }
            }
            return false;
        }
        return false;
    }

    public Field getFieldOfPawn(Pawn pawn) {
        for (StartField s : startFields) {
            if (!s.isEmpty() && s.getPawn().equals(pawn)) {
                return s;
            }
        }
        for (Field f : path) {
            if (!f.isEmpty() && f.getPawn().equals(pawn)) {
                return f;
            }
        }
        return null;
    }

    public int getPathIndexOfField(Field f) {
        return path.indexOf(f);
    }

    public Field getFieldByIndex(int index) {
        if (index > MAX_PATH_LENGTH - 1) {
            return null;
        }
        return path.get(index);
    }

    public boolean beginFieldNotPossessedByAnotherOwnerPawn() {
        return getBeginField().isEmpty() || !getBeginField().getPawn().getOwner().equals(owner);
    }

    public boolean fieldNotPossedByAnotherOwnerPawn(Field f) {
        if (f == null) {
            return false;
        }
        return f.isEmpty() || !f.getPawn().getOwner().equals(owner);
    }

    public boolean areAllStartFieldsEmpty() {
        return getFullStartField() == null ? true : false;
    }

    public Field getBeginField() {
        return path.get(0);
    }

    public List<Field> getPath() {
        return path;
    }

    public Field getFieldOfPath(int index) {
        return path.get(index);
    }

    public void setPath(List<Field> path) {
        this.path = path;
    }

    public List<StartField> getStartFields() {
        return startFields;
    }

    public Player getOwner() {
        return owner;
    }

    public void setOwner(Player owner) {
        this.owner = owner;
    }
}
