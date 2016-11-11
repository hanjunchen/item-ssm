package com.hsgene.common;

import java.util.List;

/**
 * Created by hjc on 2016/10/15.
 */
public abstract class BaseService<T> {

    protected abstract List<T> findList(T t);

    protected abstract T getById(String id);

    protected abstract int insert(T t);

    protected abstract int update(T t);

    protected abstract int delete(T t);
}
