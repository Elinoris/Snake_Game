import java.awt.*;


public abstract class GameObject {

    protected static final int CELL_SIZE = 25;

    /**
     * כל תת-מחלקה מממשת את אופן הציור שלה על הלוח.
     */
    public abstract void draw(Graphics2D g2);
}
