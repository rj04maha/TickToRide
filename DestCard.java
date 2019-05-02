
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Objects;
import javax.imageio.ImageIO;

/**
 * Used to store information on a Destination Card
 * for Ticket to Ride.
 * 
 * 
 * @author London Brunell
 */
public class DestCard {

    protected City source;
    protected City dest;

    public BufferedImage cardImage;
    private int numPoints;

    /**
     * Constructor for objects of clas DestCard.
     * 
     * @param s the Route's first City
     * @param d the route's second City
     * @param np the Route's point value
     * @param i the filename of the card's image
     */
    public DestCard(City s, City d, int np, String i){

        try{
            cardImage = ImageIO.read(new File("TicketToRideGraphics/Routes/" + i));
        }
        catch(IOException e){
            System.err.println("Could not read file: " + e);
        }

        numPoints = np;
        source = s;
        dest = d;
    }

    /**
     * Accessor for numPoints.
     * 
     * @return the number of points the destination card is worth.
     */
    public int getPoints(){
        return numPoints;
    }

    /**
     * Equals method.
     * 
     * @param o the object to compare to
     * @return true if o is a DestCard and the two are equal.
     */
    @Override
    public boolean equals(Object o){
        if(o == null) return false;
        if(! (o instanceof DestCard) ) return false;

        DestCard other = (DestCard)o;

        if( this.dest.equals(other.dest) && 
        this.source.equals(other.source)){
            return true;
        }

        if ( this.dest.equals(other.source) && 
        this.source.equals(other.dest)){
            return true;
        }

        return false;
    }

    /**
     * Hash Code method
     * 
     * @return the object's hash code
     */
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 41 * hash + Objects.hashCode(this.source);
        hash = 41 * hash + Objects.hashCode(this.dest);
        hash = 41 * hash + this.numPoints;
        return hash;
    }
    
    /**
     * toString method
     * 
     * @return a string describing the dest card 
     */
    @Override
    public String toString(){
        return source.name + " to " + dest.name;
    }

}
