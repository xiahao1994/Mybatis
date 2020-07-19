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
      /*--------xh-------源码分析记录点:获取SqlSessionFactory--------*/
      SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);//DefaultSqlSessionFactory

      // 2、获取sqlSession对象
      SqlSession openSession = sqlSessionFactory.openSession();
      try {
        User user = openSession.selectOne("testmybatis.xiahao.mapper.UserMapper.selectByPrimaryKey",1);
        //测试缓存，只打印了一次sql说明只查询了一次数据库
        User user2 = openSession.selectOne("testmybatis.xiahao.mapper.UserMapper.selectByPrimaryKey",1);
        User user3 = openSession.selectOne("testmybatis.xiahao.mapper.UserMapper.selectByPrimaryKey",1);
        System.out.println(user.toString());
        //查询的对象是一个，指向一个对象
        System.out.println(user2.toString());
        System.out.println(user3.toString());
      } finally {
        openSession.close();
      }
    } catch (IOException e) {
      e.printStackTrace();
    }


  }
}
