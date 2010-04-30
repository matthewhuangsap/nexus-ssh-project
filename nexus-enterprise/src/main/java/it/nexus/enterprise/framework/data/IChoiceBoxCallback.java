package it.nexus.enterprise.framework.data;

import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Administrator
 * Date: 2010-3-7
 * Time: 1:57:10
 * To change this template use File | Settings | File Templates.
 */
public interface IChoiceBoxCallback {
    List<WordPair> getData(JdbcTemplate jdbcTemplate, Argument arg);
}
