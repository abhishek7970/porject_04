package in.co.rays.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.rays.util.JDBCDataSource;

import in.co.rays.bean.CollegeBean;
import in.co.rays.bean.CourseBean;
import in.co.rays.exception.DuplicateRecordException;

public class CourseModel {

	public Integer nextPk() throws Exception {

		int pk = 0;

		Connection conn = JDBCDataSource.getConnection();

		PreparedStatement pstmt = conn.prepareStatement("select max(id) from st_course");

		ResultSet rs = pstmt.executeQuery();

		while (rs.next()) {

			pk = rs.getInt(1);

		}

		JDBCDataSource.closeConnection(conn);

		return pk + 1;
}
	
	public void add(CourseBean bean) throws Exception {

		CourseBean existBean = findByName(bean.getName());

			if (existBean != null) {
				throw new DuplicateRecordException("role name already exist..!!");
			}

			int pk = nextPk();

			Connection conn = JDBCDataSource.getConnection();

			  PreparedStatement pstmt = conn.prepareStatement("INSERT  st_course VALUE(?,?,?,?,?,?,?,?)");
			   	pstmt.setInt(1, pk);
			   	pstmt.setString(2, bean.getName());
			   	pstmt.setString(3, bean.getDescription());
			   	pstmt.setString(4, bean.getDuration());
			   	pstmt.setString(5, bean.getCreatedBy());
			   	pstmt.setString(6, bean.getModifiedBy());
			   	pstmt.setTimestamp(7, bean.getCreatedDatetime());
			   	pstmt.setTimestamp(8, bean.getCreatedDatetime());

			int i = pstmt.executeUpdate();

			JDBCDataSource.closeConnection(conn);

			System.out.println("data inserted => " + i);

		}
		
	public void update(CourseBean bean) throws Exception {

		CourseBean existBean = findByName(bean.getName());

		if (existBean != null && bean.getId() != existBean.getId()) {
			throw new DuplicateRecordException("role name already exist..!!");
		}

		Connection conn = JDBCDataSource.getConnection();

		 PreparedStatement pstmt = conn.prepareStatement("UPDATE st_course SET NAME=?,DESCRIPTION=?,DURATION=?,CREATED_BY=?,MODIFIED_BY=?,CREATED_DATETIME=?,MODIFIED_DATETIME=? WHERE ID=?");
		 
		 pstmt.setString(1,bean.getName());
		 pstmt.setString(2, bean.getDescription());
		 pstmt.setString(3, bean.getDuration());
		 pstmt.setString(4,bean.getCreatedBy());
		 pstmt.setString(5, bean.getModifiedBy());
		 pstmt.setTimestamp(6, bean.getCreatedDatetime());
		 pstmt.setTimestamp(7, bean.getModifiedDatetime());
		 pstmt.setLong(8, bean.getId());
		 
		int i = pstmt.executeUpdate();

		JDBCDataSource.closeConnection(conn);

		System.out.println("data updated => " + i);

	}

	public void delete(long id) throws Exception {

		Connection conn = JDBCDataSource.getConnection();

		PreparedStatement pstmt = conn.prepareStatement("delete from st_course where id = ?");

		pstmt.setLong(1, id);

		int i = pstmt.executeUpdate();

		JDBCDataSource.closeConnection(conn);

		System.out.println("data deleted => " + i);

	}

	public CourseBean findByPk(long id) throws Exception {

		Connection conn = JDBCDataSource.getConnection();

		PreparedStatement pstmt = conn.prepareStatement("select * from st_course where id = ?");

		pstmt.setLong(1, id);

		ResultSet rs = pstmt.executeQuery();

		CourseBean bean = null;

		while (rs.next()) {
			 bean = new CourseBean();
			 bean.setId(1);
			 bean.setName(rs.getString(2));
			 bean.setDescription(rs.getString(3));
			 bean.setDuration(rs.getString(4));
			 bean.setCreatedBy(rs.getString(5));
			 bean.setModifiedBy(rs.getString(6));
			 bean.setCreatedDatetime(rs.getTimestamp(7));
			 bean.setModifiedDatetime(rs.getTimestamp(8));
		}

		JDBCDataSource.closeConnection(conn);

		return bean;
	}
	
	public CourseBean findByName(String name) throws Exception {

		Connection conn = JDBCDataSource.getConnection();

		PreparedStatement pstmt = conn.prepareStatement("select * from st_course where name = ?");

		pstmt.setString(1, name);

		ResultSet rs = pstmt.executeQuery();

		CourseBean bean = null;

		while (rs.next()) {
			 bean=new CourseBean();
			 bean.setId(1);
			 bean.setName(rs.getString(2));
			 bean.setDescription(rs.getString(3));
			 bean.setDuration(rs.getString(4));
			 bean.setCreatedBy(rs.getString(5));
			 bean.setModifiedBy(rs.getString(6));
			 bean.setCreatedDatetime(rs.getTimestamp(7));
			 bean.setModifiedDatetime(rs.getTimestamp(8));
		}

		JDBCDataSource.closeConnection(conn);

		return bean;
	}
	public List search(CourseBean bean, int pageNo, int pageSize) throws Exception {

		Connection conn = JDBCDataSource.getConnection();

		StringBuffer sql = new StringBuffer("select * from st_course where 1=1");

		if (bean != null) {
			if (bean.getName() != null && bean.getName().length() > 0) {
				sql.append(" and first_name like '" + bean.getName() + "%'");
			}
		}

		if (pageSize > 0) {
			pageNo = (pageNo - 1) * pageSize;
			sql.append(" limit " + pageNo + ", " + pageSize);
		}

		System.out.println("sql ==>> " + sql.toString());

		PreparedStatement pstmt = conn.prepareStatement(sql.toString());

		ResultSet rs = pstmt.executeQuery();

		List list = new ArrayList();

		while (rs.next()) {
			 bean=new CourseBean();
			 bean.setId(rs.getLong(1));
				bean.setName(rs.getString(2));
				bean.setDescription(rs.getString(3));
				bean.setDuration(rs.getString(4));
				bean.setCreatedBy(rs.getString(5));
				bean.setModifiedBy(rs.getString(6));
				bean.setCreatedDatetime(rs.getTimestamp(7));
				bean.setModifiedDatetime(rs.getTimestamp(8));
			list.add(bean);
		}

		JDBCDataSource.closeConnection(conn);

		return list;
	}

}