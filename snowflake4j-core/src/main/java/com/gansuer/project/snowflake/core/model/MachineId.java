package com.gansuer.project.snowflake.core.model;

import java.io.Serializable;
import lombok.Data;

@Data
public class MachineId implements Serializable {
    private static final long serialVersionUID = 5817271387209185712L;

    private Long dataCenterId;

    private Long workerId;
}
