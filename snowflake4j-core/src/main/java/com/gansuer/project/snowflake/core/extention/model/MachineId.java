package com.gansuer.project.snowflake.core.extention.model;

import lombok.Data;

@Data
public class MachineId {

    private Long dataCenterId;

    private Long workerId;
}
