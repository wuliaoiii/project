package com.yangy.manage.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.yangy.common.model.Result;
import com.yangy.manage.entity.Menu;
import com.yangy.manage.entity.RoleMenu;
import com.yangy.manage.entity.User;
import com.yangy.manage.entity.UserRole;
import com.yangy.manage.pojo.vo.UserVo;
import com.yangy.manage.service.MenuService;
import com.yangy.manage.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 系统用户表 前端控制器
 *
 * @author yang yang
 * @since 2018-09-04
 */
@Slf4j
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private MenuService menuService;

    @PostMapping("/login")
    public Result login(@Valid UserVo userVo, HttpSession session) {
        Subject subject = SecurityUtils.getSubject();
//        UsernamePasswordToken token = new UsernamePasswordToken(userVo.getUsername(), userVo.getPwd());
        try {
//            subject.login(token); // 登录认证
//            String userName = (String) SecurityUtils.getSubject().getPrincipal();
            User build = User.builder().username(userVo.getUsername()).build();
            EntityWrapper<User> entityWrapper = new EntityWrapper<>();
            entityWrapper.setEntity(build);
            User first = userService.selectOne(entityWrapper);

            UserRole userRole = UserRole.builder().userId(first.getUserId()).build();
            EntityWrapper<UserRole> userRoleEntityWrapper = new EntityWrapper<>();
            List<UserRole> userRoleList = userRole.selectList(userRoleEntityWrapper);
            List<Long> roleIdList = userRoleList.stream().map(userRoleFilter -> userRole.getRoleId()).collect(Collectors.toList());

            RoleMenu roleMenu = new RoleMenu();
            List<RoleMenu> roleMenuList = roleMenu.selectList(new EntityWrapper<RoleMenu>().in("role_id", roleIdList));
            List<Long> menuIdList = roleMenuList.stream().map(roleMenuFilter -> roleMenu.getMenuId()).collect(Collectors.toList());
            List<Menu> menuList = menuService.selectList(new EntityWrapper<Menu>().in("menu_id", menuIdList));


            Map<Long, List<Menu>> collect = menuList.stream().sorted().collect(Collectors.groupingBy(Menu::getParentId));


            UserVo userResult = new UserVo();
            BeanUtils.copyProperties(userResult, first);

            userResult.setMenuList(menuList);

            session.setAttribute("userInfo", first);
            session.setAttribute("menuInfo", menuList);
            session.setAttribute("roleInfo", null);

            return new Result<UserVo>().ok(userResult);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new Result<User>().ok(null);
    }

    @PostMapping("/save/user")
    public Result save(@RequestBody User user) {
        boolean insert = userService.insert(user);
        return new Result<User>().ok(user.selectById());
    }

    @PostMapping("save/list")
    public Result saveList(@RequestBody List<User> userList) {
        boolean insert = userService.insertBatch(userList);
        return new Result<Boolean>().ok(insert);
    }

    @PostMapping("update")
    public Result update(@RequestBody User user) {
        boolean update = userService.updateById(user);
        return new Result<User>().ok(user.selectById());
    }

    @PostMapping("find/by/id")
    public Result findById(@RequestParam Long recordId) {
        User selectOne = userService.selectById(recordId);
        return new Result<User>().ok(selectOne);
    }

    @PostMapping("find/first")
    public Result findFirst(@RequestBody User user) {
        EntityWrapper<User> entityWrapper = new EntityWrapper<>();
        entityWrapper.setEntity(user);
        User selectOne = userService.selectOne(entityWrapper);
        return new Result<User>().ok(selectOne);
    }

    @PostMapping("find/by/idList")
    public Result findByIdList(@RequestBody List<Long> idList) {
        List<User> userList = userService.selectBatchIds(idList);
        return new Result<List<User>>().ok(userList);
    }

    @PostMapping("find/by/page")
    public Result findByPage(@RequestBody User user) {
        Page<User> userPage = new Page<>();
        EntityWrapper<User> entityWrapper = new EntityWrapper<>();
        entityWrapper.setEntity(user);
        Page<User> userPageDB = userService.selectPage(userPage, entityWrapper);
        return new Result<Page<User>>().ok(userPageDB);
    }

    @GetMapping("/loadMenuInfo")
    public String loadMenuInfo(Long menuId, HttpSession session) {
        User userInfo = (User) session.getAttribute("userInfo");

        //根据用户id和菜单主键查询 菜单集合
        List<UserRole> userRoleList = UserRole.builder().build().selectList(new EntityWrapper<UserRole>()
                .eq("user_id", userInfo.getUserId()));
        List<Long> roleIdList = userRoleList.stream().map(UserRole::getRoleId).collect(Collectors.toList());

        List<RoleMenu> roleMenuList = RoleMenu.builder().build().selectList(new EntityWrapper<RoleMenu>().in("role_id", roleIdList));
        List<Long> menuIdList = roleMenuList.stream().map(RoleMenu::getMenuId).collect(Collectors.toList());
        List<Menu> menuList = menuService.selectList(new EntityWrapper<Menu>()
                .in("menu_id", menuIdList)
                .eq("parent_id", menuId));

        List<Menu> collect = menuList.stream().filter(menu -> null == menu.getParentId()).collect(Collectors.toList());

        for (Menu menu : collect) {
            menu.setChildren(getChild(menu.getMenuId(), menuList));
        }

        session.setAttribute("menuInfo", collect);
        return null;
    }

    private List<Menu> getChild(Long parentId, List<Menu> rootMenu) {
        // 子菜单
        List<Menu> childList = new ArrayList<>();
        for (Menu menu : rootMenu) {
            // 遍历所有节点，将父菜单id与传过来的id比较
            if (null != menu.getParentId()) {
                if (menu.getParentId().equals(parentId)) {
                    childList.add(menu);
                }
            }
        }
        // 把子菜单的子菜单再循环一遍
        for (Menu menu : childList) {// 没有url子菜单还有子菜单
            if (StringUtils.isBlank(menu.getUrl())) {
                // 递归
                menu.setChildren(getChild(menu.getMenuId(), rootMenu));
            }
        } // 递归退出条件
        if (childList.size() == 0) {
            return null;
        }
        return childList;
    }

    @GetMapping("/auth")
    public String getCode(HttpServletRequest request, String code) {
        RestTemplate restTemplate = new RestTemplate();

        return null;
    }


}
