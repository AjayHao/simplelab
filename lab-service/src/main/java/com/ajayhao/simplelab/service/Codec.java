package com.ajayhao.simplelab.service;

/**
 * Created by AjayHao on 2017/7/13.
 * <p>
 * 解码/编码接口
 */
public interface Codec<F/*from type*/, T/*to type*/> {

    /**
     * 将From type实例转换为To type实例
     *
     * @param object
     * @return
     */
    T encode(F object);

    /**
     * 将To type实例转换为From type实例<br/>
     *
     * @param object
     * @return
     */
    F decode(T object);
}


