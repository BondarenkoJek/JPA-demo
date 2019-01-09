package ua.bondarenkojek;

import org.hibernate.Session;
import ua.bondarenkojek.model.Device;
import ua.bondarenkojek.util.HibernateUtil;

public class HibernateMain {
    public static void main(String[] args) {
//        System.out.println(getDevice(1));
//        getDevice(1);
        getDeviceSecondLevelCache(1);
    }

    public static Long createDevice() {
        Device device =  Device
                .builder()
                .name("device")
                .model("model")
                .brand("brand")
                .build();
        Session session = HibernateUtil.getSessionFactory().openSession();
        Long id = (long)session.save(device);
        session.close();
        return id;

    }

    public static Device getDevice(long id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Device device = session.load(Device.class, id);
        return device;
    }

    public static Device getDeviceSecondLevelCache(long id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Device device = session.load(Device.class, id);
        System.out.println(device.getBrand());
        session.close();
        Session session2 = HibernateUtil.getSessionFactory().openSession();
        device = session2.load(Device.class, id);
        System.out.println(device.getBrand());
        session2.close();
        return device;
    }
}
