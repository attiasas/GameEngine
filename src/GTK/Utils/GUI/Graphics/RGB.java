package GTK.Utils.GUI.Graphics;

import java.util.Objects;

/**
 * Created By:      Assaf, On 23/02/2020
 * Description:     Representation of a color in rgb format that can be used with any graphic engine.
 **/
public class RGB
{
    public static final int R = 0;
    public static final int G = 1;
    public static final int B = 2;

    public int r;
    public int g;
    public int b;

    /**
     * Copy constructor
     * @param other - color to copy values from
     */
    public RGB(RGB other)
    {
        this.r = other.r;
        this.g = other.g;
        this.b = other.b;
    }

    /**
     * Constructor base on given values
     * @param r - red value in the range [0-255]
     * @param g - green value in the range [0-255]
     * @param b - blue value in the range [0-255]
     */
    public RGB(int r, int g, int b)
    {
        setColor(r,g,b);
    }

    private void setColor(int r, int g, int b)
    {
        if(r < 0 || r > 255) throw new IllegalArgumentException("RGB (R=" + r + ") Value is in the range 0-255");
        if(g < 0 || g > 255) throw new IllegalArgumentException("RGB (G=" + g + ") Value is in the range 0-255");
        if(b < 0 || b > 255) throw new IllegalArgumentException("RGB (B=" + b + ") Value is in the range 0-255");

        this.r = r;
        this.g = g;
        this.b = b;
    }

    /**
     * Constructor for creating a rbg color that r,g,b values are the same (grayscale)
     * @param grayScale - value of r,g,b in the range [0-255]
     */
    public RGB(int grayScale) { this(grayScale,grayScale,grayScale); }

    /**
     * Constructor for arrays with r (index 0) g (index 1) and b (index 2) values
     * this array can only be with 3 values.
     * @param rgb can only be size 3
     */
    public RGB(int... rgb)
    {
        if(rgb == null || rgb.length < 1 || rgb.length == 2 || rgb.length > 3) throw new IllegalArgumentException("Array needs to be {r,g,b}");
        setColor(rgb[R],rgb[G],rgb[B]);
    }

    /**
     * Get the rgb color black
     * @return - rgb object of black color
     */
    public static RGB Black() { return new RGB(0); }

    /**
     * Get the rgb color white
     * @return - rgb object of white color
     */
    public static RGB White() { return new RGB(255); }

    /**
     * Check if a given values matches the rgb values of the object
     * @param r - red value to check if equals
     * @param g - green value to check if equals
     * @param b - blue value to check if equals
     * @return true if r,g,b (all of them) matches the values of the object
     */
    public boolean sameValues(int r, int g, int b)
    {
        return this.r == r && this.g == g && this.b == b;
    }

    @Override
    public String toString() {
        return "RGB{" +
                "r=" + r +
                ", g=" + g +
                ", b=" + b +
                '}';
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RGB rgb = (RGB) o;
        return r == rgb.r &&
                g == rgb.g &&
                b == rgb.b;
    }

    @Override
    public int hashCode() {
        return Objects.hash(r, g, b);
    }

    /**
     * return an array representation of the rgb color.
     * @return array of size 3 with the color values: [r,g,b]
     */
    public int[] toArray() { return new int[]{r,g,b}; }
}
