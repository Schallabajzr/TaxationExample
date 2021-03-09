package si.test.resources.impl;

import org.eclipse.microprofile.metrics.MetricUnits;
import org.eclipse.microprofile.metrics.annotation.Counted;
import org.eclipse.microprofile.metrics.annotation.Timed;
import si.test.beans.TradeBean;
import si.test.dtos.TradeDto;
import si.test.dtos.TradeResponseDto;
import si.test.resources.TradeResource;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

@RequestScoped
public class TradeResourceImpl implements TradeResource {

    @Inject
    TradeBean tradeBean;

    @Override
    @Counted(name = "performedChecks", description = "How many trades have been calculated performed.")
    @Timed(name = "checksTimer", description = "A measure of how long it takes to perform the calculation test.", unit = MetricUnits.MILLISECONDS)
    public TradeResponseDto calculateTax(TradeDto trade) {
        return tradeBean.calculateWinningsAndTax(trade);
    }
}
