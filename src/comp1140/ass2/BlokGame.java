package comp1140.ass2;

import java.awt.*;
import java.util.ArrayList;
import java.util.Vector;

/**
 * Created by steveb on 12/08/2015.
 */
public class BlokGame {

    /**
     * Parse a string representing a game state and determine whether it is legitimate.  The game may be in progress
     * (ie incomplete).
     * @param game A string representing the state of the game, as described in the assignment description.
     * @return True if the string represents a legitimate game state, according to the encoding defined in the
     * assignment description and the rules of the game.
     */
    public static boolean legitimateGame(String game) {
        /* FIXME */
        int[] squares = new int[400];
        boolean legit = true;
        int length = game.length();
        int index = 0;
        int encodingpart = 0;
        int turn = 0;
        char currentByte;
        ArrayList<Point> piece = new ArrayList<Point>();
        int x=0,y = 0;
        while (legit){
            if(index == length)break;
            currentByte = game.charAt(index);
            switch (encodingpart){
                case 0: //Check if pass otherwise encode piece as ArrayList of points
                    if(currentByte=='.'){
                        encodingpart = 4;
                        break;
                    }
                    //TODO
                    break;
                case 1: //Rotate each square in ArrayList as required
                    if((currentByte-'A')>3){
                        for (Point p : piece){
                            p.x = -(p.x);
                        }
                    }
                    switch((currentByte-'A') % 4 ){
                        case 0:
                            break;
                        case 1:
                            for (Point p : piece){
                                int temp = p.x;
                                p.x = p.y;
                                p.y = -temp;
                            }
                            break;
                        case 2:
                            for (Point p : piece){
                                p.x = -(p.x);
                                p.y = -(p.y);
                            }
                            break;
                        case 3:
                            for (Point p : piece){
                                int temp = p.x;
                                p.x = -(p.y);
                                p.y = temp;
                            }
                            break;
                    }
                    break;
                case 2: //Encode horizontal co-ordinate of origin
                    x = currentByte - 'A';
                    break;
                case 3: //Encode vertical co-ordinate of origin
                    y = currentByte - 'A';
                    break;
                case 4: //Check for legitimacy of game state
                    boolean cornerTouch = false; //If at least one part of the block is diagonal to an owned piece
                    for (Point p : piece){
                        Point ap = new Point(p.x+x,p.y+y); //Location of individual square on game board
                        if (ap.x < 0 || ap.x > 19 || ap.y < 0 || ap.y > 19){ //If piece is placed out of bounds
                            legit = false;
                            break;
                        }
                        if(squares[(20 * ap.y) + ap.x] !=0){//If placed on occupied square
                            legit = false;
                            break;
                        }
                        if(ap.x>0) {//Check if same colour piece to left
                            if (squares[(20 * (ap.y)) + ap.x - 1] == turn + 1) {
                                legit = false;
                                break;
                            }
                        }
                        if(ap.x<19) {//Check if same colour piece to right
                            if (squares[(20 * (ap.y)) + ap.x + 1] == turn + 1) {
                                legit = false;
                                break;
                            }
                        }
                        if(ap.y>0) {//Check if same colour piece above
                            if (squares[(20 * (ap.y-1)) + ap.x] == turn + 1) {
                                legit = false;
                                break;
                            }
                        }
                        if(ap.y<19) {//Check if same colour piece below
                            if (squares[(20 * (ap.y+1)) + ap.x] == turn + 1) {
                                legit = false;
                                break;
                            }
                        }
                        if(!cornerTouch){
                            if(ap.x>0 && ap.y>0) {//Check if same colour piece to up-left
                                if (squares[(20 * (ap.y - 1)) + ap.x - 1] == turn + 1) {
                                    cornerTouch = true;
                                    break;
                                }
                            }
                            if(ap.x>0 && ap.y<19) {//Check if same colour piece to below-left
                                if (squares[(20 * (ap.y + 1)) + ap.x - 1] == turn + 1) {
                                    cornerTouch = true;
                                    break;
                                }
                            }
                            if(ap.x<19 && ap.y>0) {//Check if same colour piece to up-left
                                if (squares[(20 * (ap.y - 1)) + ap.x + 1] == turn + 1) {
                                    cornerTouch = true;
                                    break;
                                }
                            }
                            if(ap.x<19 && ap.y<19) {//Check if same colour piece to left
                                if (squares[(20 * (ap.y - 1)) + ap.x - 1] == turn + 1) {
                                    cornerTouch = true;
                                    break;
                                }
                            }
                        }
                    }
                    for (Point p : piece){
                        Point ap = new Point(p.x+x,p.y+y); //Location of individual square on game board
                        squares[(20 * ap.y) + ap.x] = turn + 1; //Store piece on game board
                    }
                    if (!cornerTouch) legit = false;
                    turn = (turn + 1) % 4;
                    encodingpart = -1;
                    break;
                case 5: //Encoding players pass
                    encodingpart = -1;
                    turn = (turn + 1) % 4;
                    break;
            }
            encodingpart++;
            index++;
        }
        return legit;
    }

    /**
     * Parse a string representing a game state and return a score for the game.  The game may be in progress
     * (ie incomplete), in which case score should reflect the score at that time (if no further moves were made).
     * @param game A string representing the state of the game, as described in the assignment description.
     * @return An array of four integers reflecting the score for the game.   The scores should be given in the playing
     * order specified in the rules of the game, and the scores should be made according to the rules.
     */
    public static int[] scoreGame(String game) {
        /* FIXME */
        return null;
    }

    /**
     * Parse a string representing a game state and return a valid next move.  If no move is possible, return a pass ('.')
     * @param game A string representing the state of the game, as described in the assignment description.
     * @return A four-character string representing the next move.
     */
    public static String makeMove(String game) {
        /* FIXME */
        return null;
    }
}
