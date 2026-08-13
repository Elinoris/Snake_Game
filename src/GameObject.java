import java.awt.*;

/**
 * מחלקת אב אבסטרקטית לכל אובייקט מצויר בלוח המשחק (נחש, מזון וכו').
 * מאפשרת ירושה ופולימורפיזם: GamePanel יכול להחזיק אוסף של GameObject
 * ולקרוא ל-draw() על כל אחד מהם בלי לדעת את הסוג הקונקרטי שלו.
 */
public abstract class GameObject {

    protected static final int CELL_SIZE = 25;

    /**
     * כל תת-מחלקה מממשת את אופן הציור שלה על הלוח.
     */
    public abstract void draw(Graphics2D g2);
}
