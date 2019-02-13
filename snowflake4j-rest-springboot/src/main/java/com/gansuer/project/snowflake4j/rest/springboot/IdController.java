package com.gansuer.project.snowflake4j.rest.springboot;

import com.gansuer.project.snowflake.api.model.Id;
import com.gansuer.project.snowflake.core.IdConverter;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class IdController {

    @RequestMapping("/genId")
    public long genId() {
        return 0L;
    }

    @RequestMapping("/expId")
    public Id expId(@RequestParam long id) {
        return IdConverter.convert(id);
    }

    @RequestMapping("/makeId")
    public long makeId(@RequestParam("time") long timestamp,
        @RequestParam("dcId") long dc,
        @RequestParam("workerId") long worker,
        @RequestParam("seq") long seq) {

        return IdConverter.convert(new Id().setTimestamp(timestamp)
            .setDataCenterId(dc).setWorkerId(worker).setSequence(seq));
    }
}
