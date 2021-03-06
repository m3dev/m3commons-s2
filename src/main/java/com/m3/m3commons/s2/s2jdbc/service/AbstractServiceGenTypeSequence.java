package com.m3.m3commons.s2.s2jdbc.service;

import com.m3.m3commons.s2.s2jdbc.entity.AbstractEntityGenTypeSequence;

import java.util.Calendar;

/**
 * S2JDBCのserviceクラスの基底クラスです。<br>
 * 最低限の検索処理を提供します。<br>
 *
 * @param <ENTITY>
 * @author k-sera
 */
public abstract class AbstractServiceGenTypeSequence<ENTITY> extends
        AbstractService<ENTITY> {

    /**
     * エンティティを挿入します。
     *
     * @param entity エンティティ
     * @return 更新した行数
     */
    @Override
    @SuppressWarnings("unchecked")
    public int insert(ENTITY entity) {
        AbstractEntityGenTypeSequence abstractEntity = (AbstractEntityGenTypeSequence) entity;
        abstractEntity.insertTimestamp = Calendar.getInstance();
        abstractEntity.updateTimestamp = Calendar.getInstance();
        return super.insert((ENTITY) abstractEntity);
    }

    /**
     * エンティティを更新します。
     *
     * @param entity エンティティ
     * @return 更新した行数
     */
    @Override
    @SuppressWarnings("unchecked")
    public int update(ENTITY entity) {
        AbstractEntityGenTypeSequence abstractEntity = (AbstractEntityGenTypeSequence) entity;
        abstractEntity.updateTimestamp = Calendar.getInstance();
        return super.update((ENTITY) abstractEntity);
    }

}