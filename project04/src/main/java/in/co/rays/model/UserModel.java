package in.co.rays.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.rays.util.JDBCDataSource;

import in.co.rays.bean.UserBean;

public class UserModel {

	public int nextPk() throws Exception {

		int pk = 0;

		Connection conn = JDBCDataSource.getConnection();

		PreparedStatement ps = conn.prepareStatement("select max(id) from st_user");

		ResultSet rs = ps.executeQuery();

		while (rs.next()) {

			pk = rs.getInt(1);

			System.out.println("max id = " + pk);

		}
		return pk + 1;

	}

	public void add(UserBean bean) throws Exception {

		Connection conn = null;

		try {

			conn = JDBCDataSource.getConnection();

			PreparedStatement pstmt = conn.prepareStatement("insert into st_user values(?,?,?,?,?,?,?,?,?,?,?,?,?)");

			UserBean existBean = findByLoginId(bean.getLogin());

			conn.setAutoCommit(false);

			if (existBean == null) {

				pstmt.setInt(1, nextPk());
				pstmt.setString(2, bean.getFirstName());
				pstmt.setString(3, bean.getLastName());
				pstmt.setString(4, bean.getLogin());
		pstmt.setString(5,bean.getPassword());
		pstmt.setDate(6, new java.sql.Date(bean.getDob().getTime()));
		pstmt.setString(7, bean.getMobileNo());
		pstmt.setLong(8, bean.getRoleId());
		pstmt.setString(9, bean.getGender());
		pstmt.setString(10, bean.getCreatedBy());
		pstmt.setString(11, bean.getModifiedBy());
		pstmt.setTimestamp(12, bean.getCreatedDatetime());
		pstmt.setTimestamp(13, bean.getModifiedDatetime());


		

				int i = pstmt.executeUpdate();

				conn.commit();

				System.out.println("data inserted successfully.. " + i);
			} else {
				System.out.println("loginId already exist");
			}

		} catch (Exception e) {

			conn.rollback();
			e.printStackTrace();

		}

	}

	public void delete(UserBean bean) throws Exception {

		Connection conn = JDBCDataSource.getConnection();

		PreparedStatement pstmt = conn.prepareStatement("delete from st_user where id = ?");

		pstmt.setLong(8, bean.getRoleId());

		int i = pstmt.executeUpdate();

		System.out.println("data deleted successfully.. " + i);

	}

	public void update(UserBean bean) throws Exception {

		Connection conn = JDBCDataSource.getConnection();

		PreparedStatement pstmt = conn.prepareStatement(
				"update user set firstName = ?, lastName = ?, login = ?, password = ?, dob = ?, mobileNo = ?, roleId = ?, gender = ?, created_by = ?, modified_by = ?, created_datetime = ?, modified_datetime = ? where id = ?");
		

		pstmt.setInt(1, nextPk());
		pstmt.setString(2, bean.getFirstName());
		pstmt.setString(3, bean.getLastName());
		pstmt.setString(4, bean.getLogin());
pstmt.setString(5,bean.getPassword());
pstmt.setDate(6, new java.sql.Date(bean.getDob().getTime()));
pstmt.setString(7, bean.getMobileNo());
pstmt.setLong(8, bean.getRoleId());
pstmt.setString(9, bean.getGender());
pstmt.setString(10, bean.getCreatedBy());
pstmt.setString(11, bean.getModifiedBy());
pstmt.setTimestamp(12, bean.getCreatedDatetime());
pstmt.setTimestamp(13, bean.getModifiedDatetime());

		int i = pstmt.executeUpdate();

		System.out.println("data update successfully... " + i);

	}

	public UserBean searchAll() throws Exception {

		Connection conn = JDBCDataSource.getConnection();

		PreparedStatement pstmt = conn.prepareStatement("select * from st_user");

		ResultSet rs = pstmt.executeQuery();

		UserBean bean = null;

		while (rs.next()) {

			bean = new UserBean();

			bean.setId(rs.getInt(1));
			bean.setFirstName(rs.getString(2));
			bean.setLastName(rs.getString(3));
			bean.setLogin(rs.getString(4));
			bean.setPassword(rs.getString(5));
			bean.setMobileNo(rs.getString(6));
			bean.setDob(rs.getDate(7));
			bean.setRoleId(rs.getLong(8));
			bean.setCreatedBy(rs.getNString(9));
			bean.setGender(rs.getString(9));
			bean.setCreatedBy(rs.getNString(10));
			bean.setModifiedBy(rs.getNString(11));
			bean.setCreatedDatetime(rs.getTimestamp(12));
			bean.setModifiedDatetime(rs.getTimestamp(13));
			
		}

		return bean;

	}

