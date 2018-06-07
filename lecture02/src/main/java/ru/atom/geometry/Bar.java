package ru.atom.geometry;

public class Bar implements Collider {
    private Point leftLowerCorner;
    private Point leftUpperCorner;
    private Point rightUpperCorner;
    private Point rightLowerCorner;

    public Bar(int firstCornerX, int firstCornerY, int secondCornerX, int secondCornerY) {
        this.leftLowerCorner = new Point(Math.min(firstCornerX,secondCornerX),Math.min(firstCornerY,secondCornerY));
        this.leftUpperCorner = new Point(Math.min(firstCornerX,secondCornerX),Math.max(firstCornerY,secondCornerY));
        this.rightUpperCorner = new Point(Math.max(firstCornerX,secondCornerX),Math.max(firstCornerY,secondCornerY));
        this.rightLowerCorner = new Point(Math.max(firstCornerX,secondCornerX),Math.min(firstCornerY,secondCornerY));
    }

    @Override
    public boolean isColliding(Collider other) {
        if (this.equals(other)) return true;

        if (other instanceof Point) {
            return isCollidePoint((Point) other);
        }

        if (other instanceof Bar) {
            Bar otherBar = (Bar) other;
            return (isCollidePoint(otherBar.getLeftLowerCorner()) ||
                    isCollidePoint(otherBar.getLeftUpperCorner()) ||
                    isCollidePoint(otherBar.getRightLowerCorner()) ||
                    isCollidePoint(otherBar.getRightUpperCorner())
                   ) ||
                   (
                    this.getLeftUpperCorner().getX() >= otherBar.getLeftUpperCorner().getX() &&
                    this.getRightUpperCorner().getX() <= otherBar.getRightUpperCorner().getX() &&
                    this.getLeftUpperCorner().getY() >= otherBar.getLeftUpperCorner().getY() &&
                    this.getLeftLowerCorner().getY() <= otherBar.getLeftLowerCorner().getY()
                   ) ||
                   (
                    otherBar.getLeftUpperCorner().getX() >= this.getLeftUpperCorner().getX() &&
                    otherBar.getRightUpperCorner().getX() <= this.getRightUpperCorner().getX() &&
                    otherBar.getLeftUpperCorner().getY() >= this.getLeftUpperCorner().getY() &&
                    otherBar.getLeftLowerCorner().getY() <= this.getLeftLowerCorner().getY()
                   );
        }

        return false;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Bar that = (Bar) o;
        return this.getLeftLowerCorner().equals(that.getLeftLowerCorner()) &&
               this.getLeftUpperCorner().equals(that.getLeftUpperCorner()) &&
               this.getRightUpperCorner().equals(that.getRightUpperCorner()) &&
               this.getRightLowerCorner().equals(that.getRightLowerCorner());
    }

    private boolean isCollidePoint(Point p) {
        return this.getLeftLowerCorner().getX() <= p.getX() && this.getRightLowerCorner().getX() >= p.getX()
                && this.getLeftLowerCorner().getY() <= p.getY() && this.getLeftUpperCorner().getY() >= p.getY();
    }

    public Point getLeftLowerCorner() {
        return leftLowerCorner;
    }

    public Point getLeftUpperCorner() {
        return leftUpperCorner;
    }

    public Point getRightUpperCorner() {
        return rightUpperCorner;
    }

    public Point getRightLowerCorner() {
        return rightLowerCorner;
    }
}
