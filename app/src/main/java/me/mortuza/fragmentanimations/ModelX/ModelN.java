package me.mortuza.fragmentanimations.ModelX;

public class ModelN {
    String c;
    String d;

    public ModelN(String c, String d) {
        this.c = c;
        this.d = d;
    }

    public String getC() {
        return c;
    }

    public void setC(String c) {
        this.c = c;
    }

    public String getD() {
        return d;
    }

    public void setD(String d) {
        this.d = d;
    }

    @Override
    public String toString() {
        return "ModelN{" +
                "c='" + c + '\'' +
                ", d='" + d + '\'' +
                '}';
    }
}
