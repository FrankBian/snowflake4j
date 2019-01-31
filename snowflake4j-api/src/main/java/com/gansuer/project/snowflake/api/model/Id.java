package com.gansuer.project.snowflake.api.model;

import java.io.Serializable;
import lombok.NonNull;

/**
 * Id包含的元数据信息
 */
public class Id implements Serializable {

    private static final long serialVersionUID = -2063118681923875328L;
    /**
     * 时间戳
     */
    @NonNull
    private Long timestamp;

    /**
     * 数据中心Id
     */
    @NonNull
    private Long dataCenterId;

    /**
     * 机器Id
     */
    @NonNull
    private Long workerId;

    /**
     * 序列号
     */
    @NonNull
    private Long sequence;

    public Id() {
    }

    public Long getTimestamp() {
        return timestamp;
    }

    public Id setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
        return this;
    }

    public Long getDataCenterId() {
        return dataCenterId;
    }

    public Id setDataCenterId(Long dataCenterId) {
        this.dataCenterId = dataCenterId;
        return this;
    }

    public Long getWorkerId() {
        return workerId;
    }

    public Id setWorkerId(Long workerId) {
        this.workerId = workerId;
        return this;
    }

    public Long getSequence() {
        return sequence;
    }

    public Id setSequence(Long sequence) {
        this.sequence = sequence;
        return this;
    }

    @Override
    public String toString() {
        return "Id{" +
            "timestamp=" + timestamp +
            ", dataCenterId=" + dataCenterId +
            ", workerId=" + workerId +
            ", sequence=" + sequence +
            '}';
    }

    @Override public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;

        Id id = (Id) o;

        if (!timestamp.equals(id.timestamp))
            return false;
        if (!dataCenterId.equals(id.dataCenterId))
            return false;
        if (!workerId.equals(id.workerId))
            return false;
        return sequence.equals(id.sequence);
    }

    @Override public int hashCode() {
        int result = timestamp.hashCode();
        result = 31 * result + dataCenterId.hashCode();
        result = 31 * result + workerId.hashCode();
        result = 31 * result + sequence.hashCode();
        return result;
    }
}
