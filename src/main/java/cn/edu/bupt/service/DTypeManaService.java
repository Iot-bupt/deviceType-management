package cn.edu.bupt.service;

import cn.edu.bupt.entity.DeviceType;
import cn.edu.bupt.entity.DeviceTypeManagement;
import cn.edu.bupt.entity.Manufacturer;
import cn.edu.bupt.entity.Model;

import java.util.List;

public interface DTypeManaService {
    public Model findById(int modelId);
    public Manufacturer findManufactureByName(String name);
    public DeviceType findByMidAndName(int manufacturerId, String name);
    public void updateManufacturer(int manufacturerId, String manufacturerName);
    public void updateDeviceType(int deviceTypeId, String deviceTypeName);
    public void updateModel(int modelId, String modelName, String deviceIcon, Long limitLifetime);
    public void addDTMana(DeviceTypeManagement deviceTypeManagement);
    public void deleteDTMana(int modelId);
    public List<DeviceTypeManagement> getAllDTMana();
    public List<Manufacturer> getManufacturersByKeyWords(String keyWords);
    public List<DeviceType> getDeviceTypesByKeyWords(int mid, String keyWords);
    public List<Model> getModelsByKeyWords(int mid, int did, String keyWords);
    public Manufacturer getManuById(int manufacturerId);
    public DeviceType getDeviceTypeById(int deviceTypeId);
    public DeviceTypeManagement getDTManaById(int modelId);

}
