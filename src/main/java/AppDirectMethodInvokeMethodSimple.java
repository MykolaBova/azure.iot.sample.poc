import com.microsoft.azure.sdk.iot.service.devicetwin.DeviceMethod;
import com.microsoft.azure.sdk.iot.service.devicetwin.MethodResult;
import com.microsoft.azure.sdk.iot.service.exceptions.IotHubException;

import java.io.IOException;

import static managers.Constants.*;

public class AppDirectMethodInvokeMethodSimple {
    /**
     *  Article
     *  Use direct methods (Java)
     *  https://docs.microsoft.com/en-us/azure/iot-hub/iot-hub-java-java-direct-methods
     */
    public static void main( String[] args ) throws IOException {
        System.out.println("Starting sample...");
        DeviceMethod methodClient = DeviceMethod.createFromConnectionString(iotHubConnectionString);

        try {
            System.out.println("Invoke direct method");
            MethodResult result = methodClient.invoke(deviceId, methodName, responseTimeout,
                    connectTimeout, payload);

            if(result == null) {
                throw new IOException("Direct method invoke returns null");
            }
            System.out.println("Status=" + result.getStatus());
            System.out.println("Payload=" + result.getPayload());
        }
        catch (IotHubException e) {
            System.out.println(e.getMessage());
        }

        System.out.println("Shutting down sample...");

        System.exit(0);
    }
}