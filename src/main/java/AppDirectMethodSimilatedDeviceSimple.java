import com.microsoft.azure.sdk.iot.device.DeviceClient;
import com.microsoft.azure.sdk.iot.device.DeviceTwin.DeviceMethodData;
import com.microsoft.azure.sdk.iot.device.IotHubEventCallback;
import com.microsoft.azure.sdk.iot.device.IotHubStatusCode;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Scanner;

import static managers.Constants.*;

public class AppDirectMethodSimilatedDeviceSimple {
    /**
     *  Article
     *  Use direct methods (Java)
     *  https://docs.microsoft.com/en-us/azure/iot-hub/iot-hub-java-java-direct-methods
     */
    public static void main(String[] args)
            throws IOException, URISyntaxException
    {
        System.out.println("Starting device sample...");
        DeviceClient client = new DeviceClient(connString, protocol);

        try {
            client.open();
            client.subscribeToDeviceMethod(new DirectMethodCallback(),
                    null, new DirectMethodStatusCallback(),
                    null);
            System.out.println("Subscribed to direct methods. Waiting...");
        }
        catch (Exception e) {
            System.out.println("On exception, shutting down \n" + " Cause: " + e.getCause()
                    + " \n" +  e.getMessage());
            client.close();
            System.out.println("Shutting down...");
        }

        System.out.println("Press any key to exit...");
        Scanner scanner = new Scanner(System.in);
        scanner.nextLine();
        scanner.close();

        client.close();
        System.out.println("Shutting down...");

        System.exit(0);
    }

    protected static class DirectMethodStatusCallback implements IotHubEventCallback {
        public void execute(IotHubStatusCode status, Object context) {
            System.out.println("IoT Hub responded to device method operation with status " + status.name());
        }
    }

    protected static class DirectMethodCallback
            implements com.microsoft.azure.sdk.iot.device.DeviceTwin.DeviceMethodCallback {
        @Override
        public DeviceMethodData call(String methodName, Object methodData, Object context) {
            DeviceMethodData deviceMethodData;
            switch (methodName) {
                case "writeLine" : {
                    int status = METHOD_SUCCESS;
                    System.out.println("*** writeLine method" + new String((byte[])methodData));
                    deviceMethodData = new DeviceMethodData(status, "Executed direct method "
                            + methodName);
                    break;
                }
                default: {
                    int status = METHOD_NOT_DEFINED;
                    System.out.println("*** UNKNOWN METHOD" + new String((byte[])methodData));
                    deviceMethodData = new DeviceMethodData(status,
                            "Not defined direct method " + methodName);
                }
            }
            return deviceMethodData;
        }
    }
}