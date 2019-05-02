import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import javax.swing.JOptionPane;

/**
 * Class TrainGame - used to run the Ticket to Ride game. To be instantiated by
 * the applet.
 *
 * An implementation of this class must, during its initial setup, call the
 * dcardsSetup() method on each of the game's players, using their inputs to
 * determine which destination cards they want to keep.
 *
 * @author London Brunell
 */
public class TrainGame {

    /**
     * Used to store the deck of train cards.
     */
    List<TrainCard> trainDeck;

    //list of currently displayed train cards
    List<TrainCard> trainPile;
    List<TrainCard> trainDiscard;

    //deck of destination cards
    List<DestCard> dcards;

    //deck of stock cards
    StockCards stockCards;

    //list of stock cards that the player can see
    List<StockBase> shownStockCards;

    //collection of routes & cities
    //array of players (constructor?)
    protected int cardsTaken = 0;

    protected Player[] players;  // changed this from private to protected

    //used to keep track of current active player
    protected int activePlayer; // changed this from private to protected

    //used to keep track of who is taking the final turn come endgame
    protected int lastTurnTaker = -1;

    //used to check whether the last turn taker is on their last turn.
    protected boolean lastTurn = false;

    //used to hold destination cards that are visible to the player
    protected DestCard[] shownDestCards;

    protected Player dummy = null;

    /**
     * Constructor for TrainGame objects.
     *
     * @param playerNames a list of names for the game's players
     * @param playerColors colors to match the player names
     */
    public TrainGame(String[] playerNames, TrainColor[] playerColors, PennMap map) {

        //set up the players
        players = new Player[playerNames.length];

        for (int i = 0; i < players.length; i++) {
            players[i] = new Player(playerNames[i], playerColors[i]);
        }

        //set up the train cards:
        trainDeck = new ArrayList<>();
        trainDiscard = new ArrayList<>();

        //add 14 wild cards
        for (int i = 0; i < 14; i++) {
            trainDeck.add(new TrainCard(TrainColor.WILD, "WILD.jpg"));
        }
        //add 12 of each color
        for (int i = 0; i < 12; i++) {
            trainDeck.add(new TrainCard(TrainColor.YELLOW, "YELLOW.jpg"));
        }
        for (int i = 0; i < 12; i++) {
            trainDeck.add(new TrainCard(TrainColor.WHITE, "WHITE.jpg"));
        }
        for (int i = 0; i < 12; i++) {
            trainDeck.add(new TrainCard(TrainColor.RED, "RED.jpg"));
        }
        for (int i = 0; i < 12; i++) {
            trainDeck.add(new TrainCard(TrainColor.PINK, "PINK.jpg"));
        }
        for (int i = 0; i < 12; i++) {
            trainDeck.add(new TrainCard(TrainColor.ORANGE, "ORANGE.jpg"));
        }
        for (int i = 0; i < 12; i++) {
            trainDeck.add(new TrainCard(TrainColor.GREEN, "GREEN.jpg"));
        }
        for (int i = 0; i < 12; i++) {
            trainDeck.add(new TrainCard(TrainColor.BLUE, "BLUE.jpg"));
        }
        for (int i = 0; i < 12; i++) {
            trainDeck.add(new TrainCard(TrainColor.BLACK, "BLACK.jpg"));
        }

        //shuffle the deck to randomize it
        Collections.shuffle(trainDeck);

        //give players their hands
        for (Player p : players) {
            for (int i = 0; i < 4; i++) {
                p.hand.add(trainDeck.remove(0)); // changed to remove from end of pile
            }
        }

        // LONDON: need to fix this so it is called everytime, like create a 
        //new method that keeps placing cards out on table as cards are taken away
        //set up the cards on the table
        trainPile = new ArrayList<>();
        boolean pileDone = false;
        while (!pileDone) {
            //put the first five cards of the deck into the pile
            for (int i = 0; i < 5; i++) {
                trainPile.add(trainDeck.remove(0)); // changed this from 0
            }

            int wildCount = 0;
            //check if each of the cards is a wild card
            for (TrainCard c : trainPile) {
                if (c.isWild()) {
                    wildCount++;
                }
            }
            //if there's not 3 or more wild cards, we're good
            if (!(wildCount >= 3)) {
                pileDone = true;
            } else {
                //we have to discard the pile and get a new one
                for (int i = 0; i < 5; i++) {
                    trainDiscard.add(trainPile.remove(0));
                }
            }
        }

        //set up the destination cards.
        dcards = map.dest;
        Collections.shuffle(dcards);

        shownDestCards = null;
        //this gives each player 5 destination cards. 
        //the implementing class must call the dcardsSetup method on each
        //player's first turn.
        for (Player p : players) {
            for (int i = 0; i < 5; i++) {
                p.destCards.add(this.dcards.remove(0));
            }
        }

        //set up the stock cards.
        stockCards = new StockCards();
    }

    /**
     * Helper method to check if the train pile has 3 wild in it and recreate if true
     */
    private void fixTrainPile(){

        boolean pileDone = false;
        while (!pileDone) {

            int wildCount = 0;
            //check if each of the cards is a wild card
            for (TrainCard c : trainPile) {
                if (c.isWild()) {
                    wildCount++;
                }
            }
            //if there's not 3 or more wild cards, we're good
            if (!(wildCount >= 3)) {
                pileDone = true;
            } else {
                //we have to discard the pile and get a new one
                for (int i = 0; i < 5; i++) {
                    trainDiscard.add(trainPile.remove(0));
                }
                //put the first five cards of the deck into the pile
                for (int i = 0; i < 5; i++) {
                    trainPile.add(trainDeck.remove(0)); // changed this from 0
                }
            }
        }

    }

    /**
     * Used to prepare the destination cards for each player, on that player's
     * first turn.
     *
     * @param keeping, the list of indices in the player's array of DestCards
     * that the player has chosen to keep.
     */
    public void dcardsSetup(Player p, DestCard[] keeping) throws
    IllegalArgumentException {

        if (keeping.length < 3) {
            throw new IllegalArgumentException("Not enough destination cards"
                + " chosen. You have to keep at least 3.");
        }

        List<DestCard> toKeep = new ArrayList<>();

        for (DestCard d : keeping) {
            toKeep.add(d);
        }

        for (int i = 0; i < p.destCards.size(); i++) {
            if (!toKeep.contains(p.destCards.get(i))) {
                dcards.add(p.destCards.get(i));
            }
        }

        p.destCards.clear();

        for (DestCard c : toKeep) {
            p.destCards.add(c);
        }

    }

