package co.uk.gel.logging;

import ch.qos.logback.classic.pattern.ClassicConverter;
import ch.qos.logback.classic.spi.ILoggingEvent;

import java.lang.management.ManagementFactory;

public class ProcessIdConverter extends ClassicConverter {
    private static String PID [] = ManagementFactory.getRuntimeMXBean().getName().split("@");
    private static final String PROCESS_ID = PID[0];

    @Override
    public String convert(final ILoggingEvent event) {
        // for every logging event return processId from mx bean
        // (or better alternative)
        return PROCESS_ID;
    }
}
