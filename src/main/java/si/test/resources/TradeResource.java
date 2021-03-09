package si.test.resources;

import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;
import si.test.dtos.TradeDto;
import si.test.dtos.TradeResponseDto;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("trade")
@Tag(ref = "trade")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public interface TradeResource {

    @POST
    @Operation(
            summary = "Calculate tax",
            description = "Calculates the tax for a bet")
    TradeResponseDto calculateTax(TradeDto trade);
}
