package com.saih.dao;

import com.saih.pojo.Station;

import java.util.List;

public interface StationDao {

    // 增添一个新的公交站点
    int insert(Station station);

    // 根据ID，删除一个公交站点
    int remove(Integer stationID);

    // 更新公交站点
    int update(Station station);

    // 查询所有站点信息
    List<Station> queryAll();

    // 根据ID，查询公交站点
    Station queryByID(Integer stationID);

}