    /**
     * Used when a player claims a route.
     *
     * Note that this method assumes that all cards in cardsSelected are in the
     * active player's hand.
     *
     * @param p, the player who is claiming the route
     * @param r, the data for the route
     * @param cardsSelected, the train cards to claim with
     *
     */
    public boolean claimRoute(Route r, TrainCard[] cardsSelected)
    throws IllegalArgumentException {

        Player p = players[activePlayer];

        //the part where you claim the route, if you haven't done 
        //anything else yet
        if (cardsTaken == 0) {

            //if the route is already claimed
            if (r.isClaimed) {
                 throw new IllegalArgumentException("You can't claim a claimed"
                   + " route!");
                
            }

            //if you don't have enough train pieces left
            if (p.numTrains < r.length) {
                throw new IllegalArgumentException("You don't have enough "
                   + "trains!");
                
            }

            TrainCard[] cardsUsed = new TrainCard[r.length];
            int cardsUsedIndex = 0;
            //has enough on-color trains in the selected cards?
            boolean hasEnough = false;

            //if it's not a gray route
            if (r.color != TrainColor.WILD) {
                for (int i = 0; i < cardsSelected.length; i++) {

                    if (cardsSelected[i].color == r.color || cardsSelected[i].isWild()) {
                        cardsUsed[cardsUsedIndex] = cardsSelected[i];
                        cardsUsedIndex++;

                        if (cardsUsedIndex >= cardsUsed.length) {
                            hasEnough = true;
                            break;
                        }
                    }
                }
                //if it's a gray route for not ontario
            } else if ( !(r.source.name.equals("Ontario") || r.dest.name.equals("Ontario") )) {
                //establish a color
                TrainColor chosenColor = null;
                for (TrainCard c : cardsSelected) {
                    if (!c.isWild()) {
                        chosenColor = c.color;
                    }
                }

                for (int i = 0; i < cardsSelected.length; i++) {

                    if (cardsSelected[i].color == chosenColor || cardsSelected[i].isWild()) {
                        cardsUsed[cardsUsedIndex] = cardsSelected[i];
                        cardsUsedIndex++;

                        if (cardsUsedIndex >= cardsUsed.length) {
                            hasEnough = true;
                            break;
                        }
                    }
                }
                //if it's the ontario with length == 1   
            } else if (r.length == 1) {
                for (int i = 0; i < cardsSelected.length; i++) {
                    if (cardsSelected[i].isWild()) {
                        cardsUsed[cardsUsedIndex] = cardsSelected[i];
                        cardsUsedIndex++;

                        if (cardsUsedIndex >= cardsUsed.length) {
                            hasEnough = true;
                            break;
                        }
                    }
                }
                //if it's the ontario with length == 3
            } else {

                int wildsUsed = 0;

                for (int i = 0; i < cardsSelected.length; i++) {
                    if (cardsSelected[i].isWild() || (wildsUsed >= 2)) {
                        cardsUsed[cardsUsedIndex] = cardsSelected[i];
                        cardsUsedIndex++;
                        wildsUsed++;

                        if (cardsUsedIndex >= cardsUsed.length) {
                            hasEnough = true;
                            break;
                        }
                    }
                }

            }

            if (hasEnough) {

                for (TrainCard c : cardsUsed) {
                    //put the card in the discard pile
                    trainDiscard.add(c);
                    //take the cards out of the player's hand
                    for (int i = 0; i < p.hand.size(); i++) {
                        if (p.hand.get(i).equals(c)) {
                            p.hand.remove(i);
                            break;//break the inner loop
                        }
                    }
                }

                //add the route to the player's list of routes
                p.routesOwned.add(r);

                //the route is claimed now
                r.isClaimed = true;

                //use the player's trains
                p.numTrains -= r.length;

                //the player earns the route's point value
                p.numPoints += r.getPoints();

                //this move takes two "turn points"
                cardsTaken = 2;

                if(r.stocks.length > 0){
                    //the part where you claim the stocks
                    Object[] stockList = r.stocks;

                    // for (int i = 1; i < r.stocks.length; i++) {
                    //   stockList += ", " + r.stocks[i];
                    //}

                    boolean stocksClaimed = false;

                    while (!stocksClaimed) {

                        //String stockChosen = JOptionPane.showInputDialog(
                        //       "Please choose a stock: " + stockList);

                        String stockChosen = (String)JOptionPane.showInputDialog(null, "Please choose a stock: ", 
                                "Stocks", JOptionPane.INFORMATION_MESSAGE, null, stockList, stockList[0]);

                        try {

                            claimStock(stockChosen);
                            stocksClaimed = true;

                        } catch (IllegalArgumentException e) {

                            JOptionPane.showMessageDialog(null, "Sorry, we are "
                                + "out of that type of stock.)");

                        }
                    }

                    if(players.length < 2){
                        //the part where the dummy players claims the stocks
                        //String stockListDummy = r.stocks[0];

                        //for (int i = 1; i < r.stocks.length; i++) {
                        //  stockList += ", " + r.stocks[i];
                        //}

                        stocksClaimed = false;

                        while (!stocksClaimed) {

                            //String stockChosen = JOptionPane.showInputDialog(
                            //    "Please choose a stock for the dummy player: " + stockList);

                            String stockChosen = (String)JOptionPane.showInputDialog(null, 
                                    "Please choose a stock for the dummy player:", "Stocks",
                                    JOptionPane.INFORMATION_MESSAGE, null, stockList, stockList[0]);

                            try {

                                claimStockDummy(stockChosen);
                                stocksClaimed = true;

                            } catch (IllegalArgumentException e) {

                                JOptionPane.showMessageDialog(null, "Sorry, that's not "
                                    + "valid input. Please try again. (We may be"
                                    + "out of that type of stock.)");

                            }
                        }
                    }
                }

                checkForEnd();
                return true;

            } else {
                throw new IllegalArgumentException("Route not taken: insufficient cards"
                    + " selected.");
            }

        }
         throw new IllegalArgumentException("Route not taken: insufficient cards"
                    + " selected.");

    }

    /**
     * Used to check if the number of trains in any of the players' possession
     * is less than or equal to 2; therefore, if the game is ending.
     *
     * @precondition lastTurnTaker = -1
     * @postcondition lastTurnTaker != -1
     *
     * @return true if the game is set to finish soon, false otherwise
     */
    public boolean checkForEnd() {
        if (lastTurnTaker != -1) {
            return true;
        }

        //check the active player first; in all likelihood, they will be the 
        //first to get to less than 3 trains.
        if (players[activePlayer].numTrains <= 2) {
            lastTurnTaker = activePlayer;
            return true;
        }

        for (int i = 0; i < players.length; i++) {
            if (players[i].numTrains <= 2) {
                lastTurnTaker = i;
                return true;
            }
        }

        return false;
    }

