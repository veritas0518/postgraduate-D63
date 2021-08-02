package Day27.MyProject03.Services;

/**
 * @ClassName: TeamException
 * @Description: 自定义一个异常类
 * @Author: TianXing.Xue
 * @Date: 2021/7/20 15:59
 * @Version:
 **/
public class TeamException extends Exception {
    static final long serialVersionUID = -33873124229948L;

    public TeamException() {
    }

    public TeamException(String msg) {
        super(msg);
    }
}
