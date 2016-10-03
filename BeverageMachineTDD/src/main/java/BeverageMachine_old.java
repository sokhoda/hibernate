import interfaces.IBeverageMashine;
import interfaces.IMenu;

/**
 * Created by Oleksandr_Khodakovsk on 9/26/2016.
 */
public class BeverageMachine_old implements IBeverageMashine {

    private IMenu menu;

    public BeverageMachine_old() {
        menu = new Menu();
    }

    @Override
    public void init() {
        menu.init();
    }

    @Override
    public void start() {
        menu.run();
    }

}
