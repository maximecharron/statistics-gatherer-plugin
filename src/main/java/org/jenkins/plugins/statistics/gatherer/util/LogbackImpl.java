package org.jenkins.plugins.statistics.gatherer.util;

import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.classic.util.ContextInitializer;
import ch.qos.logback.core.joran.spi.JoranException;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;

public class LogbackImpl implements Logback {
    private Logger logger;
    private URLSha loggerSha;
    private String loggerName;

    @Override
    public Logback setLoggerName(String loggerName) {
        try {
            this.loggerName = loggerName;
            URL configurationUrl = getConfigurationURL();
            this.loggerSha = new URLSha(configurationUrl);
            this.logger = createLogger(configurationUrl, loggerName);
            return this;
        } catch (JoranException e) {
            throw new RuntimeException("Unable to configure logger", e);
        } catch (MalformedURLException e) {
            throw new RuntimeException("Unable to find LOGBack XML configuration", e);
        }
    }

    private Logger createLogger(URL configurationUrl, String loggerName) throws JoranException {
        LoggerContext loggerContext = new LoggerContext();
        ContextInitializer contextInitializer = new ContextInitializer(loggerContext);
        contextInitializer.configureByResource(configurationUrl);
        return loggerContext.getLogger(loggerName);
    }

    private URL getConfigurationURL() throws MalformedURLException {
        String configurationUrlString = PropertyLoader.getLogbackConfigXmlUrl();
        if (configurationUrlString == null) {
            throw new IllegalStateException("LOGBack XML configuration file not specified");
        }

        return new URL(configurationUrlString);
    }

    @Override
    public void log(String msg) {
        if (logger != null) {
            logger.info(msg);
        }
    }

    public Logger getLogger() {
        return logger;
    }

    public boolean refresh() throws Exception {
        if (loggerSha == null) {
            return false;
        }

        URL configurationUrl = getConfigurationURL();
        URLSha latestSha1 = new URLSha(getConfigurationURL());
        if (latestSha1.equals(loggerSha)) {
            this.logger = createLogger(configurationUrl, loggerName);
            return true;
        }

        return false;
    }
}
