package ua.bondarenkojek.util;

import ua.bondarenkojek.dto.DeviceDto;
import ua.bondarenkojek.model.Device;

public class DtoUtil {
    public static DeviceDto parseDevice(Device device) {
        return DeviceDto
                .builder()
                .id(device.getId())
                .name(device.getName())
                .brand(device.getBrand())
                .model(device.getModel())
                .build();
    }
}
