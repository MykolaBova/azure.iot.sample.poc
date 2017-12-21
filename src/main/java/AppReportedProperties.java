import managers.ReportedProperiesManager;

import java.io.IOException;
import java.net.URISyntaxException;

public class AppReportedProperties {
    public static void main( String[] args ) throws URISyntaxException, IOException {
        ReportedProperiesManager deviceTwinUtil = new ReportedProperiesManager();
        deviceTwinUtil.setReportedProperty("version", "65");

        System.exit(0);
    }
}