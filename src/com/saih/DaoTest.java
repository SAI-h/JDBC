package com.saih;

import com.saih.dao.StationDao;
import com.saih.dao.daoImpl.StationDaoImpl;
import com.saih.pojo.Station;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.List;

public class DaoTest {
    @Test
    public void testInsert() {
        StationDao stationDao = new StationDaoImpl();
        Station insertStation = new Station();
        insertStation.setStationName("station_1");
        insertStation.setStationAddress("place_1");
        insertStation.setStationLongitude(BigDecimal.valueOf(120.00));
        insertStation.setStationLatitude(BigDecimal.valueOf(30.00));
        insertStation.setRemarks("test Station insert");
        int rows = stationDao.insert(insertStation);
        System.out.println(rows);
    }

    @Test
    public void testRemove() {
        StationDao stationDao = new StationDaoImpl();
        Integer stationID = 6;
        int rows = stationDao.remove(stationID);
        System.out.println(rows);
    }

    @Test
    public void testUpdate() {
        StationDao stationDao = new StationDaoImpl();
        Station insertStation = new Station();
        insertStation.setStationID(1);
        insertStation.setStationName("station_1");
        insertStation.setStationAddress("place_1_new");
        insertStation.setStationLongitude(BigDecimal.valueOf(120.60));
        insertStation.setStationLatitude(BigDecimal.valueOf(30.10));
        insertStation.setRemarks("test Station update");
        int rows = stationDao.update(insertStation);
        System.out.println(rows);
    }


    @Test
    public void testQuery() {
        StationDao stationDao = new StationDaoImpl();
        List<Station> station_list = stationDao.queryAll();
        for (Station station: station_list) {
            System.out.println(station);
        }
    }

    @Test
    public void testQueryByID() {
        StationDao stationDao = new StationDaoImpl();
        Integer staID = 3;
        System.out.println(stationDao.queryByID(staID));
    }
}
