package testmybatis.xiahao;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import testmybatis.xiahao.mapper.User;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by xh on 2019/10/14.
 */
public class Test {
  public static void main(String[] args) {
    String resource = "mybatis-config.xml";
    InputStream inputStream = null;
    try {
      inputStream = Resources.getResourceAsStream(resource);
      SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
      // 2、获取sqlSession对象
      SqlSession openSession = sqlSessionFactory.openSession();
      try {
        User user = openSession.selectOne("testmybatis.xiahao.mapper.UserMapper.selectByPrimaryKey",1);
        // 3、获取接口的实现类对象
        //会为接口自动的创建一个代理对象，代理对象去执行增删改查方法
//      Employee employee = (Employee) openSession.selectOne(
//        "com.atguigu.mybatis.EmployeeMapper.selectEmp", 1);
      } finally {
        openSession.close();
      }
    } catch (IOException e) {
      e.printStackTrace();
    }


  }
}
