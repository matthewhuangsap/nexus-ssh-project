package it.nexus.core;

/**
 * Created by IntelliJ IDEA.
 * User: Administrator
 * Date: 2010-3-7
 * Time: 2:02:05
 * To change this template use File | Settings | File Templates.
 */
public class NexusException extends Exception {
    public NexusException() {
            super();
        }
        public NexusException(String msg) {
            super(msg);
        }
        public NexusException(String msg, Throwable cause) {
            super(msg, cause);
        }
        public NexusException(Throwable cause) {
            super(cause);
        }

}