    /**
     * This part of the code would read what stocks the current route has then
     * would prompt the player to choose and once that data is received
     * currently uses switch statements basically how it works is that it
     * removes a card from the Stockcard class and adds it to the player's deck
     * of stock cards.
     *
     * @author Javier Rodriguez
     *
     * @param r, the route that has been claimed
     * @param s, the desired stock
     */
    public void claimStock(String switchStocks) {

        Player p = players[activePlayer];

        switch (switchStocks) {
            case "Lehigh Valley":

            if (stockCards.lehighValley.isEmpty()) {
                throw new IllegalArgumentException();
            }

            p.lehighValley.add(stockCards.lehighValley.remove(0));
            break;
            case "Pennsylvania":
            if (stockCards.pennsylvania.isEmpty()) {
                throw new IllegalArgumentException();

            }

            p.pennsylvania.add(stockCards.pennsylvania.remove(0));
            break;
            case "New York Central System":
            if (stockCards.newyorkCentralSystem.isEmpty()) {
                throw new IllegalArgumentException();

            }
            p.newyorkCentralSystem.add(stockCards.newyorkCentralSystem.remove(0));
            break;
            case "Reading Railroad":
            if (stockCards.readingRailRoad.isEmpty()) {
                throw new IllegalArgumentException();

            }
            p.readingRailRoad.add(stockCards.readingRailRoad.remove(0));
            break;
            case "Erie Lackawanna":
            if (stockCards.erieLackawanna.isEmpty()) {
                throw new IllegalArgumentException();

            }
            p.erieLackawanna.add(stockCards.erieLackawanna.remove(0));
            break;
            case "Jersey Central Line":
            if (stockCards.jerseyCentralLine.isEmpty()) {
                throw new IllegalArgumentException();

            }
            p.jerseyCentralLine.add(stockCards.jerseyCentralLine.remove(0));
            break;
            case "Baltimore":
            if (stockCards.baltimore.isEmpty()) {
                throw new IllegalArgumentException();

            }
            p.baltimore.add(stockCards.baltimore.remove(0));
            break;
            case "Western Maryland":
            if (stockCards.westernMaryland.isEmpty()) {
                throw new IllegalArgumentException();

            }
            p.westernMaryland.add(stockCards.westernMaryland.remove(0));
            break;
            case "BRP RailWay":
            if (stockCards.brpRailWay.isEmpty()) {
                throw new IllegalArgumentException();

            }
            p.brpRailWay.add(stockCards.brpRailWay.remove(0));
            break;

        }

        //throw new IllegalArgumentException("ERROR: Stock name is invalid");
    }

    /**
     * To not change the current structure of the Traingame 
     * class this dummyStock method does the same as the 
     * above claimStock method.
     * however it's only called when we have a dummy player. 
     * Otherwise it is identical to the claimStock method
     * only difference is that the method is always set to 
     * affect Player dummy and not the current active Player.
     */
    public void claimStockDummy(String switchStocks){
        switch (switchStocks) {
            case "Lehigh Valley":

            if (stockCards.lehighValley.isEmpty()) {
                throw new IllegalArgumentException();
            }

            dummy.lehighValley.add(stockCards.lehighValley.remove(0));
            break;
            case "Pennsylvania":
            if (stockCards.pennsylvania.isEmpty()) {
                throw new IllegalArgumentException();

            }

            dummy.pennsylvania.add(stockCards.pennsylvania.remove(0));
            break;
            case "New York Central System":
            if (stockCards.newyorkCentralSystem.isEmpty()) {
                throw new IllegalArgumentException();

            }
            dummy.newyorkCentralSystem.add(stockCards.newyorkCentralSystem.remove(0));
            break;
            case "Reading Railroad":
            if (stockCards.readingRailRoad.isEmpty()) {
                throw new IllegalArgumentException();

            }
            dummy.readingRailRoad.add(stockCards.readingRailRoad.remove(0));
            break;
            case "Erie Lackawanna":
            if (stockCards.erieLackawanna.isEmpty()) {
                throw new IllegalArgumentException();

            }
            dummy.erieLackawanna.add(stockCards.erieLackawanna.remove(0));
            break;
            case "Jersey Central Line":
            if (stockCards.jerseyCentralLine.isEmpty()) {
                throw new IllegalArgumentException();

            }
            dummy.jerseyCentralLine.add(stockCards.jerseyCentralLine.remove(0));
            break;
            case "Baltimore":
            if (stockCards.baltimore.isEmpty()) {
                throw new IllegalArgumentException();

            }
            dummy.baltimore.add(stockCards.baltimore.remove(0));
            break;
            case "Western Maryland":
            if (stockCards.westernMaryland.isEmpty()) {
                throw new IllegalArgumentException();

            }
            dummy.westernMaryland.add(stockCards.westernMaryland.remove(0));
            break;
            case "BRP RailWay":
            if (stockCards.brpRailWay.isEmpty()) {
                throw new IllegalArgumentException();

            }
            dummy.brpRailWay.add(stockCards.brpRailWay.remove(0));
            break;

        }

    }

    /**
     * Used when a player draws a card from the train deck.
     *
     *
     */
    public void drawCard() {

        // displays message if you already took 2 cards
        if(cardsTaken >= 2){
            JOptionPane.showMessageDialog(null, "You already took your turn.");
        }

        if (cardsTaken < 2) {

            players[activePlayer].hand.add(trainDeck.remove(0));

            fixTrainDeck();

            cardsTaken++;

            return;
        }//else do nothing       

    }

    /**
     * Used when a player takes a card from the trains on the table.
     *
     * @param index
     */
    public void drawCard(int index) {

        Player p = players[activePlayer];

        // displays message if you already took 2 cards
        if(cardsTaken >= 2){
            JOptionPane.showMessageDialog(null, "You already took your turn.");
        }

        if(cardsTaken < 2){

            TrainCard card = trainPile.get(index);

            if (card.isWild()) {
                if (cardsTaken == 1) {
                    JOptionPane.showMessageDialog(null, 
                        " You can't take a wild after taking another card");
                } else if (cardsTaken == 0) {
                    players[activePlayer].hand.add(trainPile.remove(index));
                    trainPile.add(trainDeck.remove(0));
                    fixTrainPile();
                    fixTrainDeck();
                    cardsTaken = 2;
                }
            } else {//if the card isn't wild

                players[activePlayer].hand.add(trainPile.remove(index));
                cardsTaken++;

                trainPile.add(trainDeck.remove(0));

                fixTrainPile();
                fixTrainDeck();

            }
        }

    }

    /**
     * Helper method to reshuffle the train deck, if it is empty.
     *
     */
    private void fixTrainDeck() {
        if (trainDeck.isEmpty()) {
            for (TrainCard c : trainDiscard) {
                trainDeck.add(c);
            }
            Collections.shuffle(trainDeck);
            trainDiscard.clear();
        }
    }

