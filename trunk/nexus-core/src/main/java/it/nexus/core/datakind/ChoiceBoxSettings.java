package it.nexus.core.datakind;

import it.nexus.core.NexusException;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 * User: Administrator
 * Date: 2010-3-7
 * Time: 1:17:47
 * To change this template use File | Settings | File Templates.
 */
public final class ChoiceBoxSettings {
    static Map<String,IChoiceBoxCallback> callbacks = new HashMap<String,IChoiceBoxCallback>();
    public static void Register(String key,IChoiceBoxCallback function) throws NexusException {
        if(callbacks.containsKey(key))
            throw new NexusException(String.format("%s 在ChoiceBoxSetting中多次定义",key));
        callbacks.put(key,function);
    }

    public static Map<String, IChoiceBoxCallback> getCallbacks() {
        return callbacks;
    }
}
