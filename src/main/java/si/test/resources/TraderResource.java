package si.test.resources;


import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;
import si.test.dtos.TraderDto;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("trader")
@Tag(ref = "trader")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public interface TraderResource {

    @GET
    @Operation(
            summary = "Get all traders",
            description = "Retrieves all traders known to the application")
    List<TraderDto> getAllTraders();

    @GET
    @Operation(
            summary = "Get trader by UUID",
            description = "Retrieves a trader with the specified ID")
    @Path("{uuid}")
    TraderDto getTraderByUUID(@PathParam("uuid") String uuid);

    @POST
    @Operation(
            summary = "Adds a trader",
            description = "Adds a new trader")
    TraderDto addTrader(TraderDto trader);
}
