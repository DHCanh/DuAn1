/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Entity.ChiTietHoaDon;
import Entity.DatPhong;
import Untils.JdbcHelper;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Admin
 */
public class ChiTietHoaDonDAO extends HotelDAO<ChiTietHoaDon, String> {
    String INSERT_SQL = "INSERT INTO ChiTietHoaDon(MaCTHD,DonGia,ThanhTien,MaHD,MaNV,MaPhong)VALUES(?,?,?,?,?,?)";
    String UPDATE_SQL = "UPDATE ChiTietHoaDon SET DonGia = ?, ThanhTien = ?, MaHD = ?, MaNV = ?, MaPhong = ? WHERE MaCTHD = ?";
    String DELETE_SQL = "DELETE FROM ChiTietHoaDon WHERE MaCTHD = ?";
    String SELECTALL_SQL = "SELECT * FROM ChiTietHoaDon";
    String SELECT_BY_ID_SQL = "SELECT * FROM ChiTietHoaDon WHERE MaCTHD = ?";

    @Override
    public void insert(ChiTietHoaDon enity) {
	JdbcHelper.update(INSERT_SQL,enity.getMaCTHD(),enity.getDonGia(),enity.getThanhTien(),enity.getMaHD(),enity.getMaNV(),enity.getMaPhong() );
    }

    @Override
    public void Update(ChiTietHoaDon enity) {
	JdbcHelper.update(INSERT_SQL,enity.getDonGia(),enity.getThanhTien(),enity.getMaHD(),enity.getMaNV(),enity.getMaPhong(),enity.getMaCTHD() );
    }

    @Override
    public void delete(String key) {
	JdbcHelper.update(DELETE_SQL, key);
		
    }

    @Override
    public List<ChiTietHoaDon> selectAll() {
	return this.selectBySql(SELECTALL_SQL);
    }

    @Override
    public ChiTietHoaDon selectByID(String key) {
	List<ChiTietHoaDon> list= this.selectBySql(SELECT_BY_ID_SQL, key);
	if(list.isEmpty()){
	    return null;
	}
	return list.get(0);
    }

    @Override
    protected List<ChiTietHoaDon> selectBySql(String sql, Object... args) {
	List<ChiTietHoaDon> list = new ArrayList<>();
	try {
	    ResultSet rs = JdbcHelper.query(sql, args);
	    while(rs.next()){
		ChiTietHoaDon entity = new ChiTietHoaDon();
		entity.setMaCTHD(rs.getString("MaCTHD"));
		entity.setDonGia(rs.getDouble("DonGia"));
		entity.setThanhTien(rs.getDouble("ThanhTien"));
		entity.setMaHD(rs.getString("MaHD"));
		entity.setMaNV(rs.getString("MaNV"));
		entity.setMaPhong(rs.getString("MaPhong"));
		list.add(entity);
	    }
	    rs.getStatement().getConnection().close();
	    return list;
	} catch (SQLException e) {
	    throw new RuntimeException(e);
	}
    }
    
    
}