	public UserBean authenticate(String loginId, String password) throws Exception {

		System.out.println("data = " + loginId + " " + password);

		Connection conn = JDBCDataSource.getConnection();

		PreparedStatement pstmt = conn.prepareStatement("select * from st_user where loginId = ? and password = ?");

		pstmt.setString(1, loginId);
		pstmt.setString(2, password);

		ResultSet rs = pstmt.executeQuery();

		UserBean bean = null;

		while (rs.next()) {

			bean = new UserBean();

			bean.setId(rs.getInt(1));
			bean.setFirstName(rs.getString(2));
			bean.setLastName(rs.getString(3));
			bean.setLogin(rs.getString(4));
			bean.setPassword(rs.getString(5));
			bean.setMobileNo(rs.getString(6));
			bean.setDob(rs.getDate(7));
			bean.setRoleId(rs.getLong(8));
			bean.setCreatedBy(rs.getNString(9));
			bean.setGender(rs.getString(9));
			bean.setCreatedBy(rs.getNString(10));
			bean.setModifiedBy(rs.getNString(11));
			bean.setCreatedDatetime(rs.getTimestamp(12));
			bean.setModifiedDatetime(rs.getTimestamp(13));
			
		}

		return bean;

	}

	public UserBean findByLoginId(String loginId) throws Exception {

		System.out.println("data = " + loginId);

		Connection conn = JDBCDataSource.getConnection();

		PreparedStatement pstmt = conn.prepareStatement("select * from st_user where loginId = ?");

		pstmt.setString(1, loginId);

		ResultSet rs = pstmt.executeQuery();

		UserBean bean = null;

		while (rs.next()) {

			bean = new UserBean();

			bean.setId(rs.getInt(1));
			bean.setFirstName(rs.getString(2));
			bean.setLastName(rs.getString(3));
			bean.setLogin(rs.getString(4));
			bean.setPassword(rs.getString(5));
			bean.setMobileNo(rs.getString(6));
			bean.setDob(rs.getDate(7));
			bean.setRoleId(rs.getLong(8));
			bean.setCreatedBy(rs.getNString(9));
			bean.setGender(rs.getString(9));
			bean.setCreatedBy(rs.getNString(10));
			bean.setModifiedBy(rs.getNString(11));
			bean.setCreatedDatetime(rs.getTimestamp(12));
			bean.setModifiedDatetime(rs.getTimestamp(13));
			

		}

		return bean;

	}

	public UserBean findById(int id) throws Exception {

		System.out.println("data = " + id);

		Connection conn = JDBCDataSource.getConnection();

		PreparedStatement pstmt = conn.prepareStatement("select * from st_user where id = ?");

		pstmt.setInt(1, id);

		ResultSet rs = pstmt.executeQuery();

		UserBean bean = null;

		while (rs.next()) {

			bean = new UserBean();

			bean.setId(rs.getInt(1));
			bean.setFirstName(rs.getString(2));
			bean.setLastName(rs.getString(3));
			bean.setLogin(rs.getString(4));
			bean.setPassword(rs.getString(5));
			bean.setMobileNo(rs.getString(6));
			bean.setDob(rs.getDate(7));
			bean.setRoleId(rs.getLong(8));
			bean.setCreatedBy(rs.getNString(9));
			bean.setGender(rs.getString(9));
			bean.setCreatedBy(rs.getNString(10));
			bean.setModifiedBy(rs.getNString(11));
			bean.setCreatedDatetime(rs.getTimestamp(12));
			bean.setModifiedDatetime(rs.getTimestamp(13));
			
		}

		return bean;

	}

	public List search(UserBean bean) throws Exception {

		Connection conn = JDBCDataSource.getConnection();

		StringBuffer sql = new StringBuffer("select * from  where 1=1");

		if (bean != null) {

			if (bean.getFirstName() != null && bean.getFirstName().length() > 0) {
				sql.append(" and firstName like '" + bean.getFirstName() + "'");
			}

			if (bean.getLastName() != null && bean.getLastName().length() > 0) {
				sql.append(" and lastName like '" + bean.getLastName() + "'");
			}

		}
		System.out.println(sql.toString());

		PreparedStatement ptmt = conn.prepareStatement(sql.toString());
		ResultSet rs = ptmt.executeQuery();

		List list = new ArrayList();

		while (rs.next()) {
			bean = new UserBean();
			bean.setId(rs.getInt(1));
			bean.setFirstName(rs.getString(2));
			bean.setLastName(rs.getString(3));
			bean.setLogin(rs.getString(4));
			bean.setPassword(rs.getString(5));
			bean.setMobileNo(rs.getString(6));
			bean.setDob(rs.getDate(7));
			bean.setRoleId(rs.getLong(8));
			bean.setCreatedBy(rs.getNString(9));
			bean.setGender(rs.getString(9));
			bean.setCreatedBy(rs.getNString(10));
			bean.setModifiedBy(rs.getNString(11));
			bean.setCreatedDatetime(rs.getTimestamp(12));
			bean.setModifiedDatetime(rs.getTimestamp(13));
			
		}
		return list;

	}

}