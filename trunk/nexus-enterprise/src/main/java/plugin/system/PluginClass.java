package plugin.system;

import it.nexus.core.BasePlugin;
import it.nexus.core.dao.HibernateDAO;
import it.nexus.core.datakind.Argument;
import it.nexus.core.datakind.ChoiceBoxSettings;
import it.nexus.core.datakind.IChoiceBoxCallback;
import it.nexus.core.NexusException;
import it.nexus.core.datakind.WordPair;
import it.nexus.enterprise.system.dept.dao.DeptDAO;
import org.hibernate.Query;

import javax.annotation.Resource;
import java.util.List;

public class PluginClass extends BasePlugin{
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

    @Override
    public void Init() throws NexusException {
        ChoiceBoxSettings.Register("部门",new IChoiceBoxCallback(){
            @Resource
            DeptDAO deptDAO;
            @Override
            public List<WordPair> getData(Argument arg) {
                deptDAO.find("SELECT id,name FROM Dept d WHERE id=?",arg.getBeforedrop());
                return null;
            }
        });
    }
}
