package com.gansuer.project.snowflake.core.extention;

import com.gansuer.project.snowflake.core.model.MachineId;
import com.gansuer.project.snowflake.core.model.MachineIdConfig;

/**
 * 扩展点
 */
public interface MachineIdService {

    /**
     * @param config
     * @return
     */
    MachineId getMachineId(MachineIdConfig config);
}
