package cn.edu.bupt.service;

import cn.edu.bupt.entity.DeviceType;
import cn.edu.bupt.entity.DeviceTypeManagement;
import cn.edu.bupt.entity.Manufacturer;
import cn.edu.bupt.entity.Model;

import java.util.List;

public interface DTypeManaService {
    public void addDTMana(DeviceTypeManagement deviceTypeManagement);
    public void deleteDTMana(int modelId);
    public List<DeviceTypeManagement> getAllDTMana();
    public List<Manufacturer> getManufacturersByKeyWords(String keyWords);
    public List<DeviceType> getDeviceTypesByKeyWords(int mid, String keyWords);
    public List<Model> getModelsByKeyWords(int mid, int did, String keyWords);
}
