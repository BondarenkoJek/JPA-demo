package ua.bondarenkojek.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.xml.bind.annotation.XmlRootElement;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@XmlRootElement
public class DeviceDto {
    private long id;
    private String name;
    private String model;
    private String brand;
}
