package com.gansuer.project.snowflake.core.extention;

import com.gansuer.project.snowflake.core.config.MachineIdConfig;
import com.gansuer.project.snowflake.core.extention.model.MachineId;

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