    /**
     * Call this whenever the turn changes from one player to another.
     *
     * @return true if the game has ended; false otherwise
     *
     * @postcondition if this method returned true, finalPointsCount() should be
     * called immediately afterwards.
     */
    public boolean changeTurn() {

        if(cardsTaken >= 2){
            //reset number of actions taken
            cardsTaken = 0;

            //the first time this fires, it will be as the last turn player's
            //penultimate turn ends.
            //the second time it fires will be after the last turn of the game.
            if (checkForEnd() && activePlayer == lastTurnTaker) {
                if (!lastTurn) {
                    lastTurn = true;
                } else {
                    return true;
                }
            }

            //change active player
            activePlayer++;
            if (activePlayer >= players.length) {
                activePlayer = 0;
            }

        }else{
            JOptionPane.showMessageDialog(null, "You haven't"
            + " finished your turn yet!");
        }
        return false;

    }

    /**
     * Used when a player takes destination cards; this method reveals them.
     *
     * @param toReveal, the number of destination cards to reveal.
     *
     * toReveal should be 4 for a draw on a player's turn.
     *
     * @precondition shownDestCards == null
     * @postcondition shownDestCards != null; it's full of revealed cards
     *
     * Call chooseDest after the player has made their selection.
     */
    public void showDest(int toReveal) {
        if (cardsTaken == 0) {

            //fix toReveal if the number of cards left in the deck is too small
            toReveal = (dcards.size() < toReveal) ? dcards.size() : toReveal;

            shownDestCards = new DestCard[toReveal];

            for (int i = 0; i < toReveal; i++) {
                shownDestCards[i] = dcards.remove(0);
            }

            cardsTaken = 2;

        }

    }

    /**
     * Used when a player takes destination cards, after the ones to keep have
     * been chosen.
     *
     * @precondition showDest was just called, and shownDestCards != null
     * @postcondition shownDestCards == null
     *
     * @param chosen, a list of shown destination cards that were chosen to keep
     */
    public void chooseDest(DestCard[] chosen) {

        if (shownDestCards != null) {

            if (chosen.length < 1) {
                throw new IllegalArgumentException("Not enough destination "
                        + "cards chosen. You have to keep at least one.");
            }

            //for each chosen card
            for (DestCard d : chosen) {
                //if it's in the shownDestCards array, take it out
                for (int i = 0; i < shownDestCards.length; i++) {
                    if(shownDestCards[i].equals(d)){
                        shownDestCards[i] = null;
                    }
                }
                players[activePlayer].destCards.add(d);
            }
            
            //put untaken cards on the bottom of the deck
            for (int i = 0; i < shownDestCards.length; i++) {
                if(shownDestCards[i] != null){
                    dcards.add(shownDestCards[i] );
                }
            }

            shownDestCards = null;
        }

    }

    /**
     * Used to calculate final point totals; Call only when the game is over.
     *
     * @return the winning Player object.
     */
    protected Player finalPointsCount() {

        stocksEnd();

        int[] destCardsDone = new int[players.length];
        for (int i = 0; i < destCardsDone.length; i++) {
            destCardsDone[i] = 0;
        }

        for (int i = 0; i < players.length; i++) {// for each player in the game
            Player p = players[i];
            //destination ticket points
            for (DestCard d : p.destCards) {
                //if a path can be found using the current player's
                //captured routes
                if (traversePath(d.source, d.dest, p.routesOwned)) {
                    //they get the points
                    destCardsDone[i]++;
                    p.numPoints += d.getPoints();
                } else {
                    p.numPoints -= d.getPoints();
                }
            }
        }

        //Globetrotter Bonus:
        int maxDestCards = 0;
        int maxDestCardsIndex = 0;
        for (int i = 0; i < destCardsDone.length; i++) {
            if (destCardsDone[i] > maxDestCards) {
                maxDestCards = destCardsDone[i];
                maxDestCardsIndex = i;
            }
        }

        //handle players having the same number of max dest cards:
        List<Integer> extraIndices = new ArrayList<>();
        for (int i = 0; i < destCardsDone.length; i++) {
            if (i != maxDestCardsIndex) {
                if (destCardsDone[i] == maxDestCards) {
                    extraIndices.add(i);
                }
            }
        }

        players[maxDestCardsIndex].numPoints += 15;
        for (int i : extraIndices) {
            players[i].numPoints += 15;
        }

        //longest continuous path:
        // kill condition is when you hit a route for the second time.
        int longestContPath[] = new int[players.length];
        int lcpI = 0; //longestContinuousPathIndex
        for (int i = 0; i < longestContPath.length; i++) {
            longestContPath[i] = longestContPath(
                players[i].routesOwned.get(0).dest, players[i].routesOwned);
            if (longestContPath[i] > longestContPath[lcpI]) {
                lcpI = i;
            }
        }

        //handle players having the same length of continuous route:
        List<Integer> extraIndicesRoute = new ArrayList<>();
        for (int i = 0; i < longestContPath.length; i++) {
            if (longestContPath[i] == longestContPath[lcpI]) {
                extraIndicesRoute.add(i);
            }
        }

        //for everyone who earned the bonus, they get 10 points
        players[lcpI].numPoints += 10;
        for (int i : extraIndicesRoute) {
            players[i].numPoints += 10;
        }

        //determine a winner
        int winnerIndex = 0;
        for (int i = 0; i < players.length; i++) {
            if (players[i].numPoints > players[winnerIndex].numPoints) {
                winnerIndex = i;
            }
        }

        //handle players having the same number of points:
        List<Integer> extraIndicesWinner = new ArrayList<>();
        for (int i = 0; i < players.length; i++) {
            if (players[i].numPoints == players[winnerIndex].numPoints) {
                extraIndicesWinner.add(i);
            }
        }

        //on the off-chance points ARE equal:
        if (!extraIndicesWinner.isEmpty()) {

            //destination cards:
            extraIndicesWinner.add(winnerIndex);

            int bestDestCards = 0;

            for (Integer i : extraIndicesWinner) {
                if (destCardsDone[i] > destCardsDone[bestDestCards]) {
                    bestDestCards = i;
                }
            }

            //handle a tie in terms of destination cards completed:
            List<Integer> ex = new ArrayList<>();
            for (int i = 0; i < extraIndicesWinner.size(); i++) {
                if (destCardsDone[i] == bestDestCards) {
                    ex.add(i);
                }
            }
            if (ex.isEmpty()) {
                return players[bestDestCards];
            }

            //check longest cont path as a final option:
            int bestContPath = 0;
            for (Integer i : ex) {
                if (longestContPath[i] > longestContPath[bestContPath]) {
                    bestContPath = i;
                }
            }
            return players[bestContPath];

        }

        return players[winnerIndex];

    }

