package it.nexus.core.web;

/**
 * Created by IntelliJ IDEA.
 * User: dcriori
 * Date: 2010-3-9
 * Time: 13:19:33
 * To change this template use File | Settings | File Templates.
 */
public interface IInitStep {
    void Execute(InitContext context);
    String getName();
}
