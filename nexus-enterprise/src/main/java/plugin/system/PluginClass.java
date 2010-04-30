package plugin.system;

import it.nexus.core.BasePlugin;
import it.nexus.enterprise.system.framework.data.Argument;
import it.nexus.enterprise.system.framework.data.ChoiceBoxSettings;
import it.nexus.enterprise.system.framework.data.IChoiceBoxCallback;
import it.nexus.core.NexusException;
import it.nexus.enterprise.system.framework.data.WordPair;
import it.nexus.core.menu.Menu;
import it.nexus.enterprise.system.employee.dao.EmployeeDAO;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class PluginClass extends BasePlugin {

    public Menu getMenu() {
        return null;
    }

    /**
     * Dec 24, 2009 PluginClass.java Administrator
     */
    protected final String name = "system";
    protected final String displayName = "系统模块";

    @Override
    public String getDisplayName() {
        return this.displayName;
    }

    @Override
    public String getName() {
        return this.name;
    }

    IChoiceBoxCallback dept = new IChoiceBoxCallback() {

        @Override
        public List<WordPair> getData( JdbcTemplate jdbcTemplate,Argument arg) {
            List result = new ArrayList();
            Iterator<?> iterator = jdbcTemplate.queryForList
                    ("SELECT s.id,sb.name FROM sys_dept s inner join sys_basetree sb on s.id = sb.id")
                    .iterator();
            while(iterator.hasNext()){
                Map itmap = (Map)iterator.next();
                WordPair wp = new WordPair((String)itmap.get("id"),(String) itmap.get("name"));
                result.add(wp);
            }
            return result;
        }                                
    };

    IChoiceBoxCallback roles = new IChoiceBoxCallback() {
            @Override
            public List<WordPair> getData( JdbcTemplate jdbcTemplate,Argument arg) {
                List result = new ArrayList();
            Iterator<?> iterator = jdbcTemplate.queryForList
                    ("SELECT s.id,sb.name FROM sys_role s inner join sys_basetree sb on s.id = sb.id")
                    .iterator();
            while(iterator.hasNext()){
                Map itmap = (Map)iterator.next();
                WordPair wp = new WordPair((String)itmap.get("id"),(String) itmap.get("name"));
                result.add(wp);
            }
            return result;
            }
        };


    IChoiceBoxCallback employee = new IChoiceBoxCallback() {

        @Override
        public List<WordPair> getData( JdbcTemplate jdbcTemplate,Argument arg) {
            return null;
        }
    };

    IChoiceBoxCallback employee2 = new IChoiceBoxCallback() {
        @Resource
        EmployeeDAO employeeDAO;

        @Override
        public List<WordPair> getData( JdbcTemplate jdbcTemplate, Argument arg) {
            return null;
        }
    };

    public void Init() throws NexusException {
        ChoiceBoxSettings.Register("部门", dept);
        ChoiceBoxSettings.Register("员工", employee);
        ChoiceBoxSettings.Register("角色", roles );
    }


}
