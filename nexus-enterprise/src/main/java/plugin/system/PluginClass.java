package plugin.system;

import it.nexus.core.BasePlugin;
import it.nexus.core.datakind.Argument;
import it.nexus.core.datakind.ChoiceBoxSettings;
import it.nexus.core.datakind.IChoiceBoxCallback;
import it.nexus.core.NexusException;
import it.nexus.core.datakind.WordPair;
import it.nexus.core.menu.Menu;
import it.nexus.enterprise.system.dept.dao.DeptDAO;
import it.nexus.enterprise.system.dept.model.Dept;
import it.nexus.enterprise.system.employee.dao.EmployeeDAO;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

public class PluginClass extends BasePlugin{
  
    public Menu getMenu() {
        return null; 
    }

    /**
	 * Dec 24, 2009 PluginClass.java Administrator
	 */
	protected final String name="system";
	protected final String displayName = "系统模块";

	@Override
	public String getDisplayName() {
		return this.displayName;
	}

	@Override
	public String getName() {
		return this.name;
	}

    IChoiceBoxCallback dept = new IChoiceBoxCallback(){
        @Resource
        DeptDAO deptDAO;

        @Override
        public List<WordPair> getData(Argument arg) {
            List<WordPair> result = new ArrayList<WordPair>();
            List<Dept> list = deptDAO.find("SELECT id,name FROM Dept d WHERE id like ?",arg.getInput());
            for (Dept dept : list){
                WordPair wp = new WordPair(dept.getId(),dept.getName());
                result.add(wp);
            }
            return result;
        }
    }   ;

    IChoiceBoxCallback employee = new IChoiceBoxCallback(){
        @Resource
        EmployeeDAO employeeDAO;
        @Override
        public List<WordPair> getData(Argument arg) {
            return null;
        }
    };

     IChoiceBoxCallback employee2 = new IChoiceBoxCallback(){
        @Resource
        EmployeeDAO employeeDAO;
        @Override
        public List<WordPair> getData(Argument arg) {
            return null;
        }
    };

    public void Init() throws NexusException {
        ChoiceBoxSettings.Register("部门",dept);
        ChoiceBoxSettings.Register("员工",employee);
        
    }


}