    /**
     * Helper Method for stocksEnd
     * deals with ties
     * @param ArrayList
     * @param String
     * 
     */
    public ArrayList<Integer> fixTie(ArrayList<Integer> list, String stock){
        int max = Collections.max(list);
        int count = 0;
        int index = list.indexOf(Collections.max(list));
        int uptie = 1;
        if(stock.equals("lehighValley")){
            for (int i : list){
                if(i == max){
                    if(index == count){
                        continue;
                    }
                    if(players[count].lehighValley.get(0).share < players[index].lehighValley.get(0).share){
                        list.set(count,list.get(count)+uptie);
                        index = count;

                        uptie= uptie+2;
                    }
                    else{
                        list.set(index,list.get(index)+1);
                    }
                    count++;

                }
            }
            return list;
        }
        if(stock.equals("pennsylvania")){
            for (int i : list){
                if(i == max){
                    if(index == count){
                        continue;
                    }
                    if(players[count].pennsylvania.get(0).share < players[index].pennsylvania.get(0).share){
                        list.set(count,list.get(count)+uptie);
                        index = count;

                        uptie= uptie+2;
                    }
                    else{
                        list.set(index,list.get(index)+1);
                    }
                    count++;

                }
            }
            return list;
        }
        if(stock.equals("newyorkCentralSystem")){
            for (int i : list){
                if(i == max){
                    if(index == count){
                        continue;
                    }
                    if(players[count].newyorkCentralSystem.get(0).share < players[index].newyorkCentralSystem.get(0).share){
                        list.set(count,list.get(count)+uptie);
                        index = count;

                        uptie= uptie+2;
                    }
                    else{
                        list.set(index,list.get(index)+1);
                    }
                    count++;

                }
            }
            return list;
        }
        if(stock.equals("readingRailRoad")){
            for (int i : list){
                if(i == max){
                    if(index == count){
                        continue;
                    }
                    if(players[count].readingRailRoad.get(0).share < players[index].readingRailRoad.get(0).share){
                        list.set(count,list.get(count)+uptie);
                        index = count;

                        uptie= uptie+2;
                    }
                    else{
                        list.set(index,list.get(index)+1);
                    }
                    count++;

                }
            }
            return list;
        }
        if(stock.equals("erieLackawanna")){
            for (int i : list){
                if(i == max){
                    if(index == count){
                        continue;
                    }
                    if(players[count].erieLackawanna.get(0).share < players[index].erieLackawanna.get(0).share){
                        list.set(count,list.get(count)+uptie);
                        index = count;

                        uptie= uptie+2;
                    }
                    else{
                        list.set(index,list.get(index)+1);
                    }
                    count++;

                }
            }
            return list;
        }
        if(stock.equals("jerseyCentralLine")){
            for (int i : list){
                if(i == max){
                    if(index == count){
                        continue;
                    }
                    if(players[count].jerseyCentralLine.get(0).share < players[index].jerseyCentralLine.get(0).share){
                        list.set(count,list.get(count)+uptie);
                        index = count;

                        uptie= uptie+2;
                    }
                    else{
                        list.set(index,list.get(index)+1);
                    }
                    count++;

                }
            }
            return list;
        }
        if(stock.equals("baltimore")){
            for (int i : list){
                if(i == max){
                    if(index == count){
                        continue;
                    }
                    if(players[count].baltimore.get(0).share < players[index].baltimore.get(0).share){
                        list.set(count,list.get(count)+uptie);
                        index = count;

                        uptie= uptie+2;
                    }
                    else{
                        list.set(index,list.get(index)+1);
                    }
                    count++;

                }
            }
            return list;
        }
        if(stock.equals("westernMaryland")){
            for (int i : list){
                if(i == max){
                    if(index == count){
                        continue;
                    }
                    if(players[count].westernMaryland.get(0).share < players[index].westernMaryland.get(0).share){
                        list.set(count,list.get(count)+uptie);
                        index = count;

                        uptie= uptie+2;
                    }
                    else{
                        list.set(index,list.get(index)+1);
                    }
                    count++;

                }
            }
            return list;
        }
        if(stock.equals("brpRailWay")){
            for (int i : list){
                if(i == max){
                    if(index == count){
                        continue;
                    }
                    if(players[count].brpRailWay.get(0).share < players[index].brpRailWay.get(0).share){
                        list.set(count,list.get(count)+uptie);
                        index = count;

                        uptie= uptie+2;
                    }
                    else{
                        list.set(index,list.get(index)+1);
                    }
                    count++;

                }
            }
            return list;
        }
        return list;

    }

