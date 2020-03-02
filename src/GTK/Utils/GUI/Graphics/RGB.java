package GTK.Utils.GUI.Graphics;

import java.util.Objects;

public class RGB
{
    public static final int R = 0;
    public static final int G = 1;
    public static final int B = 2;

    public int r;
    public int g;
    public int b;

    public RGB(RGB other)
    {
        this.r = other.r;
        this.g = other.g;
        this.b = other.b;
    }

    public RGB(int... rgb)
    {
        if(rgb == null || rgb.length < 1 || rgb.length == 2 || rgb.length > 3) throw new IllegalArgumentException("Array needs to be {r,g,b}");
        if(rgb[R] < 0 || rgb[R] > 255) throw new IllegalArgumentException("RGB (R=" + rgb[R] + ") Value is in the range 0-255");

        if(rgb.length == 1)
        {
            // grayScale
            r = rgb[0];
            g = rgb[0];
            b = rgb[0];
        }
        else
        {
            if(rgb[G] < 0 || rgb[G] > 255) throw new IllegalArgumentException("RGB (G=" + rgb[G] + ") Value is in the range 0-255");
            if(rgb[B] < 0 || rgb[B] > 255) throw new IllegalArgumentException("RGB (B=" + rgb[B] + ") Value is in the range 0-255");

            r = rgb[R];
            g = rgb[G];
            b = rgb[B];
        }
    }

    public static RGB Black() { return new RGB(0); }
    public static RGB White() { return new RGB(255); }

    public boolean sameValues(int r, int g, int b)
    {
        return this.r == r && this.g == g && this.b == b;
    }
    public boolean sameValues(RGB rgb)
    {
        return sameValues(rgb.r,rgb.g,rgb.b);
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

    public int[] toArray() { return new int[]{r,g,b}; }
}
