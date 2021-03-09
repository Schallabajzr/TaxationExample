package si.test.resources;

import io.quarkus.test.common.QuarkusTestResource;
import io.quarkus.test.common.http.TestHTTPEndpoint;
import io.quarkus.test.h2.H2DatabaseTestResource;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import si.test.dtos.TradeDto;
import si.test.entities.TraderEntity;
import si.test.repositories.TraderRepository;

import javax.inject.Inject;

import static org.hamcrest.CoreMatchers.is;

@QuarkusTest
@QuarkusTestResource(H2DatabaseTestResource.class)
@TestHTTPEndpoint(TradeResource.class)
class TradeServiceTest {

    @Inject
    TraderRepository traderRepository;

    TraderEntity trader;

    @BeforeEach
    public void init() {
        trader = traderRepository.createTrader(false, false, 0.5);
    }

    @Test
    void whenGetBooks_thenShouldReturnSuccessfully() {
        TradeDto trade = new TradeDto();
        trade.setTraderId(trader.getUuid());
        trade.setPlayedAmount(5);
        trade.setOdd(2);

        RestAssured.given()
                .contentType(ContentType.JSON)
                .body(trade)
                .when().post()
                .then()
                .statusCode(200)
                .body(
                        "possibleReturnAmount", is(5.0F),
                        "possibleReturnAmountBefTax", is(10.0F),
                        "possibleReturnAmountAfterTax", is(5.0F),
                        "taxRate", is(0.5F),
                        "taxAmount", is(5.0F)
                );
    }
}
