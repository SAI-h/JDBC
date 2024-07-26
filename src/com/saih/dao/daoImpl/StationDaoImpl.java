package com.saih.dao.daoImpl;

import com.saih.dao.BaseDao;
import com.saih.dao.StationDao;
import com.saih.pojo.Station;

import java.util.List;

public class StationDaoImpl extends BaseDao implements StationDao {
    @Override
    public int insert(Station station) {
        String sql = "insert into stations(StaName, StaAddress, StaLng, StaLat, Remarks) values(?,?,?,?,?)";
        return update(sql, station.getStationName(), station.getStationAddress(), station.getStationLongitude(), station.getStationLatitude(), station.getRemarks());
    }

    @Override
    public int remove(Integer stationID) {
        String sql = "delete from stations where StaID=?";
        return update(sql, stationID);
    }

    @Override
    public int update(Station station) {
        String sql = "update stations set StaName=?, StaAddress=?, StaLng=?, StaLat=?, Remarks=? where StaID=?";
        return update(sql, station.getStationName(), station.getStationAddress(), station.getStationLongitude(), station.getStationLatitude(), station.getRemarks(), station.getStationID());
    }

    @Override
    public List<Station> queryAll() {
        String sql = "select StaID stationID, StaName stationName, StaAddress stationAddress, StaLng stationLongitude, StaLat stationLatitude, Remarks remarks from stations";
        return query(Station.class, sql, null);
    }

    @Override
    public Station queryByID(Integer stationID) {
        String sql = "select StaID stationID, StaName stationName, StaAddress stationAddress, StaLng stationLongitude, StaLat stationLatitude, Remarks remarks from stations where StaID=?";
        List<Station> result_list = query(Station.class, sql, stationID);
        return result_list.isEmpty() ? null : result_list.get(0);
    }
}
