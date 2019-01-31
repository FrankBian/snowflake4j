package com.gansuer.project.snowflake.api;

import com.gansuer.project.snowflake.api.model.Id;
@SuppressWarnings("unused")
public interface IdService {

    /**
     * 生成Id
     *
     * @return
     */
    long generateId();

    /**
     * 解析Id
     *
     * @param id
     * @return
     */
    Id explainId(long id);

    /**
     * 根据元数据生成Id
     * @param idMeta
     * @return
     */
    long makeId(Id idMeta);

}
