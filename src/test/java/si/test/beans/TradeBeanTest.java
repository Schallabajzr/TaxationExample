package si.test.beans;

import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.junit.mockito.InjectMock;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import si.test.dtos.TradeDto;
import si.test.dtos.TradeResponseDto;
import si.test.entities.TaxBaseEntity;
import si.test.entities.TraderEntity;

import javax.inject.Inject;

import static org.mockito.Mockito.when;

@QuarkusTest
class TradeBeanTest {

    @InjectMock
    TraderBean traderBean;

    @Inject
    TradeBean tradeBean;

    private TradeDto trade;

    @BeforeEach
    void setup() {
        TraderEntity traderGeneralRate = new TraderEntity(new TaxBaseEntity(false, false, 0.1));
        traderGeneralRate.setUuid("00");
        when(traderBean.getTraderByUUID("00")).thenReturn(traderGeneralRate);

        TraderEntity traderGeneralFixed = new TraderEntity(new TaxBaseEntity(false, true, 2));
        traderGeneralFixed.setUuid("01");
        when(traderBean.getTraderByUUID("01")).thenReturn(traderGeneralFixed);

        TraderEntity traderWinningsRate = new TraderEntity(new TaxBaseEntity(true, false, 0.1));
        traderWinningsRate.setUuid("10");
        when(traderBean.getTraderByUUID("10")).thenReturn(traderWinningsRate);

        TraderEntity traderWinningsFixed = new TraderEntity(new TaxBaseEntity(true, true, 2));
        traderWinningsFixed.setUuid("11");
        when(traderBean.getTraderByUUID("11")).thenReturn(traderWinningsFixed);

        trade = new TradeDto();
        trade.setTraderId(null);
        trade.setPlayedAmount(5);
        trade.setOdd(1.5);
    }

    @Test
    void testCalculateTaxGeneralRate() {
        // act
        trade.setTraderId("00");
        // arrange
        TradeResponseDto actual = tradeBean.calculateWinningsAndTax(trade);
        // assert
        Assertions.assertEquals(new TradeResponseDto(6.75, 7.5, 6.75, 0.1, 0.75), actual);
    }

    @Test
    void testCalculateTaxGeneralFixed() {
        // act
        trade.setTraderId("01");
        // arrange
        TradeResponseDto actual = tradeBean.calculateWinningsAndTax(trade);
        // assert
        Assertions.assertEquals(new TradeResponseDto(5.5, 7.5, 5.5, 0.27, 2), actual);
    }

    @Test
    void testCalculateTaxWinningsRate() {
        // act
        trade.setTraderId("10");
        // arrange
        TradeResponseDto actual = tradeBean.calculateWinningsAndTax(trade);
        // assert
        Assertions.assertEquals(new TradeResponseDto(7.25, 7.5, 7.25, 0.03, 0.25), actual);
    }

    @Test
    void testCalculateTaxWinningsFixed() {
        // act
        trade.setTraderId("11");
        // arrange
        TradeResponseDto actual = tradeBean.calculateWinningsAndTax(trade);
        // assert
        Assertions.assertEquals(new TradeResponseDto(5.5, 7.5, 5.5, 0.27, 2), actual);
    }

}
