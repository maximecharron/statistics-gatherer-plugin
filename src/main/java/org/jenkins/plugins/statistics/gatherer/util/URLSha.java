package org.jenkins.plugins.statistics.gatherer.util;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Objects;

public class URLSha {
    private String value;

    public URLSha(URL configurationUrl) throws IOException {
        try (InputStream is = configurationUrl.openStream()) {
            value = DigestUtils.sha1Hex(is);
        }
    }

    @Override
    public boolean equals(Object obj) {
        return (obj instanceof URLSha) ? Objects.equals(this.value, ((URLSha) obj).value) : false;
    }

    @Override
    public String toString() {
        return "{sha1}" + value;
    }
}
