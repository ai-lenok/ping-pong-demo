package ru.sbrf.gwec.stub;

import io.quarkus.vertx.web.Body;
import io.quarkus.vertx.web.Route;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.graalvm.collections.Pair;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class Endpoints {

    private final ResponseTimeService service;

    public Endpoints(ResponseTimeService service) {
        this.service = service;
    }

    @Route(path = "/bound", methods = Route.HttpMethod.POST)
    public Bound setBound(@Body Bound request) {
        service.setResponseTime(request.getLower(), request.getUpper());
        return Bound.from(service.getBound());
    }

    @Route(path = "/bound", methods = Route.HttpMethod.GET)
    public Bound getBound() {
        return Bound.from(service.getBound());
    }

    @AllArgsConstructor
    @Getter
    @Setter
    static class Bound {
        private Long lower;
        private Long upper;

        public static Bound from(Pair<Long, Long> bound) {
            return new Bound(bound.getLeft(), bound.getRight());
        }
    }
}
