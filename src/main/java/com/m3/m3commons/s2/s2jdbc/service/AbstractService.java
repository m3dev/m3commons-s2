package com.m3.m3commons.s2.s2jdbc.service;

import com.m3.m3commons.s2.util.S2AOPUtil;
import org.apache.commons.lang.StringUtils;
import org.seasar.extension.jdbc.JdbcManager;
import org.seasar.extension.jdbc.name.PropertyName;
import org.seasar.extension.jdbc.service.S2AbstractService;
import org.seasar.extension.jdbc.where.SimpleWhere;
import org.seasar.framework.util.StringUtil;

import java.util.List;

import static org.seasar.extension.jdbc.operation.Operations.*;

/**
 * 基底抽象サービスクラス
 *
 * @param <ENTITY>
 * @author k-sera
 */
public abstract class AbstractService<ENTITY> extends S2AbstractService<ENTITY> {

    public JdbcManager getJdbcManager() {
        return jdbcManager;
    }

    public void setJdbcManager(JdbcManager jdbcManager) {
        this.jdbcManager = jdbcManager;
    }

    /**
     * IDで検索した結果を返却します。
     *
     * @param id 識別子
     * @return エンティティ
     */
    public ENTITY findById(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("検索条件のIDがnullになっています。");
        } else {
            return select()
                    .where(new SimpleWhere().eq(new PropertyName<Long>("id"), id))
                    .getSingleResult();
        }
    }

    /**
     * for updateでロックをかけた状態でIDで検索した結果を返却します。
     *
     * @param id 識別子
     * @return ロックをかけたエンティティ
     */
    public ENTITY findByIdForUpdate(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("検索条件のIDがnullになっています。");
        } else {
            return select()
                    .where(new SimpleWhere().eq(new PropertyName<Long>("id"), id))
                    .forUpdate()
                    .getSingleResult();
        }
    }

    /**
     * 全件の件数を取得して返却します。
     *
     * @return　件数
     */
    public long countAll() {
        return select().getCount();
    }

    /**
     * propertyNameの昇順で全件取得して返却します。
     *
     * @param propertyName プロパティ名
     * @return 検索結果
     */
    public List<ENTITY> findAllOrderByAsc(PropertyName<?> propertyName) {
        return select()
                .orderBy(asc(propertyName))
                .getResultList();
    }

    /**
     * propertyNameの降順で全件取得して返却します。
     *
     * @param propertyName プロパティ名
     * @return 検索結果
     */
    public List<ENTITY> findAllOrderByDesc(PropertyName<?> propertyName) {
        return select()
                .orderBy(desc(propertyName))
                .getResultList();
    }

    /**
     * 特に並び替えをせず、ページング検索結果を取得して返却します。
     *
     * @param limit  　最大取得件数
     * @param pageNo 　ページ番号(1から始まる)
     * @return 検索結果
     */
    public List<ENTITY> find(Integer limit, Integer pageNo) {
        if (limit == null) {
            throw new IllegalArgumentException("検索条件の最大取得件数がnullになっています。");
        }
        if (pageNo == null) {
            pageNo = 1;
        }
        return select()
                .limit(limit)
                .offset((pageNo - 1) * limit)
                .orderBy(asc(new PropertyName<Long>("id")))
                .getResultList();
    }

    /**
     * 特に並び替えをせず、ページング検索結果を取得して返却します。
     *
     * @param limit  　最大取得件数
     * @param pageNo 　ページ番号(1から始まる)
     * @return 検索結果
     */
    public List<ENTITY> find(Integer limit, Long pageNo) {
        if (pageNo == null) {
            pageNo = 1L;
        }
        return find(limit, pageNo.intValue());
    }

    /**
     * propertyName昇順のページング検索結果を取得して返却します。
     *
     * @param limit        　最大取得件数
     * @param pageNo       　ページ番号(1から始まる)
     * @param propertyName プロパティ名
     * @return 検索結果
     */
    public List<ENTITY> findOrderByAsc(Integer limit, Integer pageNo, PropertyName<?> propertyName) {
        if (limit == null) {
            throw new IllegalArgumentException("検索条件の最大取得件数がnullになっています。");
        }
        if (pageNo == null) {
            pageNo = 1;
        }
        return select()
                .orderBy(asc(propertyName))
                .limit(limit)
                .offset((pageNo - 1) * limit)
                .getResultList();
    }

    /**
     * propertyName降順のページング検索結果を取得して返却します。
     *
     * @param limit        　最大取得件数
     * @param pageNo       　ページ番号(1から始まる)
     * @param propertyName プロパティ名
     * @return 検索結果
     */
    public List<ENTITY> findOrderByDesc(Integer limit, Integer pageNo,
                                        PropertyName<?> propertyName) {
        if (limit == null) {
            throw new IllegalArgumentException("検索条件の最大取得件数がnullになっています。");
        }
        if (pageNo == null) {
            pageNo = 1;
        }
        return select()
                .orderBy(desc(propertyName))
                .limit(limit)
                .offset((pageNo - 1) * limit)
                .getResultList();
    }

    /**
     * もしエンティティが存在すれば削除し、存在しない場合は何もしません。<br>
     *
     * @param entity エンティティ
     * @return 削除した行数
     */
    public int deleteIfExist(ENTITY entity) {
        try {
            return super.delete(entity);
        } catch (NullPointerException npe) {
            // 既に存在しない場合、jdbcManagerからnullPointerExceptionが返ってくる
            return 0;
        }
    }

    /**
     * SQLファイルを置くディレクトリを定義 <br>
     * <p/>
     * <pre>
     * 凡例：
     * 　　 xxx.YyyService#zzz メソッドが呼び出すSQLファイルの場合
     * 　　　⇒　resources/xxx/YyyService/zzz.sql
     *
     * 　　・基本的に、メソッド名とsqlファイル名をそろえること。
     * 　・SQLファイルを呼び出すメソッド内では、できるだけSQLファイル呼び出し以外の処理は行わないこと
     *
     *   public Yyy zzz() {
     *     ・・・
     *     return selectBySqlFile(Yyy.class, "zzz.sql", param).getSingleResult();
     *   }
     * </pre>
     */
    @Override
    protected void setEntityClass(Class<ENTITY> entityClass) {
        this.entityClass = entityClass;
        sqlFilePathPrefix = "META-INF/sql/" + StringUtil.replace(S2AOPUtil.getNonEnhancedClass(this).getName(), ".", "/") + "/";
    }

    /**
     * シーケンス名を引数に次のシーケンスを取得する
     *
     * @param sequenceName シーケンス名
     * @return 次のシーケンス
     */
    public Long getNextVal(String sequenceName) {
        if (StringUtils.isEmpty(sequenceName)) {
            throw new IllegalArgumentException("\"sequenceName\" is empty.");
        }
        String sql = "SELECT nextval('" + sequenceName + "')";
        return jdbcManager.selectBySql(Long.class, sql).getSingleResult();
    }

}
