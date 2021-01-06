import demo.dao.ClassesDao;
import demo.dao.ProductMapper;
import demo.dao.UserDao;
import demo.domain.Classes;
import demo.domain.Product;
import demo.domain.ProductExample;
import demo.domain.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.*;

public class TestDemo {
    SqlSession sqlSession = null;

    @Before
    public void before() {
        try {
            InputStream io = Resources.getResourceAsStream("conf/mybeatis-config.xml");
            SqlSessionFactory build = new SqlSessionFactoryBuilder().build(io);
            sqlSession = build.openSession();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void tes101() {
        UserDao userDao = sqlSession.getMapper(UserDao.class);
        System.out.println(userDao.selectUserById(4));
    }

    @Test
    public void test02() {
        //before();
        UserDao userDao = sqlSession.getMapper(UserDao.class);
        User user = new User();
        //user.setAddress("上海");
        user.setSex("0");
        user.setId(2);
        List<User> users = userDao.selectUserByAddrAndSexA(user);
        System.out.println(users);
    }

    @Test
    public void test03() {
        // before();
        UserDao userDao = sqlSession.getMapper(UserDao.class);
        List<User> userList = userDao.selectUserBySomeAddr(new String[]{"上海", "广州", "深圳", "北京"});
        System.out.println(userList);
    }

    @Test
    public void test04() {
        //before();
        UserDao userDao = sqlSession.getMapper(UserDao.class);
        List<User> userList = userDao.selectUserLikeName("王");
        System.out.println(userList);
    }

    @Test
    public void test05() {
        //before();
        UserDao userDao = sqlSession.getMapper(UserDao.class);
        User user = new User("龙哥", "0", new Date(), "重庆");
        userDao.insertUser(user);
        sqlSession.commit();
        System.out.println(user.getId());
    }

    @Test
    public void test06() {
        //before();
        UserDao userDao = sqlSession.getMapper(UserDao.class);
        List<User> users = userDao.selectAllUser();
        for (User user : users) {
            System.out.println(user);
        }
    }

    @Test
    public void test07() {
        //before();
        UserDao userDao = sqlSession.getMapper(UserDao.class);
        Map<Integer, User> UserMap = userDao.selectAllMapUser();
        Set<Integer> keys = UserMap.keySet();
        for (Integer key : keys) {
            System.out.println(key + "---->" + UserMap.get(key));
        }
    }

    @Test
    public void test08() {
        //before();
        UserDao userDao = sqlSession.getMapper(UserDao.class);
        List<User> list = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            User user = new User("龙哥", "0", new Date(), "重庆");
            list.add(user);
        }
        userDao.insertUserList(list);
        sqlSession.commit();
        //System.out.println(user.getId());
    }

    @Test
    public void test09() {
        ClassesDao classesDao = sqlSession.getMapper(ClassesDao.class);
        Classes classes = classesDao.getClass(1);
        System.out.println(classes);
    }

    @Test
    public void test10() {
        ClassesDao classesDao = sqlSession.getMapper(ClassesDao.class);
        List<Classes> class1 = classesDao.getClass1(2);
        System.out.println(class1);
    }

    @Test
    public void test11(){
        ProductMapper pm = sqlSession.getMapper(ProductMapper.class);
        ProductExample pe = new ProductExample();
        ProductExample.Criteria criteria = pe.createCriteria();
        criteria.andPidBetween(2,5);
        List<Product> products = pm.selectByExample(pe);
        System.out.println(products);
    }
}
