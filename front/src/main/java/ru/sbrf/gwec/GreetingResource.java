package ru.sbrf.gwec;

import io.quarkus.grpc.GrpcClient;
import io.smallrye.mutiny.Uni;
import ru.sbrf.gwec.proto.pingpong.Msg;
import ru.sbrf.gwec.proto.pingpong.PingPong;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/api/v1")
public class GreetingResource {
    private final static Msg PING = Msg.newBuilder().setBody("ping").build();

    @GrpcClient
    PingPong pingPong;

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Path("/hello")
    public String hello() {
        return "Hello from App";
    }

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Path("/ping")
    public Uni<String> ping() {
        return pingPong.ping(PING)
                .onItem().transform(Msg::getBody);
    }
}