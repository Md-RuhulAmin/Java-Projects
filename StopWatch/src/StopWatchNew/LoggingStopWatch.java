package StopWatchNew;

import java.util.HashMap;
public class LoggingStopWatch implements StopWatch {
	
	public static void main(String args[]) {
	    new LoggingStopWatch();
	  }
    private HashMap<String, Long> startingTimes;
    private HashMap<String, Long> measures;
    public static StopWatch lastCreated = new LoggingStopWatch();
    public LoggingStopWatch() {
        this.startingTimes = new HashMap<String,Long>();
        this.measures = new HashMap<String,Long>();
        lastCreated = this;
    }
    @Override
    public StopWatch start(String label) {
        startingTimes.put(label, System.currentTimeMillis());
        if (measures.get(label) == null) {
            measures.put(label, 0L);
        }
        return this;
    }
    @Override
    public StopWatch pause(String label) {
        Long start = startingTimes.get(label);
        if (start == null) {
            throw new RuntimeException("Calling pause(\"" + label + "\" without calling start() at least once.");
        }
        long newMeasure = System.currentTimeMillis() - startingTimes.get(label);
        measures.put(label, measures.get(label) + newMeasure);
        startingTimes.remove(label);
        return this;
    }
    @Override
    public StopWatch measure(String label) {
        if (startingTimes.containsKey(label)) {
            pause(label);
        }
        System.out.println("STOPWATCH " + label + ": " + measures.get(label));
        return this;
    }
    @Override
    public StopWatch reset(String label) {
        measures.remove(label);
        return this;
    }
}
