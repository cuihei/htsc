package com.ht.service.inter.complication.project.electron;

import java.util.List;

import com.ht.persistence.model.complication.project.electron.Project;

public interface ProElectronService {
  /**
   * 查询
   * @return 电子海图list
   * @throws Exception
   */
  public List<Project> findProEleList() throws Exception;
}
