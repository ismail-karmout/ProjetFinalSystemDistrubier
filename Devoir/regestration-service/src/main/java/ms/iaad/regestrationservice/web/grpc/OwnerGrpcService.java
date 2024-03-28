package ms.iaad.regestrationservice.web.grpc;
import com.google.protobuf.Timestamp;
import io.grpc.stub.StreamObserver;
import lombok.AllArgsConstructor;
import ms.iaad.regestrationservice.entites.Owner;
import ms.iaad.regestrationservice.repositories.OwnerRepository;
import ms.iaad.regestrationservice.web.grpc.stub.OwnerGrpcServiceGrpc;
import ms.iaad.regestrationservice.web.grpc.stub.OwnerService;
import net.devh.boot.grpc.server.service.GrpcService;

import java.util.List;

@GrpcService
@AllArgsConstructor
public class OwnerGrpcService extends OwnerGrpcServiceGrpc.OwnerGrpcServiceImplBase {
    private OwnerRepository ownerRepository;

    @Override
    public void getOwner(OwnerService.GetOwnerRequest request, StreamObserver<OwnerService.GetOwnerResponse> responseObserver) {
        Long ownerId = request.getId();
        Owner owner = ownerRepository.findById(ownerId).orElse(null);

        OwnerService.GetOwnerResponse response;
        if (owner != null) {
            Timestamp birthDateTimestamp = Timestamp.newBuilder()
                    .setSeconds(owner.getBirthDate().getTime() / 1000)  // Convert milliseconds to seconds
                    .setNanos((int) ((owner.getBirthDate().getTime() % 1000) * 1000000))  // Convert remainder milliseconds to nanoseconds
                    .build();
            OwnerService.Owner grpcOwner = OwnerService.Owner.newBuilder()
                    .setId(owner.getId())
                    .setName(owner.getName())
                    .setBirthDate(birthDateTimestamp) // Assuming the birthDate is a java.util.Date
                    .setEmail(owner.getEmail())
                    .build();

            response = OwnerService.GetOwnerResponse.newBuilder()
                    .setOwner(grpcOwner)
                    .build();
        } else {
            response = OwnerService.GetOwnerResponse.newBuilder().build(); // Return an empty response or handle the error case
        }

        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    @Override
    public void listOwners(OwnerService.GetAllOwnersRequest request, StreamObserver<OwnerService.GetAllOwnersResponse> responseObserver) {
        List<Owner> owners = ownerRepository.findAll();

        OwnerService.GetAllOwnersResponse.Builder responseBuilder = OwnerService.GetAllOwnersResponse.newBuilder();
        for (Owner owner : owners) {
            Timestamp birthDateTimestamp = Timestamp.newBuilder()
                    .setSeconds(owner.getBirthDate().getTime() / 1000)  // Convert milliseconds to seconds
                    .setNanos((int) ((owner.getBirthDate().getTime() % 1000) * 1000000))  // Convert remainder milliseconds to nanoseconds
                    .build();
            OwnerService.Owner grpcOwner = OwnerService.Owner.newBuilder()
                    .setId(owner.getId())
                    .setName(owner.getName())
                    .setBirthDate(birthDateTimestamp)
                    .setEmail(owner.getEmail())
                    .build();

            responseBuilder.addOwners(grpcOwner);
        }

        OwnerService.GetAllOwnersResponse response = responseBuilder.build();

        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

}
