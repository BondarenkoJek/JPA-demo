package ua.bondarenkojek;

import lombok.SneakyThrows;
import org.hibernate.Session;
import ua.bondarenkojek.dto.DeviceDto;
import ua.bondarenkojek.model.Device;
import ua.bondarenkojek.util.DtoUtil;
import ua.bondarenkojek.util.HibernateUtil;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;

public class MainXml {
    public static void main(String[] args) {
        DeviceDto deviceDto = getDeviseDto(1L);
        File file = new File("/Users/jek/Desktop/jpa-demo/src/main/java/ua/bondarenkojek/device.xml");
        writeDeviceToXml(deviceDto, file);
//        System.out.println(deviceDto.equals(readDeviceFromXml(file)));
        HibernateUtil.shutdown();
    }

    public static Long createDevise() {
        return HibernateMain.createDevice();
    }

    public static DeviceDto getDeviseDto(Long id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        DeviceDto deviceDto = DtoUtil.parseDevice(session.load(Device.class, id));
        session.close();
        return deviceDto;
    }

    public static void writeDeviceToXml(DeviceDto device, File file) {
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(DeviceDto.class);
            Marshaller marshaller = jaxbContext.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

            marshaller.marshal(device, file);
        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }
    public static DeviceDto readDeviceFromXml(File file) {
        DeviceDto deviceDto = null;
        try {
            JAXBContext jaxbContext = jaxbContext = JAXBContext.newInstance(DeviceDto.class);
            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
           deviceDto = (DeviceDto) unmarshaller.unmarshal(file);
        } catch (JAXBException e) {
            e.printStackTrace();
        }
        return deviceDto;
    }
}
