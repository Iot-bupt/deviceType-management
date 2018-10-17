package cn.edu.bupt.mapper;


import cn.edu.bupt.entity.DeviceType;
import org.apache.ibatis.annotations.*;

import java.util.List;


@Mapper

public interface DeviceTypeMapper {
    @Select("select  device_type_id  as deviceTypeId,manufacturer_id as  manufacturerId,device_type_name as deviceTypeName  from device_type where manufacturer_id = #{id} and device_type_name=#{name}")
    DeviceType findByMidAndName(@Param("id")int id, @Param("name")String name);

    @Select("select  device_type_id  as deviceTypeId,manufacturer_id as  manufacturerId,device_type_name as deviceTypeName  from device_type where device_type_id=#{did}")
    DeviceType findByDeviceTypeId(int dId);

    @Insert("insert into device_type (manufacturer_id,device_type_name) values (#{manufacturerId},#{deviceTypeName}) ")
    @Options(useGeneratedKeys = true, keyProperty = "deviceTypeId")
    void insert(DeviceType deviceType);

    @Select("select  device_type_id  as deviceTypeId,manufacturer_id as  manufacturerId,device_type_name as deviceTypeName  from device_type where manufacturer_id = #{id}")
    List<DeviceType> findAll(int id);

    @Select("select  device_type_id  as deviceTypeId,manufacturer_id as  manufacturerId,device_type_name as deviceTypeName  from device_type where manufacturer_id = #{id} and device_type_name like CONCAT(CONCAT('%', #{keyWord}), '%')")
    List<DeviceType> findAllByKeyWord(@Param("id")int id, @Param("keyWord")String keyWord);

    @Delete("delete from device_type where device_type_id = #{deviceTypeId}")
    void delete(int deviceTypeId);

    @Update("UPDATE device_type SET device_type_name = #{deviceTypeName} WHERE device_type_id = #{deviceTypeId}")
    void update(@Param("deviceTypeId") Integer deviceTypeId, @Param("deviceTypeName") String deviceTypeName);
}
