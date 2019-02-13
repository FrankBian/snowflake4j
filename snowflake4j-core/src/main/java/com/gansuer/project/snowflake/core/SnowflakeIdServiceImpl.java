package com.gansuer.project.snowflake.core;

import com.gansuer.project.snowflake.api.IdService;
import com.gansuer.project.snowflake.api.model.Id;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@SuppressWarnings("unused")
public class SnowflakeIdServiceImpl implements IdService {

    private IdGenerator idGenerator;

    public SnowflakeIdServiceImpl(IdGenerator idGenerator) {
        this.idGenerator = idGenerator;
    }

    public long generateId() {
        return idGenerator.nextId();
    }

    public Id explainId(long id) {

        return IdConverter.convert(id);
    }

    public long makeId(Id idMeta) {

        return IdConverter.convert(idMeta);
    }
}
