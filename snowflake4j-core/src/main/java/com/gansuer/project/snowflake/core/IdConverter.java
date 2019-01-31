package com.gansuer.project.snowflake.core;

import com.gansuer.project.snowflake.api.model.Id;
import com.gansuer.project.snowflake.core.constant.SnowflakeConst;

public final class IdConverter {

    public static long convert(Id idMeta) {
        return ((idMeta.getTimestamp() - SnowflakeConst.START_TIMESTAMP) << SnowflakeConst.TIME_SHIFT) |
            (idMeta.getDataCenterId() << SnowflakeConst.DEFAULT_DC_SHIFT) |
            (idMeta.getWorkerId() << SnowflakeConst.WORK_SHIFT) |
            idMeta.getSequence();
    }

    public static Id convert(long id) {

        return new Id().setSequence(id & SnowflakeConst.SEQ_MASK)
            .setWorkerId((id >>> SnowflakeConst.WORK_SHIFT) & SnowflakeConst.WORKER_MASK)
            .setDataCenterId((id >>> SnowflakeConst.DEFAULT_DC_SHIFT) & SnowflakeConst.DC_MASK)
            .setTimestamp(((id >>> SnowflakeConst.TIME_SHIFT) & SnowflakeConst.TIME_MASK)
                + SnowflakeConst.START_TIMESTAMP);
    }
}
