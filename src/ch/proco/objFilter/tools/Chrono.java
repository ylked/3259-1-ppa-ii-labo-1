package ch.proco.objFilter.tools;

public class Chrono {
    private long start = 0;
    private long stop = 0;

    public Chrono() {
    }

    public void start(){
        assert start == 0;
        this.start = System.nanoTime();
    }

    public void stop(){
        assert stop == 0;
        this.stop = System.nanoTime();
    }

    public void reset(){
        this.start = 0;
        this.stop = 0;
    }

    public double getEllapsedMillis(){
        return (stop - start) / 1000000.0;
    }
}
