package it.nexus.core.test.spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.EncodedResource;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.simple.SimpleJdbcTemplate;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.test.jdbc.SimpleJdbcTestUtils;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;

/**
 * Created by IntelliJ IDEA.
 * User: dcriori
 * Date: 2010-5-7
 * Time: 14:41:45
 */
@Transactional
@TestExecutionListeners(TransactionalTestExecutionListener.class)
public class SpringExtTestCase extends SpringContextTestCase{
  protected DataSource dataSource;

	protected SimpleJdbcTemplate jdbcTemplate;

	protected String sqlScriptEncoding;

	@Autowired
	public void setDataSource(DataSource dataSource) {
		this.jdbcTemplate = new SimpleJdbcTemplate(dataSource);
		this.dataSource = dataSource;
	}

	public void setSqlScriptEncoding(String sqlScriptEncoding) {
		this.sqlScriptEncoding = sqlScriptEncoding;
	}

	protected int countRowsInTable(String tableName) {
		return SimpleJdbcTestUtils.countRowsInTable(this.jdbcTemplate, tableName);
	}

	protected int deleteFromTables(String... names) {
		return SimpleJdbcTestUtils.deleteFromTables(this.jdbcTemplate, names);
	}

	protected void runSql(String sqlResourcePath, boolean continueOnError) throws DataAccessException {
		Resource resource = this.applicationContext.getResource(sqlResourcePath);
		SimpleJdbcTestUtils.executeSqlScript(
        this.jdbcTemplate,
        new EncodedResource(resource, this.sqlScriptEncoding),
				continueOnError);
	}
}
