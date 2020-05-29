package com.learn.system.mapper;

import com.learn.system.pojo.Account;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

@Mapper
@Component
public interface AccountMapper {

    //这里是关于登录的sql操作
    //查看是否username已经被注册过
    int checkUsername(String username);

    //插入账号和密码
    void insertAccount(Account acc);

    //查看对应账号的密码是否正确，返回对应username的password
    String checkPassword(String username);

    //查找账号权限码
    int getAuthority(String username);

    //这里是关于注册的sql操作
    //检查学生账号注册是否合法，也就是用username去student表里看看有没有这个学号
    //返回count聚集函数，如果是0就不合法，否则就是合法
    int checkIsStuLegal(String username);

}
