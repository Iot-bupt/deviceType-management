package cn.edu.bupt.entity;

import lombok.Data;

@Data
public class DeviceType {
    private int deviceTypeId;
    private int manufacturerId;
    private String deviceTypeName;
}
