package com.ruoyi.project;

import com.ruoyi.project.system.domain.SysUser;

/**
 * @author Sunss
 * @since 2020/4/21
 */
public class TestBoot {
    public static void main(String[] args) {
        SysUser user = new SysUser();
        user.setEmail("1");
        TestBoot testBoot = new TestBoot();
        testBoot.set(user);
        System.err.println(user.getEmail());
    }

    public void set(SysUser user) {
        user.setEmail("2");
    }
}
