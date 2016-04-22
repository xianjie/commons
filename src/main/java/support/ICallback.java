package support;

/**
 * 回调接口
 */
public interface ICallback<P, R> {

    /**
     * 回调行为
     *
     * @param p 参数
     * @return 返回值
     */
    R execute(P p);

}
