import com.github.pagehelper.PageHelper;
import io.itaiit.mapper.TbItemMapper;
import io.itaiit.pojo.TbItem;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

public class TestPageHelper {

    @Test
    public void testHelper(){
        ApplicationContext context = new ClassPathXmlApplicationContext("classpath:spring/applicationContext-dao.xml");
        TbItemMapper itemMapper = context.getBean(TbItemMapper.class);
        PageHelper.startPage(1, 5);

        List<TbItem> list = itemMapper.selectAll();
        for (TbItem item :
                list) {
            System.out.println("item.getTitle() = " + item.getTitle());
        }
    }
}
