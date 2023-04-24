package ru.sbrf.gwec.stub;

import io.quarkus.grpc.GrpcService;
import io.smallrye.mutiny.Uni;
import ru.sbrf.gwec.proto.pingpong.Msg;
import ru.sbrf.gwec.proto.pingpong.MutinyPingPongGrpc;

@GrpcService
public class StubService extends MutinyPingPongGrpc.PingPongImplBase {
    private final static Msg PONG = Msg.newBuilder().setBody("PONG").build();

    private final ResponseTimeService responseTimeService;

    public StubService(ResponseTimeService responseTimeService) {
        this.responseTimeService = responseTimeService;
    }

    @Override
    public Uni<Msg> ping(Msg request) {
        return Uni.createFrom().item(() -> PONG)
                .onItem().delayIt().by(responseTimeService.getResponseTime());
    }
}
