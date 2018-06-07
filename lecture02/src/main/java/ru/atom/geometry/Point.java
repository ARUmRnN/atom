package ru.atom.geometry;

/**
 * Template class for
 */
public class Point implements Collider {
    private final int x;
    private final int y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * @param o - other object to check equality with
     * @return true if two points are equal and not null.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Point that = (Point) o;

        return this.getX() == that.getX() && this.getY() == that.getY();
    }

    public int getY() {
        return y;
    }

    public int getX() {
        return x;
    }

    @Override
    public boolean isColliding(Collider other) {
        if (this.equals(other)) return true;

        if (other instanceof Bar) {
            Bar bar = (Bar) other;
            return bar.isColliding(this);
        }

        return false;
    }
}
