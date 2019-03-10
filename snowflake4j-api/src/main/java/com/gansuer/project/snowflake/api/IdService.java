package com.gansuer.project.snowflake.api;

import com.gansuer.project.snowflake.api.model.Id;
@SuppressWarnings("unused")
public interface IdService {

    /**
     * 生成Id
     *
     * @return
     */
    Result<Long> generateId();

    /**
     * 解析Id
     *
     * @param id
     * @return
     */
    Result<Id> explainId(long id);

    /**
     * 根据元数据生成Id
     * @param idMeta
     * @return
     */
    Result<Long> makeId(Id idMeta);

}
