package cn.wzga.open.entity;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

/**
 * <p><strong>不对应任何表</strong>, 基础实体，包含创建人，创建时间，修改人修改时间，状态</p>
 *
 * @author cl
 * @since 2014/12/05
 */
@SuppressWarnings("serial")
@MappedSuperclass
public abstract class AbstractBaseDeletableEntity extends AbstractBaseEntity {

    /**
     * 对应数据库的state字段
     *
     * @author cl
     * @since 2014/12/05
     */
    public final static int ENTITY_STATE_NORMAL = 1;// 正常状态
    public final static int ENTITY_STATE_DELETED = 0;// 删除状态

    private Integer state; // 是否被删除, 1 正常, 2 删除

    /**
     * <p>逻辑删除字段</p>
     * <p>{@link AbstractBaseDeletableEntity#ENTITY_STATE_NORMAL}</p>
     * <p>{@link AbstractBaseDeletableEntity#ENTITY_STATE_DELETED}</p>
     *
     * @return
     */
    @Column(name = "state")
    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }
}
