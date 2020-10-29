package com.ht.service.inter.background.authority.auth;

import java.util.List;

import com.ht.persistence.model.background.authority.auth.Auth;

public interface AuthService {
  /**
   * 保存权限
   * @param authParam json对象
   * @throws Exception
   */
  public void addAuth(String authParam) throws Exception;
  /**
   * 修改权限
   * @param authParam json对象
   * @throws Exception
   */
  public void modifyAuth(String authParam) throws Exception;
  /**
   * 删除权限
   * @param authParam json对象
   * @throws Exception
   */
  public void delAuth(String authParam) throws Exception;
  /**
   * 查询所有权限
   * @return List 查询到的集合
   * @throws Exception
   */
  public List<Auth> getAuth() throws Exception;
  /**
   * 查询一条权限
   * @param authParam json对象
   * @return Auth 查询到的对象
   * @throws Exception
   */
  public Auth getAuth(String authParam) throws Exception;
}
