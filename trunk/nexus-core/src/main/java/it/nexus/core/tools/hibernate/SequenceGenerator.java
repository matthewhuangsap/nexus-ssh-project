package it.nexus.core.tools.hibernate;

import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.HibernateException;
import org.hibernate.MappingException;
import org.hibernate.dialect.Dialect;
import org.hibernate.engine.SessionImplementor;
import org.hibernate.exception.JDBCExceptionHelper;
import org.hibernate.id.Configurable;
import org.hibernate.id.IdentifierGenerator;
import org.hibernate.id.PersistentIdentifierGenerator;

public class SequenceGenerator implements IdentifierGenerator,Configurable {
	private static final Log	log	= LogFactory
											.getLog(SequenceGenerator.class);
	private long				next;
	private String				sql;
	private String				table;
	private String				column;
	private String				schema;

	public synchronized Serializable generate(SessionImplementor session,
			Object object) throws HibernateException {
		SimpleDateFormat f = new SimpleDateFormat("yyMMdd");
		String preDate = f.format(new Date());

		String bh = "123456789";
		return bh + preDate + getNext(session, bh, table);

	}

	public void configure(org.hibernate.type.Type type, Properties params,
			Dialect d) throws MappingException {
		table = params.getProperty("table");
		if (table == null)
			table = params.getProperty(PersistentIdentifierGenerator.TABLE);
		column = params.getProperty("column");
		if (column == null)
			column = params.getProperty(PersistentIdentifierGenerator.PK);
		schema = params.getProperty(PersistentIdentifierGenerator.SCHEMA);

	}

	/**
	 * 得到当前表ID的最后六位的最大数
	 * 
	 * @param session
	 * @param jsbh
	 * @return
	 */
	private String getNext(SessionImplementor session, String bh, String table) {
		sql = "select  max(substr(" + column + ",16)) from "
				+ (schema == null ? table : schema + '.' + table)
				+ " where substr(" + column
				+ ",10,6) = to_char(sysdate,'yyMMdd') and substr(" + column
				+ ",0,9) = '" + bh + "' and  length(" + column + ")=21 ";
		log.info("fetching initial value: " + sql);

		try {
			PreparedStatement st = session.getBatcher().prepareSelectStatement(
					sql);
			try {
				ResultSet rs = st.executeQuery();
				try {
					if (rs.next()) {
						next = rs.getLong(1) + 1;
						if (rs.wasNull())
							next = 1;
					} else {
						next = 1;
					}
					sql = null;
					log.debug("first free id: " + next);
				} finally {
					rs.close();
				}
			} finally {
				session.getBatcher().closeStatement(st);
			}
			return toString(6, next);
		} catch (SQLException sqle) {
			throw JDBCExceptionHelper.convert(session.getFactory()
					.getSQLExceptionConverter(), sqle,
					"could not fetch initial value for increment generator",
					sql);
		}
	}

	/**
	 * 格式化数字不足补齐
	 * 
	 * @param num
	 * @param value
	 * @return
	 */
	public static String toString(int num, long value) {
		String result = (new Long(value)).toString();
		while (num > result.length()) {
			result = "0" + result;
		}
		return result;
	}

}