    /**
     * Checks the player stock cards and adds the to their score count.
     *
     * (Helper method for finalPointsCount().)
     *
     * @author Javier Rodriguez
     *
     */
    private void stocksEnd() {
        //used to hold the number of cards each player has for a certain stock.
        ArrayList<Integer> compare1 = new ArrayList();
        ArrayList<Integer> compare2 = new ArrayList();
        ArrayList<Integer> compare3 = new ArrayList();
        ArrayList<Integer> compare4 = new ArrayList();
        ArrayList<Integer> compare5 = new ArrayList();
        ArrayList<Integer> compare6 = new ArrayList();
        ArrayList<Integer> compare7 = new ArrayList();
        ArrayList<Integer> compare8 = new ArrayList();
        ArrayList<Integer> compare9 = new ArrayList();

        //adds the amount of stocks that each player has to a so we can award points
        // based on the amount. Ties aren't dealt with yet but all we would have 
        //is compare the values on the right stock arraylist in the player class.

        for (Player p : players) {
            compare1.add(p.lehighValley.size());
            compare2.add(p.pennsylvania.size());
            compare3.add(p.newyorkCentralSystem.size());
            compare4.add(p.readingRailRoad.size());
            compare5.add(p.erieLackawanna.size());
            compare6.add(p.jerseyCentralLine.size());
            compare7.add(p.baltimore.size());
            compare8.add(p.westernMaryland.size());
            compare9.add(p.brpRailWay.size());

        }
        if(dummy != null){
            compare1.add(dummy.lehighValley.size());
            compare2.add(dummy.pennsylvania.size());
            compare3.add(dummy.newyorkCentralSystem.size());
            compare4.add(dummy.readingRailRoad.size());
            compare5.add(dummy.erieLackawanna.size());
            compare6.add(dummy.jerseyCentralLine.size());
            compare7.add(dummy.baltimore.size());
            compare8.add(dummy.westernMaryland.size());
            compare9.add(dummy.brpRailWay.size());

        }
        ArrayList<Integer> compareList1 = fixTie(compare1,"lehighValley");
        ArrayList<Integer> compareList2 = fixTie(compare2,"pennsylvania");
        ArrayList<Integer> compareList3 = fixTie(compare3,"newyorkCentralSystem");
        ArrayList<Integer> compareList4 = fixTie(compare4,"readingRailRoad");
        ArrayList<Integer> compareList5 = fixTie(compare5,"erieLackawanna");
        ArrayList<Integer> compareList6 = fixTie(compare6,"jerseyCentralLine");
        ArrayList<Integer> compareList7 = fixTie(compare7,"baltimore");
        ArrayList<Integer> compareList8 = fixTie(compare8,"westernMaryland");
        ArrayList<Integer> compareList9 = fixTie(compare9,"brpRailWay");

        //lehigh valley point calculation
        //get the index of the of the highest value
        //the index is also the player that has the highest amount of cards so
        for (int i = 0; i < 3; i++) {
            //if we get a zero we just want to break out of the for loop
            //that means nobody has any cards.
            if (compareList1.indexOf(Collections.max(compareList1)) == 0) {
                break;
            } else {
                //first max
                if (i == 0) {
                    //add points to the player

                    Player p = players[compareList1.indexOf(Collections.max(compareList1))];
                    if(compareList1.indexOf(Collections.max(compareList1)) == players.length){
                        dummy.numPoints = dummy.numPoints + 12;
                    }
                    else{

                        p.numPoints = p.numPoints + 12;
                    }
                    //set's this to to zero
                    compareList1.set(compareList1.indexOf(Collections.max(compareList1)), 0);
                    //format repeats
                }
                if (i == 1) {
                    //add points to the player

                    Player p = players[compareList1.indexOf(Collections.max(compareList1))];
                    if(compareList1.indexOf(Collections.max(compareList1)) == players.length){
                        dummy.numPoints = dummy.numPoints + 7;
                    }
                    else{
                        p.numPoints = p.numPoints + 7;
                    }
                    //set's this to to zero so we can see the next highest number
                    compareList1.set(compareList1.indexOf(Collections.max(compareList1)), 0);
                    //format repeats
                }

                if (i == 2) {
                    //add points to the player

                    Player p = players[compareList1.indexOf(Collections.max(compareList1))];
                    if(compareList1.indexOf(Collections.max(compareList1)) == players.length){
                        dummy.numPoints = dummy.numPoints + 3;
                    }
                    else{
                        p.numPoints = p.numPoints + 3;
                    }
                    //set's this to to zero
                    compareList1.set(compareList1.indexOf(Collections.max(compareList1)), 0);
                    //format repeats
                }

            }
        }
        //now the format of the previous one would repeat for all stocks.
        //penssylvania
        for (int i = 0; i < 5; i++) {
            //if we get a zero we just want to break out of the for loop
            //that means nobody has any cards.
            if (compareList2.indexOf(Collections.max(compareList2)) == 0) {
                break;
            } else {
                //first max
                if (i == 0) {
                    //add points to the player

                    Player p = players[compareList2.indexOf(Collections.max(compareList2))];
                    if(compareList2.indexOf(Collections.max(compareList2)) == players.length){
                        dummy.numPoints = dummy.numPoints + 30;
                    }
                    else{
                        p.numPoints = p.numPoints + 30;
                    }
                    //set's this to to zero
                    compareList2.set(compareList2.indexOf(Collections.max(compareList2)), 0);
                    //format repeats
                }
                if (i == 1) {
                    //add points to the player

                    Player p = players[compareList2.indexOf(Collections.max(compareList2))];
                    if(compareList2.indexOf(Collections.max(compareList2)) == players.length){
                        dummy.numPoints = dummy.numPoints + 21;
                    }
                    else{
                        p.numPoints = p.numPoints + 21;
                    }
                    //set's this to to zero so we can see the next highest number
                    compareList2.set(compareList2.indexOf(Collections.max(compareList2)), 0);
                    //format repeats
                }
                if (i == 2) {
                    //add points to the player

                    Player p = players[compareList2.indexOf(Collections.max(compareList2))];
                    if(compareList2.indexOf(Collections.max(compareList2)) == players.length){
                        dummy.numPoints = dummy.numPoints + 14;
                    }
                    else{
                        p.numPoints = p.numPoints + 14;
                    }
                    //set's this to to zero
                    compareList2.set(compareList2.indexOf(Collections.max(compareList2)), 0);
                    //format repeats
                }
                if (i == 3) {
                    //add points to the player

                    Player p = players[compareList2.indexOf(Collections.max(compareList2))];
                    if(compareList2.indexOf(Collections.max(compareList2)) == players.length){
                        dummy.numPoints = dummy.numPoints + 9;
                    }
                    else{
                        p.numPoints = p.numPoints + 9;
                    }
                    //set's this to to zero so we can see the next highest number
                    compareList2.set(compareList2.indexOf(Collections.max(compareList2)), 0);
                    //format repeats
                }
                if (i == 4) {
                    //add points to the player

                    Player p = players[compareList2.indexOf(Collections.max(compareList2))];
                    if(compareList2.indexOf(Collections.max(compareList2)) == players.length){
                        dummy.numPoints = dummy.numPoints + 6;
                    }
                    else{
                        p.numPoints = p.numPoints + 6;
                    }
                    //set's this to to zero
                    compareList2.set(compareList2.indexOf(Collections.max(compareList2)), 0);
                    //format repeats
                }

            }
        }
        //New York Central System
        for (int i = 0; i < 3; i++) {
            //if we get a zero we just want to break out of the for loop
            //that means nobody has any cards.
            if (compareList3.indexOf(Collections.max(compareList3)) == 0) {
                break;
            } else {
                //first max
                if (i == 0) {
                    //add points to the player

                    Player p = players[compareList3.indexOf(Collections.max(compareList3))];
                    if(compareList3.indexOf(Collections.max(compareList3)) == players.length){
                        dummy.numPoints = dummy.numPoints + 10;
                    }
                    else{
                        p.numPoints = p.numPoints + 10;
                    }
                    //set's this to to zero
                    compareList3.set(compareList3.indexOf(Collections.max(compareList3)), 0);
                    //format repeats
                }
                if (i == 1) {
                    //add points to the player

                    Player p = players[compareList3.indexOf(Collections.max(compareList3))];
                    if(compareList3.indexOf(Collections.max(compareList3)) == players.length){
                        dummy.numPoints = dummy.numPoints + 6;
                    }
                    else{
                        p.numPoints = p.numPoints + 6;
                    }
                    //set's this to to zero so we can see the next highest number
                    compareList3.set(compareList3.indexOf(Collections.max(compareList3)), 0);
                    //format repeats
                }
                if (i == 2) {
                    //add points to the player

                    Player p = players[compareList3.indexOf(Collections.max(compareList3))];
                    if(compareList3.indexOf(Collections.max(compareList3)) == players.length){
                        dummy.numPoints = dummy.numPoints + 3;
                    }
                    else{
                        p.numPoints = p.numPoints + 3;
                    }
                    //set's this to to zero
                    compareList3.set(compareList3.indexOf(Collections.max(compareList3)), 0);
                    //format repeats
                }

            }
        }
        //reading railroad
        for (int i = 0; i < 3; i++) {
            //if we get a zero we just want to break out of the for loop
            //that means nobody has any cards.
            if (compareList4.indexOf(Collections.max(compareList4)) == 0) {
                break;
            } else {
                //first max
                if (i == 0) {
                    //add points to the player

                    Player p = players[compareList4.indexOf(Collections.max(compareList4))];
                    if(compareList4.indexOf(Collections.max(compareList4)) == players.length){
                        dummy.numPoints = dummy.numPoints + 14;
                    }
                    else{
                        p.numPoints = p.numPoints + 14;
                    }
                    //set's this to to zero
                    compareList4.set(compareList4.indexOf(Collections.max(compareList4)), 0);
                    //format repeats
                }
                if (i == 1) {
                    //add points to the player

                    Player p = players[compareList4.indexOf(Collections.max(compareList4))];
                    if(compareList4.indexOf(Collections.max(compareList4)) == players.length){
                        dummy.numPoints = dummy.numPoints + 9;
                    }
                    else{
                        p.numPoints = p.numPoints + 9;
                    }
                    //set's this to to zero so we can see the next highest number
                    compareList4.set(compareList4.indexOf(Collections.max(compareList4)), 0);
                    //format repeats
                }
                if (i == 2) {
                    //add points to the player

                    Player p = players[compareList4.indexOf(Collections.max(compareList4))];
                    if(compareList4.indexOf(Collections.max(compareList4)) == players.length){
                        dummy.numPoints = dummy.numPoints + 5;
                    }
                    else{
                        p.numPoints = p.numPoints + 5;
                    }
                    //set's this to to zero
                    compareList4.set(compareList4.indexOf(Collections.max(compareList4)), 0);
                    //format repeats
                }

            }
        }
        //erielackannawa
        for (int i = 0; i < 4; i++) {
            //if we get a zero we just want to break out of the for loop
            //that means nobody has any cards.
            if (compareList5.indexOf(Collections.max(compareList5)) == 0) {
                break;
            } else {
                //first max
                if (i == 0) {
                    //add points to the player

                    Player p = players[compareList5.indexOf(Collections.max(compareList5))];
                    if(compareList5.indexOf(Collections.max(compareList5)) == players.length){
                        dummy.numPoints = dummy.numPoints + 16;
                    }
                    else{
                        p.numPoints = p.numPoints + 16;
                    }
                    //set's this to to zero
                    compareList5.set(compareList5.indexOf(Collections.max(compareList5)), 0);
                    //format repeats
                }
                if (i == 1) {
                    //add points to the player

                    Player p = players[compareList5.indexOf(Collections.max(compareList5))];
                    if(compareList5.indexOf(Collections.max(compareList5)) == players.length){
                        dummy.numPoints = dummy.numPoints + 10;
                    }
                    else{
                        p.numPoints = p.numPoints + 10;
                    }
                    //set's this to to zero so we can see the next highest number
                    compareList5.set(compareList5.indexOf(Collections.max(compareList5)), 0);
                    //format repeats
                }
                if (i == 2) {
                    //add points to the player

                    Player p = players[compareList5.indexOf(Collections.max(compareList5))];
                    if(compareList5.indexOf(Collections.max(compareList5)) == players.length){
                        dummy.numPoints = dummy.numPoints + 5;
                    }
                    else{
                        p.numPoints = p.numPoints + 5;
                    }
                    //set's this to to zero
                    compareList5.set(compareList5.indexOf(Collections.max(compareList5)), 0);
                    //format repeats
                }
                if (i == 3) {
                    //add points to the player

                    Player p = players[compareList5.indexOf(Collections.max(compareList5))];
                    if(compareList5.indexOf(Collections.max(compareList5)) == players.length){
                        dummy.numPoints = dummy.numPoints + 1;
                    }
                    else{
                        p.numPoints = p.numPoints + 1;
                    }
                    //set's this to to zero
                    compareList5.set(compareList5.indexOf(Collections.max(compareList5)), 0);
                    //format repeats
                }

            }
        }
        for (int i = 0; i < 2; i++) {
            //if we get a zero we just want to break out of the for loop
            //that means nobody has any cards.
            if (compareList6.indexOf(Collections.max(compareList6)) == 0) {
                break;
            } else {
                //first max
                if (i == 0) {
                    //add points to the player

                    Player p = players[compareList6.indexOf(Collections.max(compareList6))];
                    if(compareList6.indexOf(Collections.max(compareList6)) == players.length){
                        dummy.numPoints = dummy.numPoints + 8;
                    }
                    else{
                        p.numPoints = p.numPoints + 8;
                    }
                    //set's this to to zero
                    compareList6.set(compareList6.indexOf(Collections.max(compareList6)), 0);
                    //format repeats
                }
                if (i == 1) {
                    //add points to the player

                    Player p = players[compareList6.indexOf(Collections.max(compareList6))];
                    if(compareList6.indexOf(Collections.max(compareList6)) == players.length){
                        dummy.numPoints = dummy.numPoints + 5;
                    }
                    else{
                        p.numPoints = p.numPoints + 5;
                    }
                    //set's this to to zero so we can see the next highest number
                    compareList6.set(compareList6.indexOf(Collections.max(compareList6)), 0);
                    //format repeats
                }

            }
        }
        //balitmore
        for (int i = 0; i < 5; i++) {
            //if we get a zero we just want to break out of the for loop
            //that means nobody has any cards.
            if (compareList7.indexOf(Collections.max(compareList7)) == 0) {
                break;
            } else {
                //first max
                if (i == 0) {
                    //add points to the player

                    Player p = players[compareList7.indexOf(Collections.max(compareList7))];
                    if(compareList7.indexOf(Collections.max(compareList7)) == players.length){
                        dummy.numPoints = dummy.numPoints + 20;
                    }
                    else{
                        p.numPoints = p.numPoints + 20;
                    }
                    //set's this to to zero
                    compareList7.set(compareList7.indexOf(Collections.max(compareList7)), 0);
                    //format repeats
                }
                if (i == 1) {
                    //add points to the player

                    Player p = players[compareList7.indexOf(Collections.max(compareList7))];
                    if(compareList7.indexOf(Collections.max(compareList7)) == players.length){
                        dummy.numPoints = dummy.numPoints + 14;
                    }
                    else{
                        p.numPoints = p.numPoints + 14;
                    }
                    //set's this to to zero so we can see the next highest number
                    compareList7.set(compareList7.indexOf(Collections.max(compareList7)), 0);
                    //format repeats
                }
                if (i == 2) {
                    //add points to the player

                    Player p = players[compareList7.indexOf(Collections.max(compareList7))];
                    if(compareList7.indexOf(Collections.max(compareList7)) == players.length){
                        dummy.numPoints = dummy.numPoints + 9;
                    }
                    else{
                        p.numPoints = p.numPoints + 9;
                    }
                    //set's this to to zero
                    compareList7.set(compareList7.indexOf(Collections.max(compareList7)), 0);
                    //format repeats
                }
                if (i == 3) {
                    //add points to the player

                    Player p = players[compareList7.indexOf(Collections.max(compareList7))];
                    if(compareList7.indexOf(Collections.max(compareList7)) == players.length){
                        dummy.numPoints = dummy.numPoints + 5;
                    }
                    else{
                        p.numPoints = p.numPoints + 5;
                    }
                    //set's this to to zero so we can see the next highest number
                    compareList7.set(compareList7.indexOf(Collections.max(compareList7)), 0);
                    //format repeats
                }
                if (i == 4) {
                    //add points to the player

                    Player p = players[compareList7.indexOf(Collections.max(compareList7))];
                    if(compareList7.indexOf(Collections.max(compareList7)) == players.length){
                        dummy.numPoints = dummy.numPoints + 2;
                    }
                    else{
                        p.numPoints = p.numPoints + 2;
                    }
                    //set's this to to zero
                    compareList7.set(compareList7.indexOf(Collections.max(compareList7)), 0);
                    //format repeats
                }

            }
        }
        //WesternMaryLand
        for (int i = 0; i < 2; i++) {
            //if we get a zero we just want to break out of the for loop
            //that means nobody has any cards.
            if (compareList8.indexOf(Collections.max(compareList8)) == 0) {
                break;
            } else {
                //first max
                if (i == 0) {
                    //add points to the player

                    Player p = players[compareList8.indexOf(Collections.max(compareList8))];
                    if(compareList8.indexOf(Collections.max(compareList8)) == players.length){
                        dummy.numPoints = dummy.numPoints + 9;
                    }
                    else{
                        p.numPoints = p.numPoints + 9;
                    }
                    //set's this to to zero
                    compareList8.set(compareList8.indexOf(Collections.max(compareList8)), 0);
                    //format repeats
                }
                if (i == 1) {
                    //add points to the player

                    Player p = players[compareList8.indexOf(Collections.max(compareList8))];
                    if(compareList8.indexOf(Collections.max(compareList8)) == players.length){
                        dummy.numPoints = dummy.numPoints + 5;
                    }
                    else{
                        p.numPoints = p.numPoints + 5;
                    }
                    //set's this to to zero so we can see the next highest number
                    compareList8.set(compareList8.indexOf(Collections.max(compareList8)), 0);
                    //format repeats
                }

            }
        }
        //BRP railway
        for (int i = 0; i < 2; i++) {
            //if we get a zero we just want to break out of the for loop
            //that means nobody has any cards.
            if (compareList9.indexOf(Collections.max(compareList9)) == 0) {
                break;
            } else {
                //first max
                if (i == 0) {
                    //add points to the player

                    Player p = players[compareList9.indexOf(Collections.max(compareList9))];
                    if(compareList9.indexOf(Collections.max(compareList9)) == players.length){
                        dummy.numPoints = dummy.numPoints + 7;
                    }
                    else{
                        p.numPoints = p.numPoints + 7;
                    }
                    //set's this to to zero
                    compareList9.set(compareList9.indexOf(Collections.max(compareList9)), 0);
                    //format repeats
                }
                if (i == 1) {
                    //add points to the player

                    Player p = players[compareList9.indexOf(Collections.max(compareList9))];
                    if(compareList9.indexOf(Collections.max(compareList9)) == players.length){
                        dummy.numPoints = dummy.numPoints + 4;
                    }
                    else{
                        p.numPoints = p.numPoints + 4;
                    }
                    //set's this to to zero so we can see the next highest number
                    compareList9.set(compareList9.indexOf(Collections.max(compareList9)), 0);
                    //format repeats
                }

            }
        }

    }

