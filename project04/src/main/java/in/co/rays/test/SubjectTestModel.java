
package in.co.rays.test;

import java.sql.Timestamp;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import in.co.rays.bean.SubjectBean;
import in.co.rays.model.SubjectModel;

public class SubjectTestModel {
	
     public static void main(String[] args) throws Exception {
		
		testAdd();
		//testUpdate();
		//testDelete();
		//testFindByPk();
		//testSearch();
		
	}
	public static void testAdd() throws Exception {

		SubjectBean bean = new SubjectBean();

		bean.setSubjectName("Data Structure");
		bean.setCourseId(1L);
		bean.setDescription("Master's in Computer Application");
		bean.setCreatedBy("admin@gmail.com");
		bean.setModifiedBy("admin@gmail.com");
		bean.setCreatedDatetime(new Timestamp(new Date().getTime()));
		bean.setModifiedDatetime(new Timestamp(new Date().getTime()));

		SubjectModel model = new SubjectModel();

		model.add(bean);
	}
	
	public static void testUpdate() throws Exception {

		SubjectModel model = new SubjectModel();
		SubjectBean bean = model.findByPk(2);

		bean.setSubjectName("MCA");
		bean.setCourseId(1L);
		bean.setDescription("Master's in Computer Application");
		bean.setCreatedBy("admin@gmail.com");
		bean.setModifiedBy("admin@gmail.com");
		bean.setCreatedDatetime(new Timestamp(new Date().getTime()));
		bean.setModifiedDatetime(new Timestamp(new Date().getTime()));
		bean.setId(1);

		model.update(bean);
	}
	private static void testDelete() throws Exception {

		SubjectBean bean = new SubjectBean();

		bean.setId(1);

		SubjectModel model = new SubjectModel();

		model.delete(bean);
	}

	private static void testFindByPk() throws Exception {

		SubjectBean bean = new SubjectBean();

		SubjectModel model = new SubjectModel();

		bean = model.findByPk(1);

		if (bean != null) {

			System.out.println(bean.getId());
			System.out.println(bean.getSubjectName());
			System.out.println(bean.getCourseId());
			System.out.println(bean.getDescription());
			System.out.println(bean.getCreatedBy());
			System.out.println(bean.getModifiedBy());
			System.out.println(bean.getCreatedDatetime());
			System.out.println(bean.getModifiedDatetime());

		}
	}
	private static void testSearch()throws Exception{
		
         SubjectBean bean = new SubjectBean();
		
		SubjectModel model = new SubjectModel();
		
		bean.setSubjectName("MCA");
		
		List list = model.search(bean, 1, 5);
		
		Iterator it = list.iterator();
		
		while(it.hasNext()) {
			
			bean = (SubjectBean) it.next();
			
			System.out.println(bean.getId());
			System.out.println(bean.getSubjectName());
			System.out.println(bean.getCourseId());
			System.out.println(bean.getDescription());
			System.out.println(bean.getCreatedBy());
			System.out.println(bean.getModifiedBy());
			System.out.println(bean.getCreatedDatetime());
			System.out.println(bean.getModifiedDatetime());
			
		}
	}

}
