package Day27.MyProject03.Services;

import Day27.MyProject03.domain.Architect;
import Day27.MyProject03.domain.Designer;
import Day27.MyProject03.domain.Employee;
import Day27.MyProject03.domain.Programmer;

/**
 * @ClassName: TeamService
 * @Description: 关于开发团队成员的管理、添加、删除等
 * @Author: TianXing.Xue
 * @Date: 2021/7/20 23:01
 * @Version:
 **/
public class TeamService {
    private static int counter = 1;  //给member_id赋值
    private final int MAX_MEMBER = 5;  //限制开发团队的人数
    private Programmer[] team = new Programmer[MAX_MEMBER];  //保存开发团队的人员
    private int total; //记录开发团队中实际的人数

    public TeamService() {
    }

    /*方法描述
     * @author: TianXing.Xue
     * @Description: 获取开发团队中的所有成员
     * @param:
     * @return:
     * @date: 2021/7/21 11:37
     */
    public Programmer[] getTeam() {
        Programmer[] newTeam = new Programmer[total];

        for (int i = 0; i < newTeam.length; i++) {
            newTeam[i] = team[i];
        }
        return newTeam;
    }

    /*方法描述
     * @author: TianXing.Xue
     * @Description: 将指定的员工添加到开发团队中
     * @param:
     * @return:
     * @date: 2021/7/21 11:42
     */
    public void addMember(Employee e) throws TeamException {
        Programmer p = (Programmer) e;
        //成员已满，无法添加
        if (total >= MAX_MEMBER) {
            throw new TeamException("成员已满，无法添加");
        }
        //该成员不是开发成员，无法添加
        if (!(e instanceof Programmer)) {
            throw new TeamException("该成员不是开发成员，无法添加");
        }
        //该员工已在本开发团队中
        if (isExit(e)) {
            throw new TeamException("该员工已在本开发团队中");
        }
        //该员工已是某团队成员
        if (((Programmer) e).getStatus() == Status.BUSY) {  //这里有改动
            throw new TeamException("该员工已是某团队成员");
        }
        //该员工正在休假，无法添加
        if (((Programmer) e).getStatus() == Status.VOCATION) {
            throw new TeamException("该员工正在休假，无法添加");
        }
        //团队中至多只能有一名架构师
        //团队中至多只能有两名设计师
        //团队中至多只能有三名程序员
        int numberOfArch = 0;
        int numberOfDes = 0;
        int numberOfPro = 0;
        for (int i = 0; i < total; i++) {
            if (team[i] instanceof Architect) {
                numberOfArch++;
            } else if (team[i] instanceof Designer) {
                numberOfDes++;
            } else {
                numberOfPro++;
            }
        }
        if (p instanceof Architect) {
            if (numberOfArch >= 1) {
                throw new TeamException("团队中至多只能有一名架构师");
            }
        } else if (p instanceof Designer) {
            if (numberOfDes >= 2) {
                throw new TeamException("团队中至多只能有两名设计师");
            }
        } else if (p instanceof Programmer) {
            if (numberOfPro >= 3) {
                throw new TeamException("团队中至多只能有三名程序员");
            }
        }
        //将e添加到现有的团队当中
        team[total++] = p;
        p.setStatus(Status.BUSY);
        p.setMemberId(counter++);
    }

    /*方法描述
     * @author: TianXing.Xue
     * @Description: 判断指定的员工是否已经存在于现有的开发团队中
     * @param:
     * @return:
     * @date: 2021/7/21 15:19
     */
    public boolean isExit(Employee e) {
        for (int i = 0; i < total; i++) {

            if (e.getId() == team[i].getId()) {
                return true;
            }
        }
        return false;
    }

    public void removeMember(int memberId) throws TeamException {
        int i;
        for (i = 0; i < total; i++) {
            if (team[i].getId() == memberId) {
                team[i].setStatus(Status.FREE);
                break;
            }
        }

        if (i == total) {
            throw new TeamException("找不到指定memberId的员工 、删除失败");
        }

        //后一个元素覆盖前一个元素，实现删除操作
        for (int j = i; j < total - 1; j++) {
            team[j] = team[j + 1];
        }
        team[total - 1] = null;
        total--;

    }
}


