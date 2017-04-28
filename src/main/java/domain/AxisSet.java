package domain;

/**
 * Created by jaeyoung on 2017. 4. 29..
 */
public class AxisSet {
    private int axisX;
    private int axisY;
    private boolean isBlack;

    public AxisSet() {
    }

    public AxisSet(int axisX, int axisY, boolean isBlack) {
        this.axisX = axisX;
        this.axisY = axisY;
        this.isBlack = isBlack;
    }

    public int getAxisX() {
        return axisX;
    }

    public void setAxisX(int axisX) {
        this.axisX = axisX;
    }

    public int getAxisY() {
        return axisY;
    }

    public void setAxisY(int axisY) {
        this.axisY = axisY;
    }

    public boolean isBlack() {
        return isBlack;
    }

    public void setBlack(boolean black) {
        isBlack = black;
    }

    @Override
    public String toString() {
        return "domain.AxisSet{" +
                "axisX=" + axisX +
                ", axisY=" + axisY +
                ", isBlack=" + isBlack +
                '}';
    }
}
