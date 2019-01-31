package com.gansuer.project.snowflake.core.support;

import com.gansuer.project.snowflake.api.model.Id;
import com.gansuer.project.snowflake.core.WorkerConfig;
import com.gansuer.project.snowflake.core.constant.SnowflakeConst;
import com.gansuer.project.snowflake.core.exception.ErrorCode;
import com.gansuer.project.snowflake.core.exception.SnowflakeException;
import lombok.NonNull;

public final class Precondition {

    public static void preconditionConfig(@NonNull WorkerConfig config) {
        if (SnowflakeConst.MACHINE_BITS != config.getDataCenterBits() + config.getWorkerBits()) {
            throw new SnowflakeException(
                String.format("worker config error: the sum of dataCenterBits and workerBits must equal 10," +
                    " but actual sum is %s", config.getDataCenterBits() + config.getWorkerBits()));
        }

        long dcMask = SnowflakeConst.getMask(config.getDataCenterBits());
        long workerMask = SnowflakeConst.getMask(config.getWorkerBits());

        if (config.getDataCenterId() < 0 || config.getDataCenterId() > dcMask) {
            throw new SnowflakeException(
                String.format("worker config error: dataCenterId must between 0 and %s, " +
                    "actual value is %s", dcMask, config.getDataCenterId()));
        }
        if (config.getWorkerId() < 0 || config.getWorkerId() > workerMask) {
            throw new SnowflakeException(
                String.format("worker config error: workerId must between 0 and %s, " +
                    "actual value is %s", workerMask, config.getWorkerId()));
        }
    }

    public static void preconditionMeta(@NonNull Id meta) {
        requireNonNull(meta.getDataCenterId(), SnowflakeConst.DC_NAME);
        requireNonNull(meta.getWorkerId(),SnowflakeConst.WORKER_NAME);
        requireNonNull(meta.getTimestamp(),SnowflakeConst.TIME_NAME);
        requireNonNull(meta.getSequence(),SnowflakeConst.SEQ_NAME);
        validateValue(SnowflakeConst.SEQ_NAME, meta.getSequence(), SnowflakeConst.MIN, SnowflakeConst.SEQ_MASK);
        validateValue(SnowflakeConst.TIME_NAME, meta.getTimestamp(), SnowflakeConst.START_TIMESTAMP, System.currentTimeMillis());
        validateValue(SnowflakeConst.WORKER_NAME, meta.getWorkerId(), SnowflakeConst.MIN, SnowflakeConst.WORKER_MASK);
        validateValue(SnowflakeConst.DC_NAME, meta.getDataCenterId(), SnowflakeConst.MIN, SnowflakeConst.DC_MASK);
    }

    public static void preconditionDataCenterId(Long dateCenterId) {
        requireNonNull(dateCenterId,SnowflakeConst.DC_NAME);
        validateValue(SnowflakeConst.DC_NAME, dateCenterId, SnowflakeConst.MIN, SnowflakeConst.DC_MASK);
    }

    public static void preconditionWorkerId(Long workerId) {
        requireNonNull(workerId,SnowflakeConst.WORKER_NAME);
        validateValue(SnowflakeConst.WORKER_NAME, workerId, SnowflakeConst.MIN, SnowflakeConst.WORKER_MASK);
    }

    private static void validateValue(String name, long value, long min, long max) {
        if (value < min || value > max) {
            throw new SnowflakeException(
                String.format("%s should between %s and %s,but actual value is %s",
                    name, min, max, value), ErrorCode.ILLEGAL_PARAMS);
        }
    }

    private static <T> void requireNonNull(T obj, String name) {
        if (obj == null) {
            throw new SnowflakeException(String.format("%s should not be null", name));
        }
    }

}
