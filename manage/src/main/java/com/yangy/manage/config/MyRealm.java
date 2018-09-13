//package com.yangy.manage.config;
//
//import com.baomidou.mybatisplus.mapper.EntityWrapper;
//import com.yangy.manage.entity.Role;
//import com.yangy.manage.entity.User;
//import com.yangy.manage.pojo.vo.UserVo;
//import com.yangy.manage.service.UserService;
//import org.apache.shiro.authc.AuthenticationException;
//import org.apache.shiro.authc.AuthenticationInfo;
//import org.apache.shiro.authc.AuthenticationToken;
//import org.apache.shiro.authc.SimpleAuthenticationInfo;
//import org.apache.shiro.authz.AuthorizationInfo;
//import org.apache.shiro.authz.SimpleAuthorizationInfo;
//import org.apache.shiro.realm.AuthorizingRealm;
//import org.apache.shiro.subject.PrincipalCollection;
//import org.apache.shiro.util.ByteSource;
//
//import javax.annotation.Resource;
//import java.util.List;
//
///**
// * Created by Administrator on 2017/12/11.
// * 自定义权限匹配和账号密码匹配
// */
//public class MyRealm extends AuthorizingRealm {
//
//    @Resource
//    private UserService userService;
//
//    @Override
//    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
//        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
//        UserVo userInfo = (UserVo) principals.getPrimaryPrincipal();
//
//        List<Role> roleList = userInfo.getRoleList();
//        for (Role role : roleList) {
//            authorizationInfo.addRole(role.getName());
//        }
//
////        for (SysRole role : userInfo.getRoleList()) {
////            authorizationInfo.addRole(role.getRole());
////            for (SysPermission p : role.getPermissions()) {
////                authorizationInfo.addStringPermission(p.getPermission());
////            }
////        }
//
//        return authorizationInfo;
//    }
//
//    /*主要是用来进行身份认证的，也就是说验证用户输入的账号和密码是否正确。*/
//    @Override
//    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token)
//            throws AuthenticationException {
//        //获取用户的输入的账号.
//        String username = (String) token.getPrincipal();
//        //通过username从数据库中查找 User对象，如果找到，没找到.
//        //实际项目中，这里可以根据实际情况做缓存，如果不做，Shiro自己也是有时间间隔机制，2分钟内不会重复执行该方法
//        User build = User.builder().username(username).build();
//        EntityWrapper<User> entityWrapper = new EntityWrapper<>();
//        entityWrapper.setEntity(build);
//        User userDB = userService.selectOne(entityWrapper);
//        if (null == userDB) {
//            return null;
//        }
//
//        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(
//                userDB.getUsername(), //用户名
//                userDB.getPwd(), //密码
//                ByteSource.Util.bytes(userDB.getSalt()),//salt
//                userDB.getPhone()  //real name
//        );
//        return authenticationInfo;
//    }
//
//}
