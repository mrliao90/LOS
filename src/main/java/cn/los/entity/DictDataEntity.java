package cn.los.entity;

import javax.persistence.Id;

import cn.los.common.util.SnowFlakeUtil;

public class DictDataEntity {
    @Id
    private String id = String.valueOf(SnowFlakeUtil.getFlowIdInstance().nextId());
    
    private String dictValue;
    
    private String dictDataName;
    
    private String dictDataValue ;
    
    private Integer isfixed;//是否固定   0默认为不固定，1固定；固定就不能再去修改了。 
}
