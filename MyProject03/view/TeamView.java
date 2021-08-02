package Day27.MyProject03.view;

import Day27.MyProject03.Services.NameListService;
import Day27.MyProject03.Services.TeamException;
import Day27.MyProject03.Services.TeamService;
import Day27.MyProject03.domain.Employee;
import Day27.MyProject03.domain.Programmer;

/**
 * @ClassName: TeamView
 * @Description:
 * @Author: TianXing.Xue
 * @Date: 2021/7/21 16:16
 * @Version:
 **/
public class TeamView {
    private NameListService listSvc = new NameListService();
    private TeamService teamSvc = new TeamService();

    public void enterMainMenu() {

        boolean loopFlag = true;
        char menu = 0;
        while (loopFlag) {
            if (menu != '1') {
                listAllEmployees();
            }
            System.out.println("1-团队列表 2-添加团队成员 3-删除团队成员 4-退出 请选择(1-4): ");
            menu = TSUtility.readMenuSelection();
            switch (menu) {

                case '1':
                    getTeam();
                    break;
                case '2':
                    addMember();
                    break;
                case '3':
                    deleteMember();
                    break;
                case '4':
                    System.out.println("是否要退出:(Y/N)");
                    char isExit = TSUtility.readConfirmSelection();
                    if (isExit == 'Y') {
                        loopFlag = false;
                    }
                    break;
            }
        }
    }


    /*方法描述
     * @author: TianXing.Xue
     * @Description: 显示所有的员工信息
     * @param:
     * @return:
     * @date: 2021/7/21 16:21
     */
    private void listAllEmployees() {
        System.out.println("-----------------------------------------开发团队调度软件--------------------------------------------");
        Employee[] allEmployee = listSvc.getAllEmployee();
        if (allEmployee == null || allEmployee.length == 0) {
            System.out.println("公司没有任何员工信息");
        } else {
            System.out.println("ID\t\t姓名\t\t年龄\t\t工资\t\t职位\t\t状态\t\t奖金\t\t股票\t\t使用设备");
            for (int i = 0; i < allEmployee.length; i++) {
                System.out.println(allEmployee[i]);
            }
        }
        System.out.println("----------------------------------------------------------------------------------------------------");
    }

    private void getTeam() {
        System.out.println("----------------------团队成员列表---------------------\n");
        Programmer[] team = teamSvc.getTeam();
        if (team.length == 0 || team == null) {
            System.out.println("开发团队目前没有成员！");
        } else {
            System.out.println("TID/ID\t姓名\t年龄\t工资\t职位\t奖金\t股票\n");
            for (int i = 0; i < team.length; i++) {
                System.out.println(team[i].getDetailsForTeam());
            }
        }
        System.out.println("-----------------------------------------------------");
    }

    private void addMember() {
        System.out.println("-----------------------添加成员-----------------------");
        System.out.println("请输入要添加的员工ID： ");
        int id = TSUtility.readInt();
        try {
            Employee emp = listSvc.getEmployee(id);
            teamSvc.addMember(emp);
            System.out.println("添加成功");

        } catch (TeamException e) {
            System.out.println("添加失败，原因： " + e.getMessage());
        }
        TSUtility.readReturn();  //按回车键继续
    }

    private void deleteMember() {
        System.out.println("-----------------------删除成员-------------------------");
        System.out.println("请输入要删除员工的TID");
        int memberId = TSUtility.readInt();
        System.out.println("确认是否删除(Y/N)");
        char isDelete = TSUtility.readConfirmSelection();
        if (isDelete == 'N') {
            return;
        }
        try {
            teamSvc.removeMember(memberId);
            System.out.println("删除成功");
        } catch (TeamException e) {
            System.out.println("删除失败，原因是： " + e.getMessage());
        }
        TSUtility.readReturn();
    }

    public static void main(String[] args) {
        TeamView view = new TeamView();
        view.enterMainMenu();
    }
}
