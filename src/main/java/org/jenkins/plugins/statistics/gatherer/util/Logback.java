package org.jenkins.plugins.statistics.gatherer.util;

/**
 * Subsystem to send logs through LOGBack
 */
public interface Logback {

    /**
     * Define the LOGBack logger name to use
     *
     * @param loggerName the logger name to use
     * @return this instance
     */
    Logback setLoggerName(String loggerName);

    /**
     * Send the message to LOGBack
     *
     * @param msg message to send to LOGBack
     */
    void log(String msg);

    /**
     * Check for LOGBack configuration and reload when changed.
     *
     * @return true if the configuration has been reloaded
     * @throws Exception if the configuration refresh failed
     */
    public boolean refresh() throws Exception;
}
