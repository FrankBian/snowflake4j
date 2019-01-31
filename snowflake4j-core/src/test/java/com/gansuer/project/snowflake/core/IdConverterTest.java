package com.gansuer.project.snowflake.core;

import com.gansuer.project.snowflake.api.model.Id;
import com.gansuer.project.snowflake.core.constant.SnowflakeConst;
import java.util.Calendar;
import java.util.Date;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class IdConverterTest {

    @Test
    public void convert() throws Exception {

        Id meta = new Id();
        meta.setTimestamp(System.currentTimeMillis())
            .setWorkerId(SnowflakeConst.DEFAULT_WORKER_ID)
            .setDataCenterId(SnowflakeConst.DEFAULT_DC_ID)
            .setSequence(0L);

        long id1 = IdConverter.convert(meta);

        Id expMeta = IdConverter.convert(id1);

        System.out.println("meta="+meta);
        System.out.println("id="+id1);
        Assert.assertEquals(meta,expMeta);

    }

    @Test
    public void translateTimestamp() throws Exception {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        long actualTimestamp = calendar.getTimeInMillis();
        long offsetTimestamp = actualTimestamp - SnowflakeConst.START_TIMESTAMP;

        Id id1 = new Id().setWorkerId(SnowflakeConst.DEFAULT_WORKER_ID)
            .setDataCenterId(SnowflakeConst.DEFAULT_DC_ID)
            .setTimestamp(actualTimestamp)
            .setSequence(0L);
        Id id2 = new Id().setWorkerId(SnowflakeConst.DEFAULT_WORKER_ID)
            .setDataCenterId(SnowflakeConst.DEFAULT_DC_ID)
            .setTimestamp(offsetTimestamp)
            .setSequence(0L);

        System.out.println(String.format("id1=%s,idValue=%s",id1,IdConverter.convert(id1)));
        System.out.println(String.format("id2=%s,idValue=%s",id2,IdConverter.convert(id2)));
    }
}