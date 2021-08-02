package Day27.MyProject03.Services;

import static Day27.MyProject03.data.Data.*;  //导入所有的静态结构

import Day27.MyProject03.domain.Designer;
import Day27.MyProject03.domain.*;

/**
 * @ClassName: NameListService
 * @Description: 负责将数据封装到Employee[]数组中，同时提供相关操作Employee[]的方法
 * @Author: TianXing.Xue
 * @Date: 2021/7/20 14:50
 * @Version: 1.0
 **/
public class NameListService {
    private Employee[] employees;

    public NameListService() {
        employees = new Employee[EMPLOYEES.length];
        for (int i = 0; i < employees.length; i++) {

            //获取员工的类型
            int type = Integer.parseInt(EMPLOYEES[i][0]);

            //获取Employee的4个基本信息
            int id = Integer.parseInt(EMPLOYEES[i][1]);
            String name = EMPLOYEES[i][2];
            int age = Integer.parseInt(EMPLOYEES[i][3]);
            double salary = Double.parseDouble(EMPLOYEES[i][4]);
            Equipment equipment;
            double bonus;
            int stock;


            switch (type) {

                case EMPLOYEE:
                    employees[i] = new Employee(id, name, age, salary);
                    break;
                case PROGRAMMER:
                    equipment = createEquipment(i);
                    employees[i] = new Programmer(id, name, age, salary, equipment);
                    break;
                case DESIGNER:

                    equipment = createEquipment(i);
                    bonus = Double.parseDouble(EMPLOYEES[i][5]);
                    employees[i] = new Designer(id, name, age, salary, equipment, bonus);
                    break;
                case ARCHITECT:
                    equipment = createEquipment(i);
                    bonus = Double.parseDouble(EMPLOYEES[i][5]);
                    stock = Integer.parseInt(EMPLOYEES[i][6]);
                    employees[i] = new Architect(id, name, age, salary, equipment, bonus, stock);
                    break;
            }
        }
    }

    /**
     * 获取指定index上的员工的设备
     *
     * @return
     */
    private Equipment createEquipment(int index) {
        int key = Integer.parseInt(EQUIPMENTS[index][0]);
        String modelOrName = EQUIPMENTS[index][1];
        switch (key) {
            case PC:  //21
                String display = EQUIPMENTS[index][2];
                return new PC(modelOrName, display);
            case NOTEBOOK: //22
                double price = Double.parseDouble(EQUIPMENTS[index][2]);
                return new NoteBook(modelOrName, price);
            case PRINTER:  //23
                String type = EQUIPMENTS[index][2];
                return new Printer(modelOrName, type);
        }
        return null;
    }

    /*方法描述
     * @author: TianXing.Xue
     * @Description: 获取当前所有的员工
     * @param:
     * @return:
     * @date: 2021/7/20 15:55
     */

    public Employee[] getAllEmployee() {
        return employees;
    }

    /*方法描述
     * @author: TianXing.Xue
     * @Description: 获取指定id的员工
     * @param: id
     * @return:
     * @date: 2021/7/20 15:56
     */
    public Employee getEmployee(int id) throws TeamException {
        for (int i = 0; i < employees.length; i++) {
            if (employees[i].getId() == id) {
                return employees[i];
            }
        }
        throw new TeamException("找不到指定的员工");
    }
}
