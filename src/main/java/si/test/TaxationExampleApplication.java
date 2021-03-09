package si.test;

import io.quarkus.runtime.Application;
import org.eclipse.microprofile.openapi.annotations.OpenAPIDefinition;
import org.eclipse.microprofile.openapi.annotations.info.Info;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

@OpenAPIDefinition(
        tags = {
                @Tag(name = "trade", description = "Widget operations."),
                @Tag(name = "trader", description = "Operations related to gaskets")
        },
        info = @Info(
                title = "Taxation Example API",
                version = "1.0.0")
)
public class TaxationExampleApplication extends Application {
    @Override
    protected void doStart(String[] args) {

    }

    @Override
    protected void doStop() {

    }

    @Override
    public String getName() {
        return null;
    }
}
