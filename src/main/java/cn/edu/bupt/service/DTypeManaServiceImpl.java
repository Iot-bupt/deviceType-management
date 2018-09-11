package cn.edu.bupt.service;


import cn.edu.bupt.entity.DeviceType;
import cn.edu.bupt.entity.DeviceTypeManagement;
import cn.edu.bupt.entity.Manufacturer;
import cn.edu.bupt.entity.Model;
import cn.edu.bupt.mapper.DeviceTypeMapper;
import cn.edu.bupt.mapper.ManufacturerMapper;
import cn.edu.bupt.mapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by Administrator on 2018/4/26.
 */
@Service
public class DTypeManaServiceImpl implements DTypeManaService  {
    @Autowired
    ManufacturerMapper manufacturerMapper;

    @Autowired
    DeviceTypeMapper deviceTypeMapper;

    @Autowired
    ModelMapper modelMapper;


    @Override
    @Transactional
    public void addDTMana(DeviceTypeManagement deviceTypeManagement) {
        Manufacturer manufacturer = deviceTypeManagement.getManufacturer();
        DeviceType deviceType = deviceTypeManagement.getDeviceType();
        Model model = deviceTypeManagement.getModel();

        Manufacturer m = manufacturerMapper.findManufactureByName(manufacturer.getManufacturerName());
        if(m==null){
            manufacturerMapper.insert(manufacturer);
        }else{
            manufacturer = m;
        }

        DeviceType d = deviceTypeMapper.findByMidAndName(manufacturer.getManufacturerId(),deviceType.getDeviceTypeName());
        if(d==null){
            deviceType.setManufacturerId(manufacturer.getManufacturerId());
            deviceTypeMapper.insert(deviceType);
        }else{
            deviceType = d;
        }
        Model mo = modelMapper.findByMIdAndDIdAndName(manufacturer.getManufacturerId(),deviceType.getDeviceTypeId(),model.getModelName());
        if(mo==null){
            model.setManufacturerId(manufacturer.getManufacturerId());
            model.setDeviceTypeId(deviceType.getDeviceTypeId());
            modelMapper.insert(model);
        }else{
            model = mo;
        }
    }

    @Override
    public List<DeviceTypeManagement> getAllDTMana() {
        List<Model> ls = modelMapper.find();
        List<DeviceTypeManagement> res = new LinkedList<>();
        ls.forEach(model -> {
            int mid =  model.getManufacturerId();
            int did = model.getDeviceTypeId();
            DeviceTypeManagement ag = new DeviceTypeManagement();
            ag.setManufacturer(manufacturerMapper.findManufacturerById(mid));
            ag.setDeviceType(deviceTypeMapper.findByDeviceTypeId(did));
            ag.setModel(model);
            res.add(ag);
        });
        return res;
    }

    @Override
    public void deleteDTMana(int modelId) {
        modelMapper.delete(modelId);
    }

    @Override
    public List<Manufacturer> getManufacturersByKeyWords(String keyWords) {
        if(keyWords==null||"".equals(keyWords)){
            return  manufacturerMapper.findAll();
        }else{
            return manufacturerMapper.findByKeyWord(keyWords);
        }
    }

    @Override
    public List<DeviceType> getDeviceTypesByKeyWords(int mId, String keyWords) {
        if(keyWords==null||"".equals(keyWords)){
            return deviceTypeMapper.findAll(mId);
        }else{
            return deviceTypeMapper.findAllByKeyWord(mId,keyWords);
        }
    }

    @Override
    public List<Model> getModelsByKeyWords(int mId,int dId,String keyWords) {
        if(keyWords==null||"".equals(keyWords)){
            return modelMapper.findAll(mId,dId);
        }else{
            return modelMapper.findAllByKeyWord(mId,dId,keyWords);
        }
    }
}
