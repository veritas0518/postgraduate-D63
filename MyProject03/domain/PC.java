package Day27.MyProject03.domain;

public class PC implements Equipment {
    private String model;
    private String display;

    public PC() {

    }

    public PC(String model, String display) {
        this.model = model;  //机器型号
        this.display = display;  //显示器的名称
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getModel() {
        return model;
    }

    public void setDisplay(String display) {
        this.display = display;
    }

    public String getDisplay() {
        return display;
    }

    public String getDescription() {
        return model + "(" + display + ")";
    }
}
