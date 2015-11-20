package com.m3.m3commons.s2.s2jdbc.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Calendar;

/**
 * entityクラスの基底クラスです。 <br>
 * memcachedでキャッシュ可能とするためにデフォルトでSerializableをimplしています。<br>
 * <br>
 * 人工キーのIDはGenerationType.SEQUENCEで生成します。
 *
 * @author k-sera
 */
@MappedSuperclass
public abstract class AbstractEntityGenTypeSequence implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * IDプロパティ（人工キーのPK）
     */
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(precision = 10, nullable = false, unique = true)
    public Long id;

    /**
     * 登録日時
     */
    @Column(nullable = false, unique = false)
    @Temporal(TemporalType.TIMESTAMP)
    public Calendar insertTimestamp = Calendar.getInstance();

    /**
     * 更新日時
     */
    @Column(nullable = true, unique = false)
    @Temporal(TemporalType.TIMESTAMP)
    public Calendar updateTimestamp;

}
