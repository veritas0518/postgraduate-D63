package Day27.MyProject03.junit;

import Day27.MyProject03.Services.NameListService;
import Day27.MyProject03.Services.TeamException;
import Day27.MyProject03.domain.Employee;
import org.junit.Test;

/**
 * @ClassName: NameListServiceTest
 * @Description: 对NameListService类的测试
 * @Author: TianXing.Xue
 * @Date: 2021/7/20 16:06
 * @Version:
 **/

public class NameListServiceTest {
    @Test
    public void testAllEmployees(){
        NameListService service =new NameListService();
        Employee[] allEmployee = service.getAllEmployee();
        for (int i = 0; i < allEmployee.length; i++) {
            System.out.println(allEmployee[i]);
        }
    }
    @Test
    public void testGetEmployee(){
        NameListService service =new NameListService();
        int id =10;
        try {
            Employee employee = service.getEmployee(id);
            System.out.println(employee);
        } catch (TeamException e) {
            System.out.println(e.getMessage());

        }
    }
}
