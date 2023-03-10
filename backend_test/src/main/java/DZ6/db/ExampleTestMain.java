package DZ6.db;

import DZ6.db.model.Categories;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class ExampleTestMain {

    public static void main(String[] args) throws IOException {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);

        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();

        DZ6.db.dao.CategoriesMapper categoriesMapper = sqlSession.getMapper(DZ6.db.dao.CategoriesMapper.class);
        DZ6.db.model.CategoriesExample categoriesExample = new DZ6.db.model.CategoriesExample();

        categoriesExample.createCriteria().andIdEqualTo(1L);

        List<Categories> list = categoriesMapper.selectByExample(categoriesExample);
        System.out.println(categoriesMapper.countByExample(categoriesExample));

        DZ6.db.model.Categories selected = categoriesMapper.selectByPrimaryKey(2L);
        System.out.println("ID: " + selected.getId() + "\ntitle: " + selected.getTitle());

        DZ6.db.model.Categories categories = new DZ6.db.model.Categories();
        categories.setId(Long.valueOf(3));
        categories.setTitle("Test");
        categoriesMapper.insert(categories);
        sqlSession.commit();
    }
}
