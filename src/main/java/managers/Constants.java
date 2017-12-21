package managers;

import com.microsoft.azure.sdk.iot.device.IotHubClientProtocol;

import java.util.concurrent.TimeUnit;

public class Constants {
    // pk from shared access policy named iothubowner
    public static final String iotHubConnectionString = "HostName=boxmessagehub.azure-devices.net;SharedAccessKeyName=iothubowner;SharedAccessKey=9lpC/dPWNrQiEh/XLBVxdR9IW1oEZt1bo54RGDVP0LE=";
    public static final String deviceId = "myDeviceId";
    public static final String methodName = "writeLine";

    public static final Long responseTimeout = TimeUnit.SECONDS.toSeconds(30);
    public static final Long connectTimeout = TimeUnit.SECONDS.toSeconds(5);
    public static final String payload = "a line to be written";

    // Device conncetion string
    public static String connString = "HostName=boxmessagehub.azure-devices.net;DeviceId=myDeviceId;SharedAccessKey=RA+gG0snZ2gVEe2haL6GVovsxmpePZ67zvwZpI90eEY=";

    // IoT Hub responded to device method operation with status ERROR
    // private static IotHubClientProtocol protocol = IotHubClientProtocol.HTTPS;

    // In invoke-direct-method-poc getting an error
    // Gateway timeout! {"errorCode":504101,"trackingId":"b6411e545d91430484c10591bd17f013-G:10-TimeStamp:12/19/2017 10:59:28","message":"Timed out waiting for the response from device.","info":{"timeout":"00:00:30"},"timestampUtc":"2017-12-19T10:59:28.784434Z"}
    // private static IotHubClientProtocol protocol = IotHubClientProtocol.AMQPS;

    public static IotHubClientProtocol protocol = IotHubClientProtocol.MQTT;

    public static final int METHOD_SUCCESS = 200;
    public static final int METHOD_NOT_DEFINED = 404;

}
