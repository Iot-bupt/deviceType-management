package cn.edu.bupt.entity;

import lombok.Getter;
import lombok.Setter;

public class DeviceTypeManagement {

    @Getter
    @Setter
    private Manufacturer manufacturer;
    @Getter @Setter
    private DeviceType deviceType;
    @Getter @Setter
    private Model model;

    public DeviceTypeManagement(String manufacturerName, String deviceTypeName, String modelName){
        this.manufacturer = new Manufacturer();
        manufacturer.setManufacturerName(manufacturerName);
        this.deviceType = new DeviceType();
        deviceType.setDeviceTypeName(deviceTypeName);
        this.model = new Model();
        model.setModelName(modelName);
    }

    public DeviceTypeManagement(){

    }

}
