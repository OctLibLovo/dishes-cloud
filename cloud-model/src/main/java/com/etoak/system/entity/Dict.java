package com.etoak.system.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("t_dict")
public class Dict {

    @TableId(type= IdType.AUTO)
    private Integer id;

    private String type;

    private String label;

    private Integer sort;
}
