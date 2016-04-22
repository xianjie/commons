package support;

import java.io.Serializable;

/**
 * 命令接口
 */
public interface ICommand extends Serializable {

    /**
     * 执行命令
     */
    void execute();
}
