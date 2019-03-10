package com.gansuer.project.snowflake.core;

import com.gansuer.project.snowflake.api.IdService;
import com.gansuer.project.snowflake.api.Result;
import com.gansuer.project.snowflake.api.model.Id;

@SuppressWarnings("unused")
public class SnowflakeIdServiceImpl implements IdService {

    private IdGenerator idGenerator;

    public SnowflakeIdServiceImpl(IdGenerator idGenerator) {
        this.idGenerator = idGenerator;
    }

    public Result<Long> generateId() {
        return Result.succeedWith(idGenerator.nextId());
    }

    public Result<Id> explainId(long id) {

        return Result.succeedWith(IdConverter.convert(id));
    }

    public Result<Long> makeId(Id idMeta) {

        return Result.succeedWith(IdConverter.convert(idMeta));
    }
}
