package managers;

import com.microsoft.azure.sdk.iot.device.DeviceClient;
import com.microsoft.azure.sdk.iot.device.DeviceTwin.Device;
import com.microsoft.azure.sdk.iot.device.DeviceTwin.Property;
import com.microsoft.azure.sdk.iot.device.IotHubClientProtocol;
import com.microsoft.azure.sdk.iot.device.IotHubEventCallback;
import com.microsoft.azure.sdk.iot.device.IotHubStatusCode;

import java.io.IOException;
import java.net.URISyntaxException;

import static managers.Constants.connString;
import static managers.Constants.protocol;

public class ReportedProperiesManager {

    public void setReportedProperty(String key, String value) throws URISyntaxException, IOException {
        DeviceClient client = new DeviceClient(connString, protocol);

        // Create a Device object to store the device twin properties
        Device dataCollector = new Device() {
            // Print details when a property value changes
            @Override
            public void PropertyCall(String propertyKey, Object propertyValue, Object context) {
                System.out.println(propertyKey + " changed to " + propertyValue);
            }
        };

        try {
            // Open the DeviceClient and start the device twin services.
            client.open();
            client.startDeviceTwin(new DeviceTwinStatusCallBack(), null,
                    dataCollector, null);

            // Create a reported property and send it to your IoT hub.
            dataCollector.setReportedProp(new Property(key, value));
            client.sendReportedProperties(dataCollector.getReportedProp());
        }
        catch (Exception e) {
            System.out.println("On exception, shutting down \n" + " Cause: " + e.getCause()
                    + " \n" + e.getMessage());
            dataCollector.clean();
            client.close();
            System.out.println("Shutting down...");
        }

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        dataCollector.clean();
        client.close();
    }

    protected static class DeviceTwinStatusCallBack implements IotHubEventCallback {
        public void execute(IotHubStatusCode status, Object context) {
            System.out.println("IoT Hub responded to device twin operation with status " + status.name());
        }
    }
}