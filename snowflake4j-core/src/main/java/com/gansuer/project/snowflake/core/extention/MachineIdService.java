package com.gansuer.project.snowflake.core.extention;

import com.gansuer.project.snowflake.core.extention.model.MachineId;

/**
 * 扩展点
 */
public interface MachineIdService {

    /**
     * 获取当前机器的MachineId
     *
     * @return
     */
    MachineId getMachineId();
}
