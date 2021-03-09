package si.test.beans;

import si.test.dtos.TradeDto;
import si.test.dtos.TradeResponseDto;
import si.test.entities.TaxBaseEntity;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * Business logic for calculating the taxation amount
 *
 * @author Zan Schaffer
 * @version %I%, %G%
 */
@ApplicationScoped
public class TradeBean implements Serializable {

    private static final long serialVersionUID = -6949493768556615743L;

    @Inject
    TraderBean traderBean;

    /**
     * Calculates the amount of winnings and tax for a bet
     *
     * @param trade an object containing bet information
     * @return an object containing the calculated taxes
     */
    public TradeResponseDto calculateWinningsAndTax(TradeDto trade) {
        TaxBaseEntity taxBase = traderBean.getTraderByUUID(trade.getTraderId()).getTaxBase();

        double possibleReturnAmountBefTax = getPossibleReturnAmountBefTax(trade);
        double taxableAmmount = getTaxableAmmount(trade.getPlayedAmount(), taxBase.isTaxOnWinnings(), possibleReturnAmountBefTax);
        double taxAmount = getTax(taxBase, taxableAmmount);
        double possibleReturnAmountAfterTax = getPossibleReturnAmountAfterTax(possibleReturnAmountBefTax, taxAmount);

        return new TradeResponseDto(possibleReturnAmountAfterTax, possibleReturnAmountBefTax, possibleReturnAmountAfterTax, getTaxRate(possibleReturnAmountBefTax, possibleReturnAmountAfterTax), taxAmount);
    }


    private double getPossibleReturnAmountBefTax(TradeDto trade) {
        return trade.getPlayedAmount() * trade.getOdd();
    }

    private double getTaxableAmmount(int playedAmount, boolean isTaxOnWinnings, double possibleReturnAmountBefTax) {
        return isTaxOnWinnings ? possibleReturnAmountBefTax - playedAmount : possibleReturnAmountBefTax;
    }

    private double getTax(TaxBaseEntity taxBase, double taxableAmount) {
        return taxBase.isTaxFixedRate() ? taxBase.getValue() : roundToTwoDecimals(taxableAmount * taxBase.getValue());
    }

    private double getPossibleReturnAmountAfterTax(double possibleReturnAmountBefTax, double tax) {
        return possibleReturnAmountBefTax - tax;
    }

    private double getTaxRate(double betBase, double possibleReturnAmountAfterTax) {
        return roundToTwoDecimals(1 - possibleReturnAmountAfterTax / betBase);
    }

    private double roundToTwoDecimals(double numberToRound) {
        BigDecimal a = BigDecimal.valueOf(numberToRound);
        return a.setScale(2, RoundingMode.HALF_EVEN).doubleValue();
    }
}
