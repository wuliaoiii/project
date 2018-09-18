package com.yangy.manage.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.yangy.manage.entity.Menu;
import com.yangy.manage.pojo.vo.MenuTree;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 权限表 Mapper 接口
 * </p>
 *
 * @author yang yang
 * @since 2018-09-04
 */
@Mapper
public interface MenuMapper extends BaseMapper<Menu> {

    List<MenuTree> selectMenuTreeByList(@Param("list") List<Long> list);

    List<MenuTree> selectMenuTree(Menu menu);
}
