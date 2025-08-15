package accesssystem.simulation;

import java.util.List;

public class SimulationRunner {

    private final List<AccessScenario> scenarios;

    public SimulationRunner(List<AccessScenario> scenarios) {
        this.scenarios = scenarios;
    }

    public void runAll() {
        for (AccessScenario scenario : scenarios) {
            System.out.println(" Running Scenario: " + scenario.name());
            scenario.run();
        }
    }
}