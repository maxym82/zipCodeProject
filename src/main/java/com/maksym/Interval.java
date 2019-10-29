package com.maksym;

public class Interval implements Comparable <Interval> {
    private int min;
    private int max;


    public Interval(int min, int max) {

        if (min <= max) {
            this.min = min;
            this.max = max;
        } else throw new IllegalArgumentException("Illegal interval");
    }

    public int getMin() {
        return this.min;
    }

    public int getMax() {
        return this.max;
    }

    public boolean intersects(Interval that) {
        return this.max > that.min && that.max > this.min;
    }

    public boolean containsElement(int x) {
        return min <= x && x <= max;
    }

    public boolean includeInterval (Interval that) {
        return this.min < that.min && this.max > that.max;
    }

    public void merge (Interval that) {
        if (this.min > that.min) {this.min = that.min;}
        if (this.max < that.max) {this.max = that.max;}
    }

    public int length() {
        return max - min;
    }

    @Override
    public String toString() {
        return "min = " + this.min + " max = " + this.max;
    }

    @Override
    public int compareTo(Interval that) {
        return this.min - that.min;
    }
}
