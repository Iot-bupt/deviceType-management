package cn.edu.bupt.entity;

import lombok.Data;

@Data
public class Model {
    private int modelId;
    private int manufacturerId;
    private int deviceTypeId;
    private String modelName;
}
