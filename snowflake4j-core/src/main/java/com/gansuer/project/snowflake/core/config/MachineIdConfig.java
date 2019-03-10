package com.gansuer.project.snowflake.core.config;

import com.gansuer.project.snowflake.core.constant.SnowflakeConst;
import lombok.Data;

@Data
public class MachineIdConfig {

    private long dcBits = SnowflakeConst.DEFAULT_DC_BITS;

    private long workerIdBits = SnowflakeConst.DEFAULT_WORKER_BITS;

    /**
     * dataCenter的最大值
     *
     * @return
     */
    private long getMaxDcId() {
        return SnowflakeConst.getMask(dcBits);
    }

    /**
     * workerId的最大值
     *
     * @return
     */
    private long getMaxWorkerId() {
        return SnowflakeConst.getMask(workerIdBits);
    }
}
