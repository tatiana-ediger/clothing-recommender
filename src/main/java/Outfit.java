import domain.BottomClothing;
import domain.FootwearClothing;
import domain.TopClothing;

/**
 * Represents an outfit, containing a top, bottom and footwear for a specific user.
 */
public class Outfit {

    private TopClothing top;
    private BottomClothing bottom;
    private FootwearClothing footwear;

    public Outfit(TopClothing top, BottomClothing bottom, FootwearClothing footwear) {
        this.top = top;
        this.bottom = bottom;
        this.footwear = footwear;
    }

    public TopClothing getTop() {
        return top;
    }

    public void setTop(TopClothing top) {
        this.top = top;
    }

    public BottomClothing getBottom() {
        return bottom;
    }

    public void setBottom(BottomClothing bottom) {
        this.bottom = bottom;
    }

    public FootwearClothing getFootwear() {
        return footwear;
    }

    public void setFootwear(FootwearClothing footwear) {
        this.footwear = footwear;
    }
}
