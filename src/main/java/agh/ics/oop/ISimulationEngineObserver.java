package agh.ics.oop;

import java.io.FileNotFoundException;

public interface ISimulationEngineObserver {
    void mapChanged() throws FileNotFoundException;
}