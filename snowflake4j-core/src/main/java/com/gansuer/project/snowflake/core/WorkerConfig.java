package com.gansuer.project.snowflake.core;

import lombok.Data;

import static com.gansuer.project.snowflake.core.constant.SnowflakeConst.DEFAULT_DC_BITS;
import static com.gansuer.project.snowflake.core.constant.SnowflakeConst.DEFAULT_DC_ID;
import static com.gansuer.project.snowflake.core.constant.SnowflakeConst.DEFAULT_WORKER_BITS;
import static com.gansuer.project.snowflake.core.constant.SnowflakeConst.DEFAULT_WORKER_ID;

@Data
public class WorkerConfig {

    /**
     * 数据中心id
     */
    private long dataCenterId = DEFAULT_DC_ID;

    /**
     * 实例id
     */
    private long workerId = DEFAULT_WORKER_ID;

    /**
     * 数据中心bit位
     */
    private long dataCenterBits = DEFAULT_DC_BITS;

    /**
     * 实例id bit位
     */
    private long workerBits = DEFAULT_WORKER_BITS;
}
