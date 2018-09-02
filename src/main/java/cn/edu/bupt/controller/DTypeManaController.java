package cn.edu.bupt.controller;

import cn.edu.bupt.entity.DeviceType;
import cn.edu.bupt.entity.DeviceTypeManagement;
import cn.edu.bupt.entity.Manufacturer;
import cn.edu.bupt.entity.Model;
import cn.edu.bupt.service.DTypeManaService;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/devicetypemanagement")
public class DTypeManaController {

    @Autowired
    DTypeManaService dTypeManaService;

    @RequestMapping(value = "/deviceTypeManagement", method = RequestMethod.POST)
    public DeviceTypeManagement save(@RequestBody String deviceTypeManagement){

        JsonObject obj = (JsonObject)new JsonParser().parse(deviceTypeManagement);
        String manufacturerName = obj.has("manufacturerName")?obj.get("manufacturerName").getAsString():null;
        String deviceType = obj.has("deviceType")?obj.get("deviceType").getAsString():null;
        String model = obj.has("model")?obj.get("model").getAsString():null;
        if(manufacturerName==null||deviceType==null||model==null){
            return null;
        }
        DeviceTypeManagement dtm = new DeviceTypeManagement(manufacturerName, deviceType, model);
        dTypeManaService.addDTMana(dtm);
        return dtm;

    }

    @RequestMapping(value = "/deviceTypeManagement", method = RequestMethod.GET)
    public List<DeviceTypeManagement> getAll(){
        return dTypeManaService.getAllDTMana();
    }

    @RequestMapping(value = "/deviceTypeManagement",method = RequestMethod.DELETE)
    public void delete(@RequestParam int modelId){
        dTypeManaService.deleteDTMana(modelId);
    }

    @RequestMapping(value = "/deviceTypeManagement/manufactures", method = RequestMethod.GET)
    public List<Manufacturer> getManufacturers(@RequestParam(required = false) String keyword){
        return dTypeManaService.getManufacturersByKeyWords(keyword);
    }

    @RequestMapping(value = "/deviceTypeManagement/deviceTypes", method = RequestMethod.GET)
    public List<DeviceType> getDeviceTypes(@RequestParam int manufacturerId, @RequestParam(required = false) String keyword){
        return dTypeManaService.getDeviceTypesByKeyWords(manufacturerId,keyword);
    }

    @RequestMapping(value = "/deviceTypeManagement/models", method = RequestMethod.GET)
    public List<Model> getModels(@RequestParam int manufacturerId, @RequestParam int deviceTypeId, @RequestParam(required = false)  String keyword){
        return dTypeManaService.getModelsByKeyWords(manufacturerId,deviceTypeId,keyword);
    }
}
