package com.gansuer.project.snowflake.core;

import com.gansuer.project.snowflake.api.IdService;
import org.springframework.beans.factory.FactoryBean;

public class IdServiceFactory implements FactoryBean<IdService> {


    private void init(){

    }

    @Override public IdService getObject() throws Exception {
        return null;
    }

    @Override public Class<?> getObjectType() {
        return IdService.class;
    }

    @Override public boolean isSingleton() {
        return true;
    }
}
