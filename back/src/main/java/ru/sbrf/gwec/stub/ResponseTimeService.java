package ru.sbrf.gwec.stub;

import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.graalvm.collections.Pair;

import javax.inject.Singleton;
import java.time.Duration;
import java.util.concurrent.ThreadLocalRandom;

@Singleton
public class ResponseTimeService {
    private Pair<Long, Long> bound;

    public ResponseTimeService(@ConfigProperty(name = "grpc-stub.bound.low") long low,
                               @ConfigProperty(name = "grpc-stub.bound.upper") long upper) {
        bound = Pair.create(low, upper);
    }

    public void setResponseTime(long low, long upper) {
        bound = Pair.create(low, upper);
    }

    public Pair<Long, Long> getBound() {
        return bound;
    }

    public Duration getResponseTime() {
        return Duration.ofMillis(ThreadLocalRandom.current().nextLong(bound.getLeft(), bound.getRight()));
    }

}
