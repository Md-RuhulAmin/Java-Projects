package StopWatchNew;


public interface StopWatch {
    StopWatch start(String label);
    StopWatch pause(String label);
    StopWatch measure(String label);
    StopWatch reset(String label);
}
