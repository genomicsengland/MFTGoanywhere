package co.uk.gel.proj.runner;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({MergeFailedScenarios.class, RemoveFailuresFromJsonFiles.class})

public class MergeFailureRunner {
}
