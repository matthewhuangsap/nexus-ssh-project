package it.nexus.webcontrol.showcase;

import com.opensymphony.xwork2.ActionSupport;
import it.nexus.webcontrol.showcase.Model.Animals;

/**
 * Created by IntelliJ IDEA.
 * User: Administrator
 * Date: 2010-4-18
 * Time: 14:21:37
 * To change this template use File | Settings | File Templates.
 */
public class LayoutPanelController extends ActionSupport {

    private Animals animals;

  public Animals getAnimals() {
    return animals;
  }

  public void setAnimals(Animals animals) {
    this.animals = animals;
  }


  @Override
    public String execute() throws Exception {
        animals = new Animals();
        animals.setAge(20);
        animals.setName("elephent");
        animals.setPrice("100,000,000,000");
        animals.setRemark("this is a 大象～！");
        return super.execute(); 
    }
}
