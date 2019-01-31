package com.gansuer.project.snowflake.core.constant;

public final class SnowflakeConst {

    public static final long MASK = -1L;
    public static final long MIN = 0L;

    public static final String SEQ_NAME = "sequence";
    public static final String DC_NAME = "dataCenterId";
    public static final String WORKER_NAME = "workerId";
    public static final String TIME_NAME = "timestamp";
    /**
     * 开始时间：2019-01-01 00:00:00
     */
    public static final long START_TIMESTAMP = 1546272000000L;

    public static final long DEFAULT_DC_ID = 1L << 4;
    public static final long DEFAULT_WORKER_ID = 1L << 4;

    /**
     * 默认bit位:seq,workerId,dcId,timestamp
     */
    public static final long SEQ_BITS = 12L;
    public static final long DEFAULT_WORKER_BITS = 5L;
    public static final long DEFAULT_DC_BITS = 5L;
    public static final long TIME_BITS = 41L;

    /**
     * 机器的唯一标示的bit位数:workerId+dataCenterId
     */
    public static final long MACHINE_BITS = 10L;

    /**
     * 每个组成部分的默认偏移量：workerId,dataCenterId,timestamp
     */
    public static final long WORK_SHIFT = SEQ_BITS;
    public static final long DEFAULT_DC_SHIFT = WORK_SHIFT + DEFAULT_WORKER_BITS;
    public static final long TIME_SHIFT = SEQ_BITS + MACHINE_BITS;

    /**
     * 每个组成部分的掩码(最大值)：seq,workerId,dataCenterId,timestamp
     */
    public static final long SEQ_MASK = MASK ^ (MASK << SEQ_BITS);
    public static final long WORKER_MASK = MASK ^ (MASK << DEFAULT_WORKER_BITS);
    public static final long DC_MASK = MASK ^ (MASK << DEFAULT_DC_BITS);
    public static final long TIME_MASK = MASK ^ (MASK << TIME_BITS);

    /**
     * 根据bit位生成掩码 eg: bit=5，掩码为11111
     *
     * @param bits
     * @return
     */
    public static long getMask(long bits) {
        return MASK ^ (MASK << bits);
    }
}