    /**
     * Recursive helper method for finalPointsCount().
     *
     * Calculates a player's longest continuous path, using their captured
     * routes.
     *
     * @param current the city the algorithm is currently checking
     * @param legalRoutes the player's list of captured routes
     * @return the largest length of a continuous route in legalRoutes
     */
    private int longestContPath(City current, List<Route> legalRoutes) {

        if (legalRoutes.isEmpty()) {
            return 0;
        }

        List<Integer> answers = new ArrayList<>();

        //make sure to check each possible route. This stops at the first it finds.
        for (Route r : legalRoutes) {
            if (r.source.equals(current)) {
                legalRoutes.remove(r);
                answers.add(r.length + longestContPath(r.dest, legalRoutes));
            } else if (r.dest.equals(current)) {
                legalRoutes.remove(r);
                answers.add(r.length + longestContPath(r.source, legalRoutes));
            }

        }

        int maxScore = 0;
        if (!answers.isEmpty()) {
            for (Integer i : answers) {
                if (i > maxScore) {
                    maxScore = i;
                }
            }
        }

        //if all routes from this city failed, this will = 0.
        return maxScore;
    }

    /**
     * Recursive helper method for finalPointsCount().
     *
     * Recursively traces through the path from source to dest, seeing if the
     * list of legalRoutes contains a set of necessary routes to complete the
     * path.
     *
     * @param source one of the route's cities
     * @param dest one of the route's cities
     * @param legalRoutes the current player's routesOwned list
     * @return true if the player has completed the course from source to dest,
     * and false otherwise
     */
    private boolean traversePath(City source, City dest, List<Route> legalRoutes) {
        if (dest.equals(source)) {
            return true;
        }

        for (Route r : dest.routes) {

            if (legalRoutes.contains(r)) {

                if (dest.equals(r.source)) {
                    return traversePath(source, r.dest, legalRoutes);
                } else if (r.equals(r.dest)) {
                    return traversePath(source, r.source, legalRoutes);
                }
            }
        }

        return false;

    }

    /**
     * Main method, used for testing.
     * 
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        TrainGame t = new TrainGame(new String[]{"London"}, 
                new TrainColor[]{TrainColor.BLACK},new PennMap());

        t.claimStock("Pennsylvania");
        System.out.println(t.players[0].name);
        System.out.println(t.players[0].pennsylvania.size());

        System.out.println(t.stockCards.baltimore.size());
    }

}

