package cn.los.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Proxy;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


@Entity
@Table(name = "sys_dict")
@JsonIgnoreProperties(ignoreUnknown = true)
@Proxy(lazy = false)
public class DictEntity {
    
    @Id
    private String dictValue;
    private String dictName;
    
    
    


}
