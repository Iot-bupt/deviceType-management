package cn.edu.bupt.controller;

import cn.edu.bupt.entity.DeviceType;
import cn.edu.bupt.entity.DeviceTypeManagement;
import cn.edu.bupt.entity.Manufacturer;
import cn.edu.bupt.entity.Model;
import cn.edu.bupt.service.DTypeManaService;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/devicetypemanagement")
public class DTypeManaController {

    @Autowired
    DTypeManaService dTypeManaService;

    @PreAuthorize("#oauth2.hasScope('all') OR hasPermission(null ,'saveDeviceType')")
    @RequestMapping(value = "/deviceTypeManagement", method = RequestMethod.POST)
    public DeviceTypeManagement saveDeviceType(@RequestBody String deviceTypeManagement){

        JsonObject obj = (JsonObject)new JsonParser().parse(deviceTypeManagement);
        String manufacturerName = obj.has("manufacturerName")?obj.get("manufacturerName").getAsString():null;
        String deviceType = obj.has("deviceType")?obj.get("deviceType").getAsString():null;
        String model = obj.has("model")?obj.get("model").getAsString():null;
        String icon = obj.has("icon")?obj.get("icon").getAsString():null;
        Long limit_lifetime = obj.has("limit_lifetime")?obj.get("limit_lifetime").getAsLong():null;
        if(manufacturerName==null||deviceType==null||model==null){
            return null;
        }
        DeviceTypeManagement dtm = new DeviceTypeManagement(manufacturerName, deviceType, model, icon, limit_lifetime);
        dTypeManaService.addDTMana(dtm);
        return dtm;

    }

    //更新设备型号管理。
    @RequestMapping(value = "/deviceTypeManagement/{modelId}/{deviceTypeId}/{manufacturerId}}", method = RequestMethod.PUT)
    public void updateDeviceType(@PathVariable("modelId") Integer modelId,@PathVariable("deviceTypeId") Integer deviceTypeId,
                                                 @PathVariable("manufacturerId") Integer manufacturerId, @RequestBody String deviceTypeManagement){
        JsonObject obj = (JsonObject)new JsonParser().parse(deviceTypeManagement);
        String manufacturerName = obj.has("manufacturerName")?obj.get("manufacturerName").getAsString():null;
        String deviceType = obj.has("deviceType")?obj.get("deviceType").getAsString():null;
        String model = obj.has("model")?obj.get("model").getAsString():null;
        String icon = obj.has("icon")?obj.get("icon").getAsString():null;
        Long limit_lifetime = obj.has("limit_lifetime")?obj.get("limit_lifetime").getAsLong():null;
        dTypeManaService.updateManufacturer(manufacturerId, manufacturerName);
        dTypeManaService.updateDeviceType(deviceTypeId, deviceType);
        dTypeManaService.updateModel(modelId, model, icon, limit_lifetime);

    }


    @RequestMapping(value = "/deviceTypeManagement/{modelId}", method = RequestMethod.GET)
    public DeviceTypeManagement getDTManaById(@PathVariable("modelId") int modelId){
        return dTypeManaService.getDTManaById(modelId);
    }

    /*@RequestMapping(value = "/deviceTypeManagement/manufacturer/{manufacturerId}",method = RequestMethod.GET )
    public String getManufacturerName(@PathVariable("manufacturerId") int manufacturerId){
        return dTypeManaService.getManuById(manufacturerId).getManufacturerName();
    }

    @RequestMapping(value = "/deviceTypeManagement/deviceType/{deviceTypeId}",method = RequestMethod.GET )
    public String geDeviceTypeName(@PathVariable("deviceTypeId") int deviceTypeId){
        return dTypeManaService.getDeviceTypeById(deviceTypeId).getDeviceTypeName();
    }*/

    @PreAuthorize("#oauth2.hasScope('all') OR hasPermission(null ,'getAllDeviceType')")
    @RequestMapping(value = "/deviceTypeManagement", method = RequestMethod.GET)
    public List<DeviceTypeManagement> getAllDeviceType(){
        return dTypeManaService.getAllDTMana();
    }

    @PreAuthorize("#oauth2.hasScope('all') OR hasPermission(null ,'deleteDeviceType')")
    @RequestMapping(value = "/deviceTypeManagement",method = RequestMethod.DELETE)
    public void deleteDeviceType(@RequestParam int modelId){
        dTypeManaService.deleteDTMana(modelId);
    }

    @PreAuthorize("#oauth2.hasScope('all') OR hasPermission(null ,'getManufacturers')")
    @RequestMapping(value = "/deviceTypeManagement/manufactures", method = RequestMethod.GET)
    public List<Manufacturer> getManufacturers(@RequestParam(required = false) String keyword){
        return dTypeManaService.getManufacturersByKeyWords(keyword);
    }

    @PreAuthorize("#oauth2.hasScope('all') OR hasPermission(null ,'getDeviceTypes')")
    @RequestMapping(value = "/deviceTypeManagement/deviceTypes", method = RequestMethod.GET)
    public List<DeviceType> getDeviceTypes(@RequestParam int manufacturerId, @RequestParam(required = false) String keyword){
        return dTypeManaService.getDeviceTypesByKeyWords(manufacturerId,keyword);
    }

    @PreAuthorize("#oauth2.hasScope('all') OR hasPermission(null ,'getModels')")
    @RequestMapping(value = "/deviceTypeManagement/models", method = RequestMethod.GET)
    public List<Model> getModels(@RequestParam int manufacturerId, @RequestParam int deviceTypeId, @RequestParam(required = false)  String keyword){
        return dTypeManaService.getModelsByKeyWords(manufacturerId,deviceTypeId,keyword);
    }
}
