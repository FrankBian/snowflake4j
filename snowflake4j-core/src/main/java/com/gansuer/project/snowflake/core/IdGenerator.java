package com.gansuer.project.snowflake.core;

import com.gansuer.project.snowflake.api.model.Id;
import com.gansuer.project.snowflake.core.constant.SnowflakeConst;
import com.gansuer.project.snowflake.core.exception.SnowflakeException;
import com.gansuer.project.snowflake.core.extention.model.MachineId;
import java.util.Objects;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class IdGenerator {

    public static IdGenerator INSTANCE = null;

    private long lastTimestamp = -1L;

    private long sequence = 0L;

    private final MachineId machineId;

    private IdGenerator(MachineId machineId) {
        log.warn("idGenerator initiate begin");
//        Precondition.preconditionDataCenterId(dataCenterId);
//        Precondition.preconditionWorkerId(workerId);
        this.machineId = machineId;
    }

    public static IdGenerator getInstance(final MachineId machineId) {
        if (Objects.isNull(INSTANCE)) {
            synchronized (IdGenerator.class) {
                if (Objects.isNull(INSTANCE)) {
                    INSTANCE = new IdGenerator(machineId);
                }
            }
        }
        return INSTANCE;

    }

    public long nextId() {
        long timestamp = genTime();

        if (timestamp <= lastTimestamp) {
            log.error("clock is moving backwards.  Rejecting requests until %d.", lastTimestamp);
            throw new SnowflakeException(String.format("Clock moved backwards.  Refusing to generate id for %d milliseconds", lastTimestamp - timestamp), 0);
        }

        if (timestamp == lastTimestamp) {
            sequence = (sequence + 1) & SnowflakeConst.SEQ_MASK;
            if (sequence == 0) {
                timestamp = tilNextMills(lastTimestamp);
            }
        } else {
            sequence = 0;
        }
        lastTimestamp = timestamp;

        return IdConverter.convert(new Id()
            .setDataCenterId(machineId.getDataCenterId())
            .setWorkerId(machineId.getWorkerId())
            .setTimestamp(lastTimestamp)
            .setSequence(sequence));
    }

    public long getLastTimestamp() {
        return lastTimestamp;
    }

    public long getSequence() {
        return sequence;
    }

    private long genTime() {
        return System.currentTimeMillis();
    }

    /**
     * 无锁自旋
     *
     * @param lastTimestamp
     * @return
     */
    private long tilNextMills(long lastTimestamp) {
        long timestamp = genTime();
        while (timestamp <= lastTimestamp) {
            timestamp = genTime();
        }
        return timestamp;
    }

}
