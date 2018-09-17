package cn.edu.bupt.entity;

import lombok.Data;

@Data
public class Model {
    private int modelId;
    private int manufacturerId;
    private int deviceTypeId;
    private String modelName;
    private String deviceIcon;
    private Long limitLifetime;
}
